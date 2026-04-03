import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileCopier {
    // in practice, main should NOT have a throws decleration on it.
    public static void main(String[] args) throws IOException { // option 1: throws FileNotFoundException
        if (args.length != 2) {
            System.out.println("Error: Must use exactly two arguments (input file and output file)");
            return;
        }

        // ----- INPUT FILE ------

        String inputFilename = args[0];
 
        // declare it earlier here
        Scanner input;
        try {
            input = new Scanner(new FileReader(inputFilename));
        } catch (FileNotFoundException fnf) {
            System.out.println("Cannot read input file!");
            return;
        }
        
        // ----- OUTPUT FILE ------
        String outputFilename = args[1];
        /*
        Use PrintWriter or FileWriter?
        PrintWriter is higher-level, FileWriter is low-level 

        PrintWriter doesn't have a way of opening a file non-destructively
        */

        FileWriter output;

        try {
            // the second parameter determines whether we want to keep want to keep what's in the file (true), or discard (false).
            output = new FileWriter(outputFilename, true);
        } catch (FileNotFoundException fnf) {
            /* When we create PrintWriter, we create a blank file. If the file already exists, we overwrite it. 
            however, we can't create it if those directories don't exist.
            */
            System.out.println("Cannot create output file!");
            return;
        }

        while(input.hasNextLine()) {
            String line = input.nextLine();
            System.out.println(line);
            output.write(line + "\n"); // write to the output!
        }

        input.close();
        output.close();
    }
}