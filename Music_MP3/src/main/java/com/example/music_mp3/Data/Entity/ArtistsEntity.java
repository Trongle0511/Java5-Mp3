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
@Table(name = "Artists")
public class ArtistsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int artistid;

    @Column(name = "artists_name")
    private String artists_name;

}
