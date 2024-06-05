package com.example.music_mp3.Service.ServiceImpl;


import com.example.music_mp3.Data.Entity.MailInfo;
import com.example.music_mp3.Repository.AccountRepository;
import com.example.music_mp3.Service.MailerService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MailerServiceImpl implements MailerService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendOtpEmail(String to, String otp) {
        try {
            String subject = "Password Reset OTP";
            String body = "Your OTP for password reset is: " + otp;
            send(to, subject, body);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void send(MailInfo mail) throws MessagingException {
        // Tạo message
        MimeMessage message = mailSender.createMimeMessage();

        // Sử dụng Helper để thiết lập các thông tin cần thiết cho message
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        helper.setFrom(mail.getFrom());
        helper.setTo(mail.getTo());
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getBody(), true);
        helper.setReplyTo(mail.getFrom());

        String[] cc = mail.getCc();
        if (cc != null && cc.length > 0) {
            helper.setCc(cc);
        }

        String[] bcc = mail.getBcc();
        if (bcc != null && bcc.length > 0) {
            helper.setBcc(bcc);
        }

        String[] attachments = mail.getAttachments();
        if (attachments != null && attachments.length > 0) {
            for (String path : attachments) {
                File file = new File(path);
                helper.addAttachment(file.getName(), file);
            }
        }
        // Gửi message đến SMTP server
        mailSender.send(message);
    }

    @Override
    public void send(String to, String subject, String body) throws MessagingException {
        this.send(new MailInfo(to, subject, body));
    }

    @Autowired
    private AccountRepository accountRepository;

    public boolean isEmailExists(String email) {
        return accountRepository.findByEmail(email) != null;
    }


}
