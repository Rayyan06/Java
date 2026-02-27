/*
Quadratic Equation Solution Solver
This program asks the user to input the coefficients of the quadratic equation. 
Next, it identifies the number of unique (non-repeated) real solutions to the quadratic equation (0, 1, or 2).
(Note: all quadratics have two solutions, but they may be complex or repeated)
If solutions exist, the program must display the value of each solution to the user.

The program must also handle cases where a = 0, or where no solution exists.

The program does NOT handle edge cases where the user does not input a valid number, 
as this was not stated in the assignment directions.
*/
import java.util.Scanner; // for user input and output to the console terminal

public class QuadraticSolutionSolver {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("This program calculates the solutions of a quadratic equation.");
        System.out.println("A quadratic equation is of the form ax^2 + bx + c");
        System.out.println("You will enter the integer values for the coefficients of the quadratic (a, b, c).");

        // Prompt the user for the values of a, b, and c
        /*
        note: we are using int inputs here, 
        so any decimal input for the coefficients will throw an error
        I wasn't sure if we were supposed to use int or double for the inputs to the quadratic coefficients, 
        and we have always used int so far for numerical user inputs
        so I stuck with int.
        */
        System.out.print("Enter the value of a: ");
        int a = input.nextInt();
        System.out.print("Enter the value of b: ");
        int b = input.nextInt();
        System.out.print("Enter the value of c: ");
        int c = input.nextInt();

        /* 
        IMPORTANT: if the value of a == 0 then our equation is NOT a quadratic,
         and thus its solutions cannot be determined by the quadratic formula. 
         */
        if(a == 0) {
            System.out.println("Error: You entered a value of a = 0, so your equation is not a quadratic. ");
            // now communicate to the user that they are no solutions
            // technically, there still must/might be solutions to an equation like bx + c = 0
            // but this program only handles quadratics, not linear equations.
            System.out.println("This program only supports quadratics; No solutions.");

        } else { // a != 0, so continue
            /*
            the number of solutions of a quadratic equation can be determined by its discriminant D, 
            D = b^2 - 4ac, where a, b, and c are the coefficients of the quadratic.
            if D > 0, our quadratic has two distinct real roots -> 2 solutions
            if D = 0, our quadratic has one unique (two repeated) real-root -> 1 solution
            if D < 0, our quadratic has two complex (imaginary) roots -> 0 solution
            */
            // order of operations ensures we don't need parenthesis here
            // IMPORTANT: a, b, and c are integers so we have to use "4.0" instead of "4" to avoid integer truncation
            double D = b * b - 4.0 * a * c;  

            if(D > 0) {
                /* 
                We have two distinct real solutions.
                According to the quadratic formula, they are found by:
                x = (-b ± sqrt(D)) / (2*a), 
                where D is the discriminant, and a and b are coefficients
                */
                // calculate the square root of the determinant first
                // so that we don't have to calculate it twice
                double rootD = Math.sqrt(D);    
                double solutionOne = (-b - rootD) / (2.0 * a);
                double solutionTwo = (-b + rootD) / (2.0 * a);

                System.out.println("Your quadratic equation " + a + "x^2 + " + b + "x + " + c + " has 2 solutions.");
                System.out.println("They are x1 = "
                    + solutionOne + " and x2 = " 
                    + solutionTwo);
                
            } else if (D == 0) {
                /* 
                When a quadratic equation has one solution (D = 0), 
                the solution to the quadratic formula simplifies to:
                x = -b/2a
                */
                double solution = -b / (2.0 * a);
                System.out.println("Your quadratic equation " + a + "x^2 + " + b + "x + " + c + " has 1 solution");
                System.out.println("It is x = " + solution);

            } else {
                // By exhausting the possibility that D >= 0, D must be less than 0, thus no real solutions exist
                System.out.println("Your quadratic equation " + a + "x^2 + " + b + "x + " + c + " has no solutions in the real domain.");
            }
        }
    }
}