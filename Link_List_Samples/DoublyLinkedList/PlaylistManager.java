/*
 * PlaylistManager.java
 * Author: Star Isakson
 * Course: CS II
 * Assignment: Doubly Linked List Playlist
 * Date: March 2026
 *
 * Description:
 * Manages a playlist implemented as a doubly linked list.
 * Provides functionality to add songs, display the playlist
 * forward and backward, load songs from a file, save the
 * playlist to a file, and track the total number of songs
 * in the playlist.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

public class PlaylistManager {
    private SongNode head;
    private SongNode tail;
    private int count = 0;

    public int getCount() {
        return count;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void loadFromFile(String filename) {

        try (Scanner file = new Scanner(new File(filename))) {

            while (file.hasNextLine()) {

                String title = file.nextLine().trim();
                if (title.isEmpty())
                    continue;

                String artist = file.nextLine().trim();

                Artist artistObj = new Artist(artist);
                Song song = new Song(title, artistObj);
                addLast(song);

                // consume blank separator line if present
                if (file.hasNextLine()) {
                    file.nextLine();
                }
            }

            System.out.println("Playlist loaded from: " + filename);

        } catch (FileNotFoundException e) {
            System.out.println("Could not load file: " + filename);
        }
    }

    public void addLast(Song song) {
        SongNode node = new SongNode(song);
        if (isEmpty()) {
            head = tail = node;
        } else {
            tail.setNext(node);
            node.setPrev(tail);
            tail = node;
        }
        count++;
    }

    public void displayForward() {
        if (isEmpty()) {
            System.out.println("(playlist empty)");
            return;
        }

        System.out.println("\nPlaylist contains " + count + " tracks:\n");

        SongNode cur = head;
        int i = 1;
        while (cur != null) {
            System.out.println(i + ". " + cur.getData());
            cur = cur.getNext();
            i++;
        }
    }

    public void displayBackward() {
        if (isEmpty()) {
            System.out.println("(playlist empty)");
            return;
        }

        System.out.println("\nPlaylist contains " + count + " tracks:\n");

        SongNode cur = tail;
        int i = getCount();

        while (cur != null) {
            System.out.println(i + ". " + cur.getData());
            cur = cur.getPrev();
            i--;
        }
    }

    public void saveToFile(String filename) {

        File file = new File(filename);
        Scanner sc = new Scanner(System.in);

        if (file.exists()) {
            System.out.print("File already exists. Overwrite? (y/n): ");
            String answer = sc.nextLine().trim().toLowerCase();

            if (!answer.equals("y")) {
                System.out.println("Save cancelled.");
                return;
            }
        }

        try (PrintWriter out = new PrintWriter(filename)) {

            SongNode current = head;
            while (current != null) {
                Song s = current.getData();
                out.println(s.getTitle());
                out.println(s.getArtist().getName());
                out.println();
                current = current.getNext();
            }

            System.out.println("Playlist saved to: " + filename);

        } catch (FileNotFoundException e) {
            System.out.println("Could not save file: " + filename);
        }
    }
}