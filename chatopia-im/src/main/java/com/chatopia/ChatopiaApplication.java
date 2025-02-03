package com.chatopia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatopiaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatopiaApplication.class, args);
        System.out.println("Chatopia server started!");
    }
}
