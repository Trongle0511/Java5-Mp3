package com.example.music_mp3.Service;


import com.example.music_mp3.Data.DTO.SongDTO;
import com.example.music_mp3.Data.DTO.SongInsertDto;
import com.example.music_mp3.Data.Entity.SongsEntity;
import com.example.music_mp3.Data.Model.SongM;

import java.util.List;

public interface SongService{
    SongM findById(SongDTO songDTO);
    List<SongDTO> findAll();
    SongsEntity saveSong(SongInsertDto songInsertDto);
}
