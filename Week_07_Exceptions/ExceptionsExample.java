import java.util.Scanner;
import java.util.InputMismatchException;

public class ExceptionsExample {
    public static int tryForInt() {
        while (true) {
            try {
                System.out.print("Enter a number: ");
                return scan.nextInt();
              
            } catch (InputMismatchException e) {
                // Problem: RuntimeException is a very generic type of exception
                // An InputMismatchException is a type of RuntimeException (child of it)
                System.out.println("Error! That wasn't a number!");
                scan.next();
            }
        }
    }

    public static int tryForPositiveInt() {
        while (true) {
            System.out.print("Enter a positive number: ");
            // hasNextInt() is a great alternative to having an InputMismatchException
            if(scan.hasNextInt()) {
                int i = scan.nextInt();
                if (i > 0) {
                    return i;
                } else {
                    System.out.println("Number must be positive");
                }
            } else {
                System.out.println("Error! That wasn't a number!");
                // A scanner maintains a buffer of text it has looked at, but not yet provided
                // if we have some invalid text, we need to clear that out of the buffer!
                // if we don't clear the scanner buffer using .next(), we'll end up in an infinite loop
                scan.next();
            }
        }
    }

    // So we don't have to pass the scanner!
    private static Scanner scan;

    public static void main(String[] args) {
        System.out.println(indexOf("Something", 'b'));
        // if we don't initialize, scanner will be null
        scan = new Scanner(System.in);
        int i = tryForPositiveInt();
        System.out.println("Your number is " + i);
        System.out.println("100 divided by your number is " + 100 / i);
    }

    public static int indexOf(String s, char c) {
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == c) {
                return i;
            }
        }
        throw new RuntimeException("Character " + c + " was not found in the string");
    }
}