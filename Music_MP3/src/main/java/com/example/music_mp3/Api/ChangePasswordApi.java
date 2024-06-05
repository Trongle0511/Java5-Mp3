package com.example.music_mp3.Api;

import com.example.music_mp3.Data.DTO.ChangePassworDto;
import com.example.music_mp3.Data.Entity.AccountsEntity;
import com.example.music_mp3.Data.Variable.StaticVariable;
import com.example.music_mp3.Repository.ChangeRepo;
import com.example.music_mp3.Service.ChangeService;
import com.example.music_mp3.utils.PasswordEncoderUtil;
import com.example.music_mp3.utils.PasswordUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChangePasswordApi {
    @Autowired
    private HttpSession session;
    @Autowired
    private ChangeService changeService;
    @Autowired
    private ChangeRepo changeRepo;

    @PostMapping("/change-password")
    public AccountsEntity changePassword(@RequestBody ChangePassworDto changePassworDto) throws Exception {
        AccountsEntity accountsEntity = changeRepo.findByEmail(StaticVariable.sessionEmail);

        if (accountsEntity == null) {
            throw new Exception("Tài khoản không tồn tại");
        }

        String password = accountsEntity.getHashedPassword();
        String rawPassword = changePassworDto.getHashedPassword();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isPasswordMatch = passwordEncoder.matches(rawPassword, password);

        if (isPasswordMatch) {
            accountsEntity.setHashedPassword(PasswordEncoderUtil.encodePassword(changePassworDto.getNewPasswordOne()));
            return changeRepo.save(accountsEntity);
        } else {
            throw new Exception("Mật khẩu không đúng");
        }
    }
}
