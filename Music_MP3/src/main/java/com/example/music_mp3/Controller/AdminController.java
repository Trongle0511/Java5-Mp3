package com.example.music_mp3.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String HomeAdmin() {

        return "Admin/index";
    }
}
