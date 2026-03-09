public class ForceVector() {
	private double horizontal;
	private double vertical;
	
	public ForceVector add(ForceVector that) {
		return new ForceVector(this.horizontal + that.horizontal, this.vertical + that.vertical);
	}
	
	public ForceVector toString() {
		magnitude = sqrt(horizontal * horizontal + vertical * vertical);
		angle = math.acos(horizontal / magnitude);
		return String.format("Force of %f at an angle of %f", magnitude, angle);
	}
}
