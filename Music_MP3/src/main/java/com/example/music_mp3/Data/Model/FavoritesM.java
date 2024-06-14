package com.example.music_mp3.Data.Model;

import com.example.music_mp3.Data.Entity.AccountsEntity;
import com.example.music_mp3.Data.Entity.FavoritesEntity;
import com.example.music_mp3.Data.Entity.SongsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoritesM {
    private Integer FavoriteId;
    private SongsEntity song;
    private AccountsEntity user;
    private Date createdAt;

    public static FavoritesM convertUserEToUserM(FavoritesEntity favorites) {
        return FavoritesM.builder()
                .FavoriteId(favorites.getFavoriteId())
                .song(favorites.getSong())
                .user(favorites.getUser())
                .createdAt(favorites.getCreatedAt())
                .build();
    }

    public static List<FavoritesM> converListFavoritesEToListFavoritesM(List<FavoritesEntity> favoritesEntityList){
        return favoritesEntityList.stream()
                .map(e -> convertUserEToUserM(e))
                .collect(Collectors.toList());
    }
}
