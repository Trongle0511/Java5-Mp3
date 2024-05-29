package com.example.music_mp3.Api;

import com.example.music_mp3.Data.DTO.SongDTO;
import com.example.music_mp3.Data.Entity.SongsEntity;
import com.example.music_mp3.Data.Model.SongM;
import com.example.music_mp3.Service.ServiceImpl.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api-public/song")
public class SongApi {
    @Autowired
    private SongServiceImpl songServiceImpl;

    @GetMapping("/findSongId")
    public ResponseEntity<?> findSongId(@RequestParam("songId") int songId) {
        SongM songM;
        try {
            SongDTO songDTO = new SongDTO(); // Tạo một đối tượng VoucherDto và đặt voucherID vào đó
            songDTO.setSongID(songId);
            songM = songServiceImpl.findById(songDTO);
        } catch (Exception e) {
            System.out.println("Gọi API thất bại: /api-public/song/findSongId");
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(songM);

    }
    @GetMapping("/findAllSongs")
    public ResponseEntity<Map<String, Object>> findAllSongs() {
        Map<String, Object> response = new HashMap<>();
        List<SongDTO> songs = songServiceImpl.findAll();

        if (!songs.isEmpty()) {
            response.put("status", "success");
            response.put("data", songs);
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "No songs found");
            return ResponseEntity.status(204).body(response);
        }
    }
}
