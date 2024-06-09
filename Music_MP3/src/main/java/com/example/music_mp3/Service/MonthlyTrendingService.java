package com.example.music_mp3.Service;

import com.example.music_mp3.Data.Model.MonthlyTrendingM;

import java.util.List;

public interface MonthlyTrendingService {
    List<MonthlyTrendingM> getMonthlyTrending();
    boolean updateListenCount(int songId);
}
