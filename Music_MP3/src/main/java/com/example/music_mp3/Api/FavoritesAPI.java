package com.example.music_mp3.Api;

import com.example.music_mp3.Data.DTO.FavoitesDto;
import com.example.music_mp3.Data.Entity.AccountsEntity;
import com.example.music_mp3.Data.Entity.FavoritesEntity;
import com.example.music_mp3.Data.Entity.SongsEntity;
import com.example.music_mp3.Data.Model.FavoritesM;
import com.example.music_mp3.Service.FavoritesService;
import com.example.music_mp3.Service.ServiceImpl.FavoritesServiceImpl;
import com.example.music_mp3.Service.ServiceImpl.SongServiceImpl;
import com.example.music_mp3.Service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.music_mp3.Data.Variable.StaticVariable.sessionEmail;

@RestController
//@RequestMapping("/favorites")
public class FavoritesAPI {

    @Autowired
    private FavoritesService favoritesService;

    @PostMapping("/addFavorites")
    public ResponseEntity<?> doPostCreateComment(@RequestBody FavoitesDto favoitesDto) {
        byte rowEffected;
        try {
            rowEffected = favoritesService.createFavorites(favoitesDto);
        } catch (Exception e) {
            System.out.println("Call API Failed: /addFavorites");
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(rowEffected);
    }

    // lấy danh sách
    @GetMapping("/favorites")
    @ResponseBody
    public ResponseEntity<?> getFavorites(HttpSession session) throws SQLException {
        if (sessionEmail == null) {
            throw new IllegalStateException("User is not logged in");
        }
        List<FavoritesM> favoritesMList;
        try {
            favoritesMList = favoritesService.findFavoritesByUserEmail(sessionEmail);
        }catch (SQLException e){

            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(favoritesMList);
    }

}

