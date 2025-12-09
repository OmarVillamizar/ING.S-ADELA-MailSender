package com.adela.mailsender.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AdelaMailSenderApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdelaMailSenderApplication.class, args);
    }
}
