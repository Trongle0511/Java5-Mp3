package com.example.music_mp3.Data.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongInsertDto {
    private String song_name;
    private String image;
    private String audio_file;
    private int albumid;
    private int artistid;
    private int genreid;
}
