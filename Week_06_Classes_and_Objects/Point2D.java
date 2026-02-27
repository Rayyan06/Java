// A point in two-dimensional space

public class Point2D {
    // Member variables
    // x & y represent the ordered pair (x, y), that give the point's spatial x and y coordinates
    private double x;
    private double y;

    // Constructor
    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Instance Methods
    // Getters (Accessors)
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    // Mutators
    // swaps the value of the coordinates
    public void swapXY() {
        // Save the value of x for assignment later to y
        double prevX = x;
        x = y;
        y = prevX;
    }

    // It would be nice if we could get the distance to another point as well!
    public double getDistance(Point2D that) {
        // The math library gives us a hypotenuse function, conveniently!
        return Math.hypot(x - that.x, y - that.y);
    }

    // convenient for testing
    public String toString() {
        return String.format("Point: (%.2f, %.2f)", x, y);
    }

    // Just to test to see if it all works!
    public static void main(String[] args) {
        Point2D p = new Point2D(3, 7);
        Point2D q = new Point2D(7, 10);

        System.out.println(p); // test toString()
        System.out.println(String.format("Q: (%.2f, %.2f)", q.getX(), q.getY())); // test getters
        
        System.out.println(String.format("The distance from P to Q is %.2f", p.getDistance(q)));

        q.swapXY(); // test setters
        System.out.println(q);
    }
}