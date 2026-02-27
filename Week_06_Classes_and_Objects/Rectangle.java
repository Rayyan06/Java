public class Rectangle {
    // Private member variables (Instance variables)
    // We can't modify these directly from outside the class
    private double x;
    private double y;
    private double width;
    private double height;

    // Constructor
    public Rectangle(double x, double y, double width, double height) {
        // Assign the private members to the values passed in as the parameters.
        // Notice how we need this.[] because of the scoping rules of constructors!
        this.x = x;
        // if we don't use the parameter of x, we could just say:
        // x = horizontalPosition
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public Rectangle() {
        this.width = 1;
        this.height = 1;
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    // Getters/Accessors
    public double getX() {
        // this.x is not needed here to access this
        // Scoping rules! 
        return x;
    }
    public double getY() {
        return y;
    }
    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }
    public double getArea() {
        return width * height;
    }
    // Setter/Mutator
    public void setX(double x) {
        this.y = y;
    }
    public void setY(double newY) {
        y = newY;
    }
    // we could make these private by default, technically
    public void setWidth(double width) {
        this.width = width;
    } 
    public void setHeight(double height) {
        this.height = height;
    } 
    public void scale(double amount) {
        width *= amount;
        height *= amount;
    }
    public void move(double deltaX, double deltaY) {
        x += deltaX;
        y += deltaY;
    }

    // returns true if the provided coordinates fall inside the rectangle
    public boolean contains(double x, double y) {
        return x >= this.x && x <= this.x + width
            && y >= this.y && y <= this.y + height;
    }

    // "this" is a special keyword in java, that is not
    public boolean overlaps(Rectangle that) {
        /*
        one rectangle will have a corner somewhere in the other rectangle
        we could call the contains() method 8 times
        but that's a little needlessly complicated
        instead, we could say that the horizontal coordinate of one rectangle is between one end and the other, same for vertical
        */
        boolean overlapX = (that.x >= this.x && that.x <= this.x + width) 
                        || (this.x <= that.x && this.x <= that.x + width);
        boolean overlapY = (that.y >= this.y && that.y <= this.y + height) 
                        || (this.y <= that.y && this.y <= that.y + height);
        return overlapX && overlapY;
    }

    // why is this an object? because we are redefining equals(Object)
    public boolean equals(Object other) {
        // other could be any arbitrary object
        if(!(other instanceof Rectangle)) {
            return false;
        }
        // now, we know at runtime that other is a Rectangle
        // but the variable type is still Object, so we must cast
        Rectangle otherRect = (Rectangle) other;

        // now we can check all instance variables to test if this rectangle is same!
        return otherRect.x == this.x
            && otherRect.y == this.y
            && otherRect.width == this.width
            && otherRect.height == this.height;
    }

    // override the toString() method (default: returns class name + @ + memory address)
    public String toString() {
        return String.format("Rectangle from (%.2f, %.2f) to (%.2f, %.2f)", x, y, x + width, x + height); 
    }

    // A static method... in the same class?
    // We can still access member variables!
    public static void main(String[] args) {
        Rectangle r = new Rectangle(); // from 0,0 to 1, 1
        Rectangle r2 = new Rectangle(3, 7); // from 0,0 to 3, 7
        Rectangle r3 = new Rectangle(1, 2, 6, 8); // from 1,2 to 7,10
        Rectangle r4 = new Rectangle(0, 0, 1, 1); // being very explicit here
        Rectangle r5 = r;

        if (r2.overlaps(r3)) {
            System.out.println("There's an overlap!");
        } else {
            System.out.println("They're seperated");
        }

        // make sure we define equality!
        if(r.equals(r4)) {
            System.out.println("they're equal!");
        } else {
            System.out.println("they're not equal");
        }

        if(r == r4) {
            System.out.println("they're the same object");
        } else {
            System.out.println("they're not the same object");
        }
        if(r == r5) {
            System.out.println("r and r5 are the same object");
        } else {
            System.out.println("r and r5 are not the same object");
        }

        System.out.println(r5.getArea());
        r.scale(2.0);
        System.out.println(r5.getArea());

        System.out.println(r);
        r.x = 2;
        r.y = 2.5;
        
        // Here we would get a NullPointerException
        // r = null;
        // r.scale(10);
        // Later null's will be quite useful      
    
    }
}