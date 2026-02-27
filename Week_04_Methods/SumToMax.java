/*
Calculate the sum of all numbers from 1 up to a specified maximum.
Instead of taking the maximum value from the terminal and printing the result,
write a static method that takes the maximum value as a parameter
and returns the sum as its return value
*/
import java.util.Scanner;

public class SumToMax {
    // Calculates the sum of all numbers from 1 up to a specified maximum
    // i.e. 1 + 2 + 3 + ... + max
    // also known as: triangular sum
    // Parameter: max, the specified maximum
    // Returns: The calculated sum
    public static int calculateSumUpToMax(int max) {
        // Argument must be a positive integer, 1 or greater
        if (max <= 0) {
            // Return a "dummy" sum if out of bound.
            System.out.println("Note: the specified maximum should be a positive integer >= 1.");
            return 0;            // <= exit early, the code after this never runs
        }
        // We can reuse variable names such as sum and max
        // that we previously used in main() because it is a different method
        int sum = 0;
        for (int num = 1; num <= max; num++) {
            sum += num;
        }
        return sum;
    }
    public static void main(String[] args) {
        // Instantiate a new instance of Java's Scanner class
        Scanner keyboardInput = new Scanner(System.in);

        System.out.print("Enter the maximum value to sum up to: ");
        // Grab the maximum value from the user by running the instance method .nextInt()
        int max = keyboardInput.nextInt();
        // Run the static method that we wrote earlier
        int sum = calculateSumUpToMax(max);

        // output the sum to the user
        System.out.println("The sum of all integers from 1 to " + max + " is " + sum);

        // We can also close the Scanner stream by running another method!
        keyboardInput.close();
    }
}