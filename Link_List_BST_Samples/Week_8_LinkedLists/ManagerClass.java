import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class ManagerClass {
    private ObjectClass head;

    public ManagerClass() {
        head = null;
    }

    // Add to front (fast + simple)
    public void addFirst(String fName, String lName, String address, String city, String pNumber) {
        ObjectClass newNode = new ObjectClass(fName, lName, address, city, pNumber);
        newNode.next = head;
        head = newNode;
    }

    // Search by first + last name
    public ObjectClass find(String fName, String lName) {
        String key = (lName + "," + fName).toLowerCase();
        ObjectClass current = head;

        while (current != null) {
            if (current.fullNameKey().equals(key)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // Delete by first + last name (handles deleting head too)
    public boolean delete(String fName, String lName) {
        if (head == null)
            return false;

        String key = (lName + "," + fName).toLowerCase();

        // if the head is the one to delete
        if (head.fullNameKey().equals(key)) {
            head = head.next;
            return true;
        }

        ObjectClass prev = head;
        ObjectClass current = head.next;

        while (current != null) {
            if (current.fullNameKey().equals(key)) {
                prev.next = current.next;
                return true;
            }
            prev = current;
            current = current.next;
        }

        return false;
    }

    public void printAll() {
        if (head == null) {
            System.out.println("(Directory is empty)");
            return;
        }

        ObjectClass current = head;
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
    }

    public void loadFromFile(String filename) {
        try (Scanner file = new Scanner(new File(filename))) {

            while (file.hasNextLine()) {
                String nameLine = file.nextLine().trim();
                if (nameLine.isEmpty())
                    continue;

                String[] parts = nameLine.split("\\s+");
                String fName = parts[0];
                String lName = (parts.length > 1)
                        ? String.join(" ", Arrays.copyOfRange(parts, 1, parts.length))
                        : "";

                if (!file.hasNextLine())
                    break;
                String address = file.nextLine().trim();

                if (!file.hasNextLine())
                    break;
                String city = file.nextLine().trim();

                if (!file.hasNextLine())
                    break;
                String pNumber = file.nextLine().trim();

                addFirst(fName, lName, address, city, pNumber);

        // Consume one optional blank separator line if present (but don't swallow the next name)
        if (file.hasNextLine()) {
       
        String sep = file.nextLine();
        if (!sep.trim().isEmpty()) {
            
            System.out.println("Warning: expected blank separator, got: " + sep);
        }
    }   
} 
    System.out.println("Loaded directory from: " + filename);

} catch (FileNotFoundException e) {
    System.out.println("Could not load file: " + filename);
}
}
}