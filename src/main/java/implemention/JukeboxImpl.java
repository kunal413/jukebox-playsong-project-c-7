package implemention;

import DAO.ListenMusic;
import DAO.PlaylistDAO;
import DAO.SongDAO;
import connection.DBConnection;
import data.Playlist;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class JukeboxImpl {
    public static void main(String[] args) throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException, ClassNotFoundException {
        SongDAO songPlaylistMenu = new SongDAO();
        PlaylistDAO playlistDAO = new PlaylistDAO();
        ListenMusic listenMusic = new ListenMusic();

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Jukebox \n choose the option from given blow");
        System.out.println("+++++++++++++++ Display all songs++++++++++++++++++++++++++++++");
        songPlaylistMenu.displayAllSongFromList();
        boolean response = true;
        while (true) {
            System.out.println("Press 1 to Search Song in list ");
            System.out.println("Press 2 to Create Play list ");
            System.out.println("Press 3 to Play a Song");
            System.out.println("press 4 to show song and play song from playlist");
            System.out.println("Press 5 to Exit");
            System.out.println("_______________________________________________________________________");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Press 1 to search Song details by Song Name");
                    System.out.println("press 2 to search Song details by Genre Name");
                    System.out.println("press 3 to search song details by Artist Name");

                    System.out.println("_______________________________________________________________________");
                    int choice1 = sc.nextInt();
                    sc.nextLine();
                    switch (choice1) {
                        case 1:
                            System.out.println("Enter the song Name");
                            String songName = sc.next();
                            songPlaylistMenu.songBySongName(songName);
                            System.out.println("____________________________________________________");
                            break;
                        case 2:
                            System.out.println("Enter the Genre Name");
                            String genre = sc.next();
                            songPlaylistMenu.songByGenreName(genre);
                            System.out.println("____________________________________________________________");
                            break;
                        case 3:
                            System.out.println("Enter the Artist Name");
                            String artistName = sc.next();
                            songPlaylistMenu.SongByArtistName(artistName);
                            System.out.println("______________________________________________________________________");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Enter the Creating playlist name");
                    String playlistName = sc.next();
                    playlistDAO.createPlaylistTable(DBConnection.getConnection(), playlistName);
                    System.out.println("press 1: insert the song name in the table");
                    int choice3 = sc.nextInt();
                    sc.nextLine();
                    switch (choice3) {
                        case 1:
                            boolean input = true;
                            while (input) {
                                System.out.println("Enter Playlist Song name");
                                String songName = sc.nextLine();
                                System.out.println("enter the playlist Id");
                                int playlistId = sc.nextInt();
                                Playlist playlist = new Playlist(playlistId, songName);
                                playlistDAO.insertTheValueInTheTable(DBConnection.getConnection(), playlistName, playlist);

                                System.out.println(" press :1 Insert More song ");
                                System.out.println(" press :2 show all playlist song name ");
                                int choice4 = sc.nextInt();
                                sc.nextLine();
                                switch (choice4) {
                                    case 1:
                                        input = true;
                                        break;
                                    case 2:
                                        System.out.println("++++++++++Show all playlist Song++++++++++++");
                                        playlistDAO.showPlaylistSongs(playlistName);
                                        input = false;
                                        break;
                                    case 4:
                                        input = false;
                                        break;

                                }
                            }
                    }
                    break;
                case 4:
                    System.out.println("+++++++++Song playing+++++++++");
                    listenMusic = new ListenMusic();
                    listenMusic.playlistPlayFuncation(DBConnection.getConnection());
                    break;
                case 3:
                    System.out.println("+++++++++Playing Song+++++++++++");
                    System.out.println("Enter the song Id");
                    int id = sc.nextInt();
                    listenMusic.songPlayFunction(DBConnection.getConnection(), id);
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");

            }
        }
    }
}
