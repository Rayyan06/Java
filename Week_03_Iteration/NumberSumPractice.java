// Calculate the sum of all integers from 1 to max
/* Examples:
If max is 10, then 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10 = 55
If max is 1, then 1

// Note, this should be equal to max(max + 1)/2
*/
import java.util.Scanner;

public class NumberSumPractice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Get the max from  the user
        System.out.print("Enter the maximum value to sum upto: ");
        int max = scanner.nextInt();
        // Make a variable to hold the sum
        int sum = 0;
        // start at 1 and include max, because our range to sum over is [1, max]
        for(int i = 1; i <= max; i++) {
            // add the current integer to the sum
            sum += i;
        }
        // output the sum to the user
        System.out.println("The sum of all integers from 1 to " + max + " is " + sum);
    }
}

