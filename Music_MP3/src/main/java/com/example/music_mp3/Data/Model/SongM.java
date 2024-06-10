package com.example.music_mp3.Data.Model;

import com.example.music_mp3.Data.DTO.ArtistDTO;
import com.example.music_mp3.Data.Entity.ArtistsEntity;
import com.example.music_mp3.Data.Entity.SongsEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongM {
    private int SongID;
    private String song_name;
    private String Image;
    private String audio_file;

    public static SongM convertSongsEntityToSongM(SongsEntity songE){
        return SongM.builder()
                .SongID(songE.getSongID())
                .song_name(songE.getSong_name())
                .Image(songE.getImage())
                .audio_file(songE.getAudio_file())
                .build();
    }
}
