package data.main;

import connection.DBConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListenMusicTest {
    ListenMusic listenMusic = null;
    Connection connection = null;

    @BeforeEach
    void setUp() {
        listenMusic = new ListenMusic();
        connection = DBConnection.getConnection();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void songPlayFunction() throws UnsupportedAudioFileException, SQLException, LineUnavailableException, IOException, ClassNotFoundException {
        assertEquals(1, listenMusic.songPlayFunction(connection, 1));
    }

}