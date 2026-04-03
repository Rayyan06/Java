public class CheckAverage {
    public static void main(String[] args) {
        int[] integers = new int[args.length];
        int sum = 0;
        // convert the argument list into integers!
        for(int i = 0; i < args.length; i++) {
            try {
                int nextInteger = Integer.parseInt(args[i]);
                integers[i] = nextInteger;
            } catch (NumberFormatException nfe) {
                System.out.println("Cannot evaluate non-numeric input");
                return;
            }
            // integers is still in scope!
            sum += integers[i];
        }

        double average = sum / (double) integers.length;

        int[] partitioned = new int[integers.length];

        int smallPos = 0;
        int largePos = integers.length - 1;

        for(int i = 0; i < integers.length; i++) {
            if(integers[i] > average) {
                // put it in the large array
                partitioned[largePos] = integers[i];
                largePos--;
            } else {
                // put it in the small array
                partitioned[smallPos] = integers[i];
                smallPos++;
            }
        }

        System.out.println("Small numbers:");
        for(int i = 0; i < smallPos; i++) {
            System.out.println(partitioned[i]);
        }
        System.out.println("The average is: " + average);

        System.out.println("Large numbers:");
        for(int i = partitioned.length - 1; i > largePos; i--) {
            System.out.println(partitioned[i]);
        }
    }
}

