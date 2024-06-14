package com.example.music_mp3.Repository;


import com.example.music_mp3.Data.Entity.SongsEntity;
import com.example.music_mp3.Data.Model.SongInsertM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SongRepo extends JpaRepository<SongsEntity, Integer> {
    SongsEntity save(SongsEntity song);
    SongsEntity findById(int songID);
    List<SongsEntity> findAll();

    List<SongsEntity> findByArtist_Artistid(int artistId);

    @Query(value = "select s.song_id, s.Image, s.song_name,s.audio_file, s.artistid,s.albumid, s.genreid " +
            "from Songs s " +
            "join Favorites f on f.song_id = s.song_id " +
            "join Account a on f.user_id = a.user_id " +
            "where a.email = :email ", nativeQuery = true)
    List<SongsEntity> findSongsEntityByUserEmailByUserEmail(@Param("email") String email);
}
