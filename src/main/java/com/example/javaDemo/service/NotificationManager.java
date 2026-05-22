package com.example.javaDemo.service;

import org.springframework.stereotype.Component;

@Component
public class NotificationManager {
    private final EmailSevice emailSevice;
    private final SmsSevice smsSevice;

    public NotificationManager(
            EmailSevice emailSevice,
            SmsSevice smsSevice
    ) {
        this.emailSevice = emailSevice;
        this.smsSevice = smsSevice;
    }

    public void sendAll() {
        emailSevice.sendMail();
        smsSevice.sendSms();
    }

}
