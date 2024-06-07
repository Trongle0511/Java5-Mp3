package com.example.music_mp3.Service;


public interface AccountService {
        boolean authenticateUser(String email, String password);
        boolean isAdmin(String email);
        boolean emailExists(String email);

}
