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
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ChangePasswordApi {
    @Autowired
    private ChangeService changeService;
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassworDto changePassworDto){
        Map<String,Object> result  = new HashMap<>();
        AccountsEntity accountsEntity = changeService.findEmail(StaticVariable.sessionEmail);
        if (accountsEntity == null) {
            result.put("success", false);
            result.put("message", "Tài khoản không tồn tại");
            result.put("data", null);
            return ResponseEntity.status(404).body(result);
        }
        String password = accountsEntity.getHashedPassword();
        String rawPassword = changePassworDto.getHashedPassword();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isPasswordMatch = passwordEncoder.matches(rawPassword, password);
        if (isPasswordMatch) {
            changeService.changeAccount(changePassworDto);
            result.put("success", true);
            result.put("message", "Đổi mật khẩu thành công");
            result.put("data", accountsEntity);
            return ResponseEntity.ok(result);
        } else {
            result.put("success", false);
            result.put("message", "Không đúng mật khẩu");
            result.put("data", null);
            return ResponseEntity.status(400).body(result);
        }
    }
}
