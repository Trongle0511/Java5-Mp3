package com.example.music_mp3.Api;

import com.example.music_mp3.Data.DTO.ArtistDTO;
import com.example.music_mp3.Data.DTO.SongDTO;
import com.example.music_mp3.Data.Entity.SongsEntity;
import com.example.music_mp3.Service.ArtistService;
import com.example.music_mp3.Service.SongService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/artist")
@Slf4j
public class ArtistAPI {
    @Autowired
    private ArtistService artistService;

    @Autowired
    private SongService songService;

    @GetMapping("/findAllArtist")
    public ResponseEntity<Map<String, Object>> findAllArtist() {
        Map<String, Object> response = new HashMap<>();
        List<ArtistDTO> artists = artistService.findAll();

        if (!artists.isEmpty()) {
            response.put("status", "success");
            response.put("data", artists);
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "No artists found");
            return ResponseEntity.status(204).body(response);
        }
    }

    @GetMapping("/detail/{artistId}")
    public ResponseEntity<Object> getArtistDetail(@PathVariable int artistId) {
        Map<String, Object> response = new HashMap<>();
        Optional<ArtistDTO> artist = artistService.findById(artistId);

        if (artist.isPresent()) {
            response.put("status", "success");
            response.put("data", artist.get());
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Artist not found");
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping("/byArtist/{artistId}")
    public ResponseEntity<List<SongDTO>> getSongsByArtistId(@PathVariable int artistId) {
        List<SongDTO> songs = songService.findSongsByArtistId(artistId);
        if (songs.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(songs);
        }
    }

//    @PostMapping("/findById")
//    public ResponseEntity<Object> findById(@RequestBody Integer artistId) {
//        Map<String, Object> response = new HashMap<>();
//
//        if (artistId != null) {
//            Optional<ArtistDTO> artist = artistService.findById(artistId);
//            if (artist.isPresent()) {
//                response.put("status", "success");
//                response.put("data", artist.get());
//                return ResponseEntity.ok(response);
//            } else {
//                response.put("status", "error");
//                response.put("message", "Artist not found");
//                return ResponseEntity.status(204).body(response);
//            }
//        } else {
//            response.put("status", "error");
//            response.put("message", "Missing artistId in request body");
//            return ResponseEntity.status(400).body(response);
//        }
//    }

    @GetMapping("/findById/{artistId}")
    public ResponseEntity<Object> findById(@PathVariable Integer artistId) {
        Map<String, Object> response = new HashMap<>();

        if (artistId != null) {
            Optional<ArtistDTO> artist = artistService.findById(artistId);
            if (artist.isPresent()) {
                response.put("status", "success");
                response.put("data", artist.get());
                return ResponseEntity.ok(response);
            } else {
                response.put("status", "error");
                response.put("message", "Artist not found");
                return ResponseEntity.status(204).body(response);
            }
        } else {
            response.put("status", "error");
            response.put("message", "Missing artistId in URL");
            return ResponseEntity.status(400).body(response);
        }
    }



}
