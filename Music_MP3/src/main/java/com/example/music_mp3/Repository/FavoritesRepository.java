package com.example.music_mp3.Repository;

import com.example.music_mp3.Data.Entity.FavoritesEntity;
import com.example.music_mp3.Data.Model.FavoritesM;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FavoritesRepository extends JpaRepository<FavoritesEntity, Integer> {

    @Modifying
    @Transactional
    @Query(value = "insert into Favorites (user_id, song_id, created_at) " +
            "values (:user_id,:song_id, :created_at)", nativeQuery = true)
    void insertFavorites(@Param("user_id") Integer user_id,
                         @Param("song_id") Integer song_id,
                         @Param("created_at") Date created_at);


    @Modifying
    @Transactional
    @Query(value = "delete from Favorites where user_id = :user_id and song_id = :song_id", nativeQuery = true)
    void deleteFavoriteByUserIdAndSongId(@Param("user_id") Integer user_id,
                                         @Param("song_id") Integer song_id);
}
