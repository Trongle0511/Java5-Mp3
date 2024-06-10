package com.example.music_mp3.Service;


import com.example.music_mp3.Data.DTO.SongAndArtistInsertDto;
import com.example.music_mp3.Data.DTO.SongDTO;
import com.example.music_mp3.Data.Entity.SongsEntity;
import com.example.music_mp3.Data.Model.SongInsertM;
import com.example.music_mp3.Data.Model.SongM;

import java.util.List;

public interface SongService{
    SongM findById(SongDTO songDTO);
    List<SongDTO> findAll();
    SongsEntity saveSongAndArtist(SongAndArtistInsertDto songAndArtistInsertDto);
    List<SongInsertM> findAllSongInsertM();

    List<SongDTO> findSongsByArtistId(int artistId);
}
