package DAO;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class ListenMusic {
    static int repeat = 0;
    Connection con;
    Clip clip;

    public int songPlayFunction(Connection con, int id) throws UnsupportedAudioFileException, IOException, LineUnavailableException, SQLException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);

        if (repeat == 0) {
            System.out.println("enter the song Id to play the song");

        }

        String query = "select Song_path from song where SongId=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        String song_path = "";

        while (rs.next()) {
            song_path = rs.getString(1);

        }
        File file = new File(song_path);
        AudioInputStream audiostream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audiostream);
        clip.start();

        String response = "";
        long duration = clip.getMicrosecondLength();
        duration = clip.getMicrosecondLength();
        long finalDuration = (duration / 1000000) * 1000;      //converting into microsecond
        final Timer t = new Timer(1000, new ActionListener() {
            private long time = finalDuration; //10 seconds, for example

            public void actionPerformed(ActionEvent e) {
                if (time >= 0) {
                    long s = ((time / 1000) % 60);   //converting into sec
                    long m = (((time / 1000) / 60) % 60); //converting into min
                    long h = ((((time / 1000) / 60) / 60) % 60);   //converting into hour
                    System.out.print("\r" + h + " hours, " + m + " minutes " + s + " seconds");//it moves the cursor to the beginning of the line
                    time -= 1000;
                }
            }
        });
        t.start();
        while (!response.equals("C")) {
            System.out.println("P=Play\n,S=Stop\n,R=Reset\n,N=Next song\n,L=Previous song\n,F=Forward\n,B=Backward\n,C=Close\n");
            System.out.println("enter your choice: ");
            response = scan.next();
            response = response.toUpperCase();

            try {
                switch (response) {
                    case ("P"):
                        clip.start();
                        System.out.println("song play");
                        break;
                    case ("S"):
                        clip.stop();
                        System.out.println("song stop");
                        t.stop();
                        break;
                    case ("R"):
                        t.stop();
                        clip.setMicrosecondPosition(0);
                        System.out.println("song restart");
                        break;
                    case ("N"):
                        t.stop();
                        id++;
                        repeat++;
                        clip.stop();
                        songPlayFunction(con, id);
                        System.out.println(" next song play");

                        break;
                    case ("L"):
                        t.stop();
                        id--;
                        repeat++;
                        clip.stop();
                        songPlayFunction(con, id);
                        System.out.println(" previous song play");

                        break;
                    case ("F"):
                        clip.setMicrosecondPosition(clip.getMicrosecondPosition() + 10000000); // 10sec
                        break;
                    case ("B"):
                        clip.setMicrosecondPosition(clip.getMicrosecondPosition() - 10000000);
                        break;
                    case ("C"):
                        t.stop();
                        clip.stop();
                        System.out.println("Thanks for playing ");
                        break;

                    default:
                        System.out.println("not a valid response");

                }
            } catch (FileNotFoundException fe) {
                fe.printStackTrace();
            }
        }
        return id;
    }

    public void playlistPlayFuncation(Connection con) throws UnsupportedAudioFileException, IOException, LineUnavailableException, SQLException, ClassNotFoundException {
//        Scanner scanner=new Scanner(System.in);
        try {
            Scanner scan = new Scanner(System.in);
            String songName = "";
            if (repeat == 0) {
                System.out.println("enter the Song name. to play");
                songName = scan.nextLine();
            }
            String query = "select Song_path from song where Song_name = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, songName);
            ResultSet rs = ps.executeQuery();
            String song1_path = "";
            while (rs.next()) {
                song1_path = rs.getString(1);
            }
            File file = new File(song1_path);
            AudioInputStream audiostream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audiostream);
            clip.start();
            String response = "";
            long duration = clip.getMicrosecondLength();
            duration = clip.getMicrosecondLength();
            long finalDuration = (duration / 1000000) * 1000;//converting into microsecond
            final Timer t = new Timer(1000, new ActionListener() {   //block lambda
                private long time = finalDuration; //10 seconds, for example

                public void actionPerformed(ActionEvent e) {
                    if (time >= 0) {
                        long s = ((time / 1000) % 60);   //converting into sec
                        long m = (((time / 1000) / 60) % 60); //converting into min
                        long h = ((((time / 1000) / 60) / 60) % 60);   //converting into hour
                        System.out.print("\r" + h + " hours, " + m + " minutes " + s + " seconds");
                        time -= 1000;
                    }
                }
            });
            t.start();

            while (!response.equals("C")) {
                try {
                    System.out.println("P=Play\n,S=Stop\n,R=Reset\n,N=Next song\n,L=Previous song\n,F=Forward \n,B=Backward\n,C=Close\n");
                    System.out.println("enter your choice: ");
                    response = scan.next();
                    response = response.toUpperCase();

                    switch (response) {
                        case ("P"):
                            clip.start();
                            System.out.println("Song play");
                            break;
                        case ("S"):
                            clip.stop();
                            System.out.println("Song stop");
                            t.stop();
                            break;
                        case ("R"):
                            t.stop();
                            clip.setMicrosecondPosition(0);
                            System.out.println("Song restart");

                            break;
                        case ("N"):
                            t.stop();
                            repeat++;
                            clip.stop();
                            playlistPlayFuncation(con);
                            System.out.println(" next Song to play");
                            break;
                        case ("L"):
                            t.stop();
                            repeat++;
                            clip.stop();
                            playlistPlayFuncation(con);
                            System.out.println(" previous song to play");
                            break;
                        case ("F"):
                            clip.setMicrosecondPosition(clip.getMicrosecondPosition() + 10000000); // 10sec
                            break;
                        case ("B"):
                            clip.setMicrosecondPosition(clip.getMicrosecondPosition() - 10000000);//-10sec
                            break;
                        case ("C"):
                            t.stop();
                            clip.close();
                            System.out.println("Thanks for playing ");
                            break;
                        default:
                            System.out.println("Invalid Response");

                    }
                } catch (FileNotFoundException fe) {
                    fe.printStackTrace();
                }

            }
            System.out.println("Thanks for playing ");
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        }

    }
}