package com.example.music_mp3.Api;

import com.example.music_mp3.Data.Entity.SongsEntity;
import com.example.music_mp3.Data.Model.MonthlyTrendingM;
import com.example.music_mp3.Service.MonthlyTrendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MonthlyTrendingApi {
    @Autowired
    MonthlyTrendingService monthlyTrendingService;

    @GetMapping("/trending")
    public ResponseEntity<?> getTrending() {
        List<MonthlyTrendingM> monthlyTrending = monthlyTrendingService.getMonthlyTrending();
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Call Api success !");
            result.put("data", monthlyTrending);
        }catch (Exception e){
            result.put("status", false);
            result.put("message", "Call Api fail !");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }


    @PostMapping("/update-listen-count")
    public ResponseEntity<?> updateListenCount(@RequestBody Map<String, Integer> payload) {
        int songId = payload.get("songId");
        boolean success = monthlyTrendingService.updateListenCount(songId);
        Map<String, Object> result = new HashMap<>();
        if (success) {
            result.put("status", true);
            result.put("message", "Cập nhật lượt nghe thành công!");
        } else {
            result.put("status", false);
            result.put("message", "Cập nhật lượt nghe thất bại!");
        }
        return ResponseEntity.ok(result);
    }

}
