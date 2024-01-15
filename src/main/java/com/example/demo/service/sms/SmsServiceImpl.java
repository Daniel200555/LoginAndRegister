package com.example.demo.service.sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.core.env.Environment;

public non-sealed class SmsServiceImpl implements SmsService {

    private String companyPhone;
    private Environment environment;

    public SmsServiceImpl(Environment environment) {
        this.environment = environment;
        this.companyPhone = this.environment.getProperty("phone");
    }

    @Override
    public void sendSms(String phone, String message) {
        System.out.println();
        Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"), System.getenv("TWILIO_AUTH_TOKEN"));
        Message.creator(new PhoneNumber(phone),
                new PhoneNumber(this.companyPhone), message).create();
        System.out.printf("[sendSms][%d] Is success to send message to : %s ",
                System.currentTimeMillis(),
                phone);
        System.out.println();
    }

    @Override
    public void sendEmail(String email, String message) {

    }
}
