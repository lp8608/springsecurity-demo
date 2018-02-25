package com.lp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@ComponentScan("com.lp")
@ServletComponentScan("com.lp")
public class SecurityDemoApplication {

    public static void main(String[] args){
        SpringApplication.run(SecurityDemoApplication.class,args);
    }


    @RequestMapping("/hello")
    public String hello(){
        return "hello lp !";

    }

}
