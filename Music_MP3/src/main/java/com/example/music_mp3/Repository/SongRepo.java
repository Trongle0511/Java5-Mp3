package com.example.music_mp3.Repository;


import com.example.music_mp3.Data.Entity.SongsEntity;
import com.example.music_mp3.Data.Model.SongInsertM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SongRepo extends JpaRepository<SongsEntity, Integer> {
    SongsEntity save(SongsEntity song);
    SongsEntity findById(int songID);
    List<SongsEntity> findAll();

    List<SongsEntity> findByArtist_Artistid(int artistId);
}
