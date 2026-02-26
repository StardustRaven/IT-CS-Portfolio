// Name: Summer Star Isakson
// Lab Date: 2026-02-13

public class LetterCounter {

    public static void main(String[] args) {

        /*
         * Use System.console() to get user input without importing Scanner.
         * This requires running the program from a terminal.
         */
        if (System.console() == null) {
            System.out.println("Console not available. Please run this program from a terminal.");
            return;
        }

        // Prompt the user to enter a sentence
        String text = System.console().readLine("Enter a sentence: ");

        /*
         * Convert the entire string to lowercase so that
         * 'A' and 'a' are counted as the same letter.
         */
        text = text.toLowerCase();

        /*
         * Create an array of size 26 to store counts for letters a–z.
         * Index 0 -> 'a'
         * Index 1 -> 'b'
         * ...
         * Index 25 -> 'z'
         */
        int[] letterCounts = new int[26];

        /*
         * Process the string one character at a time.
         */
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            /*
             * Check if the character is a lowercase letter.
             * ASCII range:
             * 'a' = 97
             * 'z' = 122
             */
            if (ch >= 'a' && ch <= 'z') {

                /*
                 * Convert the letter into an array index.
                 * Example:
                 * 'a' - 'a' = 0
                 * 'b' - 'a' = 1
                 * 'z' - 'a' = 25
                 */
                int index = ch - 'a';

                // Increment the count for that letter
                letterCounts[index]++;
            }
            // All other characters (spaces, punctuation, numbers) are ignored
        }

        /*
         * Display the results.
         */
        System.out.println("\nLetter Counts:");

        for (int i = 0; i < letterCounts.length; i++) {
            char letter = (char) ('a' + i);
            System.out.println(letter + ": " + letterCounts[i]);
        }
    }
}
