package com.example.music_mp3.Api;

import com.example.music_mp3.Data.DTO.AccountDTO;
import com.example.music_mp3.Data.Entity.AccountsEntity;
import com.example.music_mp3.Service.AccountService;
import com.example.music_mp3.Service.RegisterService;
import com.example.music_mp3.Service.ServiceImpl.RegisterServiceImpl;
import com.example.music_mp3.utils.OTPUtil;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/accounts")
@Slf4j
public class AccountAPI {

    @Autowired
    private AccountService accountService;

    @Autowired
    RegisterServiceImpl registerService;

    @Autowired
    HttpSession httpSession;

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

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody() AccountDTO accountDTO) {
        Map<String, Object> result = new HashMap<>();
        try {
            AccountsEntity accountEntity = registerService.save(accountDTO);
            result.put("success", true);
            result.put("message", "Call api thành công");
            result.put("data", accountEntity);

        } catch (Exception e) {
            log.error("Call api thất bại: /register ", e);
            result.put("success", false);
            result.put("message", "Call api thất bại");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/send-email-for-user")
    public ResponseEntity<?> sendEmailForUser(@RequestBody AccountDTO accountDTO) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean checkExistEmail = registerService.existsByEmail(accountDTO.getEmail());
            boolean checkExistUsername = registerService.existsByUsername( accountDTO.getUsername());
            if (!checkExistEmail) {
                result.put("success", true);
                result.put("message", "Email đã tồn tại");
                result.put("data", null);
                return ResponseEntity.ok(result);
            } if (!checkExistUsername) {
                result.put("success", true);
                result.put("message", "Username đã tồn tại");
                result.put("data", null);
                return ResponseEntity.ok(result);
            }

            String Otp = OTPUtil.generateOTP();
            httpSession.setAttribute("otp", Otp);
            httpSession.setAttribute("account", accountDTO);
            registerService.sendOTPEmail(accountDTO.getEmail(), Otp);
            result.put("success", true);
            result.put("message", "Call api thành công");
            result.put("data", Otp);
        } catch (Exception e) {
            log.error("Call api thất bại: /register ", e);
            result.put("success", false);
            result.put("message", "Call api thất bại");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/confirm-register")
    public ResponseEntity<?> confirmRegister(@RequestParam("otp1") String confirm1,
                                             @RequestParam("otp2") String confirm2,
                                             @RequestParam("otp3") String confirm3,
                                             @RequestParam("otp4") String confirm4,
                                             @RequestParam("otp5") String confirm5,
                                             @RequestParam("otp6") String confirm6) {
        Map<String, Object> result = new HashMap<>();
        try {
            String Otp = registerService.concatOtp(confirm1, confirm2, confirm3, confirm4, confirm5, confirm6);
            String OtpServer = (String) httpSession.getAttribute("otp");
            AccountDTO accountDTO = (AccountDTO) httpSession.getAttribute("account");
            if (Otp.equals(OtpServer)) {
                registerService.save(accountDTO);
            }
            result.put("success", true);
            result.put("message", "Call api thành công");
            result.put("data", "Dã gữi được Email");
        } catch (Exception e) {
            log.error("Call api thất bại: confirm-register ", e);
            result.put("success", false);
            result.put("message", "Call api thất bại");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
}