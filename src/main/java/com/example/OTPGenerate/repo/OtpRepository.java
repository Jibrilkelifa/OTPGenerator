package com.example.OTPGenerate.repo;

import com.example.OTPGenerate.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {
    List<Otp> findByPhoneNumber(String phoneNumber);
}
