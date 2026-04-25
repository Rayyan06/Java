public class Circle {
    private double centerX;
    private double centerY;
    private double radius;

    public Circle(double x, double y, double r) {
        centerX = x;
        centerY = y;
        radius = r;
    }

    // ACCESSORS 
    public double getArea() {
        return radius * radius * Math.PI; 
    }

    public double getX() {
        return centerX;
    }

    public double getY() {
        return centerY;
    }

    public double getRadius() {
        return radius;
    }

}