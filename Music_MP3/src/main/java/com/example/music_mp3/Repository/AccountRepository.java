package com.example.music_mp3.Repository;

import com.example.music_mp3.Data.Entity.AccountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountsEntity, Integer> {
    AccountsEntity findByEmail(String email);

    AccountsEntity findByResetToken(String resetToken);
}
