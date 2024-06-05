package com.example.music_mp3.Service;


import com.example.music_mp3.Data.Entity.MailInfo;
import jakarta.mail.MessagingException;


public interface MailerService {
    void sendOtpEmail(String to, String otp);
    void send(MailInfo mail) throws MessagingException;
    void send(String to, String subject, String body) throws MessagingException;






}
