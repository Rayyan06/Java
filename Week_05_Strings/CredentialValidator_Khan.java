/*
Validates username and password selected by user to ensure it follows security rules
Tells the user WHY their username/password was invalid! (extra!)
Does not authenticate the user for login

- It's possible that the username and password could have non-ASCII characters.
*/

import java.util.Scanner; // for user I/O
import java.lang.Character; // for Character class

public class CredentialValidator_Khan {
    public static boolean isValidUsername(String username) {
        // the username must be between 3 and 16 characters long
        int usernameLength = username.length();

        if(usernameLength < 3 || usernameLength > 16) {
            System.out.println("Your username has " + usernameLength + " characters.");
            System.out.println("#1: The username must be between 3 and 16 characters.");
            return false;
        }

        // the username must contain only letters and numbers
        // iterate over the string, ensuring each character is alphanumeric
        // Could also use for-each loop to iterate over username:
        // for(char c : username.toCharArray())
        for(int i = 0; i < usernameLength; i++) {
            char c = username.charAt(i);
            if (!Character.isLetterOrDigit(c)) { // nifty function in the Character class!
                System.out.println("Your username contains a non-alphanumeric character: " + c);
                System.out.println("#2: The username must only contain alphanumeric characters.");
                return false;
            }
        }

        // all checks completed; the username must valid at this point.
        return true;
    }
    
    /*
    Checks if a password meets the program requirements. 
    Note that username is a parameter to this method, for Check #2 (username within?)
    */
    public static boolean isValidPassword(String password, String username) {
        // Check 1: The password must be at least 8 characters long
        int passwordLength = password.length();

        if(passwordLength < 8) {
            System.out.println("Your password has " + passwordLength + " characters");
            System.out.println("#1: The password must be at least 8 characters long");
            return false;
        }

        // Check 2: The password must contain:
        // A) at least one number, 
        // B) at least one letter, 
        // C) at least one character that is neither a letter nor a number

        // Validation flags, initialize to false and set them true as we check
        boolean containsNumber = false;
        boolean containsLetter = false;
        boolean containsOther = false; // neither a letter nor a number

        // validate that the password meets the conditions of Check 2
        for (int i = 0; i < passwordLength; i++) {
            char c = password.charAt(i);
            if (Character.isDigit(c)) containsNumber = true;
            else if (Character.isLetter(c)) containsLetter = true;
            else containsOther = true;
        }

        // if NONE of these conditions are satisfied, the password is invaild
        if(!(containsNumber && containsLetter && containsOther)) {
            System.out.println("#2: The password must contain at least one number, letter, and non-alphanumeric character.");
            return false;
        }

        // Check 3: The password cannot contain the username as a substring
        if (password.contains(username)) {
            System.out.println("Your password contains your username " + username + " as a substring.");
            System.out.println("#3: The password cannot contain your username as a substring.");
            return false;
        }

        // Valid!
        return true;
    }


    public static void main(String[] args) {
        Scanner keyboardInput = new Scanner(System.in);
        
        System.out.println("------------------");
        System.out.println("Create a username");
        System.out.println("------------------");
        System.out.println("1) The username must be between 3 and 16 characters long");
        System.out.println("2) The username must contain only letters and numbers");
        System.out.print("Enter a username: ");        
        String usernameInput = keyboardInput.nextLine();
        
        // if the username is invalid...
        if(!isValidUsername(usernameInput)) {
            // ... stop the program
            System.out.println("Your username was invalid. Please try again");
            return;
        }

        System.out.println("------------------");
        System.out.println("Create a password");
        System.out.println("------------------");
        System.out.println("1) The password must be at least 8 characters long.");
        System.out.println("2) The password must contain at least one number, letter, and non-alphanumeric character.");
        System.out.println("3) The password cannot contain your username \"" + usernameInput + "\" as a substring.");

        System.out.print("Enter password: ");
        String passwordInput = keyboardInput.nextLine();

        if(isValidPassword(passwordInput, usernameInput)) {
            // .repeat() is only available in Java 11 onwards
            System.out.println("Your username \"" + usernameInput + "\" and password " + "*".repeat(passwordInput.length()) + " are valid.");
        } else {
            System.out.println("You selected an invalid password. Please try again");
        }

        keyboardInput.close();
    }
}