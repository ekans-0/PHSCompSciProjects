package cipherProject;

public class SubstitutionCipher implements Cipherable {
    private String key;

    public SubstitutionCipher(String key) {
        this.key = key;
    }
   
    @Override
    public String encode(String input) {
        String result = "";

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (Character.isUpperCase(ch)) {
                int index = ch - 'A'; 
                result += Character.toUpperCase(key.charAt(index)); 
            } 
            else if (Character.isLowerCase(ch)) {
                int index = ch - 'a'; 
                result += Character.toLowerCase(key.charAt(index));  
            } 
            else {
                result += ch;
            }
        }
        return result;
    }

    @Override
    public String decode(String input) {
        String result = "";

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            
            if (Character.isUpperCase(ch)) {
                int index = key.indexOf(Character.toLowerCase(ch));  
                result += (char) ('A' + index);  
            } 
            else if (Character.isLowerCase(ch)) {
                int index = key.indexOf(Character.toLowerCase(ch));  
                result += (char) ('a' + index); 
            } 
            else {
                result += ch;
            }
        }
        return result;
    }

    public static String generateRandomKey() {
        String referenceAlphabet = "abcdefghijklmnopqrstuvwxyz";  
        String randomizedKey = "";  
        
        while (randomizedKey.length() < 26) {
            int randomIndex = (int) (Math.random() * referenceAlphabet.length());
            char randomChar = referenceAlphabet.charAt(randomIndex);

            if (randomizedKey.indexOf(String.valueOf(randomChar)) == -1) {
                randomizedKey += randomChar;  
            }
        }
        return randomizedKey.toLowerCase();  
    }

}