/*
 * File:
 * Author: Star Isakson
 * Course: CS II
 * Assignment: 
 * Date: 
 *
 * Description:
 *
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BST tree = new BST();

        // Sample data for testing
        tree.insert(1005, "Ashwhisker", "Thistlewick", "Market Row", "555-1005", "MARKET");
        tree.insert(1002, "Tanner", "Ravenshield", "Hearth Wing", "555-1002", "WATCH");
        tree.insert(1008, "Elyria", "Willowmere", "Grove Path", "555-1008", "GROVE");
        tree.insert(1001, "Star", "Isakson", "Hearth Wing", "555-1001", "WHEEL");
        tree.insert(1003, "Seris", "Sliverborn", "Spindle Gate", "555-1003", "WATCH");

        int choice;

        do {
            System.out.println("\n===== Binary Search Tree Menu =====");
            System.out.println("1. Add record");
            System.out.println("2. Remove record");
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
                    System.out.println("Record added.");
                    break;

                case 2:
                    System.out.print("Enter ID to remove: ");
                    int deleteId = input.nextInt();
                    input.nextLine();

                    tree.delete(deleteId);
                    System.out.println("Delete operation completed.");
                    break;

                case 3:
                    System.out.print("Enter ID to search: ");
                    int searchId = input.nextInt();
                    input.nextLine();

                    PersonRecord found = tree.search(searchId);
                    if (found != null) {
                        System.out.println("Record found:");
                        System.out.println(found);
                    } else {
                        System.out.println("Record not found.");
                    }
                    break;

                case 4:
                    System.out.println("\nPreorder Traversal:");
                    tree.preorder();
                    break;

                case 5:
                    System.out.println("\nInorder Traversal:");
                    tree.inorder();
                    break;

                case 6:
                    System.out.println("\nPostorder Traversal:");
                    tree.postorder();
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