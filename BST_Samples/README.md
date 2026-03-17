# BST Samples

This project demonstrates three tree-based data structures in Java:

- **Recursive Binary Search Tree (BST)**
- **Iterative Binary Search Tree (BSTLoop)**
- **AVL Tree (self-balancing BST)**

The program loads records from a text file, allows the user to insert, search, delete, traverse, and save records, and compares how different tree implementations handle the same data.

## Features

- Load records from `database.txt`
- Add records to all tree types
- Search for records by ID
- Delete records in recursive and iterative BST versions
- Traverse trees in preorder, inorder, and postorder
- Save output to separate files for validation:
  - `database_recursive.txt`
  - `database_loop.txt`
  - `database_avl.txt`

## Files

- `MainDemo.java` - menu-driven program that runs the project
- `PersonRecord.java` - node class for recursive BST
- `PersonRecLoop.java` - node class for iterative BST
- `BST.java` - recursive binary search tree
- `BSTLoop.java` - iterative binary search tree
- `AVLNode.java` - node class for AVL tree
- `AVLTree.java` - self-balancing AVL tree
- `database.txt` - input data file

## Notes

- The AVL tree supports insert, search, traversal, and save.
- AVL delete is not implemented in this version.
- Inorder traversal is used for file saving so records are written in sorted ID order.

## How to Run

1. Open the project in VS Code or another Java IDE.
2. Make sure all `.java` files are in the same project folder.
3. Run `MainDemo.java`.
4. Use the menu to test insert, search, delete, traversal, and save operations.

## Learning Goals

This project demonstrates:

- recursive vs iterative problem solving
- binary search tree structure and traversal
- self-balancing tree behavior with AVL rotations
- file input and output in Java
- menu-driven program design