public class MethodExamples {
    public static void extremelySimpleMethod() {
        System.out.println("This method takes no parameters and does not return anything!");
    }

    public static void extremelySimpleMethod(String s) {
        System.out.println("This method's argument is " + s + " but it still does not return anything!");
    }

    // What we had to calculate in Week3 practice, the sum of the maximum
    public static int calculateTriangularNumber(int max) {
        int sum = 0;
        for (int i = 1; i <= max; i++ ) {
            sum += i;
        }
        return sum;
    }

    public static void printTriangularNumber(int max) {
        int tri = calculateTriangularNumber(max);
        System.out.println("The " + max + "th triangular number is: " + tri);
    }

    public static double calculateCircleArea(double radius) {
        return Math.PI * radius * radius;
    }

    public static double calculateRectangleArea(double length, double width) {
        return length * width;
    }

    public static double calculateGravitationalForce(double m1, double m2, double r) {
        final double G = 6.6743e-11;

        return G * m1 * m2 / ( r * r );
    }

    /*
    public static void main(String[] args) {
    }
    */
}