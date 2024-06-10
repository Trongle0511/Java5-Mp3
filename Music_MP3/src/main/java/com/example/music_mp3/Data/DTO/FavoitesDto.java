package com.example.music_mp3.Data.DTO;

import com.example.music_mp3.Data.Entity.AccountsEntity;
import com.example.music_mp3.Data.Entity.FavoritesEntity;
import com.example.music_mp3.Data.Entity.SongsEntity;
import lombok.*;

import java.util.Date;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FavoitesDto {
    private int FavoriteId;

    // Getter and setter methods
    private SongsEntity songsEntity;

    private AccountsEntity accountsEntity;

    private Date createdAt;
}
