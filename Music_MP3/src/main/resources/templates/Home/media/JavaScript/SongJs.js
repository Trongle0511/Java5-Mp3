
    const fetchAllSongs = async () => {
    try {
    const response = await axios.get('http://localhost:8083/findAllSongs');
    const songs = response.data;
    const songContainer = document.getElementById('song-container');
    songContainer.innerHTML = '';

    songs.forEach(song => {
    const card = document.createElement('div');
    card.className = 'card';

    card.innerHTML = `
                        <div>
                            <!-- image corresponding to the card -->
                            <img src="media/songs/${song.image}" alt="${song.song_name}"">
                            <!-- play button, which will be shown on hover on the card image -->
                            <div class="play-button">
                                <i class="fas fa-play" style="width:100%; height:100%; display:inline-block"></i>
                            </div>
                        </div>
                        <!-- it will contain the name and date of release of the song -->
                        <div class="song-description">
                            <h3>
                                Closer
                            </h3>
                            <p>
                                Aug 20, 2020
                            </p>
                        </div>
                        <!-- if someone clicks on the three dots, options will be shown to the user for further action -->
                        <div class="options">
                            <label for="latest-release-checkbox"><i class="fas fa-ellipsis-h"></i></label>
                            <input type="checkbox" id="latest-release-checkbox">
                            <div class="latest-release-dropdown">
                                <div class="drop-item">
                                    <!-- if user clicks on play now button, he/she will be taken to the single playlist page -->
                                    <p><i class="fas fa-play-circle"></i> <a href="SinglePlaylistScreen.html">Play
                                            Now</a></p>
                                </div>
                                <hr>
                                <div class="drop-item">
                                    <p><i class="fas fa-list-ul"></i> Add to Queue</p>
                                </div>
                                <hr>
                                <div class="drop-item">
                                    <p><i class="fas fa-music"></i> Add to playlist</p>
                                </div>
                                <hr>
                                <div class="drop-item">
                                    <p><i class="fas fa-info-circle"></i> Get Info</p>
                                </div>
                            </div>
                            <!-- duration of the song -->
                            <p>
                                4:44
                            </p>
                        </div>
                    `;

    songContainer.appendChild(card);
});
} catch (error) {
    console.error('Error fetching songs:', error);
}
};

    window.onload = fetchAllSongs;
