package com.example.music_mp3.Data.Model;

import com.example.music_mp3.Data.DTO.ArtistDTO;
import com.example.music_mp3.Data.Entity.ArtistsEntity;
import com.example.music_mp3.Data.Entity.SongsEntity;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

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
    private ArtistsEntity artist;

    public static SongM convertSongsEntityToSongM(SongsEntity songE){
        return SongM.builder()
                .SongID(songE.getId())
                .song_name(songE.getSong_name())
                .Image(songE.getImage())
                .audio_file(songE.getAudio_file())
                .artist(songE.getArtist())
                .build();
    }

    public static List<SongM> convertListSongsEntityToListSongM(List<SongsEntity> listSongsEntities) {
        return  listSongsEntities.stream()
                .map(e -> convertSongsEntityToSongM(e))
                .collect(Collectors.toList());
    }
}
