package com.example.music_mp3.Data.DTO;

import com.example.music_mp3.Data.Entity.AccountsEntity;
import com.example.music_mp3.utils.PasswordEncoderUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {
    private String username;
    private String email;
    private String hashedPassword ;
    private Boolean role;

    public static AccountsEntity convertAccountDTOToAccountsEntity(AccountDTO accountDTO) {
        return AccountsEntity.builder()
                .username(accountDTO.getUsername())
                .email(accountDTO.getEmail())
                .hashedPassword(PasswordEncoderUtil.encodePassword(accountDTO.getHashedPassword()))
                .role(accountDTO.getRole())
                .build();
    }
}
