
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



-- Chèn dữ liệu vào bảng Artists
INSERT INTO Artists (artists_name) VALUES ('Artist One');
INSERT INTO Artists (artists_name) VALUES ('Artist Two');
INSERT INTO Artists (artists_name) VALUES ('Artist Three');

-- Chèn dữ liệu vào bảng Genres
INSERT INTO Genres (genres_name) VALUES ('Rock');
INSERT INTO Genres (genres_name) VALUES ('Pop');
INSERT INTO Genres (genres_name) VALUES ('Jazz');

-- Chèn dữ liệu vào bảng Album
-- Giả sử các ArtistID là 1, 2, 3 tương ứng với Artist One, Artist Two và Artist Three
INSERT INTO Album (album_name) VALUES ('Album One');
INSERT INTO Album (album_name) VALUES ('Album Two');
INSERT INTO Album (album_name) VALUES ('Album Three');

-- Inserting records into the Songs table with NULL values for foreign keys
INSERT INTO Songs (song_name, Image, audio_file, albumid, artistid, genreid)
VALUES 
(N'Ánh sao và bầu trời', 'AnhSaoVaBauTroi.jpg', 'AnhSaoVaBauTroi-TRI-7085073.mp3', 1, 1, 1),
(N'Từng Quen', 'TungQuen.jpg', 'Tungquen.mp3', 1, 1, 1),
(N'Buồn Hay Vui', 'BuonHayVui.jpg', 'BuonHayVuiFeatRptMckObitoRonboogz-VSOULRPTMCKObitoRonboogz-13159599.mp3', 1, 1, 1),
(N'Chàng Trai Bất Tử', 'ChangTraiBatTu.jpg', 'ChangTraiBatTu-AnVuSino-13594520.mp3', 1, 1, 3),
(N'Chúng Ta Của Hiện Tại', 'ChungTaCuaHienTai.jpg', 'ChungTaCuaHienTai-SonTungMTP-6892340.mp3', 1, 1, 2);
select * from Songs