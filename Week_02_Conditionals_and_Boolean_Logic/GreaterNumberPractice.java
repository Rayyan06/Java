import java.util.Scanner;

public class GreaterNumberPractice {
    public static void main(String[] args) {
        // Create the scanner for user input
        // note: I added a few lines here so that the program actually prompts the user to input numbers
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the first number: ");
        int first = scanner.nextInt();
        System.out.print("Enter the second number: ");
        int second = scanner.nextInt();

        if (first > second) {
            System.out.println("The first number, " + first + ", is larger than the second, " + second);
        } else if (first < second) {
            System.out.println("The second number, " + second + ", is larger than the first, " + first);
        } else {
        // now we have exhausted the possibilities, so now the first number must be equal to the second.
            System.out.println("The first and second numbers are both equal to the same value: " + first);
        }
    }
}