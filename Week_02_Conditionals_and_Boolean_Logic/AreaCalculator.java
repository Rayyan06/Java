import java.util.Scanner;

public class AreaCalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("What shape do you want to find the area of? ");
        String choice = input.next();

        if(choice.equals("rectangle")) {
            System.out.print("Enter the value for the first side: ");
            int a = input.nextInt();

            System.out.print("Enter the value for the second side: ");
            int b = input.nextInt();

            double area = a * b;
            System.out.println("The area of the rectangle is " + area);
        } else if (choice.equals("circle")) {
            System.out.print("Enter the radius of the circle: ");
            int radius = input.nextInt();
            double area = Math.PI * radius * radius;

            System.out.println("The area of the circle is " + area);
        
        } else if(choice.equals("triangle")) {
            System.out.print("Enter the base of the triangle: ");    
            int base = input.nextInt();
            System.out.print("Enter the height of the triangle: ");    
            int height = input.nextInt();

            double area = base * height / 2.0;

            System.out.println("The area of the triangle is " + area);
        } else {
            System.out.println("That shape isn't supported yet.");
        }
    }
}