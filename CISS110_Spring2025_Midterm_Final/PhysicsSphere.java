public class PhysicsSphere {
	public static final double G = 6.674e-11;

	private double mass;
	private double radius;
	private double x;
	private double y;
	
	public PhysicsSphere(double mass, double radius, double x, double y) {
		// Error 23 (Logic): When assigning variables with the same name as private members in the constructor, we must use the 'this' keyword
		this.mass = mass;
		this.radius = radius;
		this.x = x;
		this.y = y;
	}
	
	public boolean intersects(PhysicsSphere that) {	// Fix 13: Remove static keyword from intersects() function to align with use case
		// Error 13 (Compile): "this" keyword cannot be used in a non-static context
		double deltaX = this.x - that.x;
		double deltaY = this.y - that.y;

		// Error 5 (Compile): public access modifier cannot be assigned inside a method in Java, they only apply to members of the class directly
		// Error 14a (Compile): sqrt static method not defined, must reference from Math package
		double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

		// Error 25 (Logic): We're supposed to return true (intersects) if the distance is LESS than the sum of the radii, not greater than
		return distance < this.radius + that.radius;
	}
	
	/*
	Calculates the force on planet 'this' caused BY planet 'that'
	*/
	public ForceVector forceBetween(PhysicsSphere that) {
		// Error 15 (Compile): undefined variables deltaX, deltaY, distance, magnitude
		// Fix 15: add double type to them
		// Error 27 (Logic): this.x - x will always be 0, because this.x and x both refer to the same private variable
		// Fix 27: following the intended logic, (the attractive force vector should point FROM this TO that)
		double deltaX = that.x - this.x; 	
		double deltaY = that.y - this.y;
		// Error 14b (Compile): sqrt static method not defined, must reference from Math package
		double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
		// Error 16 (Compile): magnitude not defined in variable assignment
		// Fix 16 (Compile/Logic): Change to distance to align with gravitational formula
		double magnitude = G * this.mass * that.mass / (distance * distance); // parenthesis are needed here!

		// Error 17 (Compile): ForceVector is missing a constructor called below:
		return new ForceVector(magnitude * deltaX / distance, magnitude * deltaY / distance);
	}	
}
