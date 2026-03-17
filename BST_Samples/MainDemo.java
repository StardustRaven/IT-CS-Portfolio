/*
 * File: MainDemo.java
 * Author: Star Isakson
 * Course: CS II
 * Assignment: Binary Search Trees
 * Date: 3/11/2026
 *
 * Description:
 *
 */

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainDemo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        BST tree = new BST();
        BSTLoop loopTree = new BSTLoop();

        loadFromFile("database.txt", tree, loopTree);

        int choice;

        do {
            System.out.println("\n===== Binary Search Tree Menu =====");
            System.out.println("1. Add record");
            System.out.println("2. Remove record (recursive only for now)");
            System.out.println("3. Lookup record");
            System.out.println("4. Preorder traversal");
            System.out.println("5. Inorder traversal");
            System.out.println("6. Postorder traversal");
            System.out.println("7. Save database");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            choice = input.nextInt();
            input.nextLine(); // clear newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = input.nextInt();
                    input.nextLine();

                    System.out.print("Enter first name: ");
                    String firstName = input.nextLine();

                    System.out.print("Enter last name: ");
                    String lastName = input.nextLine();

                    System.out.print("Enter address: ");
                    String address = input.nextLine();

                    System.out.print("Enter phone: ");
                    String phone = input.nextLine();

                    tree.insert(id, firstName, lastName, address, phone);
                    loopTree.insert(id, firstName, lastName, address, phone);

                    System.out.println("Record added to both trees.");
                    break;

                case 2:
                    System.out.print("Enter ID to remove: ");
                    int deleteId = input.nextInt();
                    input.nextLine();

                    tree.delete(deleteId);
                    System.out.println("Delete completed in recursive tree.");
                    System.out.println("Loop delete not added yet.");
                    break;

                case 3:
                    System.out.print("Enter ID to search: ");
                    int searchId = input.nextInt();
                    input.nextLine();

                    PersonRecord found = tree.search(searchId);
                    PersonRecLoop foundLoop = loopTree.search(searchId);

                    System.out.println("\nRecursive search result:");
                    if (found != null) {
                        System.out.println(found);
                    } else {
                        System.out.println("Record not found.");
                    }

                    System.out.println("\nLoop search result:");
                    if (foundLoop != null) {
                        System.out.println(foundLoop);
                    } else {
                        System.out.println("Record not found.");
                    }
                    break;

                case 4:
                    System.out.println("\nRecursive Preorder Traversal:");
                    tree.preorder();

                    System.out.println("\nLoop Preorder Traversal:");
                    loopTree.preorder();
                    break;

                case 5:
                    System.out.println("\nRecursive Inorder Traversal:");
                    tree.inorder();

                    System.out.println("\nLoop Inorder Traversal:");
                    loopTree.inorder();
                    break;

                case 6:
                    System.out.println("\nRecursive Postorder Traversal:");
                    tree.postorder();

                    System.out.println("\nLoop Postorder Traversal:");
                    loopTree.postorder();
                    break;

                case 7:
                    System.out.println("Saving file to: " + new java.io.File("database.txt").getAbsolutePath());
                    tree.saveToFile("database.txt");
                    break;

                case 8:
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 8);

        input.close();
    }

    public static void loadFromFile(String filename, BST tree, BSTLoop loopTree) {

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty())
                    continue;

                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String firstName = parts[1];
                String lastName = parts[2];
                String address = parts[3];
                String phone = parts[4];

                tree.insert(id, firstName, lastName, address, phone);
                loopTree.insert(id, firstName, lastName, address, phone);
            }

            System.out.println("Database loaded from " + filename);

            // quick visual check
            System.out.println("\nLoaded records:");
            tree.inorder();

        } catch (IOException e) {

            System.out.println("No database file found. Starting empty.");
        }
    }
}