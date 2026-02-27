/*
This program prints a triangle like this:
#
##
###
####
#####

*/

public class PrintTriangle {
    public static void main(String[] args) {
        String row = "";
        // Mostly, we'll start counting at 0, but here we start at 1 because the first row has 1 char
        for(int i = 0; i < 5; i++) {
            row += "#";
            System.out.println(row);
        }

        row = "";
        while(row.length() < 5) {
            row += "#";
            System.out.println(row);
        }

        for(int i = 1; i <= 5; i++) {
            for(int j = 1; j <= i; j++) {
                System.out.print("#");
            }
            System.out.println();
        } 
    }
}