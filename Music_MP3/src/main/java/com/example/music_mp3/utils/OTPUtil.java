package com.example.music_mp3.utils;

import java.util.Random;

public class OTPUtil {
    public static String generateOTP() {
        int otpLength = 6;
        String numbers = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(otpLength);
        for (int i = 0; i < otpLength; i++) {
            char randomChar = numbers.charAt(random.nextInt(numbers.length()));
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
