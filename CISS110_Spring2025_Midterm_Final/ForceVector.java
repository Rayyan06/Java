// Error 6 (Compile): Extra parenthesis after Java class name definition, () are only used for class methods.
public class ForceVector {
	private double horizontal;
	private double vertical;
	// Fix 17: add constructor
	public ForceVector(double horizontal, double vertical) {
		this.horizontal = horizontal;
		this.vertical = vertical;
	}
	public ForceVector add(ForceVector that) {
		return new ForceVector(this.horizontal + that.horizontal, this.vertical + that.vertical);
	}
	
	// Fix 29: Add negation method
	public ForceVector negate() {
		return new ForceVector(-this.horizontal, -this.vertical);
	}

	// Error 18 (Compile): mismatched return type
	public String toString() {
		// Error 19 (Compile): magnitude, angle not defined
		// Error 14c (Compile): sqrt static method not defined, must reference from Math package
		double magnitude = Math.sqrt(horizontal * horizontal + vertical * vertical);

		// Error 30 (Logic Error): If the magnitude of a resultant force is 0, we will have a ZeroDivisionError
		double angle = 0.0;		// Fix 30: Initialize angle to a default value before setting it
		if (magnitude != 0) {
			angle = Math.acos(horizontal / magnitude);
		}

		return String.format("Force of %f at an angle of %f", magnitude, angle);
	}
}
