package com.timmy;

import java.sql.*;

public class Database {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = Pwd.getUser();
    private static final String PASSWORD = Pwd.getPassword();
    private static Connection conn = null;
    private static PreparedStatement psStat = null;
    private static Statement statement = null;
    private static ResultSet rs = null;

    public Database() {

    }

    public static void loadDriver() {

        try {
            Class.forName(JDBC_DRIVER);
        }

        catch (ClassNotFoundException cnfe) {
            System.out.println("Can't instantiate driver class; check you have drivers and classpath configured correctly?");
            cnfe.printStackTrace();
            System.exit(-1);  //No driver? Need to fix before anything else will work. So quit the program
        }

    }

    public void initDB() {

        try {
            conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);

            //Create the database if it doesn't exist
            String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS music_library";
            statement = conn.createStatement();
            statement.executeUpdate(createDatabaseSQL);
            System.out.println("Created music_library table");

            //Use the music_library database
            String useDatabaseSQL = "USE music_library";
            statement.executeUpdate(useDatabaseSQL);

            //Create songs table in the database if it doesn't exist
            String createTableSQL = "CREATE TABLE IF NOT EXISTS songs (title VARCHAR(30), artist VARCHAR(30), album VARCHAR (30))";
            statement.executeUpdate(createTableSQL);
            System.out.println("Created songs table");

            //Create file table in the database if it doesn't exist
            createTableSQL = "CREATE TABLE IF NOT EXISTS file (path VARCHAR(60))";
            statement.executeUpdate(createTableSQL);
            System.out.println("Created file table");

            //Get all of the songs from the file table
            loadMusicLibrary();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void addToSongs() {
        if (Menu.isAdd()) {
            MusicFile mf = Menu.getSelectedFile();
            try {
                String psStatInsert = "INSERT INTO songs VALUES (?,?,?)";
                psStat = conn.prepareStatement(psStatInsert);
                psStat.setString(1, mf.getTitle() );
                psStat.setString(2, mf.getArtist() );
                psStat.setString(3, mf.getAlbum() );
                psStat.executeUpdate();
                System.out.println("Added song: " + mf.getTitle());

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addSongPath() {
        if (Menu.isAdd()) {
            MusicFile mf = Menu.getSelectedFile();
//            String path = mf.getPath();

            try {
                String psStatInsert = "INSERT INTO file VALUES (?)";
                psStat = conn.prepareStatement(psStatInsert);
                psStat.setString(1, mf.getPath());
                psStat.executeUpdate();
                System.out.println("Added: " + mf.getTitle());
                System.out.println("Path: " + mf.getPath());
            }

            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadMusicLibrary() {

        try {

            String fetchAllSongs = "SELECT * FROM file";
            rs = statement.executeQuery(fetchAllSongs);
            while (rs.next()) {
                String path = rs.getString("Path");
                System.out.println("Path: " + path);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getSong() {

        try {

            String fetchAllSongs = "SELECT * FROM songs";
            rs = statement.executeQuery(fetchAllSongs);
            while (rs.next()) {
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                String album = rs.getString("album");
                System.out.println("" + title + "" + artist + "" + album);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
