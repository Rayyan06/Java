import java.util.Scanner;

public class PythagoreanMethodCalculator {

    public static double calculateHypotenuse(double a, double b) {
        return Math.sqrt(a * a + b * b);
    }

    // Uses the hypotenuse and one leg of a right triangle to calculate the other length of the other leg,
    // using the Pythagorean theorem.
    public static double calculateLeg(double hypotenuse, double otherLeg) {
        return Math.sqrt(hypotenuse * hypotenuse - otherLeg * otherLeg);
    }
    public static void main(String[] args) {
        // here we are defining and declaring a new variable "keyboard", of type "Scanner"
        // System.in refers to anything that we type into the console
        Scanner keyboard = new Scanner(System.in);
        System.out.print("What side do you want to solve for? (a, b, or c): ");
        // next() is an instance method
        String choice = keyboard.next();

        if(choice.equals("a") || choice.equals("b")) {
            System.out.print("Enter the value for the leg: ");
            int a = keyboard.nextInt();

            System.out.print("Enter the value for the hypotenuse: ");
            int c = keyboard.nextInt();

            // Problem: What if a > c?
            if (a >= c) {
                System.out.println("The leg can't be longer or the same length as the hypotenuse!");
            } else {
                // we need to use double here for decimals
                // sqrt() is a method of Math, it is a static method.
                double b = calculateLeg(c, a);
                System.out.println("The length of the other leg is " + b);
            }
        } else if (choice.equals("c")) {
            System.out.print("Enter the value for the first side: ");
            int a = keyboard.nextInt();

            System.out.print("Enter the value for the second side: ");
            int b = keyboard.nextInt();

            // we need to use double here for decimals
            double c = calculateHypotenuse(a, b);
            System.out.println("The length of the hypotenuse is " + c);
        } else {
            System.out.println("Invalid choice!");
        }

    }
}
