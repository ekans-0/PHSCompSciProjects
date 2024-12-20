package cipherProject;

public class CaesarCipher implements Cipherable {
    private int shift;

    //Given a set shift, it will set the shift value of the cipher object to that shift
    public CaesarCipher(int shift) {
        this.shift = shift;
    }

    //Encodes a given string using the value of the set shift, listed above
    @Override
    public String encode(String in) {
    	String upper = in.toUpperCase();
    	String answer = "";
    		for (int i = 0; i < in.length(); i++) {
    			if (Character.isLetter(upper.charAt(i))) {
    				char ch = (char) (upper.charAt(i) + shift);
    				if (ch > 'Z') {
    					ch = (char) (ch - 26);
    				}
    				if (Character.isLowerCase(in.charAt(i))) {
    					ch = Character.toLowerCase(ch);
    				}
    				answer += ch;
    			} else {
    				answer += upper.charAt(i);
    			}
    		}
    	return answer;
    	}
    
    //Decodes the message using the encode method, but in reverse to save time
    @Override
    public String decode(String in) {
    	shift = 26 - shift;
    	String answer = encode(in);
    	shift = 26 - shift;
    	return answer;
    	}

    // The shift/key value random generator so that you can generate a random key if wanted
    public static int generateRandomKey() {
        return (int) (Math.random() * 26); 
    }
}