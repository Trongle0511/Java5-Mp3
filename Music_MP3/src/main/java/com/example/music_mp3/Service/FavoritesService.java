package com.example.music_mp3.Service;

import com.example.music_mp3.Data.DTO.FavoitesDto;

import java.sql.SQLException;

public interface FavoritesService {

    byte createFavorites(FavoitesDto favoitesDto) throws SQLException;
    byte deleteFavorite(int userId, int songId) throws SQLException;  // Add this method


}
