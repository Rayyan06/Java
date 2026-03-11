public class PhysicsSphere {
	public static final double G = 6.674e-11;

	private double mass;
	private double radius;
	private double x;
	private double y;
	
	public PhysicsSphere(double mass, double radius, double x, double y) {
		// Logic Error: We must use the this keyword if we have the same parameter
		// names as the private variables
		this.mass = mass;
		this.radius = radius;
		this.x = x;
		this.y = y;
	}
	
	// Compile Error: the intersects() function is written like a non-static function, but it has the keyword static
	public boolean intersects(PhysicsSphere that) {	// FIX: Remove static keyword
		double deltaX = this.x - that.x;
		double deltaY = this.y - that.y;

		// Compile Error: a variable cannot be declared public inside a class method. 
		// The scope of the "distance" variable is exclusively restricted to the "intersects" method.
		double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);	// FIX: remove "public" access modifier keyword
		// Note: There is no logic error here, even though we don't have parenthesis 
		// because Java executes arithmetic ops before relational ops
		
		// Logic Error: intersects should return true if distance is LESS than the sum of the radii
		// FIX: change > to <
		return distance < this.radius + that.radius;
	}
	
	public ForceVector forceBetween(PhysicsSphere that) {
		// Compile Errors: deltaX and deltaY, distance, magnitude were never defined
		// FIX: declare them as doubles

		// LOGIC errors: this.x - x will always be equal to zero since they refer to the same variable
		// FIX: replace x, y with that.x, that.y
		double deltaX = this.x - that.x;	
		double deltaY = this.y - that.y;
		// Compile Error: sqrt() method is not defined
		double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY); // FIX: it is found in the Math package
		// Compile and Logic Error: Magnitude is not defined yet.
		double magnitude = G * this.mass * that.mass / (distance * distance); // FIX: replace the magnitude with distance to match gravity formula

		// Compile Error: ForceVector is missing a constructor!
		return new ForceVector(magnitude * deltaX / distance, magnitude * deltaY / distance);
	}	
}
