package com.example.music_mp3.Data.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PlaylistSongs")
public class PlaylistSongsEntity {
    @EmbeddedId
    private PlaylistSongsId id;

    @ManyToOne
    @MapsId("playlistID")
    @JoinColumn(name = "PlaylistID")
    private PlaylistsEntity playlist;

    @ManyToOne
    @MapsId("songID")
    @JoinColumn(name = "SongID")
    private SongsEntity song;
}
