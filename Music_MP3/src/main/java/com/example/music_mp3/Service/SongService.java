package com.example.music_mp3.Service;


import com.example.music_mp3.Data.DTO.SongDTO;
import com.example.music_mp3.Data.Entity.SongsEntity;
import com.example.music_mp3.Data.Model.SongM;

import java.util.List;

public interface SongService {
//    SongDTO save(SongsEntity song);
    SongM findById(SongDTO songDTO);
    List<SongDTO> findAll();
}
