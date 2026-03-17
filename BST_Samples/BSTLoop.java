/*
 * File: BSTLoop.java
 * Author: Star Isakson
 * Course: CS II
 * Assignment: Binary Search Trees / AVL Tree Extra Credit
 * Date: 3/11/2026
 *
 * Description:
 * Iterative Binary Search Tree implementation supporting insert, search,
 * delete, traversal, and file save operations without recursion.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;

public class BSTLoop {

    // Root of the iterative BST
    private PersonRecLoop root;

    // =========================
    // CONSTRUCTOR
    // =========================
    // Creates an empty iterative BST
    public BSTLoop() {
        root = null;
    }

    // =========================
    // INSERT
    // =========================
    // Inserts a new record into the tree using loops
    public void insert(int id, String firstName, String lastName,
                       String address, String phone) {

        PersonRecLoop newNode = new PersonRecLoop(id, firstName, lastName, address, phone);

        if (root == null) {
            root = newNode;
            return;
        }

        PersonRecLoop current = root;
        PersonRecLoop parent = null;

        while (current != null) {
            parent = current;

            if (id < current.id) {
                current = current.left;
            } else if (id > current.id) {
                current = current.right;
            } else {
                System.out.println("Duplicate ID not allowed. Record skipped.");
                return;
            }
        }

        if (id < parent.id) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    // =========================
    // SEARCH
    // =========================
    // Searches for a record by ID using loops
    public PersonRecLoop search(int id) {
        PersonRecLoop current = root;

        while (current != null) {
            if (current.id == id) {
                return current;
            }

            if (id < current.id) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    // =========================
    // DELETE
    // =========================
    // Deletes a record by ID using loops
    public void delete(int id) {
        PersonRecLoop current = root;
        PersonRecLoop parent = null;

        // Step 1: find node and parent
        while (current != null && current.id != id) {
            parent = current;

            if (id < current.id) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        // If record was not found
        if (current == null) {
            System.out.println("Record not found.");
            return;
        }

        // Case 3: node has two children
        if (current.left != null && current.right != null) {
            PersonRecLoop successorParent = current;
            PersonRecLoop successor = current.right;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            current.id = successor.id;
            current.firstName = successor.firstName;
            current.lastName = successor.lastName;
            current.address = successor.address;
            current.phone = successor.phone;

            current = successor;
            parent = successorParent;
        }

        // Case 1 and 2: node has zero or one child
        PersonRecLoop child;

        if (current.left != null) {
            child = current.left;
        } else {
            child = current.right;
        }

        // Reconnect parent to replacement child
        if (parent == null) {
            root = child;
        } else if (parent.left == current) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    // =========================
    // HELPERS
    // =========================
    // Returns true if the tree has no nodes
    public boolean isEmpty() {
        return root == null;
    }

    // =========================
    // TRAVERSALS
    // =========================
    // Prints records in inorder using a stack
    public void inorder() {
        Stack<PersonRecLoop> stack = new Stack<>();
        PersonRecLoop current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            System.out.println(current);
            current = current.right;
        }
    }

    // Prints records in preorder using a stack
    public void preorder() {
        if (root == null) {
            return;
        }

        Stack<PersonRecLoop> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            PersonRecLoop current = stack.pop();
            System.out.println(current);

            if (current.right != null) {
                stack.push(current.right);
            }

            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }

    // Prints records in postorder using two stacks
    public void postorder() {
        if (root == null) {
            return;
        }

        Stack<PersonRecLoop> stack1 = new Stack<>();
        Stack<PersonRecLoop> stack2 = new Stack<>();

        stack1.push(root);

        while (!stack1.isEmpty()) {
            PersonRecLoop current = stack1.pop();
            stack2.push(current);

            if (current.left != null) {
                stack1.push(current.left);
            }

            if (current.right != null) {
                stack1.push(current.right);
            }
        }

        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop());
        }
    }

    // =========================
    // SAVE TO FILE
    // =========================
    // Saves tree records in sorted order using iterative inorder traversal
    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            Stack<PersonRecLoop> stack = new Stack<>();
            PersonRecLoop current = root;

            while (current != null || !stack.isEmpty()) {
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }

                current = stack.pop();

                writer.println(current.id + "," +
                               current.firstName + "," +
                               current.lastName + "," +
                               current.address + "," +
                               current.phone);

                current = current.right;
            }

            System.out.println("Loop BST saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}