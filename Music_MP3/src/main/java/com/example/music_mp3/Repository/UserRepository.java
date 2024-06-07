package com.example.music_mp3.Repository;

import com.example.music_mp3.Data.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query("select u from UserEntity u where u.account.email = :email")
    UserEntity findByEmailFromSession(@Param("email") String email);
}
