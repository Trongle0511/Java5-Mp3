package com.example.music_mp3.Service.ServiceImpl;

import com.example.music_mp3.Data.Entity.UserEntity;
import com.example.music_mp3.Repository.UserRepository;
import com.example.music_mp3.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpSession httpSession;

//    @Override
//    public UserEntity getUserByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }

    @Override
    public UserEntity getCurrentUser() {
        String email = (String) httpSession.getAttribute("email");
        return email == null ? null : userRepository.findByEmailFromSession(email);
    }

    @Override
    public boolean saveOrUpdateUser(UserEntity user) {
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
