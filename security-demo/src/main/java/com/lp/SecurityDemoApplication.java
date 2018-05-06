package com.lp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RestController
@ComponentScan("com.lp")
@ServletComponentScan("com.lp")
@EnableSwagger2
public class SecurityDemoApplication {

    public static void main(String[] args){
        SpringApplication.run(SecurityDemoApplication.class,args);
    }


    @RequestMapping("/hello")
    public String hello(){
        return "hello lp !";

    }

}
