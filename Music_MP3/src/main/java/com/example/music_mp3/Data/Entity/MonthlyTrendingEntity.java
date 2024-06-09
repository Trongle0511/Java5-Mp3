package com.example.music_mp3.Data.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "monthlytrending")
public class MonthlyTrendingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "monthly_trendingid")
    private Integer monthly_Trendingid;

    @Column(name = "monthly_views")
    private Integer monthly_Views;

    @ManyToOne
    @JoinColumn(name = "songid", nullable = false)
    private SongsEntity song;
}
