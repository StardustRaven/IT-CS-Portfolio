/*
 * File: AVLNode.java
 * Author: Star Isakson
 * Course: CS II
 * Assignment: Binary Search Trees / AVL Tree Extra Credit
 * Date: 3/11/2026
 *
 * Description:
 * Stores one person record for the AVL tree, including node height.
 */

public class AVLNode {

    int id;
    String firstName;
    String lastName;
    String address;
    String phone;

    int height;
    AVLNode left;
    AVLNode right;

    // =========================
    // CONSTRUCTOR
    // =========================
    // Creates a new AVL node with initial height of 1
    public AVLNode(int id, String firstName, String lastName,
                   String address, String phone) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;

        this.height = 1;
        this.left = null;
        this.right = null;
    }

    // =========================
    // STRING OUTPUT
    // =========================
    // Returns record data as a readable string
    @Override
    public String toString() {
        return "ID: " + id +
               ", Name: " + firstName + " " + lastName +
               ", Address: " + address +
               ", Phone: " + phone;
    }
}