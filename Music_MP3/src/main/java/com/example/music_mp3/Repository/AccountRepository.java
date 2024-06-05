package com.example.music_mp3.Repository;

import com.example.music_mp3.Data.Entity.AccountsEntity;
import com.example.music_mp3.Data.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<AccountsEntity, Integer> {
    AccountsEntity findByEmail(String email);

    AccountsEntity save(AccountsEntity account);

    @Query("select a from AccountsEntity a where a.email = :email")
    AccountsEntity existsByEmail(@Param("email") String email);

    @Query("select a from AccountsEntity a where a.username = :username")
    AccountsEntity existsByUsername(@Param("username") String username);
}


