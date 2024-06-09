package com.example.music_mp3.Repository;

import com.example.music_mp3.Data.Entity.SongsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MonthlyTrendingRepo extends JpaRepository<SongsEntity,Integer> {
    @Query("SELECT s FROM SongsEntity s JOIN s.monthlyTrending mt ORDER BY mt.monthly_Views DESC")
    List<SongsEntity> findTrending();
}
