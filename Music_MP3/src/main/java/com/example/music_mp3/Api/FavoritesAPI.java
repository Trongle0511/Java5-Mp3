package com.example.music_mp3.Api;

import com.example.music_mp3.Data.DTO.FavoitesDto;
import com.example.music_mp3.Data.Entity.AccountsEntity;
import com.example.music_mp3.Data.Entity.FavoritesEntity;
import com.example.music_mp3.Data.Entity.SongsEntity;
import com.example.music_mp3.Data.Model.FavoritesM;
import com.example.music_mp3.Data.Model.SongM;
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
import java.util.*;

import static com.example.music_mp3.Data.Variable.StaticVariable.sessionEmail;

@RestController
//@RequestMapping("/favorites")
public class FavoritesAPI {

    @Autowired
    private FavoritesService favoritesService;

    @Autowired
    private SongServiceImpl songService;


//    @PostMapping("/create-favorites")
//    public ResponseEntity<?> doPostCreateComment(@RequestBody FavoitesDto favoitesDto) {
//        byte rowEffected;
//        try {
//            rowEffected = favoritesService.createFavorites(favoitesDto);
//        } catch (Exception e) {
//            System.out.println("Call API Failed: /createComment");
//            throw new RuntimeException(e);
//        }
//        return ResponseEntity.ok(rowEffected);
//    }

    @PostMapping("/create-favorites")
    public ResponseEntity<?> doPostCreateFavorites(@RequestBody FavoitesDto favoitesDto, HttpSession session) {
        if (sessionEmail == null) {
            throw new IllegalStateException("User is not logged in");
        }
        byte rowEffected;
        try {
            // Gọi service để tạo mới mục yêu thích
            rowEffected = favoritesService.createFavorites(favoitesDto);
        } catch (Exception e) {
            System.out.println("Error occurred while creating favorites");
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(rowEffected); // Trả về số hàng bị ảnh hưởng (thành công)
    }


    // lấy danh sách
    @GetMapping("/favorites")
    @ResponseBody
    public ResponseEntity<?> getFavorites(HttpSession session) throws SQLException {
        if (sessionEmail == null) {
            throw new IllegalStateException("User is not logged in");
        }
        List<SongM> songMS;
        try {
            songMS = songService.findSongsEntityByUserEmailByUserEmail(sessionEmail);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(songMS);
    }

    @DeleteMapping("/delete-favorites")
    public ResponseEntity<String> deleteFavorite(@RequestParam int userId, @RequestParam int songId) {
        try {
            byte result = favoritesService.deleteFavorite(userId, songId);
            if (result == 1) {
                return ResponseEntity.ok("Xóa bài hát khỏi danh sách yêu thích thành công.");
            } else {
                return ResponseEntity.status(400).body("Xóa không thành công. Vui lòng kiểm tra lại.");
            }
        } catch (SQLException e) {
            return ResponseEntity.status(500).body("Lỗi hệ thống: " + e.getMessage());
        }
    }


}

