package com.example.music_mp3.Api;

import com.example.music_mp3.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountAPI {

    @Autowired
    private AccountService accountService;

    @PostMapping("/loginApi")
    public ResponseEntity<?> login(@RequestParam("email") String email,
                                   @RequestParam("password") String password) {
        if (accountService.authenticateUser(email, password)) {
            if (accountService.isAdmin(email)) {
                return ResponseEntity.ok("Đăng nhập thành công với vai trò admin");
            } else {
                return ResponseEntity.ok("Đăng nhập thành công với vai trò user");
            }
        } else {
            return ResponseEntity.status(401).body("Email hoặc mật khẩu không hợp lệ");
        }
    }
}