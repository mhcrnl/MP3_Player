package com.timmy;

import java.io.File;
import java.util.Map;

import javazoom.spi.mpeg.sampled.file.*;

import javax.sound.sampled.AudioFormat;

public class MusicFile {

    private String artist;
    private String album;
    private String title;
    private String path;
    private File name;

    public MusicFile(String path, File fileName) {
        this.path = path;
        this.name = fileName;
    }

//    public MusicFile(String artist, String album, String title) {
//        this.artist = artist;
//        this.album = album;
//        this.title = title;
//    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public File getName() {
        return name;
    }

    public void setName(File name) {
        this.name = name;
    }
}
