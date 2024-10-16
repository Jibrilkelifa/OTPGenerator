package com.example.OTPGenerate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "otps")
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String otp;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Correct the constructor to assign values
    public Otp(String phoneNumber, String otp, LocalDateTime createdAt) {
        this.phoneNumber = phoneNumber;
        this.otp = otp;
        this.createdAt = createdAt;
    }
}
