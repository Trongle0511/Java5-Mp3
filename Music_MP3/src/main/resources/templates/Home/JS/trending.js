document.addEventListener('DOMContentLoaded', function() {
    axios.get('/trending')
        .then(function(response) {
            if (response.data.status) {
                const songs = response.data.data;
                const playlistContent = document.querySelector('.playlist-content');
                let htmlContent = '';

                songs.forEach((song, index) => {
                    htmlContent += `
                        <div class="playlist-item" data-song-id="${song.songId}" data-song-name="${song.song_name}" data-artist-name="${song.artists_name}" data-image="library/image/${song.image}" data-audio-file="library/Music/${song.audio_file}">
                            <div class="left-content">
                                <!-- index -->
                                <div style="margin-right:4px;">
                                    ${(index + 1).toString().padStart(2, '0')}
                                </div>
                                <div class="coverer">
                                    <img src="library/image/${song.image}" alt="Cover Image">
                                    <div class="play-button">
                                        <i class="fas fa-play" aria-hidden="true"></i>
                                    </div>
                                </div>
                                <!-- name and author of the song -->
                                <div>
                                    <div>${song.song_name}</div>
                                    <p class="artist-name">${song.artists_name}</p>
                                    <p class="song-views">${song.monthlyViews} views</p>
                                </div>
                            </div>
                            <!-- like button -->
                            <div class="right-content">
                                <i class="far fa-heart"></i>
                            </div>
                        </div>`;
                });
                playlistContent.innerHTML = htmlContent;
                document.querySelectorAll('.playlist-item').forEach(item => {
                    item.addEventListener('click', function() {
                        const songId = this.getAttribute('data-song-id');
                        const songName = this.getAttribute('data-song-name');
                        const artistName = this.getAttribute('data-artist-name');
                        const image = this.getAttribute('data-image');
                        const audioFile = this.getAttribute('data-audio-file');
                        console.log("Audio file path:", audioFile);
                        document.querySelector('.active-song-description #song-image img').src = image;
                        document.querySelector('.active-song-description .song-desc div:nth-child(1)').textContent = songName;
                        document.querySelector('.active-song-description .song-desc div:nth-child(2)').textContent = artistName;
                        const audioPlayer = document.getElementById('audio-player');
                        if (!audioPlayer) {
                            console.error("Phần tử audio-player không được tìm thấy.");
                            return;
                        }
                        audioPlayer.src = audioFile;
                        audioPlayer.play().then(() => {
                            console.log("Phát nhạc thành công");
                            let isThirtySecondsReached = false;
                            audioPlayer.addEventListener('timeupdate', function() {
                                console.log("Thời gian hiện tại:", audioPlayer.currentTime);
                                if (!isThirtySecondsReached && audioPlayer.currentTime >= 29) {
                                    isThirtySecondsReached = true;
                                    updateListenCount(songId);
                                }
                            });
                        }).catch((error) => {
                            console.error("Lỗi khi phát nhạc:", error);
                        });
                        const playPauseButton = document.getElementById('play-pause-button').querySelector('i');
                        playPauseButton.classList.remove('fa-play-circle');
                        playPauseButton.classList.add('fa-pause-circle');



                    });
                });
            } else {
                console.error("Gọi API thất bại!");
            }
        })
        .catch(function(error) {
            console.error("Có lỗi xảy ra khi gọi API:", error);
        });
});
function updateListenCount(songId) {
    console.log("Đã gọi hàm updateListenCount với songId:", songId);
    axios.post('/update-listen-count', { songId: songId })
        .then(function(response) {
            if (response.data.status) {
                console.log("Cập nhật lượt nghe thành công!");
            } else {
                console.error("Cập nhật lượt nghe thất bại!");
            }
        })
        .catch(function(error) {
            console.error("Có lỗi xảy ra khi cập nhật lượt nghe:", error);
        });
}
