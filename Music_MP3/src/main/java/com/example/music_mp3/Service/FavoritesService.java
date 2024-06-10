package com.example.music_mp3.Service;

import com.example.music_mp3.Data.DTO.FavoitesDto;
import com.example.music_mp3.Data.Entity.AccountsEntity;
import com.example.music_mp3.Data.Entity.FavoritesEntity;
import com.example.music_mp3.Data.Entity.SongsEntity;
import com.example.music_mp3.Data.Model.FavoritesM;
import jakarta.servlet.http.HttpSession;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface FavoritesService {
    List<FavoritesM> findFavoritesByUserEmail(String email) throws SQLException;

    byte createFavorites(FavoitesDto favoitesDto) throws SQLException;
}