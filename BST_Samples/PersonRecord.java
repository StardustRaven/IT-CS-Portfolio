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

public class PersonRecord {
    int id;
    String firstName;
    String lastName;
    String address;
    String phone;
    String groupId;

    PersonRecord left;
    PersonRecord right;

    public PersonRecord(int id, String firstName, String lastName,
                        String address, String phone, String groupId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.groupId = groupId;
        this.left = null;
        this.right = null;
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