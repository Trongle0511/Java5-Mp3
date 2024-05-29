package com.example.music_mp3.Data.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SongGenres")
public class SongGenresEntity {
    @EmbeddedId
    private SongGenresId id;

    @ManyToOne
    @MapsId("songID")
    @JoinColumn(name = "SongID")
    private SongsEntity song;

    @ManyToOne
    @MapsId("genreID")
    @JoinColumn(name = "GenreID")
    private GenresEntity genre;
}
