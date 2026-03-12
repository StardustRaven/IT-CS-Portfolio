/*
 * File: BST.java
 * Author: Star Isakson
 * Course: CS II
 * Assignment: Binary Search Trees
 * Date: 3/11/2026
 *
 * Description:
 *
 */

public class BST {
    private PersonRecord root;

    public BST() {
        root = null;
    }

    public PersonRecord getRoot() {
        return root;
    }

    // =========================
    // ADD / INSERT (recursive)
    // =========================
    public void insert(int id, String firstName, String lastName,
                       String address, String phone, String groupId) {
        root = insertRecursive(root, id, firstName, lastName, address, phone, groupId);
    }

    private PersonRecord insertRecursive(PersonRecord current, int id, String firstName, String lastName,
                                         String address, String phone, String groupId) {
        if (current == null) {
            return new PersonRecord(id, firstName, lastName, address, phone, groupId);
        }

        if (id < current.id) {
            current.left = insertRecursive(current.left, id, firstName, lastName, address, phone, groupId);
        } else if (id > current.id) {
            current.right = insertRecursive(current.right, id, firstName, lastName, address, phone, groupId);
        } else {
            System.out.println("Duplicate ID not allowed: " + id);
        }

        return current;
    }

    // =========================
    // LOOKUP / SEARCH (recursive)
    // =========================
    public PersonRecord search(int id) {
        return searchRecursive(root, id);
    }

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
    // REMOVE / DELETE (recursive)
    // =========================
    public void delete(int id) {
        root = deleteRecursive(root, id);
    }

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
            current.groupId = smallestInRight.groupId;

            current.right = deleteRecursive(current.right, smallestInRight.id);
        }

        return current;
    }

    private PersonRecord findMin(PersonRecord current) {
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // =========================
    // TRAVERSALS
    // =========================
    public void preorder() {
        preorderRecursive(root);
    }

    private void preorderRecursive(PersonRecord current) {
        if (current != null) {
            System.out.println(current);
            preorderRecursive(current.left);
            preorderRecursive(current.right);
        }
    }

    public void inorder() {
        inorderRecursive(root);
    }

    private void inorderRecursive(PersonRecord current) {
        if (current != null) {
            inorderRecursive(current.left);
            System.out.println(current);
            inorderRecursive(current.right);
        }
    }

    public void postorder() {
        postorderRecursive(root);
    }

    private void postorderRecursive(PersonRecord current) {
        if (current != null) {
            postorderRecursive(current.left);
            postorderRecursive(current.right);
            System.out.println(current);
        }
    }

    // =========================
    // HELPER
    // =========================
    public boolean isEmpty() {
        return root == null;
    }

 public int height() {
    return height(root);
}

private int height(PersonRecord node) {
    if (node == null) {
        return 0;
    }

    int leftHeight = height(node.left);
    int rightHeight = height(node.right);

    return 1 + Math.max(leftHeight, rightHeight);
}
}