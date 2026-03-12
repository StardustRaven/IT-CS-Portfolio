/*
 * File: BSTLoop.java
 * Author: Star Isakson
 * Course: CS II
 * Assignment: Binary Search Trees
 * Date: 3/11/2026
 *
 * Description:
 *
 */

import java.util.Stack;

public class BSTLoop {

    PersonRecLoop root;

    public void insert(int id, String firstName, String lastName,
            String address, String phone, String groupId) {

        PersonRecLoop newNode = new PersonRecLoop(id, firstName, lastName, address, phone, groupId);

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
                System.out.println("Duplicate ID not allowed.");
                return;
            }
        }

        if (id < parent.id) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

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

    public boolean isEmpty() {
        return root == null;
    }

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
}
