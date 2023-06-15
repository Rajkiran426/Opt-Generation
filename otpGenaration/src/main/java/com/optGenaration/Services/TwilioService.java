package com.optGenaration.Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String phoneNumber;

    public void sendOTP(String phone, String otp) {
        Twilio.init(accountSid, authToken);

        PhoneNumber to = new PhoneNumber(phone);
        PhoneNumber from = new PhoneNumber(phoneNumber);

        String messageBody = "Your OTP is: " + otp;

        Message message = Message.creator(to, from, messageBody).create();
        System.out.println("OTP message SID: " + message.getSid());
    }
}

