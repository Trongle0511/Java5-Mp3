package com.example.music_mp3.Service;

import com.example.music_mp3.Data.Entity.UserEntity;

public interface UserService {
    UserEntity getCurrentUser();
    boolean saveOrUpdateUser(UserEntity user);
}
