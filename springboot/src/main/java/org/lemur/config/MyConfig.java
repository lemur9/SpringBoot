package org.lemur.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.lemur.filter.MyFilter;
import org.lemur.interceptor.MyInterceptor;
import org.lemur.listener.MyListener;
import org.lemur.servlet.MyServlet;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.Ssl;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.Objects;

@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Bean
    public ServletRegistrationBean<HttpServlet> servletRegistrationBean() {
        ServletRegistrationBean<HttpServlet> servletRegistrationBean = new ServletRegistrationBean<>(new MyServlet());
        servletRegistrationBean.setUrlMappings(Collections.singleton("/youServlet"));
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>(new MyFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean<ServletContextListener> listenerRegistrationBean() {
        return new ServletListenerRegistrationBean<>(new MyListener());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/app/api/*","/api/*","/**")
                .excludePathPatterns("/api/login");
    }

    /**
     * 编程方式实现springboot的http
     *
     * @return
     */
    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(createHttpConnector());
        return tomcat;
    }

    /**
     * 创建http连接器
     *
     * @return
     */
    private Connector createHttpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        // http端口
        connector.setPort(8080);
        connector.setSecure(false);
        // https端口
        connector.setRedirectPort(8444);
        return connector;
    }

    /**
     * 编程方式实现springboot的https
     *
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> containerCustomizer() {
        return container -> {
            Ssl ssl = new Ssl();
            //服务器私钥和证书
            String path = Objects.requireNonNull(this.getClass().getClassLoader().getResource("server.pkcs12")).getPath();
            try {
                path = URLDecoder.decode(path, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            ssl.setKeyStore(path);
            ssl.setKeyStorePassword("123456");
            ssl.setKeyStoreType("pkcs12"); //通过配置文件读进来 @Value("${}")
            container.setSsl(ssl);
            container.setPort(8444);
        };
    }
}
