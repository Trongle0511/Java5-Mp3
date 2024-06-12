
use DuAnMP3_Music
go
-- delete from Userinfo
-- go
-- delete from Account
-- go

-- Insert data into the Users table
-- Inserting data into Account table
INSERT INTO Account (username, email, hashed_password, role) VALUES 
('johndoe', 'abc@gmail.com', '$2a$12$NtnhefK8AlaqnheWo0VruObg3LVe.W45MhJupHLulRjR7rNdP5tKu', 0),
('janedoe', 'janedoe@gmail.com', '$2a$12$NtnhefK8AlaqnheWo0VruObg3LVe.W45MhJupHLulRjR7rNdP5tKu', 0),
('adminuser', 'admin@gmail.com', '$2a$12$NtnhefK8AlaqnheWo0VruObg3LVe.W45MhJupHLulRjR7rNdP5tKu', 1);

-- Inserting data into Users table
INSERT INTO Users (name, phone, image, account_id) VALUES
('John Doe', '123-456-7890', 'john_image.png', 1),
('Jane Doe', '987-654-3210', 'jane_image.png', 2),
('Admin User', '555-555-5555', 'admin_image.png', 3);
select * from Users;



-- Chèn dữ liệu vào bảng Artists
INSERT INTO Artists (artists_name, artists_image, artists_description) VALUES ('MCK', 'MCKey.jpg', N'RPT MCK tên thật là Nghiêm Vũ Hoàng Long, anh chàng là một Rapper khá nổi tiếng. RPT MCK được biết nhiều đến sau khi tham gia chương trình Rap Việt. Phong cách rap melody ấn tượng, đầy kỹ năng, vừa mang tính giải trí. Đặc biệt là khả năng Autotune live đã được Wowy khen là “Thiên Tài”. Anh còn được gọi với 2 biệt danh là “Ngher” và “Ngơ”.');
INSERT INTO Artists (artists_name, artists_image, artists_description) VALUES (N'Phương Ly Official', 'PhuongLy.jpg', N'Trần Phương Ly (sinh ngày 28 tháng 10 năm 1990), thường được biết đến với nghệ danh Phương Ly, là một nữ ca sĩ người Việt Nam. Cô bắt đầu nổi danh với vai trò là một nhân vật nổi tiếng trên mạng xã hội, sau đó hoạt động với tư cách là một nghệ sĩ âm nhạc và nhanh chóng nhận về những thành công lớn.');
INSERT INTO Artists (artists_name, artists_image, artists_description) VALUES ('VSOUL', 'VSOUL.jpg', N'VSoul (tên thật là Ngô Cao Hoàng Việt, sinh ngày 24/12/1996 tại Sóc Trăng), là nam rapper trẻ người Việt Nam.');
INSERT INTO Artists (artists_name, artists_image, artists_description) VALUES (N'Sơn Tùng MTP', 'SonTungMTP.jpg', N'Nguyễn Thanh Tùng hay được biết đến với nghệ danh Sơn Tùng M-TP (sinh năm 1994, Thái Bình) là một ca sĩ nhạc pop Việt Nam. Sơn Tùng từng đoạt hai giải Bài hát yêu thích với các ca khúc "Cơn mưa ngang qua" và "Em của ngày hôm qua".');
INSERT INTO Artists (artists_name, artists_image, artists_description) VALUES ('HIEUTHUHAI', 'HIEUTHUHAI.jpg', N'Hieuthuhai nổi bật với nhiều bản hit viral, được khán giả yêu thích và lên xu hướng của nhiều nền tảng mạng xã hội như "Chơi", "Bật nhạc lên"... Anh được nhiều rapper gạo cội nhận định là một trong những nhân tố trẻ nổi bật nhất của dòng nhạc Rap');
INSERT INTO Artists (artists_name, artists_image, artists_description) VALUES ('JustaTeeMusic', 'JayTee.jpg', N'JustaTee Tên thật của JustaTee: Nguyễn Thanh Tuấn Ngày sinh của JustaTee: 01/11/1991 Chiều cao của JustaTee: 1m66 Quê quán: Hà Nội Cung hoàng đạo: Bọ Cạp (Scorpio) Bài hát nổi bật: EZ Papa (2023), Forget about her (2022), Em không lẻ loi (2022),...');
select * from Artists where artistid = 1;

-- Chèn dữ liệu vào bảng Genres
INSERT INTO Genres (genres_name) VALUES ('Rock');
INSERT INTO Genres (genres_name) VALUES ('Pop');
INSERT INTO Genres (genres_name) VALUES ('Jazz');

-- Chèn dữ liệu vào bảng Album
-- Giả sử các ArtistID là 1, 2, 3 tương ứng với Artist One, Artist Two và Artist Three
INSERT INTO Album (album_name) VALUES ('Album One');
INSERT INTO Album (album_name) VALUES ('Album Two');
INSERT INTO Album (album_name) VALUES ('Album Three');
INSERT INTO Album (album_name) VALUES ('Album Four');
INSERT INTO Album (album_name) VALUES ('Album Five');
INSERT INTO Album (album_name) VALUES ('Album Six');
INSERT INTO Album (album_name) VALUES ('Album Seven');
INSERT INTO Album (album_name) VALUES ('Album Eight');
INSERT INTO Album (album_name) VALUES ('Album Nine');
INSERT INTO Album (album_name) VALUES ('Album Ten');

-- Inserting records into the Songs table with NULL values for foreign keys
INSERT INTO Songs (song_name, Image, audio_file, albumid, artistid, genreid)
VALUES 
(N'Va Vào Giai Điệu Này', 'VaVaoGiaiDieuNay.jpg', 'VaVaoGiaiDieuNay-MCK.mp3', 1, 1, 1), --MCK 1
(N'Đìu Anh Luôn Giữ Kín Trong Tim', 'DiuAnhLuonGiuKinTrongTim.jpg', 'DiuAnhLuonGiuKinTrongTim-MCK.mp3', 1, 1, 1), --MCK 1
(N'Em Là Châu Báu', 'EmLaChauBau.jpg', 'EmLaChauBau-MCK.mp3', 1, 1, 1), --MCK 1
(N'Tại Vì Sao', 'TaiViSao.jpg', 'TaiViSao-MCK.mp3', 1, 1, 1), --MCK 1
(N'Chìm Sâu', 'ChimSau.jpg', 'ChimSau-MCK.mp3', 1, 1, 1), --MCK 1
(N'Anh Là Ai?', 'AnhLaAi.jpg', 'AnhLaAi-PhuongLy.mp3', 1, 2, 1), --PhuongLy 2
(N'Anh Là Ngoại Lệ Của Em', 'AnhLaNgoaiLeCuaEm.jpg', 'AnhLaNgoaiLeCuaEm-PhuongLy.mp3', 1, 2, 1), --PhuongLy 2
(N'Mặt Trời Của Em', 'MatTroiCuaEm.jpg', 'MatTroiCuaEm-PhuongLy.mp3', 1, 2, 1), --PhuongLy 2
(N'Missing U', 'MissingYou.jpg', 'MissingU-PhuongLy.mp3', 1, 2, 1), --PhuongLy 2
(N'THICHTHICH', 'THICHTHICH.jpg', 'THICHTHICH-PhuongLy.mp3', 1, 2, 1), --PhuongLy 2
(N'Buồn Hay Vui', 'BuonHayVui.jpg', 'BuonHayVuiFeatRptMckObitoRonboogz-VSOULRPTMCKObitoRonboogz-13159599.mp3', 1, 3, 1), --Vsoul 3
(N'Ngtanoise', 'Ngtanoise.jpg', 'NGTANOISE-VSOUL.mp3', 1, 3, 1), --Vsoul 3
(N'Cột Mốc Thời Gian', 'CotMocThoiGian.jpg', 'CotMocThoiGian-VSOUL.mp3', 1, 3, 1), --Vsoul 3
(N'Shawty', 'Shawty.jpg', 'SHAWTY-VSOUL.mp3', 1, 3, 1), --Vsoul 3
(N'Tầng Tầng Lớp Love', 'TangTangLopLove.jpg', 'TangTangLopLop-VSOUL.mp3', 1, 3, 1), --Vsoul 3
(N'Chúng Ta Của Hiện Tại', 'ChungTaCuaHienTai.jpg', 'ChungTaCuaHienTai-SonTungMTP-6892340.mp3', 1, 4, 2), --SonTungMTP 4
(N'Âm Thầm Bên Em', 'AmThamBenEm.jpg', 'AmThamBenEm-SonTungMTP.mp3', 1, 4, 2), --SonTungMTP 4
(N'Hãy Trao Cho Anh', 'HayTraoChoAnh.jpg', 'HayTraoChoAnh-SonTungMTP.mp3', 1, 4, 2), --SonTungMTP 4
(N'Muộn Rồi Mà Sao Còn', 'MuonRoiMaSaoCon.jpg', 'MuonRoiMaSaoCon-SonTungMTP.mp3', 1, 4, 2), --SonTungMTP 4
(N'Nơi Này Có Anh', 'NoiNayCoAnh.jpg', 'NoiNayCoAnh-SonTungMTP.mp3', 1, 4, 2), --SonTungMTP 4
(N'-237 độ C', '237.jpg', '237-HIEUTHUHAI.mp3', 1, 5, 2), --HIEUTHUHAI 5
(N'5050', '5050.jpg', '5050-HIEUTHUHAI.mp3', 1, 5, 2), --HIEUTHUHAI 5
(N'CUA', 'CUA.jpg', 'CUA-HIEUTHUHAI.mp3', 1, 5, 2), --HIEUTHUHAI 5
(N'Vệ Tinh', 'VeTinh.jpg', 'VETINH-HIEUTHUHAI.mp3', 1, 5, 2), --HIEUTHUHAI 5
(N'Xoay Một Vòng', 'XoayMotVong.jpg', 'XoayMotVong-HIEUTHUHAI.mp3', 1, 5, 2), --HIEUTHUHAI 5
(N'Thằng Điên', 'ThangDien.jpg', 'ThangDien-JayTee.mp3', 1, 6, 1), --JayTee 6
(N'Đã Lỡ Yêu Em Nhiều', 'DaLoYeuEmNhieu.jpg', 'DaLoYeuEmNhiu-JayTee.mp3', 1, 6, 1), --JayTee 6
(N'Đi Về Nhà', 'DiVeNha.jpg', 'DiVeNha-JayTee.mp3', 1, 6, 1), --JayTee 6
(N'Làm Gì Mà Phải Hốt', 'LamGiMaPhaiHot.jpg', 'LamGiMaPhaiHot-JayTee.mp3', 1, 6, 1), --JayTee 6
(N'Dân Chơi Xóm', 'DanChoiXom.jpg', 'DanChoiXom-JayTee.mp3', 1, 6, 1) --JayTee 6
GO

select * from Songs where artistid = 1

select * from Songs

INSERT INTO MonthlyTrending (songid, monthly_views) VALUES 
(1, 0),
(2, 0),
(3, 0),
(4, 0),
(5, 0),
(6, 0),
(7, 0),
(8, 0),
(9, 0),
(10, 0),
(11, 0),
(12, 0),
(13, 0),
(14, 0),
(15, 0),
(16, 0),
(17, 0),
(18, 0),
(19, 0),
(20, 0),
(21, 0),
(22, 0),
(23, 0),
(24, 0),
(25, 0),
(26, 0),
(27, 0),
(28, 0),
(29, 0),
(30, 0);
GO