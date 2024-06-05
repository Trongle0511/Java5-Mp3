package com.example.music_mp3.Service.ServiceImpl;

import com.example.music_mp3.Data.DTO.SongDTO;
import com.example.music_mp3.Data.DTO.SongInsertDto;
import com.example.music_mp3.Data.Entity.AlbumsEntity;
import com.example.music_mp3.Data.Entity.ArtistsEntity;
import com.example.music_mp3.Data.Entity.GenresEntity;
import com.example.music_mp3.Data.Entity.SongsEntity;
import com.example.music_mp3.Data.Model.SongM;
import com.example.music_mp3.Repository.AlbumRepo;
import com.example.music_mp3.Repository.ArtistRepo;
import com.example.music_mp3.Repository.GenreRepo;
import com.example.music_mp3.Repository.SongRepo;
import com.example.music_mp3.Service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private SongRepo songRepo;
    @Autowired
    private AlbumRepo albumRepo;
    @Autowired
    private ArtistRepo artistRepo;
    @Autowired
    private GenreRepo genreRepo;

    @Override
    public SongM findById(SongDTO songDTO) {
        return SongM.convertSongsEntityToSongM(songRepo.findById(songDTO.getSongID()));
    }

    @Override
    public List<SongDTO> findAll() {
        List<SongsEntity> songsEntities = songRepo.findAll();
        return songsEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SongsEntity saveSong(SongInsertDto songInsertDto) {
        SongsEntity song = new SongsEntity();
        song.setSong_name(songInsertDto.getSong_name());
        song.setImage(songInsertDto.getImage());
        song.setAudio_file(songInsertDto.getAudio_file());

        AlbumsEntity album = findAlbumById(songInsertDto.getAlbumid());
        ArtistsEntity artist = findArtistById(songInsertDto.getArtistid());
        GenresEntity genre = findGenreById(songInsertDto.getGenreid());

        song.setAlbum(album);
        song.setArtist(artist);
        song.setGenre(genre);

        return songRepo.save(song);
    }

    private AlbumsEntity findAlbumById(int albumId) {
        return albumRepo.findById(albumId).orElse(null);
    }

    private ArtistsEntity findArtistById(int artistId) {
        return artistRepo.findById(artistId).orElse(null);
    }

    private GenresEntity findGenreById(int genreId) {
        return genreRepo.findById(genreId).orElse(null);
    }



    private SongDTO convertToDTO(SongsEntity song) {
        return new SongDTO(
                song.getSongID(),
                song.getSong_name(),
                song.getImage(),
                song.getAudio_file()
        );
    }

}
