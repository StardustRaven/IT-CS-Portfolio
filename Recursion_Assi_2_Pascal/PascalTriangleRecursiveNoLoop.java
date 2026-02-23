// Name: Summer Isakson
// Lab: Week 4 – Pascal's Triangle (Recursive, No Loops)
// Date: 02/23/2026
//
// Prints Pascal’s Triangle using recursion only.
// No loops are used for calculation or formatting.

public class PascalTriangleRecursiveNoLoop {

    // Returns the Pascal value at a given row and column
    public static int pascal(int row, int col) {
        if (col == 0 || col == row) {
            return 1;
        }
        return pascal(row - 1, col - 1) + pascal(row - 1, col);
    }

    // Recursively prints leading spaces for centering
    public static void printSpaces(int count) {
        if (count == 0) {
            return;
        }
        System.out.print("  ");
        printSpaces(count - 1);
    }

    // Recursively prints all values in a row
    public static void printRow(int row, int col) {
        if (col > row) {
            return;
        }

        System.out.printf("%4d", pascal(row, col));
        printRow(row, col + 1);
    }

    // Recursively prints each row of the triangle
    public static void printTriangle(int currentRow, int totalRows) {
        if (currentRow == totalRows) {
            return;
        }

        printSpaces(totalRows - currentRow - 1);
        printRow(currentRow, 0);
        System.out.println();

        printTriangle(currentRow + 1, totalRows);
    }

    public static void main(String[] args) {
        int levels = 7;
        printTriangle(0, levels);
    }
}