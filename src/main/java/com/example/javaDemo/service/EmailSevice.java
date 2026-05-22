package com.example.javaDemo.service;

import com.example.javaDemo.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmailSevice {

    public void sendMail() {
        System.out.println("sending mail");
    }
}
