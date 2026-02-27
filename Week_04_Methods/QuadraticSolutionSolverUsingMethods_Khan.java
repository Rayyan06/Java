/*
Quadratic Equation Solution Solver
This program asks the user to input the coefficients of the quadratic equation. 
Next, it identifies the number of unique (non-repeated) real solutions to the quadratic equation (0, 1, or 2).
(Note: all quadratics have two solutions, but they may be complex or repeated)
If solutions exist, the program must display the value of each solution to the user.

The program must also handle cases where a = 0, or where no solution exists.

The program does NOT handle edge cases where the user does not input a valid number, 
as this was not stated in the assignment directions.
This program does not handle complex solutions/roots to quadratics
*/
import java.util.Scanner; // for user input and output to the console terminal

public class QuadraticSolutionSolverUsingMethods_Khan {

    /*
    Solves quadratic equation (form: ax^2 + bx + c)
    Parameters a, b, and c are the coefficients of the quadratic.
    No return value, because we would have to return an object that contains both possible roots
    */
    public static void solveQuadraticEquation(double a, double b, double c) {
        /* 
        if the value of a == 0 then our equation is not a quadratic,
        and thus its solutions cannot be determined by the quadratic formula. 
        */
        if(a == 0) {
            System.out.println("Error: You entered a value of a = 0, so your equation is not a quadratic. ");
            System.out.println("This program only supports quadratics; No solutions.");
            return; // <= exit early without solving quadratic
        } // note that no else statement is required when we return from a method

        // a != 0

        /*
        the number of solutions of a quadratic equation can be determined by its discriminant D, 
        D = b^2 - 4ac, where a, b, and c are the coefficients of the quadratic.
        if D > 0, our quadratic has two distinct real roots -> 2 solutions
        if D = 0, our quadratic has one unique (two repeated) real-root -> 1 solution
        if D < 0, our quadratic has two complex (imaginary) roots -> 0 solution
        */

        // Call the static method we wrote to compute the discriminant
        double discriminant = calculateDiscriminant(a, b, c);

        if (discriminant > 0) {
            // Since D > 0, We have two distinct real solutions.
            /* 
            According to the quadratic formula, they are found by:
            x = (-b ± sqrt(D)) / (2*a), 
            where D is the discriminant, and a and b are coefficients
            */

            // To avoid repeating calculations, use the square root of the discriminant
            double rootD = Math.sqrt(discriminant);

            double positiveRoot = calculatePositiveRoot(a, b, rootD);
            double negativeRoot = calculateNegativeRoot(a, b, rootD);

            printQuadratic(a, b, c, 2);
            System.out.println("They are x1 = " + negativeRoot + " and x2 = " + positiveRoot);

        } else if (discriminant == 0) {
            // We have one unique solution
            double singleRoot = calculateSingleRoot(a, b);
            
            printQuadratic(a, b, c, 1);
            System.out.println("It is x = " + singleRoot);

        } else {
            // By exhausting the possibility that D >= 0, D must be less than 0, thus no real solutions exist
            printQuadratic(a, b, c, 0);
        }
    }

    // Evaluates the discriminant of a quadratic
    // The discriminant D = b^2 - 4ac
    public static double calculateDiscriminant(double a, double b, double c) {
        return b * b - 4.0 * a * c;
    }

    /* Calculates the "positive" root of a quadratic equation that has two roots 
    Parameters: 
    a, b are coefficients of the quadratic
    rootD is the square root of the discriminant.

    Note: the "positive" root isn't always going to be >0, 
    in this context, "positive" simply means we are using the positive sign
    in this equation:
        x = (-b ± sqrt(D)) / (2*a)
    for example, if a < 0, the root could very well be negative.
    */
    public static double calculatePositiveRoot(double a, double b, double rootD) {
        return (-b + rootD) / (2.0 * a);
    }
    
    /* Calculates the "negative" root of a quadratic equation that has two roots */
    public static double calculateNegativeRoot(double a, double b, double rootD) {
        return (-b - rootD) / (2.0 * a);
    }

    /* Calculates the single root of a quadratic equation that has only one root 
    Note that we don't need to know the value of D, because it must be 0 for a single root
    */
    public static double calculateSingleRoot(double a, double b) {
        /*
        When a quadratic equation has one solution (D = 0), 
        the solution to the quadratic formula simplifies to:
        x = -b/2a
        */
        return -b / (2.0 * a);
    }

    /* Prints the quadratic equation that the user inputted
    Handles sign, omits zero terms, hides coefficents of 1
    Example: 
    Your quadratic equation x^2 - 2x + 1 has 1 solution.
    */
    public static void printQuadratic(double a, double b, double c, int solutionCount) {
        System.out.print("Your quadratic equation ");
        
        // We don't want to print a coefficient of 1 i.e. 1x^2, 
        // so we check if a and b are equal to one, or negative one
        if(a == -1) {
            // for a = -1, we want it to print like -x^2
            System.out.print("-");
        } else if (a != 1) {            
            // If a == 1, we wouldn't write anything at all!
            System.out.print(a);
        }
        
        // u00b is the unicode for superscript
        System.out.print("x\u00b2 ");
        
        // Handle printing b
        if(b != 0) {            
            // We don't print 0 coefficients, as they make the entire term 0

            // First, print the sign
            // I used the ternary operator to clean the logic up
            System.out.print((b > 0) ? "+ " : "- ");

            // Now, print the value without the sign
            double absB = Math.abs(b);

            // We don't print the coefficient when b = -1 or +1, 
            if (absB != 1) {
                System.out.print(absB);
            }
            
            System.out.print("x ");
        }

        if (c != 0) {
            // similar logic to b, but without the "1" coefficient check
            System.out.print((c > 0) ? "+ " : "- ");
            System.out.print(Math.abs(c) + " ");
        }

        System.out.print("= 0 has ");

        if (solutionCount == 0) {
            System.out.print("no roots");
        }
        else if (solutionCount == 1) {
            System.out.print("one repeated solution");
        } else { // solution count is 2
            System.out.print("two solutions");
        }

        System.out.println(" in the real domain.");
    }

    public static void main(String[] args) {
        /* 
        We should not be doing any calculations in main(), besides user input
        */
        Scanner keyboardInput = new Scanner(System.in);

        System.out.println("This program calculates the solutions of a quadratic equation.");
        System.out.println("A quadratic equation is of the form ax\u00b2 + bx + c");
        System.out.println("You will enter the integer values for the coefficients of the quadratic (a, b, c).");

        /*
        Prompt the user for the values of a, b, and c

        note: I'm using double inputs here instead of the int inputs used in Week 2
        */
        System.out.print("Enter the value of a: ");
        double a = keyboardInput.nextDouble();
        System.out.print("Enter the value of b: ");
        double b = keyboardInput.nextDouble();
        System.out.print("Enter the value of c: ");
        double c = keyboardInput.nextDouble();

        // This method solves the quadratic and outputs the roots, we don't need to do anything else in main()
        solveQuadraticEquation(a, b, c);

        keyboardInput.close();
    }
}