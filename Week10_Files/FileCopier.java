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
 
        // ----- OUTPUT FILE ------
        String outputFilename = args[1];
       

        try (
            Scanner input = new Scanner(new FileReader(inputFilename));
            FileWriter output = new FileWriter(outputFilename, true) // true for append
        ) {
            while(input.hasNextLine()) {
                String line = input.nextLine();
                System.out.println(line);
                output.write(line); // write to the output!
                output.write(System.lineSeparator()); // platform-independent linebreaks!
            }
        } catch(FileNotFoundException fnf) {
            System.out.println("Error, file not found");    
        } catch (IOException ioe) {
                ioe.printStackTrace();
        }


        // no longer needed    

        // input.close();
        // output.close();
    }
}