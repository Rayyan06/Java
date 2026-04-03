public class CheckIncreasing {
    public static void main(String[] args) {
        int[] integers = new int[args.length];
        // convert the argument list into integers!
        for(int i = 0; i < args.length; i++) {
            try {
                int nextInteger = Integer.parseInt(args[i]);
                integers[i] = nextInteger;
            } catch (NumberFormatException nfe) {
                System.out.println("Cannot evaluate non-numeric input");
                return;
            }
        }

        if(ArrayMethods.increasing(integers)) {
            System.out.println("These numbers are in ascending order!");
        } else {
            System.out.println("These numbers are NOT in ascending order!");
        }
    }
}