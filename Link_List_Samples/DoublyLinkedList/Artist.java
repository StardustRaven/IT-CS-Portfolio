/*
 * Artist.java
 * Author: Star [Last Name]
 * Course: CS II
 * Date: March 2026
 *
 * Description:
 * Represents a musical artist. This class stores
 * the artist's name and is associated with Song
 * objects in the playlist.
 */

public class Artist {
    private String name;

    public Artist(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Artist name cannot be blank.");
        }
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) return;
        this.name = name.trim();
    }

    @Override
    public String toString() {
        return name;
    }
}