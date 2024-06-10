package com.example.music_mp3.Repository;

import com.example.music_mp3.Data.Entity.AccountsEntity;
import com.example.music_mp3.Data.Entity.FavoritesEntity;
import com.example.music_mp3.Data.Entity.SongsEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritesRepository extends JpaRepository<FavoritesEntity, Integer> {

    //    @Query(value = "select s.Image, s.song_name,s.audio_file, s.artistid " +
//            "from Favorites f " +
//            "left join Songs s on f.song_id = s.song_id " +
//            "left join Account a on f.user_id = a.user_id " +
//            "where a.email = :email ", nativeQuery = true)
    @Query(value = "select f " +
            "from FavoritesEntity f " +
            "join fetch f.song s " +
            "join fetch f.user u " +
            "where u.email = :email")
    List<FavoritesEntity> findFavoritesByUserEmail(@Param("email") String email);
//    List<Object[]> findFavoritesByUserEmail(@Param("email") String email);


    @Modifying
    @Transactional
    @Query(value = "insert into Favorites (user_id, song_id, created_at) " +
            "values (:user_id,:song_id, :created_at)", nativeQuery = true)
    void insertFavorites(@Param("user_id") Integer user_id,
                         @Param("song_id") Integer song_id,
                         @Param("created_at") Date created_at);

}
