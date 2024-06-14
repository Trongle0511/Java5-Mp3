package com.example.music_mp3.Data.Model;

import com.example.music_mp3.Data.Entity.SongsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonthlyTrendingM {
    private int songId;
    private String song_name;
    private String image;
    private String artists_name;
    private String audio_file;
    private int monthlyViews;

    public static MonthlyTrendingM convertSongEntityToMonthlyTrendingM(SongsEntity songsEntity){
        int monthlyViews = songsEntity.getMonthlyTrending().isEmpty() ? 0 : songsEntity.getMonthlyTrending().get(0).getMonthly_Views();
        String artistsName = songsEntity.getArtist() != null ? songsEntity.getArtist().getArtists_name() : "No name :)))";
        return  MonthlyTrendingM.builder()
                .songId(songsEntity.getId())
                .song_name(songsEntity.getSong_name())
                .image(songsEntity.getImage())
                .audio_file(songsEntity.getAudio_file())
                .artists_name(artistsName)
                .monthlyViews(monthlyViews)
                .build();
    }
    public static List<MonthlyTrendingM> convertListSongEToMonthlyTrendingM(List<SongsEntity> listSongsE) {
        return listSongsE.stream()
                .map(MonthlyTrendingM::convertSongEntityToMonthlyTrendingM)
                .collect(Collectors.toList());
    }
}
