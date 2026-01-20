// Java is an Object-Oriented language
// We need a lot of boilerplate to write basic code
public class Week1Demonstration {
    // Every Java program has a main() function
    // Reminds me of int main() in C/C++
    public static void main(String[] args) {
        // And finally... our code goes here. We write
        int x = 2; // we just defined a variable!
        System.out.println(x);
        int y = x;
        x = 1;

        System.out.println(x);

        String hello = "Hello!";
        System.out.println(hello);

        System.out.println(9 / y);
        y = y + 1;

        String s1 = "CISS";
        String s2 = "110";

        System.out.println(s1 + " " + s2);


        final String CONSTANT_STRING = "this string can't be changed";

        System.out.println(CONSTANT_STRING);
        

        int z = x + y * 3;
        /*
        Java types:
        byte, short, int, long
        we're not going to use short or byte very much
        
        float, double

        char - character
        */

        /* 
        operators
        1 + 1
        7 % 3
        what time will it be 30 hours from now?
        (current time + 30) % 12
        */
       // If you add a string and a number, you get a string.
    }
}