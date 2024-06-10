package com.example.music_mp3.Data.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Artists")
public class ArtistsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int artistid;

    @Column(name = "artists_name")
    private String artists_name;

    @Column(name = "artists_image")
    private String artists_image;

    @Column(name = "artists_description")
    private String artists_description;

}
