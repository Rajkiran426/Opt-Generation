package com.optGenaration.Controller;

import com.optGenaration.Services.TwilioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;

@RestController
public class OTPController {

    private final TwilioService twilioService;

    public OTPController(TwilioService twilioService) {
        this.twilioService = twilioService;
    }


    @PostMapping("/generate-otp")
    public ResponseEntity<String> generateOtp(@RequestBody Map<String, String> requestBody) {
        String phone = requestBody.get("phone");
        String otp = generateOtpLogic();
        twilioService.sendOTP(phone, otp);
        return ResponseEntity.ok("OTP generated and sent successfully.");
    }

    private String generateOtpLogic() {
        int otpLength = 6; // Define the length of the OTP
        Random random = new Random();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < otpLength; i++) {
            int digit = random.nextInt(10);
            otp.append(digit);
        }

        return otp.toString();
    }

}
