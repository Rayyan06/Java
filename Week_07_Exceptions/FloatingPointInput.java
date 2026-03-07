import java.util.Scanner;
import java.util.InputMismatchException;    // Exceptions need to be imported

public class FloatingPointInput {
    // Set up a scanner as a member variable of the class
    // ensure it's a static variable so it's accessible from public methods
    private static Scanner scan;
    
    /*
    Attempts to get a floating point number (double) from the user
    */
    public static double tryForDouble() {
        // alternatively, we could use scan.hasNextDouble() as the loop condition here and go from there
        while (true) {
            try {
                System.out.print("Please enter a valid floating point number: ");
                // Note: Even if the user does not enter a decimal point, the scanner will still read in a double
                return scan.nextDouble(); 
            } catch (InputMismatchException ime) { // user entered string or other type instead of int
                System.out.println("Error! That wasn't a floating point number!");
                // Clear the scanner buffer to prevent infinite loop
                // Note: nextLine() is better than next() here, because if the user enters two words like "hello world", 
                // we don't want it to scan twice unnecessarily
                scan.nextLine();
            } 
        }
        // Note: we don't need another return statement here
    }

    public static void main(String[] args) {
        scan = new Scanner(System.in);
        double floatingPointNumber = tryForDouble();
        System.out.printf("You entered %.2f\n", floatingPointNumber);
        scan.close();
    }
}