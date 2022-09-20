package data.main;

import connection.DBConnection;
import data.Playlist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlaylistDAOTest {
    PlaylistDAO playlistDAO = null;
    Playlist playlist = null;
    Connection connection = null;

    @BeforeEach
    void setUp() {
        playlistDAO = new PlaylistDAO();
        playlist = new Playlist();
        connection = DBConnection.getConnection();
    }

    @AfterEach
    void tearDown() {
        playlistDAO = null;
        playlist = null;

    }

    @Test
    void createPlaylistTable() throws SQLException {
        assertEquals(0, playlistDAO.createPlaylistTable(connection, "playlistTable"), "check table method ");

    }

    @Test
    void insertTheValueInTheTable() throws SQLException {
        Playlist playlist1 = new Playlist(1, "pasoori");
        assertEquals(1, playlistDAO.insertTheValueInTheTable(connection, "playlistTable", playlist1));
    }

    @Test
    void showPlaylistSongs() throws SQLException {
        Playlist playlist1 = new Playlist(1, "pasoori");
        List<Playlist> pl = new ArrayList<>();
        pl.add(playlist1);
        assertEquals("[Playlist{playlistId=1, playlistName='pasoori'}]", playlistDAO.showPlaylistSongs(pl, "playlistTable"));
    }
}