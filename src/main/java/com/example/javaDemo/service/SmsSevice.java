package com.example.javaDemo.service;

import com.example.javaDemo.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class SmsSevice {

    public void sendSms() {
        System.out.println("sending sms");
    }
}
