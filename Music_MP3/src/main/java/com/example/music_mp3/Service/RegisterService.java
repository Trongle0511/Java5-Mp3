package com.example.music_mp3.Service;

import com.example.music_mp3.Data.DTO.AccountDTO;
import com.example.music_mp3.Data.Entity.AccountsEntity;

public interface RegisterService {
    AccountsEntity save(AccountDTO accountDTO);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
