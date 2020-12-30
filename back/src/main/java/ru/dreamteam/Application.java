package ru.dreamteam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableWebMvc
//@EnableConfigurationProperties
@ConfigurationPropertiesScan
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:platform.properties")
})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args );
    }
}
