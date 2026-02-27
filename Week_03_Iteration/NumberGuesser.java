// collect numbers of a certain quality
import java.util.Scanner;

public class NumberGuesser {
    public static void main(String[] args) {
        // random number between 0 and 100
        int max = 99;
        int min = 0;
        int secret = (int) (Math.random() * (max + 1));
        int count = 0;

    
        Scanner scanner = new Scanner(System.in);
        int guess = -1;

        while (guess != secret) {
            System.out.print("Guess a number between " + min + " and " + max + ": ");
            guess = scanner.nextInt();
            if(guess > max || guess < min) {
                System.out.println("Out of bounds!");
            } else if(guess > secret) {
                System.out.println("Too high!");            
                count += 1;
                max = guess - 1;
            } else if (guess < secret) {
                System.out.println("Too low!");
                count += 1;
                min = guess + 1;
            }
        }

        System.out.println("Correct after " + count + " guesses!");
    }
}