import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

// Reads numbers from the console and saves the byte value of the numbers to a binary file
public class NumbersToBinaryFile {
    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream("numbers.bin")) {
            Scanner input = new Scanner(System.in);
            for (int i = 0; i < 10; i++) {
                int value = input.nextInt();
                fos.write(value);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}