import java.util.Scanner;

public class AgeCalculator {
    public static void main(String[] args) {
        // we are reading from the users standard input (System.in)
        Scanner input = new Scanner(System.in);
        // we don't use println because we don't want to go to the next line when asking for input
        System.out.print("How old are you? ");
        // grab the value as an integer
        int age = input.nextInt();

        // it's only january, so most people haven't gotten their birthday yet
        // in reality, its better to get the current year
        // TODO: Make this smart
        int birthYear = 2025 - age;
        System.out.println("You were probably born in " + birthYear);

        // what if we are wrong? what if they had their birthday so far in January?
        System.out.print("Is this correct? ");
        // grab the response
        String response = input.next();

        // we don't use the double equals (==) because we have Java string methods.
        if(response.equals("yes")) {
            System.out.println("Great!");
        } else if (response.equals("no")) {
            // their birthday must have recently happened
            birthYear += 1;
            System.out.println("That means you were probably born in " + birthYear);
        } else {
            System.out.println("That wasn't a valid response!");
        }
    }
}