/*
 * Name: Star Isakson
 * Course: CS II
 * Assignment: Doubly Linked List Playlist
 * Date: March 2026
 *
 * Description:
 * This program implements a playlist using a doubly linked list.
 * Songs can be loaded from a file, displayed forward or backward,
 * added manually, and saved back to a file. The program also
 * tracks the number of songs in the playlist.
 */


import java.util.Scanner;

public class PlaylistDemo {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        PlaylistManager playlist = new PlaylistManager();

        int choice;

        do {
            System.out.println("\n===== PLAYLIST MENU =====");
            System.out.println("1. Load playlist from file");
            System.out.println("2. Show playlist (forward)");
            System.out.println("3. Show playlist (backward)");
            System.out.println("4. Add song");
            System.out.println("5. Save playlist");
            System.out.println("0. Quit");
            System.out.print("Choice: ");

            choice = input.nextInt();
            input.nextLine(); // eat leftover newline

            switch (choice) {

                case 1:
                    System.out.print("Enter filename: ");
                    String loadFile = input.nextLine();
                    playlist.loadFromFile(loadFile);
                    break;

                case 2:
                    playlist.displayForward();
                    break;

                case 3:
                    playlist.displayBackward();
                    break;

                case 4:
                    System.out.print("Song title: ");
                    String title = input.nextLine();

                    System.out.print("Artist: ");
                    String artist = input.nextLine();

                    Artist artistObj = new Artist(artist);
                    Song song = new Song(title, artistObj);

                    playlist.addLast(song);
                    System.out.println("Song added.");
                    break;

                case 5:
                    System.out.print("Enter filename to save: ");
                    String saveFile = input.nextLine();
                    playlist.saveToFile(saveFile);
                    break;

                case 0:
                    System.out.println("Peace Out Fam.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        input.close();
    }
}