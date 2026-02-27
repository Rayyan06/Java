import java.util.Scanner;

public class StringToNumber {
    // Returns
    // parseInt()
    public static int stringToInt(String string) {
        int output = 0;
        for (int i = 0; i < string.length(); i++) {
            // We start off with output equaling zero, 
            // every time we add to output, we are moving over a place value
            output *= 10;
            System.out.println("value is now " + output);
            char c = string.charAt(i);
            // We can use this 'hack' to use ASCII values to check if we are a digit! 
            if (c >= '0' && c <= '9') {
                // c - '0' is the numeric value of the character c represents!
                output += c - '0';
                System.out.println("value is now " + output);
            } else {
                // Non-integer detected, safely exit!
                return -1;
            }

        }
        return output;
    }
    
    public static void main(String[] args) {
        Scanner keyboardInput = new Scanner(System.in);
        while (true) {
            // Get the next word, ending at whitespaces, that the user inputted
            String input = keyboardInput.next();
            System.out.println(stringToInt(input));
        }
    }
}