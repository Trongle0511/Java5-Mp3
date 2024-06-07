package com.example.music_mp3.Data.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongAndArtistInsertDto  {
    private String song_name;
    private String image;
    private String audio_file;
    private Integer albumid;
    private Integer genreid;
    private String artist_name;
}
