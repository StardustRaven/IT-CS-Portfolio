/*
 * File: BST.java
 * Author: Star Isakson
 * Course: CS II
 * Assignment: Binary Search Trees / AVL Tree Extra Credit
 * Date: 3/11/2026
 *
 * Description:
 * Recursive Binary Search Tree implementation supporting insert, search,
 * delete, traversal, height, and file save operations.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BST {

    // Root of the recursive BST
    private PersonRecord root;

    // =========================
    // CONSTRUCTOR
    // =========================
    // Creates an empty recursive BST
    public BST() {
        root = null;
    }

    // =========================
    // ROOT ACCESS
    // =========================
    // Returns the root node of the tree
    public PersonRecord getRoot() {
        return root;
    }

    // =========================
    // INSERT
    // =========================
    // Public insert method starts recursive insertion at the root
    public void insert(int id, String firstName, String lastName,
                       String address, String phone) {
        root = insertRecursive(root, id, firstName, lastName, address, phone);
    }

    // Recursively inserts a new record into the BST
    private PersonRecord insertRecursive(PersonRecord current, int id, String firstName,
                                         String lastName, String address, String phone) {
        if (current == null) {
            return new PersonRecord(id, firstName, lastName, address, phone);
        }

        if (id < current.id) {
            current.left = insertRecursive(current.left, id, firstName, lastName, address, phone);
        } else if (id > current.id) {
            current.right = insertRecursive(current.right, id, firstName, lastName, address, phone);
        } else {
            System.out.println("Duplicate ID not allowed. Record skipped.");
        }

        return current;
    }

    // =========================
    // SEARCH
    // =========================
    // Public search method finds a record by ID
    public PersonRecord search(int id) {
        return searchRecursive(root, id);
    }

    // Recursively searches for a record by ID
    private PersonRecord searchRecursive(PersonRecord current, int id) {
        if (current == null || current.id == id) {
            return current;
        }

        if (id < current.id) {
            return searchRecursive(current.left, id);
        } else {
            return searchRecursive(current.right, id);
        }
    }

    // =========================
    // DELETE
    // =========================
    // Public delete method removes a record by ID
    public void delete(int id) {
        root = deleteRecursive(root, id);
    }

    // Recursively deletes a record and reconnects the tree
    private PersonRecord deleteRecursive(PersonRecord current, int id) {
        if (current == null) {
            return null;
        }

        if (id < current.id) {
            current.left = deleteRecursive(current.left, id);
        } else if (id > current.id) {
            current.right = deleteRecursive(current.right, id);
        } else {
            // Case 1: no children
            if (current.left == null && current.right == null) {
                return null;
            }

            // Case 2: one child
            if (current.left == null) {
                return current.right;
            }
            if (current.right == null) {
                return current.left;
            }

            // Case 3: two children
            PersonRecord smallestInRight = findMin(current.right);
            current.id = smallestInRight.id;
            current.firstName = smallestInRight.firstName;
            current.lastName = smallestInRight.lastName;
            current.address = smallestInRight.address;
            current.phone = smallestInRight.phone;

            current.right = deleteRecursive(current.right, smallestInRight.id);
        }

        return current;
    }

    // Finds the smallest node in a subtree
    private PersonRecord findMin(PersonRecord current) {
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // =========================
    // TRAVERSALS
    // =========================
    // Prints records in preorder
    public void preorder() {
        preorderRecursive(root);
    }

    // Recursively prints root, left, then right
    private void preorderRecursive(PersonRecord current) {
        if (current != null) {
            System.out.println(current);
            preorderRecursive(current.left);
            preorderRecursive(current.right);
        }
    }

    // Prints records in inorder
    public void inorder() {
        inorderRecursive(root);
    }

    // Recursively prints left, root, then right
    private void inorderRecursive(PersonRecord current) {
        if (current != null) {
            inorderRecursive(current.left);
            System.out.println(current);
            inorderRecursive(current.right);
        }
    }

    // Prints records in postorder
    public void postorder() {
        postorderRecursive(root);
    }

    // Recursively prints left, right, then root
    private void postorderRecursive(PersonRecord current) {
        if (current != null) {
            postorderRecursive(current.left);
            postorderRecursive(current.right);
            System.out.println(current);
        }
    }

    // =========================
    // HELPERS
    // =========================
    // Returns true if the tree has no nodes
    public boolean isEmpty() {
        return root == null;
    }

    // Returns the height of the tree
    public int height() {
        return height(root);
    }

    // Recursively calculates subtree height
    private int height(PersonRecord node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    // =========================
    // SAVE TO FILE
    // =========================
    // Saves tree records in sorted order using inorder traversal
    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            saveInOrder(root, writer);
            System.out.println("Recursive BST saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // Recursively writes inorder records to the file
    private void saveInOrder(PersonRecord current, PrintWriter writer) {
        if (current != null) {
            saveInOrder(current.left, writer);

            writer.println(current.id + "," +
                           current.firstName + "," +
                           current.lastName + "," +
                           current.address + "," +
                           current.phone);

            saveInOrder(current.right, writer);
        }
    }
}