package DAO;

import connection.DBConnection;
import data.Playlist;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class PlaylistDAO {
    Playlist playlist = new Playlist();
    Scanner sc = new Scanner(System.in);
    String playlistName;


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


    public void showPlaylistSongs(String playlistName) throws SQLException {
        Connection con = DBConnection.getConnection();
        Statement statement = con.createStatement();
        String sql = "SELECT * FROM jukebox." + playlistName;
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.format("%-10d%-30s", resultSet.getInt(1), resultSet.getString(2));
            System.out.println();
        }
    }

    public void insertingSong() throws UnsupportedAudioFileException, SQLException, LineUnavailableException, IOException, ClassNotFoundException {
        System.out.println("Enter the Creating playlist name");
        playlistName = sc.nextLine();
        createPlaylistTable(DBConnection.getConnection(), playlistName);
        System.out.println("press 1: insert the song in the table");
        System.out.println("press 2 exits");
        int choice3 = sc.nextInt();
        sc.nextLine();
        switch (choice3) {
            case 1:
                addSong();
                System.out.println(" press :1 Insert More song ");
                System.out.println(" press :2 show all playlist song name ");
                System.out.println(" press :3 play song from playlist");
                System.out.println(" press :4 Exit");
                int choice4 = sc.nextInt();
                sc.nextLine();
                switch (choice4) {
                    case 1:
                        addSong();
                        break;
                    case 2:
                        showPlaylistSongs(playlistName);
                        break;
                    case 3:
                        ListenMusic listenMusic = new ListenMusic();
                        listenMusic.playlistPlayFuncation(DBConnection.getConnection());
                        break;
                }


                break;
        }
    }

    public void addSong() throws SQLException {
        System.out.println("Enter Playlist Song name");
        String songName = sc.nextLine();
        System.out.println("enter the playlist Id");
        int playlistId = sc.nextInt();
        Playlist playlist = new Playlist(playlistId, songName);
        insertTheValueInTheTable(DBConnection.getConnection(), playlistName, playlist);
    }
}