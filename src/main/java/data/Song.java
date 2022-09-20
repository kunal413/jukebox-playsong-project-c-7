package data;

import java.sql.Time;

public class Song {


    private int songId;
    private String song_name;
    private String genres;
    private String artist_name;
    private String Song_path;
    private Time songDuration;

    public Song() {
    }

    public Song(int songId, String song_name, String genres, String artist_name, String album_name, Time songDuration) {
        this.songId = songId;
        this.song_name = song_name;
        this.genres = genres;
        this.artist_name = artist_name;
        this.Song_path = album_name;
        this.songDuration = songDuration;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getSong_path() {
        return Song_path;
    }

    public void setSong_path(String song_path) {
        this.Song_path = song_path;
    }

    public Time getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(Time songDuration) {
        this.songDuration = songDuration;
    }

    @Override
    public String toString() {
        return "SongDetails{" + "songId=" + songId + ", song_name='" + song_name +
                '\'' + ", genres='" + genres + '\'' + ", artist_name='" + artist_name + '\''
                + ", album_name='" + Song_path + '\'' + ", songDuration=" + songDuration + '}';
    }
}


