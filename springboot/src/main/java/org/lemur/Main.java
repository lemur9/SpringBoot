package org.lemur;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Main {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .web(WebApplicationType.SERVLET)
                .bannerMode(Banner.Mode.OFF)
                .sources(Main.class)
                .run(args);
    }
}