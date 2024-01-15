package com.example.demo.service.sms;

public sealed interface SmsService permits SmsServiceImpl {

    void sendSms(String phone, String message);

    void sendEmail(String email, String message);

}
