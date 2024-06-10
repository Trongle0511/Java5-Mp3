package com.example.music_mp3.Service.ServiceImpl;

import com.example.music_mp3.Data.DTO.ArtistDTO;

import com.example.music_mp3.Data.DTO.SongDTO;
import com.example.music_mp3.Data.Entity.ArtistsEntity;
import com.example.music_mp3.Data.Entity.SongsEntity;
import com.example.music_mp3.Repository.ArtistRepository;
import com.example.music_mp3.Service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    private ArtistRepository artistRepo;

    @Override
    public Optional<ArtistDTO> findById(int artistId) {
        Optional<ArtistsEntity> artistEntity = artistRepo.findById(artistId);
        return artistEntity.map(artist -> new ArtistDTO(
                artist.getArtistid(),
                artist.getArtists_name(),
                artist.getArtists_image(),
                artist.getArtists_description()
        ));
    }

    @Override
    public List<ArtistDTO> findAll() {
        return artistRepo.findAll().stream()
                .map(artist -> new ArtistDTO(
                        artist.getArtistid(),
                        artist.getArtists_name(),
                        artist.getArtists_image(),
                        artist.getArtists_description()
                ))
                .collect(Collectors.toList());
    }

}
