import java.util.Scanner; // FIX: import missing class

public class MidtermDebugging {

	// Compile Error: The Scanner class is not included in the java.lang package, it must be imported from java.utils
	static Scanner scanner;	// FIX: declare scanner as static

	public static double inputDouble() {
		// Syntax Error: missing closing paranthesis for while conditional
		// Compile Error: scanner must be declared static in order for us to use it inside our static method
		// Solutions: (1) remove static from inputDouble(), (2) declare scanner variable as static
		// Solution (2) is preferred due to program context, to avoid restructuring
		while(!scanner.hasNextDouble()) {		// Fix: Add Parenthesis
			System.out.println("Invalid!");
		}
		return scanner.nextDouble();
	}
	
	public double inputPositive() {
		// Compile Error: value was never defined in the method

		double value = scanner.nextDouble(); // FIX: define value variable before entering loop
		while(value < 0) {	// FIX: change loop condition to true, removing reference to nonexistent variable
			// FIX: Add logic to get value and return result if value is valid (positive)
			double value = scanner.nextDouble(); 
			if(value > 0) {
				return value;
			}
			System.out.println("Value must be positive!");
		}

		return value
	}

	public static PhysicsSphere inputSphere() {
		// Syntax Errors: Missing semicolon at the end of statements
		System.out.println("Please enter the horizontal position of the object"); // Fix: Add semicolon (repeat for lines below)
		int x = inputDouble();
		System.out.println("Please enter the vertical position of the object");
		int y = inputDouble();
		System.out.println("Please enter the mass of the object");
		int mass = inputPositive();
		System.out.println("Please enter the radius of the object");
		int radius = inputPositive();
		PhysicsSphere(mass, radius, x, y);
	}

	public static void main(String[] args) {
		System.out.println("First object"); // add missing semicolon, repeat below
		PhysicsSphere p1 = inputSphere();

		System.out.println("Second object");
		PhysicsSphere p2 = inputSphere();
		if(p1.intersects(p2)) {
			System.out.println("Error: object 2 intersects object 1");
			return false;
		}

		System.out.println("Third object");
		PhysicsSphere p3 = inputSphere();
		if(p1.intersects(p2)) {
			System.out.println("Error: object 3 intersects object 1");
			return false;
		}
		if(p2.intersects(p3)) {
			System.out.println("Error: object 3 intersects object 2");
			return false;
		}
		
		ForceVector force12 = p1.forceBetween(p2);
		ForceVector force23 = p2.forceBetween(p3);
		ForceVector force31 = p3.forceBetween(p2);

		System.out.println("Total force on object 1: " + force12.add(force31));
		System.out.println("Total force on object 2: " + force23.add(force12));
		System.out.println("Total force on object 3: " + force31.add(force23));
	}
}
