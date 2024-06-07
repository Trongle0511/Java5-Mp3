package com.example.music_mp3.Service;

import com.example.music_mp3.Data.Entity.AccountsEntity;

public interface ProfileService {
    AccountsEntity findByEmail(String email);
}
