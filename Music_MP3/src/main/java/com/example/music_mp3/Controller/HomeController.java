package com.example.music_mp3.Controller;

import com.example.music_mp3.Data.Entity.AccountsEntity;
import com.example.music_mp3.Data.Entity.UserEntity;
import com.example.music_mp3.Data.Variable.StaticVariable;
import com.example.music_mp3.Service.AccountService;
import com.example.music_mp3.Service.ProfileService;
import com.example.music_mp3.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/MusicMp3")
    public String Home() {
        return "Home/index";
    }

    @Autowired
    private AccountService authService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;


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
    //VanHiep Begin
    @GetMapping("/register")
    public String register() {
        return "Admin/auth/register";
    }

    @GetMapping("/confirmOTP")
    public String confirmOTP() {
        return "Admin/auth/confirmOtp";
    }
    //VanHiep End

    //VanHiep - TuongVi Begin

    @GetMapping("/myprofile")
    public String myprofile(Model model) {
        String email = (String) session.getAttribute("email");
//        String username = (String) session.getAttribute("username");

        // Tìm thông tin người dùng dựa trên email
        AccountsEntity userAccount = profileService.findByEmail(email);


        UserEntity user = userService.getCurrentUser();

        // Thêm thông tin người dùng vào model
        model.addAttribute("username", userAccount.getUsername());
        model.addAttribute("email", userAccount.getEmail());

        if (user != null && user.getName() != null && user.getPhone() != null) {
            // Nếu có, hiển thị thông tin name và phone trên HTML
            model.addAttribute("name", user.getName());
            model.addAttribute("phone", user.getPhone());
            return "Home/MyProfile";
        } else {
            // Nếu không, yêu cầu nhập thông tin và hiển thị form nhập liệu trên HTML
            return "Home/MyProfile";
        }
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@RequestParam("name") String name,
                                @RequestParam("phone") String phone,
                                HttpSession session) {
        UserEntity user = userService.getCurrentUser();
        if (user != null) {
            user.setName(name);
            user.setPhone(phone);
            boolean updateSuccess = userService.saveOrUpdateUser(user);
            if (updateSuccess) {
                return "redirect:/myprofile?updateSuccess=true";
            } else {
                return "redirect:/myprofile?updateSuccess=false";
            }
        } else {
            return "redirect:/myprofile";
        }
    }


    @GetMapping("/check-login-status")
    @ResponseBody
    public String checkLoginStatus(HttpSession session) {
        // Kiểm tra xem người dùng đã đăng nhập hay chưa (dựa vào session)
        boolean isLoggedIn = (session.getAttribute("email") != null);
        if (isLoggedIn) {
            System.out.println(isLoggedIn);
            System.out.println("Logged in user email: " + session.getAttribute("email"));
        } else {
            System.out.println("No user is logged in.");
        }
        return String.valueOf(isLoggedIn);
    }

    @GetMapping("/logout")
    @ResponseBody
    public String logout(HttpSession session) {
        // Xóa thông tin đăng nhập khỏi session
        session.removeAttribute("email");

        return "Logout successful";
    }



    //VanHiep - TuongVi End
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
