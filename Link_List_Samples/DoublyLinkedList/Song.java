/*
 * Song.java
 * Author: Star Isakson
 * Course: CS II
 * Assignment: Doubly Linked List Playlist
 * Date: March 2026
 *
 * Description:
 * Represents a song in the playlist. This class stores
 * the song title and the associated Artist object.
 * Song objects are stored inside SongNode objects
 * within the doubly linked list managed by PlaylistManager.
 */

public class Song {
    private String title;
    private Artist artist;   // keeps artist info structured
    private int durationSec; // optional; use 0 if unknown

    public Song(String title, Artist artist) {
        this(title, artist, 0);
    }

    public Song(String title, Artist artist, int durationSec) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Song title cannot be blank.");
        }
        this.title = title.trim();
        this.artist = artist; // can be null if you want to allow "unknown"
        this.durationSec = Math.max(0, durationSec);
    }

    public String getTitle() {
        return title;
    }

    public Artist getArtist() {
        return artist;
    }

    public int getDurationSec() {
        return durationSec;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) return;
        this.title = title.trim();
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setDurationSec(int durationSec) {
        this.durationSec = Math.max(0, durationSec);
    }

    public String getDurationFormatted() {
        if (durationSec <= 0) return "--:--";
        int m = durationSec / 60;
        int s = durationSec % 60;
        return m + ":" + (s < 10 ? "0" : "") + s;
    }

    @Override
    public String toString() {
        String artistName = (artist == null) ? "Unknown Artist" : artist.getName();
        return String.format("%s | %s", title, artistName);
    }
}