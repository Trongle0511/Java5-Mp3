package com.example.music_mp3.Data.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePassworDto {
    private String hashedPassword;
    private String newPasswordOne;
    private String newPasswordTwo;

}
