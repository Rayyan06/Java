// ask for ten numbers, then determine which one is biggest
import java.util.Scanner;

public class FindLargest {
    public static void main(String[] args) {
        // Make a variable to hold the biggest number so far
        int largest = 0;

        // scanner to ask for user input
        Scanner scanner = new Scanner(System.in);

        // Repeat ten times:
        for(int i = 0; i < 10; i++) {
            System.out.print("Enter the next number: ");
            int next = scanner.nextInt();
            // Check to see if the new number is larger than the current largest stored in the variable
            if(next > largest) {
                // if so, we have found our new largest number
                largest = next;
            }
        }
        // Print out the current largest
        System.out.println("The largest number is " + largest);
    }
}