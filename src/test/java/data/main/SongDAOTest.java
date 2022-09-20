package data.main;

import data.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SongDAOTest {
    SongDAOTest songDAOTest = null;
    SongDAO songDAO = null;
    Song song = null;
    Connection connection = null;

    @BeforeEach
    void setUp() {
        songDAOTest = new SongDAOTest();
        songDAO = new SongDAO();
        song = new Song();
    }


    @Test
    void displayAllSongFromList() throws SQLException {
        assertEquals(7, songDAO.displayAllSongFromList());
    }

    @Test
    void songByArtistName() throws SQLException {
        assertEquals(2, songDAO.SongByArtistName("ramesh"), "wrong number found");
    }

    @Test
    void songByGenreName() throws SQLException {
        assertEquals(4, songDAO.songByGenreName("pop"));
    }

    @Test
    void songBySongName() throws SQLException {
        assertEquals(1, songDAO.songBySongName("pasoori"));
    }
}