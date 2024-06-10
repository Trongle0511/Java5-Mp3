USE master;
GO

DROP DATABASE IF EXISTS DuAnMP3_Music;
GO

CREATE DATABASE DuAnMP3_Music;
GO

USE DuAnMP3_Music;
GO
-- Bảng Account
CREATE TABLE Account (
    user_id INT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(50) NOT NULL UNIQUE,
    email NVARCHAR(100) NOT NULL UNIQUE,
    hashed_password VARCHAR(255) NOT NULL,
    role BIT

);
-- Bảng User
CREATE TABLE Users (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(100) NOT NULL,
    phone NVARCHAR(15) NOT NULL,
    image NVARCHAR(255),
	account_id INT UNIQUE, -- Thiết lập cột account_ID là duy nhất
    FOREIGN KEY (account_ID) REFERENCES Account(user_id)
--	account_ID int null foreign key references Account(user_Id)
);
-- Bảng Nghệ sĩ (Artists)
CREATE TABLE Artists (
    artistid INT IDENTITY(1,1) PRIMARY KEY,
    artists_name NVARCHAR(100) NOT NULL,
	artists_image NVARCHAR(255),
	artists_description NVARCHAR(MAX)
);

-- Bảng Album
CREATE TABLE Album (
    albumid INT IDENTITY(1,1) PRIMARY KEY,
    album_name NVARCHAR(100) NOT NULL,
    --artistid INT,
    --FOREIGN KEY (artistid) REFERENCES Artists(artistid)
);

-- Bảng Thể loại (Genres)
CREATE TABLE Genres (
    genreid INT IDENTITY(1,1) PRIMARY KEY,
    genres_name NVARCHAR(50) NOT NULL
);

-- Bảng Bài hát (Songs)
CREATE TABLE Songs (
    songid INT IDENTITY(1,1) PRIMARY KEY,
    song_name NVARCHAR(100) NOT NULL,
    Image NVARCHAR(255) NOT NULL,
    audio_file NVARCHAR(255) NOT NULL,
    albumid INT NULL,
    artistid INT NULL,
    genreid INT NULL,
    FOREIGN KEY (albumid) REFERENCES Album(albumid),
    FOREIGN KEY (artistid) REFERENCES Artists(artistid),
    FOREIGN KEY (genreid) REFERENCES Genres(genreid)
);

-- Bảng Đánh giá bài hát (SongRatings)
CREATE TABLE SongRatings (
    songid INT,
    Views INT NOT NULL,
    user_id INT,
    FOREIGN KEY (songid) REFERENCES Songs(songid),
    FOREIGN KEY (user_id) REFERENCES Account(user_id),
    PRIMARY KEY (songid, user_id)
);

-- Bảng Playlist
CREATE TABLE Playlist (
    playlistid INT IDENTITY(1,1) PRIMARY KEY,
    playlist_name NVARCHAR(100) NOT NULL,
    description TEXT,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES Account(user_id)
);

-- Bảng Chi tiết bài hát trong playlist (PlaylistSongs)
CREATE TABLE PlaylistSongs (
    playlistid INT,
    songid INT,
    FOREIGN KEY (playlistid) REFERENCES Playlist(playlistid),
    FOREIGN KEY (songid) REFERENCES Songs(songid),
    PRIMARY KEY (playlistid, songid)
);
select * from Songs

GO
DROP TRIGGER trg_CreateUserOnAccountInsert;

GO
CREATE TRIGGER trg_CreateUserOnAccountInsert
ON Account
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    -- Insert dữ liệu vào bảng Users từ bảng Account
    INSERT INTO Users (name, phone, account_id)
    SELECT 
        CASE WHEN i.username IS NOT NULL THEN ' ' ELSE u.name END,
        CASE WHEN i.username IS NOT NULL THEN ' ' ELSE u.phone END,
        i.user_id
    FROM inserted i
    LEFT JOIN Account a ON i.user_id = a.user_id
    LEFT JOIN Users u ON a.user_id = u.account_id;
END;
