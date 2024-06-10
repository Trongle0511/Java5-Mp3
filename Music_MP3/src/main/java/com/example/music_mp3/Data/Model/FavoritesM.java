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
public class FavoritesM extends FavoritesEntity {
    private Integer FavoriteId;
    private SongsEntity songsEntity;
    private AccountsEntity accountsEntity;
    private Date createdAt;

    public static FavoritesM convertUserEToUserM(FavoritesEntity favorites) {
        return FavoritesM.builder()
                .FavoriteId(favorites.getFavoriteId())
                .songsEntity(favorites.getSong())
                .accountsEntity(favorites.getUser())
                .createdAt(favorites.getCreatedAt())
                .build();
    }

    public static List<FavoritesM> converListFavoritesEToListFavoritesM(List<FavoritesEntity> favoritesEntityList){
        return favoritesEntityList.stream()
                .map(e -> convertUserEToUserM(e))
                .collect(Collectors.toList());
    }
}
