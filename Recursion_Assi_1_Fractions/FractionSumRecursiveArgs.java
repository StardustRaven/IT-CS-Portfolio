// Name: Star Isakson
// Lab: Week 6 – Recursive Fraction LCD
// Date: 02/23/2026
//
// This program adds two fractions provided as command-line arguments.
// Recursion is used to find the GCD so the final result can be reduced.

public class FractionSumRecursiveArgs {

    /*
     * Finds the Greatest Common Divisor (GCD) of two integers
     * using Euclid’s algorithm.
     *
     * This method is recursive: each call reduces the problem
     * until it reaches a simple stopping condition.
     */
    public static int gcd(int a, int b) {

        // Use absolute values to avoid issues with negatives
        a = Math.abs(a);
        b = Math.abs(b);

        // Base case: when b is 0, a is the GCD
        if (b == 0) {
            return a;
        }

        // Recursive call with smaller values
        return gcd(b, a % b);
    }

    /*
     * Computes the Least Common Denominator (LCD) of two values.
     * This method relies on the recursive gcd() method above.
     */
    public static int lcd(int a, int b) {
        return Math.abs(a * b) / gcd(a, b);
    }

    public static void main(String[] args) {

        /*
         * The program expects exactly four command-line arguments:
         * numerator1 denominator1 numerator2 denominator2
         */
        if (args.length != 4) {
            System.out.println("Usage: java FractionSumRecursiveArgs <n1> <d1> <n2> <d2>");
            System.out.println("Example: java FractionSumRecursiveArgs 1 4 1 6");
            System.out.println("Run from the terminal by typing: java FractionSumRecursiveArgs followed by four integers");
            return;
        }

        int n1, d1, n2, d2;

        /*
         * Convert command-line arguments from strings to integers.
         * If conversion fails, the program exits safely.
         */
        try {
            n1 = Integer.parseInt(args[0]);
            d1 = Integer.parseInt(args[1]);
            n2 = Integer.parseInt(args[2]);
            d2 = Integer.parseInt(args[3]);
        } catch (NumberFormatException e) {
            System.out.println("All inputs must be integers.");
            return;
        }

        // Prevent division by zero
        if (d1 == 0 || d2 == 0) {
            System.out.println("Denominators cannot be 0.");
            return;
        }

        // Display the original input
        System.out.println("You entered:");
        System.out.println("Fraction 1: " + n1 + "/" + d1);
        System.out.println("Fraction 2: " + n2 + "/" + d2);

        // Find the least common denominator
        int commonDenominator = lcd(d1, d2);

        // Convert both fractions to use the common denominator
        int convertedN1 = n1 * (commonDenominator / d1);
        int convertedN2 = n2 * (commonDenominator / d2);

        System.out.println("\nLeast Common Denominator (LCD): " + commonDenominator);

        System.out.println("\nConverted fractions:");
        System.out.println(n1 + "/" + d1 + " = " + convertedN1 + "/" + commonDenominator);
        System.out.println(n2 + "/" + d2 + " = " + convertedN2 + "/" + commonDenominator);

        // Add the fractions
        int sumNumerator = convertedN1 + convertedN2;
        int sumDenominator = commonDenominator;

        System.out.println("\nSum (before reducing):");
        System.out.println(
                convertedN1 + "/" + commonDenominator + " + " +
                convertedN2 + "/" + commonDenominator + " = " +
                sumNumerator + "/" + sumDenominator
        );

        // Reduce the final fraction using the recursive GCD method
        int reductionFactor = gcd(sumNumerator, sumDenominator);

        int finalNumerator = sumNumerator / reductionFactor;
        int finalDenominator = sumDenominator / reductionFactor;

        System.out.println("\nFinal answer (reduced):");
        System.out.println(finalNumerator + "/" + finalDenominator);
    }
}