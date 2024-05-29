package com.example.music_mp3.Data.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Songs")
public class SongsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int SongID;

    @Column(name = "song_name")
    private String song_name;

    @Column(name = "Image")
    private String Image;

    @Column(name = "audio_file")
    private String audio_file;

    @ManyToOne
    @JoinColumn(name = "albumid")
    private AlbumsEntity album;

    @ManyToOne
    @JoinColumn(name = "artistid")
    private ArtistsEntity artist;

    @ManyToOne
    @JoinColumn(name = "genreid")
    private GenresEntity genre;
}