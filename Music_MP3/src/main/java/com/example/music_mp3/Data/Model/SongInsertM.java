package com.example.music_mp3.Data.Model;

import com.example.music_mp3.Data.Entity.SongsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongInsertM {
    private String song_name;
    private String image;
    private String artist_name;
    private String genre_name;
    private String album_name;

    public static SongInsertM fromEntity(SongsEntity songsEntity) {
        return SongInsertM.builder()
                .song_name(songsEntity.getSong_name())
                .image(songsEntity.getImage())
                .artist_name(songsEntity.getArtist() != null ? songsEntity.getArtist().getArtists_name() : null)
                .genre_name(songsEntity.getGenre() != null ? songsEntity.getGenre().getGenres_name() : null)
                .album_name(songsEntity.getAlbum() != null ? songsEntity.getAlbum().getAlbum_name() : null)
                .build();
    }

    public static List<SongInsertM> convertListSongEToListSongM(List<SongsEntity> listSongsE) {
        return listSongsE.stream()
                .map(SongInsertM::fromEntity)
                .collect(Collectors.toList());
    }
}

