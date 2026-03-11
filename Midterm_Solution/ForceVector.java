// Syntax Error: A class is not declared with parenthesis(), only methods are
public class ForceVector {	// FIX: remove parenthesis ()
	private double horizontal;
	private double vertical;
	
	// FIX: add constructor to force vector
	public ForceVector(double horizontal, double vertical) {
		this.horizontal = horizontal;
		this.vertical = vertical;
	}
	
	public ForceVector add(ForceVector that) {
		return new ForceVector(this.horizontal + that.horizontal, this.vertical + that.vertical);
	}
	
	public ForceVector negate() {
		return new ForceVector(-this.horizontal, -this.vertical);
	}
	// Compile Error: toString() returns a string, yet its return type is ForceVector
	// FIX: replace ForceVector return type with String
	public String toString() {
		// Compile Error: the sqrt and acos methods reside in the Math library
		// FIX: place Math. before math method calls
		// Compile Error: magnitude and angle are not defined 
		// FIX: add double type keyword 
		double magnitude = Math.sqrt(horizontal * horizontal + vertical * vertical); 
		double angle = Math.acos(horizontal / magnitude);
		return String.format("Force of %f at an angle of %f", magnitude, angle);
	}
}
