package com.example.music_mp3.Data.Model;

import com.example.music_mp3.Data.Entity.SongsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongInsertM {
    private String song_name;
    private String image;
    private String audio_file;
    private int albumid;
    private int artistid;
    private int genreid;

    public static SongInsertM fromEntity(SongsEntity songsEntity) {
        return SongInsertM.builder()
                .song_name(songsEntity.getSong_name())
                .image(songsEntity.getImage())
                .audio_file(songsEntity.getAudio_file())
                .albumid(songsEntity.getAlbum().getAlbumid())
                .artistid(songsEntity.getArtist().getArtistid())
                .genreid(songsEntity.getGenre().getGenreid())
                .build();
    }
}
