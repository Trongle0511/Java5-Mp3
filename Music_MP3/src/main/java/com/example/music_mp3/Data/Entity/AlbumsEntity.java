package com.example.music_mp3.Data.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Album")
public class AlbumsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int albumid;

    @Column(name = "album_name")
    private String album_name;

    @OneToMany(mappedBy = "album")
    private List<SongsEntity> songs;
}
