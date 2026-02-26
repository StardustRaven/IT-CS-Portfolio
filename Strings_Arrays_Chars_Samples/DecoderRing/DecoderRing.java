// Name: Summer Star Isakson
// Lab Date: 2026-02-13

public class DecoderRing {

    public static void main(String[] args) {

        /*
         * System.console() allows us to read user input from the terminal
         * WITHOUT importing Scanner or any other libraries.
         * 
         * Note: This will not work if the program is run using some IDE
         * "Run" buttons. It is intended to be run from the command line.
         */
        if (System.console() == null) {
            System.out.println("Console not available. Please run this program from a terminal.");
            return;
        }

        // Prompt the user for the encrypted message
        String cipherText = System.console().readLine("Enter the encrypted message: ");

        // Prompt the user for the key used to encrypt the message
        String key = System.console().readLine("Enter the key: ");

        // Decode the encrypted message using the Vigenère cipher
        String plainText = vigenereDecrypt(cipherText, key);

        // Display the decoded result
        System.out.println("\nDecoded Message:");
        System.out.println(plainText);
    }

    /*
     * This method performs Vigenère cipher decryption.
     *
     * Vigenère decryption works by:
     * - Converting letters to numbers (A=0, B=1, ..., Z=25)
     * - Subtracting the key letter value from the cipher letter value
     * - Using modulo 26 arithmetic to wrap around the alphabet
     *
     * Non-letter characters (spaces, punctuation) are preserved
     * and do NOT advance the key.
     */
    public static String vigenereDecrypt(String cipherText, String key) {

        /*
         * First, we "clean" the key.
         * We remove any non-letter characters and convert all letters
         * to uppercase so we only have one alphabet to work with.
         */
        String cleanKey = "";

        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);

            // Convert lowercase letters to uppercase using ASCII math
            if (ch >= 'a' && ch <= 'z') {
                ch = (char) (ch - 32);
            }

            // Keep only letters A–Z
            if (ch >= 'A' && ch <= 'Z') {
                cleanKey += ch;
            }
        }

        // If the key has no letters, decryption cannot proceed
        if (cleanKey.length() == 0) {
            return "[ERROR] Key must contain at least one letter.";
        }

        // This will hold the decrypted plaintext
        String result = "";

        // Tracks which letter of the key we are currently using
        int keyIndex = 0;

        /*
         * Process the encrypted message one character at a time.
         */
        for (int i = 0; i < cipherText.length(); i++) {
            char c = cipherText.charAt(i);

            // Normalize lowercase letters to uppercase
            if (c >= 'a' && c <= 'z') {
                c = (char) (c - 32);
            }

            /*
             * If the character is a letter (A–Z),
             * apply the Vigenère decryption math.
             */
            if (c >= 'A' && c <= 'Z') {

                // Select the current key letter (wrap using modulo)
                char k = cleanKey.charAt(keyIndex % cleanKey.length());

                /*
                 * Convert characters to numbers:
                 * 'A' -> 0, 'B' -> 1, ..., 'Z' -> 25
                 */
                int cVal = c - 'A';
                int kVal = k - 'A';

                /*
                 * Decryption formula:
                 * plaintext = ciphertext - key (mod 26)
                 */
                int pVal = cVal - kVal;

                // Ensure the value stays within 0–25
                if (pVal < 0) {
                    pVal += 26;
                }

                // Convert number back into a letter
                char p = (char) ('A' + pVal);

                // Append the decrypted letter to the result
                result += p;

                /*
                 * Advance the key index ONLY when a letter is processed.
                 * This is a core rule of the Vigenère cipher.
                 */
                keyIndex++;

            } else {
                /*
                 * Non-letter characters are added unchanged.
                 * They do not affect key alignment.
                 */
                result += cipherText.charAt(i);
            }
        }

        // Return the fully decrypted message
        return result;
    }
}
