import java.util.Scanner;
import java.lang.StringBuilder;

public class ToTitleCase {
    /*
    converts a word to title-case.
    takes String parameter and checks argument to ensure its first character is uppercase,
    if its a letter, and all other letters in the string are be lowercase.
    Return the capitalized version of the word.
    Assume all characters in the string are ASCII
    Since the assignment says we can assume ASCII, i'll forgo using the Character class and stick to direct ASCII comparisons
    */
    public static String convertToTitleCase(String s) {
        // if the string is empty, we can't handle it
        if(s.isEmpty()) return "";

        // get first character
        char firstC = s.charAt(0);

        final boolean isUpperCase = firstC >= 'A' && firstC <= 'Z';
        final boolean isLowerCase = firstC >= 'a' && firstC <= 'z';

        // if the first character is not a letter (neither uppercase NOR lowercase), we don't know how to handle this sentence
        // Apply DeMorgan's Law to simplify this!
        if(!(isUpperCase || isLowerCase)) {
            return "";
        }
        else if(isLowerCase) {        // if the first character is lowercase, convert it to uppercase
            // here we can use a nifty trick to make it uppercase
            // in ascii, the uppercase letters come 32 indexes before the lowercase letters
            // by subtracting 32, we can convert to uppercase!
            firstC -= 32;
        } // at this point, it must be uppercase, so we can move on


        // We can concatenate together the first Character and the lowercase version of the rest of the string for our output
        // .substring(1) gives us the rest of the string starting at index 1, and .toLowerCase converts it to lowercase (if it wasn't already)
        // I'll use StringBuilder for this, as this seems to be the newer and better way to do things
        // TODO: It might be better to rewrite this whole function using StringBuilder and the Character class actually!
        // create a new string builder with the length of our desired output
        StringBuilder result = new StringBuilder(s.length());

        result.append(firstC); // add the first character
        result.append(s.substring(1).toLowerCase()); // add the rest of the word!

        return result.toString(); // convert to string and finish!
    }

    public static void main(String[] args) {
        Scanner keyboardInput = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a word to convert to title-case: ");
            String input = keyboardInput.nextLine();
            System.out.println(convertToTitleCase(input));
        }
    }
}