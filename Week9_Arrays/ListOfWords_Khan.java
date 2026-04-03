import java.util.Scanner;

public class ListOfWords_Khan {
    // attempt to get a valid integer input from the user
    // handles clearing buffer as well
    public static int tryForInt(Scanner scan) {
        while(true) {
            if(scan.hasNextInt()) {
                // we must do this, to clear the buffer, even for valid input
                int result = scan.nextInt();
                scan.nextLine(); // clear buffer
                return result;
            }

            System.out.print("Invalid number. Try again: ");
            scan.nextLine(); // flush the scan buffer
        }
    }

    // get an integer from the user that is strictly >0
    public static int tryForPositiveInt(Scanner scan) {
        while(true) {
            int inputInt = tryForInt(scan);
            if (inputInt > 0) {
                return inputInt;
            }
            System.out.println("Number Must be positive and nonzero");
        }
    }

    // get an integer from the user that is an index of an array
    public static int getIndexInput(Scanner scan, int arrayLength) {
        while(true) {
            int indexInput = tryForInt(scan); // first, it must be a valid int

            // if the index is within bounds of the array indices, return it
            if (indexInput >= 0 && indexInput < arrayLength) {
                return indexInput;
            }
            // no need to clear buffer here, tryForInt() already did it!
            System.out.print("Invalid number. Try again: ");
        }
    }

    // non-mutating, returns the longest word in a sentence
    public static String getLongestWord(String[] sentence) {
        int longestWordLength = 0;
        int longestWordIndex = 0; // the position of the longest word in the sentence array

        // note: use the .length property for arrays, .length() method for Strings
        for (int i = 0; i < sentence.length; i++) {
            int currentWordLength = sentence[i].length();
            if (currentWordLength > longestWordLength) { // if we've found a longer word
                longestWordLength = currentWordLength;
                longestWordIndex = i;
            }
        }

        // note: if two words are the same length, and they are longest, the program will return the
        // first one
        return sentence[longestWordIndex];
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the length of your text: ");
        int textLength = tryForPositiveInt(scan);

        // Create an array of the appropriate length
        String[] sentence = new String[textLength];

        System.out.print("Enter the text to process: ");
        
        for (int i = 0; i < sentence.length; i++) {
            sentence[i] = scan.next(); // read each word, ending at whitespace
        }

        // flush the buffer, dropping any extra words
        scan.nextLine();

        while (true) {
            System.out.print("Type \"longest\", \"replace\", or \"done\": ");
            // IMPORTANT: We must read the newline as well!
            String userChoice = scan.nextLine();

            switch(userChoice.toLowerCase()) {
                case "longest":
                    String longestWord = getLongestWord(sentence);
                    System.out.println("The longest word is " + longestWord);
                    break;
                case "replace":
                    System.out.print("Enter the index of the word to replace: ");
                    int replacedWordIndex = getIndexInput(scan, sentence.length);
            
                    System.out.print("Enter the word to replace with: ");
                    String newWord = scan.next(); // read until the next whitespace
                    scan.nextLine(); // flush the buffer, dropping anything extra

                    // replace the word
                    sentence[replacedWordIndex] = newWord;

                    System.out.print("After replacement, the words are: ");
                    // foreach loop works well here because we don't care about the indices to print the sentence
                    for (String word : sentence) {
                        // print each word, seperated by whitespace
                        System.out.print(word + " ");
                    }

                    System.out.println(); // end with a newline

                    break;

                case "exit":
                case "done":
                    return; // terminate the program

                default:
                    System.out.println("That wasn't a valid choice. Try again. ");
            }
        }

    }
}