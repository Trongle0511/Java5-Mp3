package com.example.music_mp3.Repository;

import com.example.music_mp3.Data.DTO.ArtistDTO;
import com.example.music_mp3.Data.Entity.ArtistsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistsEntity, Integer> {
    List<ArtistsEntity> findAll();
}
