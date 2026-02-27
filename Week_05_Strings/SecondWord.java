import java.util.Scanner;

public class SecondWord {
    public static String getSecondWord(String s) {
        // How do we get the second word?
        // Find the first space
        int firstSpacePos = s.indexOf(' ');
        int secondSpacePos = s.indexOf(' ', firstSpacePos + 1); // Now we are starting "after" the first space
        
        // if first space position is bad, exit
        if (firstSpacePos < 0) {
            return "";       
        }
        // second space good, first space bad
        if (secondSpacePos < 0) {
            return s.substring(firstSpacePos + 1);
        }
        // System.out.println(firstSpacePos + ", " + secondSpacePos);
        // "Aw...."
        return s.substring(firstSpacePos + 1, secondSpacePos);
    }

    public static void main(String[] args) {
        Scanner keyboardInput = new Scanner(System.in);
        while (true) {
            // Get the next line! YESSS BRO.
            String input = keyboardInput.nextLine();
            System.out.println(getSecondWord(input));
        }
    }
}