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
@Table(name = "Playlist")
public class PlaylistsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int playlistID;

    @Column(name = "PlaylistName", nullable = false, length = 100)
    private String playlistName;

    @Column(name = "Description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private AccountsEntity account;

    @OneToMany(mappedBy = "playlist")
    private List<PlaylistSongsEntity> playlistSongs;
}
