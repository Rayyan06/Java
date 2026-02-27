/*
Muhammed Rayyan Khan
This program calculates the area and perimeter of a rectangle with a height and width.
*/

public class RectanglePractice {
    public static void main(String[] args) {

        // define the dimensions of the rectangle
        // i'll declare these final, since they don't change in our program
        final int rect_width = 5;
        final int rect_height = 6;

        // calculate the perimeter and area (the derived variables)
        int perimeter = 2 * (rect_width + rect_height);
        int area = rect_width * rect_height;

        // output the perimeter and area
        System.out.println("The perimeter is " + perimeter);
        System.out.println("The area is " + area);
    }
}