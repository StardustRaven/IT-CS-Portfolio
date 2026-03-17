/*
 * File: MainDemo.java
 * Author: Star Isakson
 * Course: CS II
 * Assignment: Binary Search Trees / AVL Tree Extra Credit
 * Date: 3/11/2026
 *
 * Description:
 * Demonstrates recursive BST, iterative BST, and AVL tree operations
 * including load, insert, search, traversal, delete, and save.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MainDemo {

    // =========================
    // MAIN PROGRAM
    // =========================
    // Runs the menu-driven tree demo
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        BST tree = new BST();
        BSTLoop loopTree = new BSTLoop();
        AVLTree avlTree = new AVLTree();

        loadFromFile("database.txt", tree, loopTree, avlTree);

        int choice;

        do {
            System.out.println("\n===== BST / AVL Menu =====");
            System.out.println("1. Add record");
            System.out.println("2. Remove record");
            System.out.println("3. Lookup record");
            System.out.println("4. Preorder traversal");
            System.out.println("5. Inorder traversal");
            System.out.println("6. Postorder traversal");
            System.out.println("7. Save database");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            choice = input.nextInt();
            input.nextLine();

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
                    avlTree.insert(id, firstName, lastName, address, phone);

                    System.out.println("Record added to all trees.");
                    break;

                case 2:
                    System.out.print("Enter ID to remove: ");
                    int deleteId = input.nextInt();
                    input.nextLine();

                    tree.delete(deleteId);
                    loopTree.delete(deleteId);

                    System.out.println("Delete completed in recursive and loop trees.");
                    System.out.println("AVL delete not implemented.");
                    break;

                case 3:
                    System.out.print("Enter ID to search: ");
                    int searchId = input.nextInt();
                    input.nextLine();

                    PersonRecord found = tree.search(searchId);
                    PersonRecLoop foundLoop = loopTree.search(searchId);
                    AVLNode foundAVL = avlTree.search(searchId);

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

                    System.out.println("\nAVL search result:");
                    if (foundAVL != null) {
                        System.out.println(foundAVL);
                    } else {
                        System.out.println("Record not found.");
                    }
                    break;

                case 4:
                    System.out.println("\nRecursive Preorder Traversal:");
                    tree.preorder();

                    System.out.println("\nLoop Preorder Traversal:");
                    loopTree.preorder();

                    System.out.println("\nAVL Preorder Traversal:");
                    avlTree.preorder();
                    break;

                case 5:
                    System.out.println("\nRecursive Inorder Traversal:");
                    tree.inorder();

                    System.out.println("\nLoop Inorder Traversal:");
                    loopTree.inorder();

                    System.out.println("\nAVL Inorder Traversal:");
                    avlTree.inorder();
                    break;

                case 6:
                    System.out.println("\nRecursive Postorder Traversal:");
                    tree.postorder();

                    System.out.println("\nLoop Postorder Traversal:");
                    loopTree.postorder();

                    System.out.println("\nAVL Postorder Traversal:");
                    System.out.println("Not implemented for AVL.");
                    break;

                case 7:
                    tree.saveToFile("database_recursive.txt");
                    loopTree.saveToFile("database_loop.txt");
                    avlTree.saveToFile("database_avl.txt");
                    System.out.println("Recursive, loop, and AVL trees saved successfully.");
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

    // =========================
    // LOAD FROM FILE
    // =========================
    // Loads records from a text file into all three tree types
    public static void loadFromFile(String filename, BST tree, BSTLoop loopTree, AVLTree avlTree) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String firstName = parts[1];
                String lastName = parts[2];
                String address = parts[3];
                String phone = parts[4];

                tree.insert(id, firstName, lastName, address, phone);
                loopTree.insert(id, firstName, lastName, address, phone);
                avlTree.insert(id, firstName, lastName, address, phone);
            }

            System.out.println("Database loaded from " + filename);

            System.out.println("\nLoaded records:");
            tree.inorder();

        } catch (IOException e) {
            System.out.println("No database file found. Starting empty.");
        }
    }
}