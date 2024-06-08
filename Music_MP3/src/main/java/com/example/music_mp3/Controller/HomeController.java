package com.example.music_mp3.Controller;

import com.example.music_mp3.Data.Entity.AccountsEntity;
import com.example.music_mp3.Data.Entity.UserEntity;
import com.example.music_mp3.Data.Variable.StaticVariable;
import com.example.music_mp3.Repository.AccountRepository;
import com.example.music_mp3.Service.AccountService;
import com.example.music_mp3.Service.ProfileService;
import com.example.music_mp3.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import com.example.music_mp3.Service.MailerService;
import com.example.music_mp3.utils.PasswordEncoderUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.net.URI;
import java.util.Random;
import java.util.UUID;

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

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MailerService mailer;  // Inject the MailerService

    @GetMapping("/login")
    public String login(Model model) {
        AccountsEntity account = new AccountsEntity();
        model.addAttribute("account", account);
        return "Admin/auth/login";
    }

//    @PostMapping("/submitLogin")
//    public String submitLogin(@RequestParam("email") String email,
//                              @RequestParam("hashedPassword") String password,
//                              Model model ) {
//        if (authService.authenticateUser(email, password)) {
//            StaticVariable.sessionEmail = email;
//            if (authService.isAdmin(email) ) {
//                // Đăng nhập thành công cho vai trò admin
//                // lưu email vào session
//                session.setAttribute("email", email);
//                String userEmail = (String) session.getAttribute("email");
//                System.out.println(userEmail);
//                return "redirect:/admin";
//            } else {
//                // Đăng nhập thành công cho vai trò user
//                // lưu email vào session
//                session.setAttribute("email", email);
//                String userEmail = (String) session.getAttribute("email");
//                System.out.println(userEmail);
//                return "redirect:/MusicMp3";
//            }
//        } else {
//            // Đăng nhập thất bại
//            model.addAttribute("error", "Invalid email or password");
//            model.addAttribute("account", new AccountsEntity());
//            return "redirect:/login";
//        }
//    }

    @PostMapping("/submitLogin")
    public ResponseEntity<String> submitLogin(@RequestParam("email") String email,
                                              @RequestParam("hashedPassword") String password) {
        if (!authService.emailExists(email)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email không tồn tại");
        }
        if (authService.authenticateUser(email, password)) {
            StaticVariable.sessionEmail = email;
            session.setAttribute("email", email);
            System.out.println(email);
            if (authService.isAdmin(email)) {
                // Đăng nhập thành công cho vai trò admin
                return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/admin")).build();
            } else {
                // Đăng nhập thành công cho vai trò user
                return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/MusicMp3")).build();
            }
        } else {
            // Đăng nhập thất bại
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(" Mật khẩu không chính xác!");
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
        return "Admin/auth/check-email";
    }
    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam("email") String email,
                                        Model model, HttpSession session) {
        boolean emailExists = accountService.emailExists(email);

        if (emailExists) {
            // Generate OTP
            String otp = generateOtp(); // Assume this method generates an OTP

            // Store OTP and email in session
            session.setAttribute("otp", otp);
            session.setAttribute("email", email);

            // Send the OTP via email
            mailer.sendOtpEmail(email, otp); // Assume this method sends the OTP to the given email

            model.addAttribute("message", "An OTP has been sent to your email for password reset.");
            return "Admin/auth/forgot-password";
        } else {
            model.addAttribute("error", "Email address not found.");
            return "Admin/auth/check-email";
        }
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(
            @RequestParam("otps1") String otps1,
            @RequestParam("otps2") String otps2,
            @RequestParam("otps3") String otps3,
            @RequestParam("otps4") String otps4,
            @RequestParam("otps5") String otps5,
            @RequestParam("otps6") String otps6,
            HttpSession session,
            Model model) {

        String otpInput = otps1 + otps2 + otps3 + otps4 + otps5 + otps6;

        String sessionOtp = (String) session.getAttribute("otp");

        if (sessionOtp != null && sessionOtp.equals(otpInput)) {
            // OTP is correct, proceed to reset password page
            return "Admin/auth/reset-forgot-password";
        } else {
            model.addAttribute("error", "Invalid OTP. Please try again.");
            return "Admin/auth/forgot-password";
        }
    }

    @PostMapping("/reset-password")
    public String resetPassword(
            @RequestParam("confirmPassword") String newPassword,
            HttpSession session,
            Model model) {

        String email = (String) session.getAttribute("email");
        AccountsEntity account = accountRepository.findByEmail(email);

        if (account != null) {
            // Reset the password
            String hashedPassword = PasswordEncoderUtil.encodePassword(newPassword);
            account.setHashedPassword(hashedPassword); // Ensure the password is hashed before saving
            accountRepository.save(account);

            // Clear the session attributes
            session.removeAttribute("email");

            model.addAttribute("messagee", "Your password has been successfully reset. Please login again!");
            return "Admin/auth/reset-forgot-password";
        } else {
            model.addAttribute("error", "Account not found. Please try again.");
            return "Admin/auth/reset-forgot-password";
        }
    }


    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
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
