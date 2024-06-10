package com.example.music_mp3.Service.ServiceImpl;

import com.example.music_mp3.Data.DTO.SongAndArtistInsertDto;
import com.example.music_mp3.Data.DTO.SongDTO;
import com.example.music_mp3.Data.Entity.AlbumsEntity;
import com.example.music_mp3.Data.Entity.ArtistsEntity;
import com.example.music_mp3.Data.Entity.GenresEntity;
import com.example.music_mp3.Data.Entity.SongsEntity;
import com.example.music_mp3.Data.Model.SongInsertM;
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
    public SongsEntity saveSongAndArtist(SongAndArtistInsertDto songAndArtistInsertDto) {


        // Tạo và lưu nghệ sĩ mới
        ArtistsEntity artist = new ArtistsEntity();
        artist.setArtists_name(songAndArtistInsertDto.getArtist_name());
        artist = artistRepo.save(artist);

        // Tạo và lưu bài hát mới
        SongsEntity song = new SongsEntity();
        song.setSong_name(songAndArtistInsertDto.getSong_name());
        song.setImage(songAndArtistInsertDto.getImage());
        song.setAudio_file(songAndArtistInsertDto.getAudio_file());
        song.setAlbum(findAlbumById(songAndArtistInsertDto.getAlbumid()));
        song.setGenre(findGenreById(songAndArtistInsertDto.getGenreid()));
        song.setArtist(artist);
        return songRepo.save(song);
    }

    @Override
    public List<SongInsertM> findAllSongInsertM() {
        List<SongsEntity> listSongsE = songRepo.findAll();
        return SongInsertM.convertListSongEToListSongM(listSongsE);
    }


    private AlbumsEntity findAlbumById(Integer albumId) {
        if (albumId == null) return null;
        return albumRepo.findById(albumId).orElse(null);
    }

    private GenresEntity findGenreById(Integer genreId) {
        if (genreId == null) return null;
        return genreRepo.findById(genreId).orElse(null);
    }



    private SongDTO convertToDTO(SongsEntity song) {
        return new SongDTO(
                song.getId(),
                song.getSong_name(),
                song.getImage(),
                song.getAudio_file()
        );
    }

    //Văn Hip
    @Override
    public List<SongDTO> findSongsByArtistId(int artistId) {
        List<SongsEntity> songsEntities = songRepo.findByArtist_Artistid(artistId);
        return songsEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

}
