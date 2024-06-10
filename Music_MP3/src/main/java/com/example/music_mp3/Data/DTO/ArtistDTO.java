package com.example.music_mp3.Data.DTO;

import com.example.music_mp3.Data.Entity.ArtistsEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistDTO {
    private int artistid;
    private String artists_name;
    private String artists_image;
    private String artists_description;

    public static ArtistsEntity convertArtistDTOToArtistEntity(ArtistDTO artistDTO) {
        return ArtistsEntity.builder()
                .artistid(artistDTO.getArtistid())
                .artists_name(artistDTO.getArtists_name())
                .artists_image(artistDTO.getArtists_image())
                .artists_description(artistDTO.getArtists_description())
                .build();
    }
}
