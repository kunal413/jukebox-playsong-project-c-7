package data.main;

import connection.DBConnection;
import data.Playlist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {
    Playlist playlist = new Playlist();

    public int createPlaylistTable(Connection con, String playlistName) throws SQLException {
        String sql = "create table " + playlistName + " (PlaylistId int primary key auto_increment,PlaylistSongName varchar(100))";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        int i = preparedStatement.executeUpdate();
        return i;
    }

    public int insertTheValueInTheTable(Connection con, String playlistName, Playlist pl) throws SQLException {
        int rows = 0;
        String sql = "insert into " + playlistName + " values(?,?)";
        PreparedStatement preparedStatement = con.prepareStatement
                (sql, Statement.RETURN_GENERATED_KEYS);
        //set the values for placeholders
        //setter provided by prepared statement, using the type of data and index
        preparedStatement.setInt(1, pl.getPlaylistId());
        preparedStatement.setString(2, pl.getPlaylistName());
        rows = preparedStatement.executeUpdate();
        int generatedId = 0;
        if (rows == 1) {
            //get generated id
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
            }
        }
        return generatedId;
    }


    public List<Playlist> showPlaylistSongs(List<Playlist> playlists, String playlistName) throws SQLException {
        List<Playlist> playlists1 = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        Statement statement = con.createStatement();
        String sql = "SELECT * FROM jukebox." + playlistName;
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int playlistId = resultSet.getInt(1);
            String name = resultSet.getString(2);
            Playlist pl = new Playlist(playlistId, name);
            playlists1.add(pl);
        }
        return playlists1;
    }
}
