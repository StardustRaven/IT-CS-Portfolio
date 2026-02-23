// Name: Star Isakson
// Lab: Week 6 – Pascal's Triangle (Recursive)
// Date: 02/23/2026
//
// Prints Pascal’s Triangle using recursion for the values
// and loops for formatting the output.

public class PascalTriangleRecursive {

    /*
     * Returns the value at the given row and column
     * using Pascal’s Triangle recursion.
     */
    public static int pascal(int row, int col) {

        // Base case: edges are always 1
        if (col == 0 || col == row) {
            return 1;
        }

        // Recursive case
        return pascal(row - 1, col - 1) + pascal(row - 1, col);
    }

    public static void main(String[] args) {

        int levels = 5; // Change this to print more rows

        for (int row = 0; row < levels; row++) {

            // Print leading spaces to center the row
            for (int space = 0; space < levels - row - 1; space++) {
                System.out.print("  ");
            }

            // Print values in the current row
            for (int col = 0; col <= row; col++) {
                System.out.print(pascal(row, col) + "   ");
            }

            // Move to the next line
            System.out.println();
        }
    }
}
