package com.example.music_mp3.Service.ServiceImpl;

import com.example.music_mp3.Data.DTO.AccountDTO;
import com.example.music_mp3.Data.Entity.AccountsEntity;
import com.example.music_mp3.Repository.AccountRepository;
import com.example.music_mp3.Service.RegisterService;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;



@Service
public class RegisterServiceImpl implements RegisterService{

    @Autowired
    AccountRepository repo;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    HttpSession session;

    @Override
    public AccountsEntity save(AccountDTO accountDTO) {
        return repo.save(AccountDTO.convertAccountDTOToAccountsEntity(accountDTO));
    }

    @Override
    public boolean existsByEmail(String email) {
        AccountsEntity accountsEntity = repo.existsByEmail(email);
        return accountsEntity == null ? true : false;
    }

    @Override
    public boolean existsByUsername(String username) {
        AccountsEntity accountsEntity = repo.existsByUsername(username);
        return accountsEntity == null ? true : false;
    }


    public void sendOTPEmail(String toEmail, String otp) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject("Mã OTP cho đăng ký tài khoản");
            helper.setText("Mã OTP của bạn là: " + otp);
            emailSender.send(message);
            session.setAttribute("otp", otp);
            session.setAttribute("email", toEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String concatOtp(String... args) {
        String otpConcatSuccess = "";
        for (int i = 0; i < args.length; i++) {
            otpConcatSuccess += args[i];
        }
        return otpConcatSuccess;
    }



}
