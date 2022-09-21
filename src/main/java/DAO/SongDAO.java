package DAO;

import connection.DBConnection;
import data.Song;

import java.sql.*;
import java.util.Scanner;

public class SongDAO {
    Scanner sc = new Scanner(System.in);
    Song song = new Song();

    public int displayAllSongFromList() throws SQLException {
        int result = 0;
        Connection con = DBConnection.getConnection();
        Statement statement = con.createStatement();
        String sql = "select * from song";
        ResultSet resultSet = statement.executeQuery(sql);//Navigate through records in result set
        System.out.format("%-10s%-50s%-30s%-30s%-40s%-30s", "SongId", "Song Name", "Artist Name", "genres", "Song path", "Song Duration");
        System.out.println();
        while (resultSet.next()) {

//            int id=resultSet.getInt(1);
//            String name=resultSet.getString(2);
//            String artistName=resultSet.getString(3);
//            String genres = resultSet.getString(4);
//            String songPath = resultSet.getString(5);
//            Time duration = resultSet.getTime(6);
//            songs.add(new Song(id,name,artistName,genres,songPath,duration));
            System.out.format("%-10d%-50s%-30s%-30s%-40s%-30s", resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)
                    , resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
            System.out.println();
            result++;

        }
        return result;
    }

    public int SongByArtistName(String artist_name) {
        try {
            int result = 0;
            Connection connection = DBConnection.getConnection();
            String sql = "select * from song where Artist_name like '%" + artist_name + "%'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.format("%-10s%-30s%-30s%-30s%-30s%-30s", "SongId", "Song Name", "Artist Name", "genres", "Song path", "Song Duration");
            System.out.println();
            while (resultSet.next()) {
                System.out.format("%-10d%-30s%-30s%-30s%-30s%-30s", resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)
                        , resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
                System.out.println();
                result++;
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int songByGenreName(String genreName) throws SQLException {
        try {
            int result = 0;
            Connection connection = DBConnection.getConnection();
            String sql1 = "select *from song where Genre like '%" + genreName + "%'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.format("%-10s%-30s%-30s%-30s%-30s%-30s", "SongId", "Song Name", "Artist Name", "genres", "Song path", "Song Duration");
            System.out.println();
            while (resultSet.next()) {
                System.out.format("%-10d%-30s%-30s%-30s%-30s%-30s", resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)
                        , resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
                System.out.println();
                result++;
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int songBySongName(String songName) {
        try {
            int result = 0;
            Connection connection = DBConnection.getConnection();
            String sql2 = "select * from song where song_name like '%" + songName + "%'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql2);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.format("%-10s%-30s%-30s%-30s%-30s%-30s", "SongId", "Song Name", "Artist Name", "genres", "Song path", "Song Duration");
            System.out.println();
            while (resultSet.next()) {
                System.out.format("%-10d%-30s%-30s%-30s%-30s%-30s", resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)
                        , resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
                System.out.println();
                result++;
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

