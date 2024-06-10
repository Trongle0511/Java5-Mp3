package com.example.music_mp3.Repository;

import com.example.music_mp3.Data.Entity.MonthlyTrendingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrendingRepo extends JpaRepository<MonthlyTrendingEntity,Integer> {
    @Query("SELECT mt FROM MonthlyTrendingEntity mt WHERE mt.song.SongID = :songId")
    MonthlyTrendingEntity findBySongId(@Param("songId") int songId);
}
