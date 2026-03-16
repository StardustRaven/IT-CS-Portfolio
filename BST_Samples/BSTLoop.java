/*
 * File: BSTLoop.java
 * Author: Star Isakson
 * Course: CS II
 * Assignment: Binary Search Trees
 * Date: 3/11/2026
 *
 * Description:
 * Iterative (loop-based) Binary Search Tree implementation using PersonRecLoop nodes.
 * Supports insert, search, and tree traversals without recursion.
 */

import java.util.Stack;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BSTLoop {

    // Root of the tree
    PersonRecLoop root;

    // ADD / INSERT (iterative)

    public void insert(int id, String firstName, String lastName,
            String address, String phone) {

        // Create the new node to be inserted
        PersonRecLoop newNode = new PersonRecLoop(id, firstName, lastName, address, phone);

        // If tree is empty, new node becomes the root
        if (root == null) {
            root = newNode;
            return;
        }

        // Start at the root and move downward until an empty spot is found
        PersonRecLoop current = root;
        PersonRecLoop parent = null;

        while (current != null) {

            parent = current;

            // Move left if new id is smaller
            if (id < current.id) {
                current = current.left;

                // Move right if new id is larger
            } else if (id > current.id) {
                current = current.right;

                // Duplicate ids are not allowed
            } else {
                System.out.println("Duplicate ID not allowed.");
                return;
            }
        }

        // Attach the new node to the correct side of the parent
        if (id < parent.id) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    // LOOKUP / SEARCH (iterative)

    public PersonRecLoop search(int id) {

        // Start searching at the root
        PersonRecLoop current = root;

        while (current != null) {

            // If matching id is found, return that node
            if (current.id == id) {
                return current;
            }

            // Move left or right depending on comparison
            if (id < current.id) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        // If loop ends, the id was not found
        return null;
    }

    // HELPER

    public boolean isEmpty() {
        return root == null;
    }

    // TRAVERSALS (iterative)

    // Inorder: Left, Root, Right
    public void inorder() {
        Stack<PersonRecLoop> stack = new Stack<>();
        PersonRecLoop current = root;

        // Continue while there are nodes to process
        // either in the stack or in the current branch
        while (current != null || !stack.isEmpty()) {

            // Push all left children onto the stack
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // Visit the node at the top of the stack
            current = stack.pop();
            System.out.println(current);

            // Move to the right subtree
            current = current.right;
        }
    }

    // Preorder: Root, Left, Right
    public void preorder() {
        if (root == null) {
            return;
        }

        Stack<PersonRecLoop> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            PersonRecLoop current = stack.pop();
            System.out.println(current);

            // Push right first so left is processed first
            if (current.right != null) {
                stack.push(current.right);
            }

            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }

    // Postorder: Left, Right, Root
    public void postorder() {
        if (root == null) {
            return;
        }

        // First stack is used to process nodes
        // Second stack reverses the order for postorder output
        Stack<PersonRecLoop> stack1 = new Stack<>();
        Stack<PersonRecLoop> stack2 = new Stack<>();

        stack1.push(root);

        while (!stack1.isEmpty()) {
            PersonRecLoop current = stack1.pop();
            stack2.push(current);

            // Push left and right children into stack1
            if (current.left != null) {
                stack1.push(current.left);
            }

            if (current.right != null) {
                stack1.push(current.right);
            }
        }

        // Print nodes in postorder
        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop());
        }
    }
// adding print to file capability
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