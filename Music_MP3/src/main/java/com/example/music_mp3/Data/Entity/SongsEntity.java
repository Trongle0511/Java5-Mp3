package com.example.music_mp3.Data.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Songs")
public class SongsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private int id;

    @Column(name = "song_name")
    private String song_name;

    @Column(name = "Image")
    private String Image;

    @Column(name = "audio_file")
    private String audio_file;

    @ManyToOne
    @JoinColumn(name = "albumid")
    @JsonIgnore
    private AlbumsEntity album;

    @ManyToOne
    @JoinColumn(name = "artistid")
    private ArtistsEntity artist;

    @ManyToOne
    @JoinColumn(name = "genreid")
    @JsonIgnore
    private GenresEntity genre;

    @OneToMany(mappedBy = "song")
    private List<MonthlyTrendingEntity> monthlyTrending;
}