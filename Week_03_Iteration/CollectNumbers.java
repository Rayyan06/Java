// collect numbers of a certain quality
import java.util.Scanner;

public class CollectNumbers {
    public static void main(String[] args) {
        // Let's assume we are only working with positive integers here
        int large = -1;
        int even = -1;
        int square = -1;
        
        Scanner scanner = new Scanner(System.in);

        while (large < 0 || even < 0 || square < 0) {
            System.out.print("Enter the next number: ");
            int next = scanner.nextInt();
            // we're only accepting positive numbers, so anything <= 0 is invalid
            if(next <= 0) {
                System.out.println("Only positive numbers are accepted");
            } else {
                if(next >= 100) {
                    large = next;
                }
                if(next % 2 == 0) {
                    even = next;
                }
                // if the number's square root is a whole number
                // we can type cast to check for this condition
                if((int) (Math.sqrt(next)) == Math.sqrt(next)) {
                    square = next;
                }
            }
        }

        System.out.println("Large is " + large);
        System.out.println("Even is " + even);
        System.out.println("Square is " + square);
    }
}