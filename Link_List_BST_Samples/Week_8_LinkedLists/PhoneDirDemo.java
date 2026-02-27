import java.util.Scanner;

public class PhoneDirDemo {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        ManagerClass dir = new ManagerClass();
        dir.loadFromFile("Addresses.txt");

        boolean running = true;
        while (running) {
            System.out.println("\nPHONE DIRECTORY");
            System.out.println("1) Add Entry");
            System.out.println("2) Find Entry");
            System.out.println("3) Delete Entry");
            System.out.println("4) Print All");
            System.out.println("5) Quit");
            System.out.print("Choose: ");

            int choice = Integer.parseInt(kb.nextLine().trim());

            switch (choice) {
                case 1 -> {
                    System.out.print("First name: ");
                    String f = kb.nextLine();
                    System.out.print("Last name: ");
                    String l = kb.nextLine();
                    System.out.print("Address: ");
                    String a = kb.nextLine();
                    System.out.print("City: ");
                    String c = kb.nextLine();
                    System.out.print("Phone number: ");
                    String p = kb.nextLine();

                    dir.addFirst(f, l, a, c, p);
                    System.out.println("Added.");
                }
                case 2 -> {
                    System.out.print("First name: ");
                    String f = kb.nextLine();
                    System.out.print("Last name: ");
                    String l = kb.nextLine();

                    ObjectClass found = dir.find(f, l);
                    if (found != null) System.out.println("FOUND: " + found);
                    else System.out.println("Not found.");
                }
                case 3 -> {
                    System.out.print("First name: ");
                    String f = kb.nextLine();
                    System.out.print("Last name: ");
                    String l = kb.nextLine();

                    boolean ok = dir.delete(f, l);
                    System.out.println(ok ? "Deleted." : "Not found (nothing deleted).");
                }
                case 4 -> dir.printAll();
                
                case 5 -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }

        kb.close();
        System.out.println("Bye.");
    }
}