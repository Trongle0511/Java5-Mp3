package com.example.music_mp3.Service.ServiceImpl;

import com.example.music_mp3.Data.Entity.AccountsEntity;
import com.example.music_mp3.Repository.AccountRepository;
import com.example.music_mp3.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

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

    @Override
    public boolean emailExists(String email) {
        return accountRepository.findByEmail(email) != null;
    }
}