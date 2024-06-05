package com.example.music_mp3.Controller;

import com.example.music_mp3.Data.Entity.AccountsEntity;
import com.example.music_mp3.Data.Variable.StaticVariable;
import com.example.music_mp3.Service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class HomeController {

    @GetMapping("/MusicMp3")
    public String Home() {
        return "Home/index";
    }

    @Autowired
    private AccountService authService;

    @Autowired
    private HttpSession session;

    @GetMapping("/login")
    public String login(Model model) {
        AccountsEntity account = new AccountsEntity();
        model.addAttribute("account", account);
        return "Admin/auth/login";
    }

    @PostMapping("/submitLogin")
    public String submitLogin(@RequestParam("email") String email,
                              @RequestParam("hashedPassword") String password,
                              Model model ) {
        if (authService.authenticateUser(email, password)) {
            StaticVariable.sessionEmail = email;
            if (authService.isAdmin(email) ) {
                // Đăng nhập thành công cho vai trò admin
                // lưu email vào session
                session.setAttribute("email", email);
                String userEmail = (String) session.getAttribute("email");
                System.out.println(userEmail);
                return "redirect:/admin";
            } else {
                // Đăng nhập thành công cho vai trò user
                // lưu email vào session
                session.setAttribute("email", email);
                String userEmail = (String) session.getAttribute("email");
                System.out.println(userEmail);
                return "redirect:/MusicMp3";
            }
        } else {
            // Đăng nhập thất bại
            model.addAttribute("error", "Invalid email or password");
            model.addAttribute("account", new AccountsEntity());
            return "redirect:/login";
        }
    }

    @GetMapping("/register")
    public String register() {
        return "Admin/auth/register";
    }

    @GetMapping("/forgot-password")
    public String forgotpassword() {
        return "Admin/auth/forgot-password";
    }
    @GetMapping("/reset")
    public String reset() {
        return "Admin/auth/reset-password";
    }
    @GetMapping("/detail")
    public String detail() {

        return "Home/SinglePlaylistScreen";
    }
}
