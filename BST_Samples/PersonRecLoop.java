/*
 * File: PersonRecLoop.java
 * Author: Star Isakson
 * Course: CS II
 * Assignment: Binary Search Trees
 * Date: 3/11/2026
 *
 * Description:
 *
 */

public class PersonRecLoop {

    int id;
    String firstName;
    String lastName;
    String address;
    String phone;
    String groupId;

    PersonRecLoop left;
    PersonRecLoop right;

    public PersonRecLoop(int id, String firstName, String lastName,
            String address, String phone, String groupId) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.groupId = groupId;

        left = null;
        right = null;
    }

    @Override
public String toString() {
    return "ID: " + id +
           ", Name: " + firstName + " " + lastName +
           ", Address: " + address +
           ", Phone: " + phone +
           ", Group ID: " + groupId;
}
}