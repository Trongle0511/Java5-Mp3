package com.example.music_mp3.Repository;

import com.example.music_mp3.Data.Entity.ArtistsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepo extends JpaRepository<ArtistsEntity,Integer> {

}
