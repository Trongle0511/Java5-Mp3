package com.example.music_mp3.Service.ServiceImpl;

import com.example.music_mp3.Data.Entity.AccountsEntity;
import com.example.music_mp3.Repository.ProfileRepository;
import com.example.music_mp3.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public AccountsEntity findByEmail(String email) {
        return profileRepository.findByEmail(email);
    }
}
