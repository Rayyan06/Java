/*
Finds the index of a character in a String
*/
public class IndexFinder {

    /*
    Finds the index of a the first occurance of a character within a string
    */
    public static int findIndex(String string, char c) {
        for (int i = 0; i < string.length(); i++) {
            // We are able to use == because char's are primitive numericals, not objects
            // Technically, strings are also numeric values, but the numeric value is the memory address where the string is located
        
            // Problem: if we never get into this if, we never return
            if (string.charAt(i) == c) {
                // We are going to return early here! We don't care about the rest of the string! We're done
                return i;
            }
        }
        // If we didn't find any character in the string,
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findIndex("hello", 'e')); // should be 1
        System.out.println(findIndex("hello", 'o')); // 4
        System.out.println(findIndex("hello", 'l')); // 2  
        System.out.println(findIndex("hello", 'a')); // -1
    }

}


