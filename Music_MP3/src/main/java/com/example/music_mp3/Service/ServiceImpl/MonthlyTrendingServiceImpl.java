package com.example.music_mp3.Service.ServiceImpl;

import com.example.music_mp3.Data.Entity.MonthlyTrendingEntity;
import com.example.music_mp3.Data.Entity.SongsEntity;
import com.example.music_mp3.Data.Model.MonthlyTrendingM;
import com.example.music_mp3.Repository.MonthlyTrendingRepo;
import com.example.music_mp3.Repository.SongRepo;
import com.example.music_mp3.Repository.TrendingRepo;
import com.example.music_mp3.Service.MonthlyTrendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthlyTrendingServiceImpl implements MonthlyTrendingService {
    @Autowired
    private MonthlyTrendingRepo monthlyTrendingRepo;
    @Autowired
    private SongRepo songRepo;

    @Autowired
    private TrendingRepo trendingRepo;
    @Override
    public List<MonthlyTrendingM> getMonthlyTrending() {
        return MonthlyTrendingM.convertListSongEToMonthlyTrendingM(monthlyTrendingRepo.findTrending());
    }

    @Override
    public boolean updateListenCount(int songId) {
        SongsEntity songsEntity = songRepo.findById(songId);
        if (songsEntity != null) {
            List<MonthlyTrendingEntity> monthlyTrendingList = songsEntity.getMonthlyTrending();
            for (MonthlyTrendingEntity monthlyTrendingEntity : monthlyTrendingList) {
                int currentViews = monthlyTrendingEntity.getMonthly_Views();
                int updatedViews = currentViews + 1;
                monthlyTrendingEntity.setMonthly_Views(updatedViews);
            }
            songRepo.save(songsEntity);
            return true;
        } else {
            return false;
        }
    }



}
