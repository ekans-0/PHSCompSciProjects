package cipherProject;

public class VigenereCipher implements Cipherable {
    private String key; 

    public VigenereCipher(String key) {
        this.key = key;
    }

    // Method to encode the message
    @Override
    public String encode(String input) {
        String result = ""; 
        int keyIndex = 0; 

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (Character.isLetter(currentChar)) {
                char keyChar = key.charAt(keyIndex % key.length()); 
                int shift = keyChar - 'A'; 

                if (Character.isUpperCase(currentChar)) {
                    currentChar = (char) ((currentChar - 'A' + shift) % 26 + 'A');
                } else if (Character.isLowerCase(currentChar)) {
                    currentChar = (char) ((currentChar - 'a' + shift) % 26 + 'a');
                }

                keyIndex++; 
            }
            result += currentChar; 
        }
        return result;
    }

    // Method to decode the message
    @Override
    public String decode(String input) {
        String result = ""; 
        int keyIndex = 0; 

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (Character.isLetter(currentChar)) {
                char keyChar = key.charAt(keyIndex % key.length()); 
                int shift = keyChar - 'A'; 
                if (Character.isUpperCase(currentChar)) {
                    currentChar = (char) ((currentChar - 'A' - shift + 26) % 26 + 'A');
                } else if (Character.isLowerCase(currentChar)) {
                    currentChar = (char) ((currentChar - 'a' - shift + 26) % 26 + 'a');
                }

                keyIndex++;
            }
            result += currentChar;  
        }

        return result;
    }

    // Generates a random key for the cipher
    public static String generateRandomKey(int length) {
        String result = ""; 
        for (int i = 0; i < length; i++) {
            char randomChar;
            if (Math.random() < 0.5) {
                randomChar = (char) ('A' + (int)(Math.random() * 26));
            } else {
                randomChar = (char) ('a' + (int)(Math.random() * 26)); 
            }
            result += randomChar;  
        }
        return result;
    }
}
