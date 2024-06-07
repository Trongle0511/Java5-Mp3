package com.example.music_mp3.Service;

import com.example.music_mp3.Data.Entity.AccountsEntity;
import com.example.music_mp3.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public String getUsernameByEmail(String email) {
        AccountsEntity account = accountRepository.findByEmail(email);
        return account != null ? account.getUsername() : null;
    }

    public boolean authenticateUser(String email, String password) {
        AccountsEntity account = accountRepository.findByEmail(email);
        if (account == null || password == null) {
            return false;
        }
        return BCrypt.checkpw(password, account.getHashedPassword());
    }

    public boolean isAdmin(String email) {
        AccountsEntity account = accountRepository.findByEmail(email);
        return account != null && account.getRole();
    }
    public AccountsEntity findUserByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

}



