package com.example.music_mp3.Repository;

import com.example.music_mp3.Data.Entity.AlbumsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepo extends JpaRepository<AlbumsEntity, Integer> {
}
