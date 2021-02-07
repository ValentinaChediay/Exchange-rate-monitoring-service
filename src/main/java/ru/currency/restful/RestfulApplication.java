package ru.currency.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RestfulApplication {

    public static void main(String[] args) {

        SpringApplication.run(RestfulApplication.class, args);
    }

}
