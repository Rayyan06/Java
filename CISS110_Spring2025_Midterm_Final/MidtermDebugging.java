/*
Midterm

Three types of errors will be categorized and labelled below, as they are debugged.
1. Compile Errors (e.g. Syntax Errors)
2. Runtime Errors
3. Logic Errors

Whenever a compile or runtime error is debugged, the mistake/error will be pointed out and corrected.
For logic errors,  modifications will need to be made to code that is syntactically correct, 
so additional elaboration will be required explaining why the previous logic is invalid justifying the changes,
and the fix will be clearly labelled.

Errors will be numbered in the order that they are encountered.
Explanations will be provided where the code logic is unclear.
*/

import java.util.Scanner;

public class MidtermDebugging {

	// Error 4 (Compile): Missing import statement for Scanner class
	private static Scanner scanner;	// Fix 7: Declare scanner as static (and private too, for good OOP practice)

	public static double inputDouble() {
		// Error 1 (Compile): Missing closing Parenthesis of while conditional
		// Error 7 (Compile): the non-static variable scanner is referenced from a static context
		// Error 20 (Runtime): scanner is null
		// Error 22 (Logic): The while loop is infinite, 
		// because whatever "garbage" remaining in the buffer persists through iterations of the loop
		while(!scanner.hasNextDouble()) {
			System.out.println("Invalid!");
			scanner.nextLine(); // Fix 22: flush the buffer!
		}
		return scanner.nextDouble();
	}
	
	// Fix 10: inputPositive() must be static
	public static double inputPositive() {
		// Error 8 (Compile): Value is not defined
		double value = inputDouble(); // Fix 8: Define value from user input scanner double
		// Error 21 (Logic): The while loop is infinite, and is only true if the input is valid (positive). 
		while(value <= 0) {	// Fix 21a: Change loop condition to check for invalid mass input. mass = 0 is also invalid here.
			System.out.println("Value must be positive!");
			value = inputDouble();	// Fix 21b: Re-request input
		}
		return value;
	}

	public static PhysicsSphere inputSphere() {
		// Error 2 (Compile): Missing semicolons at the end of every statement below
		// Error 9 (Compile): Incorrect type for inputDouble() variable assignments
		System.out.println("Please enter the horizontal position of the object");
		double x = inputDouble();
		System.out.println("Please enter the vertical position of the object");
		double y = inputDouble();
		System.out.println("Please enter the mass of the object");
		// Error 10 (Compile): non-static method inputPositive() referenced from static inputSphere()
		double mass = inputPositive();
		System.out.println("Please enter the radius of the object");
		double radius = inputPositive();

		// Error 11 (Compile): Missing return statement to return new PhysicsSphere constructor
		return new PhysicsSphere(mass, radius, x, y);
	}

	public static void main(String[] args) {
		// Error 3 (Compile): Missing semicolon at the end of every println method call below
		// Error 12 (Compile): Incompatible return types for return statements below (bool vs. void)
		// Fix 20: Assign scanner to new Scanner class
		scanner = new Scanner(System.in);

		System.out.println("First object");
		PhysicsSphere p1 = inputSphere();

		System.out.println("Second object");
		PhysicsSphere p2 = inputSphere();
		if(p1.intersects(p2)) {
			System.out.println("Error: object 2 intersects object 1");
			return;
		}

		System.out.println("Third object");
		PhysicsSphere p3 = inputSphere();

		// Error 24 (Logic Error): We're supposed to check if Object 1 intersects object 3, we already checked 1 & 2
		if(p1.intersects(p3)) {
			System.out.println("Error: object 3 intersects object 1");
			return;
		}
		if(p2.intersects(p3)) {
			System.out.println("Error: object 3 intersects object 2");
			return;
		}
		/* Assuming the notation used here means:
		Fxy - Force on planet x caused by planet y
		*/
		ForceVector force12 = p1.forceBetween(p2);	// Force on planet 1 by planet 2
		ForceVector force23 = p2.forceBetween(p3);	// Force on planet 2 by planet 3
		// Error 28 (Logic Error): We already found the force between planets 2 and 3
		ForceVector force31 = p3.forceBetween(p1);	// Force on planet 3 by planet 1

		/*
		Following this convention, the force on planet 1 from planets 2 and 3 =
		force on planet 1 from 2 + force on planet 1 from 3, or
		F12 + F13
		*/

		// Error 29 (Logic Error): The forces we are adding to the first force have incorrect direction
		// F13 = -F31
		System.out.println("Total force on object 1: " + force12.add(force31.negate()));
		System.out.println("Total force on object 2: " + force23.add(force12.negate()));
		System.out.println("Total force on object 3: " + force31.add(force23.negate()));
	}
}
