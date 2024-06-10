package com.example.music_mp3.Service;

import com.example.music_mp3.Data.DTO.ArtistDTO;
import com.example.music_mp3.Data.Entity.ArtistsEntity;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    Optional<ArtistDTO> findById(int artistId);
    List<ArtistDTO> findAll();
}
