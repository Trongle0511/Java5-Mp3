package com.example.music_mp3.Api;

import com.example.music_mp3.Data.DTO.SongAndArtistInsertDto;
import com.example.music_mp3.Data.DTO.SongDTO;
import com.example.music_mp3.Data.Entity.SongsEntity;
import com.example.music_mp3.Data.Model.SongInsertM;
import com.example.music_mp3.Data.Model.SongM;
import com.example.music_mp3.Service.ServiceImpl.SongServiceImpl;
import com.example.music_mp3.Service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api-public/song")
public class SongApi {
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    @Autowired
    private SongServiceImpl songServiceImpl;

    @Autowired
    private SongService songService;

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

    @PostMapping("/addSongAndArtist")
    public ResponseEntity<?> addSongAndArtist(@RequestParam("song_name") String songName,
                                              @RequestParam("image") MultipartFile image,
                                              @RequestParam("audio_file") MultipartFile audioFile,
                                              @RequestParam("artist_name") String artistName) {

        String imagePath = null;
        if (image != null && !image.isEmpty()) {
            try {
                Path path = CURRENT_FOLDER.resolve("src\\main\\resources\\templates\\Home\\library\\image").resolve(image.getOriginalFilename());
                Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                imagePath = image.getOriginalFilename();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to store image", e);
            }
        }

        String audioPath = null;
        if (audioFile != null && !audioFile.isEmpty()) {
            try {
                Path path = CURRENT_FOLDER.resolve("src\\main\\resources\\templates\\Home\\library\\Music").resolve(audioFile.getOriginalFilename());
                Files.copy(audioFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                audioPath = audioFile.getOriginalFilename();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to store audio file", e);
            }
        }

        SongAndArtistInsertDto songAndArtistInsertDto = new SongAndArtistInsertDto();
        songAndArtistInsertDto.setSong_name(songName);
        songAndArtistInsertDto.setArtist_name(artistName);
        songAndArtistInsertDto.setImage(imagePath);
        songAndArtistInsertDto.setAudio_file(audioPath);

        Map<String, Object> result = new HashMap<>();
        try {
            SongsEntity savedSong = songService.saveSongAndArtist(songAndArtistInsertDto);
            result.put("status", true);
            result.put("message", "Call API Insert Song and Artist success !");
            result.put("data", savedSong);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call API Insert Song and Artist false !");
            result.put("data", null);
            return ResponseEntity.status(500).body(result);
        }
    }

    @GetMapping("/show-song")
    public ResponseEntity<?> showSong() {
        List<SongInsertM> song = songService.findAllSongInsertM();
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Call API success !");
            result.put("data", song);
            return ResponseEntity.ok(result);
        }catch(Exception e) {
            result.put("status", false);
            result.put("message", "Call API false !");
            result.put("data", null);
            return ResponseEntity.status(500).body(result);
        }

    }

}
