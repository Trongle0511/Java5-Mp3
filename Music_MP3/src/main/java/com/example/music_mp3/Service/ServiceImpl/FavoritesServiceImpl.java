package com.example.music_mp3.Service.ServiceImpl;

import com.example.music_mp3.Data.DTO.FavoitesDto;
import com.example.music_mp3.Data.Entity.AccountsEntity;
import com.example.music_mp3.Data.Entity.FavoritesEntity;
import com.example.music_mp3.Data.Entity.SongsEntity;
import com.example.music_mp3.Data.Model.FavoritesM;
import com.example.music_mp3.Repository.AccountRepository;
import com.example.music_mp3.Repository.FavoritesRepository;
import com.example.music_mp3.Repository.SongRepo;
import com.example.music_mp3.Service.FavoritesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.music_mp3.Data.Variable.StaticVariable.sessionEmail;

@Service
public class FavoritesServiceImpl implements FavoritesService {

    @Autowired
    private FavoritesRepository favoritesRepo;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SongRepo songRepo;

    @Override
    public byte createFavorites(FavoitesDto favoitesDto) throws SQLException {
        var Song = songRepo.findById(favoitesDto.getSongsEntity().getId());
        System.out.println(sessionEmail);
        if (sessionEmail == null) {
            // Xử lý khi người dùng chưa đăng nhập
            return -1;
        }
        // Kiểm tra nếu chuỗi loggedInUser không rỗng
        if (sessionEmail != null) {
            var user = accountRepository.findByEmail(sessionEmail);
            // Tìm người dùng bằng email
            if (sessionEmail != null) {
                favoritesRepo.insertFavorites(user.getUserId()
                                            ,Song.getId()
                                            ,favoitesDto.getCreatedAt());
                return 1;
            }
        }

        // Xử lý khi không tìm thấy người dùng hoặc có lỗi xảy ra
        return -1;
    }

    @Override
    public byte deleteFavorite(int userId, int songId) throws SQLException {
        try {
            favoritesRepo.deleteFavoriteByUserIdAndSongId(userId, songId);
            return 1;
        } catch (Exception e) {
            // Handle any errors
            e.printStackTrace();
            return -1;
        }
    }


}
