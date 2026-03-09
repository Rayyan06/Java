public class PhysicsSphere {
	public static final double G = 6.674e-11;

	private double mass;
	private double radius;
	private double x;
	private double y;
	
	public PhysicsSphere(double mass, double radius, double x, double y) {
		mass=mass;
		radius=radius;
		x=x;
		y=y;
	}
	
	public static boolean intersects(PhysicsSphere that) {
		double deltaX = this.x - that.x;
		double deltaY = this.y - that.y;

		// Compile Error: a variable cannot be declared public inside a class method. 
		// The scope of the "distance" variable is exclusively restricted to the "intersects" method.
		double distance = sqrt(deltaX * deltaX + deltaY * deltaY);	// FIX: remove "public" access modifier keyword
		return distance > this.radius + that.radius;
	}
	
	public ForceVector forceBetween(PhysicsSphere that) {
		deltaX = this.x - x;
		deltaY = this.y - y;
		distance = sqrt(deltaX * deltaX + deltaY * deltaY);
		magnitude = G * this.mass * that.mass / magnitude * magnitude;
		return new ForceVector(magnitude * deltaX / distance, magnitude * deltaY / distance);
	}	
}
