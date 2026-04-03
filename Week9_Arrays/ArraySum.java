import java.util.Scanner;

public class ArraySum {

    // attempt to get a valid integer input from the user
    public static int tryForInt(Scanner scan) {
        while(true) {
            if(!scan.hasNextInt()) {
                System.out.print("Error! That wasn't a valid integer input! Try again: ");
                scan.nextLine(); // flush the scan buffer
            } else {
                int result = scan.nextInt();
                /*  
                flush the buffer, even if the user inputted a valid input, 
                it could be followed by invalid input 
                ,or the newline character
                */
                scan.nextLine(); 
                return result;
            }
         
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // we are using 10 integers
        final int arrayLength = 10;

        // create a new array of integers with specified length
        int integers[] = new int[arrayLength];

        int sum = 0;

        // fill the array with the users input
        for(int i = 0; i < integers.length; i++) {
            // get the integer from the user
            // use i + 1 for the user-presented index since Array indices start at 0
            System.out.printf("Enter Element #%d: ", i + 1);
            integers[i] = tryForInt(scanner);

            sum += integers[i];
        }

        System.out.printf("The sum of the %d elements in the array is %d", arrayLength, sum);
    }
}