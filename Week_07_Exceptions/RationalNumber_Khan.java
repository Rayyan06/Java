import java.util.Scanner;

/*
Represents a Rational Number 
A rational number is any number that can be expressed as a fraction p/q of two integers.
*/
public class RationalNumber_Khan {
    private int numerator;
    private int denominator;

    // constructor
    public RationalNumber_Khan(int numerator, int denominator) {
        if (denominator == 0) {
            // arithmeticexception is an unchecked exception, we don't need the throws declaration
            throw new ArithmeticException("Error! Zero division: The denominator cannot be equal to zero");
        } else if (denominator < 0) {
            /* We want to modify the sign of the parameters appropriately if the denominator is negative
            let the numerator hold the sign of the number and not the denominator
            user enters a negative numerator and denominator => should both be turned positive
            user enters a positive numerator and negative denominator => numerator should be negative, denominator positive
            Notice: We just have to invert both their signs! 
            */
            this.numerator = -numerator;
            this.denominator = -denominator;
        } else {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }

    // accessors
    // retrieve member variables
    public int getNumerator() {
        return numerator;
    }
    public int getDenominator() {
        return denominator;
    }

    // retrieves the floating-point value of the rational number
    public double getValue() {
        // we must cast to double BEFORE division
        return (double) numerator / (double) denominator;
    }

    /*
    Prints rational number as quotient of two integers
        Format: [numerator]/[denominator]
        %d is a decimal integer
    */
    public String toString() {
        return String.format("%d/%d", numerator, denominator);
    }
    
    // ensure non-numeric non-integer input is rejected
    public static int tryForIntegerInput(Scanner scan) {
        // Alternatively, we could use a try-catch block
        while(!scan.hasNextInt()) {
            System.out.print("Error! That wasn't a number! Try again: ");
            scan.nextLine();            // flush the buffer
        }
        
        return scan.nextInt();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the integer value of the numerator: ");
        int numerator = tryForIntegerInput(scan);
        System.out.print("Enter the integer value of the denominator: ");
        int denominator = tryForIntegerInput(scan); // do not prevent the user from entering a denominator of zero

        // instantiate our rational number object
        // we must catch the possible ArithmeticException here
        try {
            RationalNumber_Khan num1 = new RationalNumber_Khan(numerator, denominator);      
            // print the rational number and its floating-point value to 3 decimal places
            System.out.printf("Fraction: %s; Decimal: %.3f", num1, num1.getValue());
        } catch (ArithmeticException ae) {
            System.out.println("Error! The denominator of the rational number cannot be zero.");
            // Note: The program instructions did not specify whether we should let the user try again if they entered 0
        }

        scan.close();
    }
}