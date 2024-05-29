package com.example.music_mp3.Repository;

import com.example.music_mp3.Data.DTO.SongDTO;
import com.example.music_mp3.Data.Entity.SongsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepo extends JpaRepository<SongsEntity, Integer> {
    SongsEntity save(SongsEntity song);
    SongsEntity findById(int songID);
    List<SongsEntity> findAll();
}
