package com.example.demo;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAsync
@Controller
@EnableSwagger2
public class DemoApplication implements CommandLineRunner, ApplicationRunner {

    @Autowired
    UserService userService;

    @Value("${my.name}")
    private String secret;

    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder()
                .lazyInitialization(true)
                .sources(DemoApplication.class)
                .web(WebApplicationType.SERVLET)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
        UserService bean = context.getBean(UserService.class);
        System.out.println(bean.getUser());
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("springboot启动完成1。。。" + userService.getUser());
        System.out.println(secret);
    }

    @RequestMapping("/test")
    public @ResponseBody String test() {
        return "hello lemur";
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("springboot启动完成2。。。" + userService.getUser());
    }
}
