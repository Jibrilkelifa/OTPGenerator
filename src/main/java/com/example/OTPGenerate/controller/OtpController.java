package com.example.OTPGenerate.controller;
import com.example.OTPGenerate.otpService.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/generate")
    public String generateOtp(@RequestParam String phoneNumber) {
        return otpService.generateOtp(phoneNumber);
    }

    @PostMapping("/validate")
    public String validateOtp(@RequestParam String phoneNumber, @RequestParam String otp) {
        boolean isValid = otpService.validateOtp(phoneNumber, otp);
        return isValid ? "OTP is valid" : "OTP is invalid";
    }
}
