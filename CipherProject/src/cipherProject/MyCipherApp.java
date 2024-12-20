package cipherProject;
import java.util.Scanner;

public class MyCipherApp {
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
        	//Input message at the beginning of the program
            int cipherChoice = getCipherMethodChoice(scanner);
            System.out.print("Enter the message: ");
            String message = scanner.nextLine();
            if (message == null) {
                throw new IllegalArgumentException("Message cannot be empty.");
            }
            //Gets the choice of type of method and encode/decode option
            int actionChoice = getCipheringChoice(scanner);
            int useRandomKeyChoice = getRandomKeyChoice(scanner);

            String key = "";
            //They choose to get a key if they want a random one
            if (useRandomKeyChoice == 1) { 
                String randomKeyMessage = "";
                if (cipherChoice == 1) { 
                    int randomKey = CaesarCipher.generateRandomKey();
                    key = String.valueOf(randomKey);
                    //Prints out the random Caesar cipher shift 
                    randomKeyMessage = "Random shift for Caesar Cipher: " + randomKey;
                    //Prints out the random substitution cipher key
                } else if (cipherChoice == 2) {  
                    key = SubstitutionCipher.generateRandomKey();
                    randomKeyMessage = "Random key for Substitution Cipher: " + key;
                    //Prints out the random viginere cipher key
                } else if (cipherChoice == 3) { 
                    key = VigenereCipher.generateRandomKey(message.length());
                    randomKeyMessage = "Random key for Vigenere Cipher: " + key;
                }
                System.out.println(randomKeyMessage + "\nYou can now copy this key.");
            //The user has a key that they want to input
            } else {  
                System.out.print("Enter the key: ");
                key = scanner.nextLine();
                if (key.isEmpty()) {
                    throw new IllegalArgumentException("Key cannot be empty.");
                }
            }
            
            //Caesar cipher outputs
            if (cipherChoice == 1) { 
            	//Checks if chosen action was encode, and does encoding if so
                int shift = Integer.parseInt(key);
                CaesarCipher cipher = new CaesarCipher(shift);
                if (actionChoice == 1) {  
                    String encodedMessage = cipher.encode(message);
                    System.out.println("Encoded message: " + encodedMessage);
                //Decodes otherwise
                } else {  
                    String decodedMessage = cipher.decode(message);
                    System.out.println("Decoded message: " + decodedMessage);
                }
                
            //Substitution cipher outputs
            } else if (cipherChoice == 2) { 
            	//Checks if the chosen action option was encode, and executes action if so
                SubstitutionCipher cipher = new SubstitutionCipher(key);
                if (actionChoice == 1) { 
                    String encodedMessage = cipher.encode(message);
                    System.out.println("Encoded message: " + encodedMessage);
                // Decodes otherwise
                } else { 
                    String decodedMessage = cipher.decode(message);
                    System.out.println("Decoded message: " + decodedMessage);
                }
                
            //Viginere cipher outputs
            } else if (cipherChoice == 3) {  
                VigenereCipher cipher = new VigenereCipher(key);
                //Checks if we chose encode and prints out result if so
                if (actionChoice == 1) {
                    String encodedMessage = cipher.encode(message);
                    System.out.println("Encoded message: " + encodedMessage);
                //Does decoding otherwise
                } else {
                    String decodedMessage = cipher.decode(message);
                    System.out.println("Decoded message: " + decodedMessage);
                }
            //Gives an error for illegal cipher
            } else {
                System.out.println("Invalid cipher choice.");
            }
        //Catching every possible exception (I had to learn how to catch multiple errors)
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format for the key. Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } 
        //If it's done, it closes finally at the end
        scanner.close();
    }

    public static int getCipherMethodChoice(Scanner scanner) {
        int choice = 0;
        while (true) {
            try {
                System.out.println("Choose a cipher:\n1. Caesar\n2. Substitution\n3. Vigenère");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 3) {
                    return choice;
                } else {
                    System.out.println("Please choose a valid cipher (1, 2, or 3).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (1, 2, or 3).");
            }
        }
    }

    public static int getCipheringChoice(Scanner scanner) {
        int choice = 0;
        while (true) {
            try {
                System.out.println("Do you want to:\n1. Encode\n2. Decode");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1 || choice == 2) {
                    return choice;
                } else {
                    System.out.println("Please enter 1 for Encode or 2 for Decode.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (1 or 2).");
            }
        }
    }

    public static int getRandomKeyChoice(Scanner scanner) {
        int choice = 0;
        while (true) {
            try {
                System.out.println("Do you want to use a random key?"
                		+ "\n 1. Yes"
                		+ "\n 2. No");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1 || choice == 2) {
                    return choice;
                } else {
                    System.out.println("Please enter 1 for Yes or 2 for No.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (1 or 2).");
                scanner.nextLine();
            }
        }
    }

}

