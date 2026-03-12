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

public class MainDemo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        BST tree = new BST();
        BSTLoop loopTree = new BSTLoop();

        // Sample data for testing
        tree.insert(1005, "Ashwhisker", "Thistlewick", "Market Row", "555-1005", "MARKET");
        tree.insert(1002, "Tanner", "Ravenshield", "Hearth Wing", "555-1002", "WATCH");
        tree.insert(1008, "Elyria", "Willowborne", "Grove Path", "555-1008", "GROVE");
        tree.insert(1001, "Star", "Ravenshield", "Hearth Wing", "555-1001", "WHEEL");
        tree.insert(1003, "Seris", "Silverthorn", "Spindle Gate", "555-1003", "WATCH");

        loopTree.insert(1005, "Ashwhisker", "Thistlewick", "Market Row", "555-1005", "MARKET");
        loopTree.insert(1002, "Tanner", "Ravenshield", "Hearth Wing", "555-1002", "WATCH");
        loopTree.insert(1008, "Elyria", "Willowborne", "Grove Path", "555-1008", "GROVE");
        loopTree.insert(1001, "Star", "Ravenshield", "Hearth Wing", "555-1001", "WHEEL");
        loopTree.insert(1003, "Seris", "Silverthorn", "Spindle Gate", "555-1003", "WATCH");

        int choice;

        do {
            System.out.println("\n===== Binary Search Tree Menu =====");
            System.out.println("1. Add record");
            System.out.println("2. Remove record (recursive only for now)");
            System.out.println("3. Lookup record");
            System.out.println("4. Preorder traversal");
            System.out.println("5. Inorder traversal");
            System.out.println("6. Postorder traversal");
            System.out.println("7. Exit");
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

                    System.out.print("Enter group ID: ");
                    String groupId = input.nextLine();

                    tree.insert(id, firstName, lastName, address, phone, groupId);
                    loopTree.insert(id, firstName, lastName, address, phone, groupId);

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
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 7);

        input.close();
    }
}