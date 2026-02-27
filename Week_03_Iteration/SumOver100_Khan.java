/*
Write a program that repeatedly prompts the user to enter numbers, until the sum of the numbers exceeds 100. 
Once the sum of the numbers is greater than or equal to 100,
 print the sum, and also print the average of all numbers entered.
*/
import java.util.Scanner;

public class SumOver100_Khan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize a variable for the current sum
        // note that we do not need a second variable for the total sum
        int sum = 0;

        // Initialize a variable to count the amount of numbers entered
        int count = 0;
        
        // Condition: Stop when the sum is greater than or equal to 100 
        // Equivalent Statement: keep going as long as the sum is under 100
        while (sum < 100) {
            System.out.print("Enter next number: ");
            int next = scanner.nextInt();

            count++;

            // Add the number entered by the user to the sum
            sum += next;

            // Output the current sum, but only if our sum is less than 100
            // otherwise, it's reduntant.
            /*
            Annoyingly, the following if-statement adds a second condition check to our program
            but this is the only simple way I could think of that would meet the exact output in the given example, 
            where the program does not repeat the current sum on the last iteration when the sum is over 100.

            There is another solution I can think of, where we would put the "Current Sum" print statement 
            at the start of the while loop BEFORE we increment the sum, but that would add additional complexity
            */
            if (sum < 100) {
                System.out.println("Current sum is " + sum);
            }
        }

        System.out.println("Sum of all numbers is: " + sum);

        // Calculate the average
        // Ensure we type cast int->double to avoid integer truncation when dividing
        double average = (double)(sum) / (double)(count);

        System.out.println("Average of all numbers is: " + average);
    }
}