import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class WriteEveryOtherLine {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Error: Must use exactly two arguments (input file and output file)");
            return;
        }

        String inputFilename = args[0];
        String outputFilename = args[1];

        try (
            Scanner input = new Scanner(new FileReader(inputFilename));
            FileWriter output = new FileWriter(outputFilename, true) // true for appending
        ) {
            int lineCount = 0;

            while(input.hasNextLine()) {
                String line = input.nextLine();
                System.out.println(line);
                // Let's write every EVEN line to the output file
                if (lineCount % 2 == 0) { // the modulus operator returns the remainder, an even number / 2 will always have 0 remainder
                    output.write(line); // write to the output!
                    output.write(System.lineSeparator()); // platform-independent linebreaks!
                }
                lineCount++;
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("Error, file not found:");
            System.out.println(fnf.getMessage()); // convey additional details to the user
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}