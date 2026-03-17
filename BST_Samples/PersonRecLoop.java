/*
 * File: PersonRecLoop.java
 * Author: Star Isakson
 * Course: CS II
 * Assignment: Binary Search Trees / AVL Tree Extra Credit
 * Date: 3/11/2026
 *
 * Description:
 * Stores one person record for the iterative BST implementation.
 */

public class PersonRecLoop {

    int id;
    String firstName;
    String lastName;
    String address;
    String phone;

    PersonRecLoop left;
    PersonRecLoop right;

    // =========================
    // CONSTRUCTOR
    // =========================
    // Creates a new record node for the iterative BST
    public PersonRecLoop(int id, String firstName, String lastName,
                         String address, String phone) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;

        left = null;
        right = null;
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