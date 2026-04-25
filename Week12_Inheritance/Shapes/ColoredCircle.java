public class ColoredCircle extends Circle {
    private String color;

    public ColoredCircle(double x, double y, double r, String color) {
        super(x, y, r); // setup the numeric data
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}