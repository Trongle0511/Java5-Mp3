package com.example.music_mp3.Service.ServiceImpl;

import com.example.music_mp3.Data.DTO.ChangePassworDto;
import com.example.music_mp3.Data.Entity.AccountsEntity;
import com.example.music_mp3.Data.Variable.StaticVariable;
import com.example.music_mp3.Repository.ChangeRepo;
import com.example.music_mp3.Service.ChangeService;
import com.example.music_mp3.utils.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangeServiceImpl implements ChangeService {
    @Autowired
    private ChangeRepo changeRepo;

    @Override
    public AccountsEntity changeAccount(ChangePassworDto changePassworDto) {
        AccountsEntity accountsEntity = changeRepo.findByEmail(StaticVariable.sessionEmail);
        accountsEntity.setHashedPassword(PasswordEncoderUtil.encodePassword(changePassworDto.getNewPasswordOne()));
        return changeRepo.save(accountsEntity);
    }

    @Override
    public AccountsEntity findEmail(String email) {
        return  changeRepo.findByEmail(email);
    }
}
