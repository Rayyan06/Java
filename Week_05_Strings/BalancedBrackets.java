import java.util.Scanner;

public class BalancedBrackets {
    /*
    Determine if the parentheses are balanced around a string.
    In other words, every open paren must get closed, 
    and every closed paren is paired with an open paren before it
    balanced: (), ((())), ()()(), (())(), (()())
    imbalanced: (, ), )(
    */
    public static boolean checkParenthesis(String s) {
        /*
        Idea, keep a running count of how nested parens we are inside of!
        */
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                count++;
            } else if(s.charAt(i) == ')') {
                count--;
                if (count < 0) {
                    // If we have an extra closing bracket, this means we made a  mistake!
                    return false;
                }
            }
        }

        // if we are out of all the nested parens, we are good!
        return count == 0;
    }

    public static void main(String[] args) {
        Scanner keyboardInput = new Scanner(System.in);

        System.out.print("Enter: ");
        String testString = keyboardInput.nextLine();

        System.out.println(checkParenthesis(testString));
    }
}