package Week14_Collections;

import java.util.ArrayList;
import java.util.List;

public class ArrayListMethods {
    // Use capital Integer because we can't use primitives
    /*
    Reverse works better with ArrayList because 
    LinkedLists should be used sparingly
    */
    public static void reverse(ArrayList<Integer> numbers) {
        // swap the first one with the last one, second with second-last, etc until we hit the middle
        for(int i = 0; i < numbers.size() / 2; i++) { // note that the floating point will be truncated, so this works for odd lengths!!
            // the index of the last element of the array is numbers.length - 1
            // and notice that i starts at 0
            int endPos = numbers.size() - 1 - i;
            int temp = numbers.get(i);
            numbers.set(i, numbers.get(endPos));
            numbers.set(endPos, temp);
        }
    }

    // non-mutating variant of reverse
    public static ArrayList<Integer> reverseCopy(List<Integer> numbers) {
        ArrayList<Integer> output = new ArrayList<Integer>();
        for(int i = 0; i < numbers.size(); i++) {
            output.add(numbers.get(numbers.size() - 1 - i));
        }
        return output;
    }
    // replaces each values in the array greater than max, with max
    public static void setMax(ArrayList<Integer> numbers, int max) {
        for(int i = 0; i < numbers.size(); i++) {
            // if we have a number in the array, that is larger than max
            if(numbers.get(i) > max) {
                numbers.set(i, max);
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
    
    public static boolean increasing(List<Integer> numbers) {
        for(int i = 1; i < numbers.size(); i++) {
            if(numbers.get(i) <= numbers.get(i - 1)) { // if the current number is less than the previous number
                return false;
            }
        }
        // no problem?
        return true;
    }

    public static void main(String[] args) {
        // we don't neeed the type parameter again on the right.
        // ArrayList<Integer> numbers1 = new ArrayList<>();

        // for(int i = 1; i <= 32; i *= 2) {
        //     numbers1.add(i);
        // }
        List<Integer> numbers1 = List.of(1, 2, 4, 8, 16, 32);
        ArrayList<Integer> numbers2 = reverseCopy(numbers1);

        System.out.println("Numbers1: " + numbers1);
        System.out.println("Numbers2: " + numbers2);
        setMax(numbers2, 7);  
        reverse(numbers2);
  

 
        System.out.println("Numbers1: " + numbers1);
        System.out.println("Numbers2: " + numbers2);

        System.out.println("Is numbers1 increasing? " + increasing(numbers1));
        System.out.println("Is numbers2 increasing? " + increasing(numbers2));
    }
}