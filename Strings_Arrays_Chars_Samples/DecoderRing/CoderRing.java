// Name: Summer Star Isakson
// Lab Date: 2026-02-13

public class CoderRing {

    public static void main(String[] args) {

        // Use System.console() for input without imports
        if (System.console() == null) {
            System.out.println("Console not available. Run this program from a terminal.");
            return;
        }

        String plainText = System.console().readLine("Enter the message to encrypt: ");
        String key = System.console().readLine("Enter the key: ");

        String cipherText = vigenereEncrypt(plainText, key);

        System.out.println("\nCiphertext:");
        System.out.println(cipherText);
    }

    /*
     * Vigenère encryption:
     * ciphertext = plaintext + key (mod 26)
     */
    public static String vigenereEncrypt(String plainText, String key) {

        // Clean the key: keep letters only, convert to uppercase
        String cleanKey = "";

        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                ch = (char) (ch - 32); // to uppercase via ASCII
            }

            if (ch >= 'A' && ch <= 'Z') {
                cleanKey += ch;
            }
        }

        if (cleanKey.length() == 0) {
            return "[ERROR] Key must contain letters.";
        }

        String result = "";
        int keyIndex = 0;

        for (int i = 0; i < plainText.length(); i++) {
            char p = plainText.charAt(i);

            if (p >= 'a' && p <= 'z') {
                p = (char) (p - 32);
            }

            if (p >= 'A' && p <= 'Z') {
                char k = cleanKey.charAt(keyIndex % cleanKey.length());

                int pVal = p - 'A';
                int kVal = k - 'A';

                int cVal = (pVal + kVal) % 26;
                char c = (char) ('A' + cVal);

                result += c;
                keyIndex++;
            } else {
                result += plainText.charAt(i);
            }
        }

        return result;
    }
}
