package com.example.OTPGenerate.otpService;

import com.example.OTPGenerate.model.Otp;
import com.example.OTPGenerate.repo.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class OtpService {

    private static final int OTP_LENGTH = 6;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${sms.api.url}")
    private String smsApiUrl;

    public String generateOtp(String phoneNumber) {

        String otp = String.valueOf(new Random().nextInt(900000) + 100000);

        Otp otpEntity = new Otp(phoneNumber, otp, LocalDateTime.now());
        otpRepository.save(otpEntity);

        sendOtpSms(phoneNumber, otp);

        return otp;
    }



    public boolean validateOtp(String phoneNumber, String otp) {
        List<Otp> otpEntities = otpRepository.findByPhoneNumber(phoneNumber);

        if (otpEntities.isEmpty()) {
            return false;
        }


        Otp latestOtpEntity = otpEntities.get(otpEntities.size() - 1); // Assuming the last one is the most recent

        return latestOtpEntity.getOtp().equals(otp);
    }



    private void sendOtpSms(String phoneNumber, String otp) {

        String url = smsApiUrl + "&to=" + phoneNumber + "&text=Your OTP is " + otp;

        restTemplate.getForObject(url, String.class);
    }
}
