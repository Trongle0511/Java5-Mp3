package com.example.music_mp3.Data.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SongRatings")
public class SongRatingsEntity {
    @EmbeddedId
    private SongRatingsId id;

    @ManyToOne
    @MapsId("songID")
    @JoinColumn(name = "SongID")
    private SongsEntity song;

    @ManyToOne
    @MapsId("userID")
    @JoinColumn(name = "UserID")
    private AccountsEntity account;

    @Column(name = "Rating")
    private int rating;
}
