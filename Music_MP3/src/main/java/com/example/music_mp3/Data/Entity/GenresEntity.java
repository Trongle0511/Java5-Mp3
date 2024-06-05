package com.example.music_mp3.Data.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Genres")
public class GenresEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genreid;

    @Column(name = "genres_name")
    private String genres_name;

    @OneToMany(mappedBy = "genre")
    @JsonBackReference
    private List<SongsEntity> songs;
}
