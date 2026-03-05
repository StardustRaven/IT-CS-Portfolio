import java.util.Scanner;

public class PlaylistDemo {
    public static void main(String[] args) {
        PlaylistManager playlist = new PlaylistManager();
        Scanner sc = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("\n--- Doubly Linked Playlist ---");
            System.out.println("1) Add song");
            System.out.println("2) Show forward");
            System.out.println("3) Show backward");
            System.out.println("4) Import Playlist");
            System.out.println("5) Save Playlist");
            System.out.println("6) Quit");
            System.out.print("Choose: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    System.out.print("Song title: ");
                    String title = sc.nextLine();

                    System.out.print("Artist name: ");
                    String artistName = sc.nextLine();

                    Artist artist = new Artist(artistName);
                    Song song = new Song(title, artist);

                    playlist.addLast(song);
                    System.out.println("Added: " + song);
                }
                case "2" -> playlist.displayForward();
                case "3" -> playlist.displayBackward();
                case "4" -> playlist.loadFromFile("playlist.txt");
                case "5" -> playlist.saveToFile("playlist.txt");
                case "6" -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }

        sc.close();
        System.out.println("Bye!");
    }
}