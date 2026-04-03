import java.util.Arrays;

public class ArrayMethods {
    public static void reverse(int[] numbers) {
        // swap the first one with the last one, second with second-last, etc until we hit the middle
        for(int i = 0; i < numbers.length / 2; i++) { // note that the floating point will be truncated, so this works for odd lengths!!
            // the index of the last element of the array is numbers.length - 1
            // and notice that i starts at 0
            int endPos = numbers.length - 1 - i;
            int temp = numbers[i];
            numbers[i] = numbers[endPos];
            numbers[endPos] = temp;
        }
    }

    // non-mutating variant of reverse
    public static int[] reverseCopy(int[] numbers) {
        int[] output = new int[numbers.length];
        for(int i = 0; i < numbers.length; i++) {
            output[i] = numbers[numbers.length - 1 - i];
        }
        return output;
    }
    // replaces each values in the array greater than max, with max
    public static void setMax(int[] numbers, int max) {
        for(int i = 0; i < numbers.length; i++) {
            // if we have a number in the array, that is larger than max
            if(numbers[i] > max) {
                numbers[i] = max;
            }
        }
    }

    // non-mutating variant of setMax()
    public static int[] setMaxCopy(int[] numbers, int max) {
        int[] output = new int[numbers.length];
        for(int i = 0; i < numbers.length; i++) {
            if(numbers[i] > max) {
                output[i] = max;
            } else {
                output[i] = numbers[i];
            }
        }
        return output;
    }
    
    public static boolean increasing(int[] numbers) {
        for(int i = 1; i < numbers.length; i++) {
            if(numbers[i] <= numbers[i - 1]) { // if the current number is less than the previous number
                return false;
            }
        }
        // no problem?
        return true;
    }

    public static 
    public static void main(String[] args) {
        int[] numbers1 = { 1,2,4,8,16,32 };
        reverse(numbers1);
        System.out.println("Numbers1: " + Arrays.toString(numbers1));

        int[] numbers2 = reverseCopy(numbers1);
        System.out.println("Numbers2: " + Arrays.toString(numbers2));

        setMax(numbers1, 7);
        System.out.println("Numbers1: " + Arrays.toString(numbers1));

        int[] numbers3 = setMaxCopy(numbers2, 4);
        System.out.println("Numbers3: " + Arrays.toString(numbers3));

        System.out.println("Is numbers1 increasing? " + increasing(numbers1));
        System.out.println("Is numbers2 increasing? " + increasing(numbers2));
    }
}