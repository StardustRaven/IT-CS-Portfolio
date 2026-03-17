/*
 * File: AVLTree.java
 * Author: Star Isakson
 * Course: CS II
 * Assignment: Binary Search Trees / AVL Tree Extra Credit
 * Date: 3/11/2026
 *
 * Description:
 * Self-balancing Binary Search Tree (AVL) using recursive insert,
 * rotations, search, traversal, and file save operations.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AVLTree {

    // Root of the AVL tree
    private AVLNode root;

    // =========================
    // CONSTRUCTOR
    // =========================
    // Creates an empty AVL tree
    public AVLTree() {
        root = null;
    }

    // =========================
    // HEIGHT HELPER
    // =========================
    // Returns height of a node, or 0 if the node is null
    private int getHeight(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // =========================
    // BALANCE FACTOR
    // =========================
    // Returns left height minus right height for a node
    private int getBalance(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    // =========================
    // RIGHT ROTATION
    // =========================
    // Rotates a left-heavy subtree to the right
    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode temp = x.right;

        x.right = y;
        y.left = temp;

        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return x;
    }

    // =========================
    // LEFT ROTATION
    // =========================
    // Rotates a right-heavy subtree to the left
    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode temp = y.left;

        y.left = x;
        x.right = temp;

        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));

        return y;
    }

    // =========================
    // INSERT
    // =========================
    // Public insert method starts recursive insertion at the root
    public void insert(int id, String firstName, String lastName,
                       String address, String phone) {
        root = insert(root, id, firstName, lastName, address, phone);
    }

    // =========================
    // INSERT HELPER
    // =========================
    // Recursively inserts a node, updates height, and rebalances if needed
    private AVLNode insert(AVLNode node, int id, String firstName, String lastName,
                           String address, String phone) {

        // Step 1: regular BST insert
        if (node == null) {
            return new AVLNode(id, firstName, lastName, address, phone);
        }

        if (id < node.id) {
            node.left = insert(node.left, id, firstName, lastName, address, phone);
        } else if (id > node.id) {
            node.right = insert(node.right, id, firstName, lastName, address, phone);
        } else {
            System.out.println("Duplicate ID not allowed. Record skipped.");
            return node;
        }

        // Step 2: update height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // Step 3: check balance factor
        int balance = getBalance(node);

        // Case 1: Left-Left
        if (balance > 1 && id < node.left.id) {
            return rotateRight(node);
        }

        // Case 2: Right-Right
        if (balance < -1 && id > node.right.id) {
            return rotateLeft(node);
        }

        // Case 3: Left-Right
        if (balance > 1 && id > node.left.id) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Case 4: Right-Left
        if (balance < -1 && id < node.right.id) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // =========================
    // SEARCH
    // =========================
    // Finds a node by ID
    public AVLNode search(int id) {
        return search(root, id);
    }

    // Recursively searches for a node by ID
    private AVLNode search(AVLNode node, int id) {
        if (node == null || node.id == id) {
            return node;
        }

        if (id < node.id) {
            return search(node.left, id);
        } else {
            return search(node.right, id);
        }
    }

    // =========================
    // TRAVERSALS
    // =========================
    // Prints records in preorder
    public void preorder() {
        preorder(root);
    }

    // Recursively prints root, left, then right
    private void preorder(AVLNode node) {
        if (node != null) {
            System.out.println(node);
            preorder(node.left);
            preorder(node.right);
        }
    }

    // Prints records in inorder
    public void inorder() {
        inorder(root);
    }

    // Recursively prints left, root, then right
    private void inorder(AVLNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node);
            inorder(node.right);
        }
    }

    // =========================
    // SAVE TO FILE
    // =========================
    // Saves AVL records in sorted order using inorder traversal
    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            saveInOrder(root, writer);
            System.out.println("AVL tree saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving AVL file: " + e.getMessage());
        }
    }

    // Recursively writes inorder records to the file
    private void saveInOrder(AVLNode node, PrintWriter writer) {
        if (node != null) {
            saveInOrder(node.left, writer);

            writer.println(node.id + "," +
                           node.firstName + "," +
                           node.lastName + "," +
                           node.address + "," +
                           node.phone);

            saveInOrder(node.right, writer);
        }
    }
}