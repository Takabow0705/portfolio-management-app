package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
