package com.example.music_mp3.Service;

import com.example.music_mp3.Data.DTO.ChangePassworDto;
import com.example.music_mp3.Data.Entity.AccountsEntity;

public interface ChangeService{
    AccountsEntity changeAccount(ChangePassworDto changePassworDto);
}
