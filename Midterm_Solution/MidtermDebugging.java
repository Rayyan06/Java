import java.util.Scanner; // FIX: import missing class

public class MidtermDebugging {

	// Compile Error: The Scanner class is not included in the java.lang package, it must be imported from java.util
	private static Scanner scanner;	// FIX: declare scanner as static

	public static double inputDouble() {
		// Syntax Error: missing closing paranthesis for while conditional
		// Compile Error: scanner must be declared static in order for us to use it inside our static method
		// Solutions: (1) remove static from inputDouble(), (2) declare scanner variable as static
		// Solution (2) is preferred due to program context, to avoid restructuring
		while(!scanner.hasNextDouble()) {		// Fix: Add Parenthesis
			System.out.println("Invalid!");
			scanner.nextLine(); // flush the buffer
		}
		return scanner.nextDouble();
	}
	
	// Compile Error: inputPositive() is called from static methods, yet is not declared as static
	public static double inputPositive() {	// FIX: declare inputPositive() as static
		
		double value = inputDouble(); // FIX: Define value before entering loop

		// Compile Error: Value not defined
		// RUNTIME error: this is an infinite loop if we have a valid input
		// FIX: change loop condition to check for INVALID input
		while(value <= 0) { // Note: mass or radius of zero is also an undesirable input, as it would cause problems later in the force calculation
			System.out.println("Value must be positive!");
			// Ask user to input again until we have a valid (positive) input
			value = inputDouble();
		}
		return value;
	}

	public static PhysicsSphere inputSphere() {
		// Syntax Errors: Missing semicolon at the end of statements
		System.out.println("Please enter the horizontal position of the object"); // Fix: Add semicolon (repeat for lines below)
		// Compile Error: Incompatible types
		// FIX: Change the type of the variables x, y, mass, and radius below to double, as defined in the constructor for PhysicsSphere
		double x = inputDouble();
		System.out.println("Please enter the vertical position of the object");
		double y = inputDouble();
		System.out.println("Please enter the mass of the object");
		double mass = inputPositive();
		System.out.println("Please enter the radius of the object");
		double radius = inputPositive();
		// Compile Error: Symbol not found
		// FIX: add missing return statement and new keyword to newly constructed PhysicsSphere object
		return new PhysicsSphere(mass, radius, x, y);
	}

	public static void main(String[] args) {
		// Runtime Error: the scanner is null
		scanner = new Scanner(System.in); // FIX: Initialize scanner
		// Compile Errors: main() return type is void, yet boolean return statements exist below
		// FIX: Remove boolean return value
		System.out.println("First object"); // add missing semicolon, repeat below
		PhysicsSphere p1 = inputSphere();

		System.out.println("Second object");
		PhysicsSphere p2 = inputSphere();
		if(p1.intersects(p2)) {
			System.out.println("Error: object 2 intersects object 1");
			return;
		}

		System.out.println("Third object");
		PhysicsSphere p3 = inputSphere();
		if(p1.intersects(p3)) { // Logic error: we should really be checking p1 and p3 here
			System.out.println("Error: object 3 intersects object 1");
			return;
		}
		if(p2.intersects(p3)) {
			System.out.println("Error: object 3 intersects object 2");
			return;
		}
		// Presumably, the notation here is force on [this] FROM [that]
		ForceVector force12 = p1.forceBetween(p2);	// Force on Planet 1 FROM planet 2
		ForceVector force23 = p2.forceBetween(p3);  // Force on Planet 2 FROM planet 3

		// Logic Error: the force on planet 3 from planet 1 should not reference planet 2
		ForceVector force31 = p3.forceBetween(p1); // FIX: change p2 to p1, force on planet 3 FROM planet 1
		
		// Total force on planet 1 = force on planet 1 from planet 2 + force on planet 1 from planet 3
		// the force on planet 1 from planet 3 = the negative of the force on planet 3 from planet 1
		// therefore, lets add a negate() method to the force class as well, a reasonable clean solution
		System.out.println("Total force on object 1: " + force12.add(force31.negate()));
		// total force on planet 2 = force on planet 2 from planet 3 + force on planet 2 from planet 1
		System.out.println("Total force on object 2: " + force23.add(force12.negate()));
		// total force on planet 3 = force on planet 3 from planet 1 + force on planet 3 from planet 2
		System.out.println("Total force on object 3: " + force31.add(force23.negate()));
	}
}
