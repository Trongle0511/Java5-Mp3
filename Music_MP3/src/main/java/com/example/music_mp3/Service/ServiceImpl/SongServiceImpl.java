package com.example.music_mp3.Service.ServiceImpl;

import com.example.music_mp3.Data.DTO.SongDTO;
import com.example.music_mp3.Data.Entity.SongsEntity;
import com.example.music_mp3.Data.Model.SongM;
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

//    @Override
//    public SongsEntity save(SongsEntity song) {
//        return songRepo.save(song);
//    }

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

    private SongDTO convertToDTO(SongsEntity song) {
        return new SongDTO(
                song.getSongID(),
                song.getSong_name(),
                song.getImage(),
                song.getAudio_file()
        );
    }

}
