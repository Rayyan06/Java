import java.util.Random;
import java.util.Scanner;

public class Computer_Khan {    

    // Private variables

    // We don't typically refer to GB in fractional form, so leave the RAM and hard disk space size an int
    // everything else can be a double

    private String manufacturerName;// Ex: IBM, Lenovo, Dell
    private double cpuSpeed;        // CPU clock speed in GHz
    private int RAMSize;            // RAM Size in GB
    private int hardDiskSize;       // Storage size of the hard disk in GB
    private double powerConsumption; // Power consumed in Watts

    // Constructor
    public Computer_Khan(String manufacturerName, double cpuSpeed, int RAMSize, int hardDiskSize, double powerConsumption) {
        this.manufacturerName = manufacturerName;
        this.cpuSpeed = cpuSpeed;
        this.RAMSize = RAMSize;
        this.hardDiskSize = hardDiskSize;
        this.powerConsumption = powerConsumption;
    }
    // Accessors (extra method)
    public String getManufacturerName() {
        return manufacturerName;
    }

    // Mutators

    /*
    overclock(), scales both the CPU speed and power consumption by amount
    */
    public void overclock(double amount) {
        // I probably shouldn't be printing from a method like this, but I'll do it here for convenience
        // Scaling to a negative value or zero is... meaningless!
        if (amount <= 0) {
            System.out.println("The scaling factor for overclocking must be greater than zero");
            return;
        } else if (amount < 1) {
            double percentUnderclocked = 100.0 * (1.0 - amount);
            System.out.printf("Underclocked CPU of %s by %.2f%%%n", manufacturerName, percentUnderclocked);
        } else {
            double percentOverclocked = 100.0 * (amount - 1.0);
            System.out.printf("Overclocked CPU of %s by %.2f%%%n", manufacturerName, percentOverclocked);
        }

        cpuSpeed *= amount;
        powerConsumption *= amount;
    }

    public String toString() {
        // Format: IBM computer with 32 GB of RAM, 256 GB of hard disk space, a 3.57 GHz CPU, and consumes 300 W of power
        // https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html#syntax
        // %f is a floating point number, so %.2f has two decimal places
        // %s is a String.
        // %d is a decimal integer
        return String.format(
            "%s computer with %d GB of RAM, %d GB of hard disk space, a %.2f GHz CPU, and consumes %.0f W of power", 
            manufacturerName,
            RAMSize,
            hardDiskSize,
            cpuSpeed,
            powerConsumption
        );
    }

    // override the equals() method that is default for Objects
    public boolean equals(Object other) {
        // We must make sure we are comparing the same Class!
        if(!(other instanceof Computer_Khan)) {
            return false;
        }

        // cast the Object into a Computer
        Computer_Khan otherComputer = (Computer_Khan) other;

        // Finally, check all the instance variables to ensure they are equal

        // IMPORTANT We can't compare string's with string's using == 
        // I also read that the best way to compare double's in Java is the static .compare method
        // https://www.geeksforgeeks.org/java/double-compare-method-in-java-with-examples/
        return otherComputer.manufacturerName.equals(manufacturerName)
            && Double.compare(otherComputer.cpuSpeed, cpuSpeed) == 0
            && otherComputer.RAMSize == RAMSize
            && otherComputer.hardDiskSize == hardDiskSize
            && Double.compare(otherComputer.powerConsumption, powerConsumption) == 0;
    }

    public static void main(String[] args) {
        // Instantiate a new instance of the Scanner class using its constructor
        Scanner input = new Scanner(System.in);

        /*
        Create a new Dell Workstation with 3.85GHz clock, 64 GB RAM, 1TB storage, and 180W power.
        and a new Lenovo Desktop with 3.10Ghz clock, 16GB RAM, 512GB storage, and 50W power
        */
        Computer_Khan dellPC = new Computer_Khan("Dell", 3.85, 64, 1024, 180);
        
        Computer_Khan thinkCentre = new Computer_Khan("Lenovo", 3.10, 16, 512, 50);

        // Create another identical thinkCentre
        // Important: Do not make a copy like this:
        // Computer_Khan anotherThinkCentre = thinkCentre;
        // Because this copies the reference, not the actual object. They are both pointing to the same thing!

        Computer_Khan anotherThinkCentre = new Computer_Khan("Lenovo", 3.10, 16, 512, 50);

        // Test toString() method
        // By default, printing the instance name runs the toString() method!
        System.out.println("1. " + dellPC);
        System.out.println("2. " + thinkCentre);
        System.out.println("3. " + anotherThinkCentre);

        // Test for equality (should be true)
        System.out.print("The two " + thinkCentre.getManufacturerName() + "s are ");
        if(thinkCentre.equals(anotherThinkCentre)) {
            System.out.println("equivalent!");
        } else {
            System.out.println("not equivalent!");
        }

        // make a change in the other thinkcentre
        anotherThinkCentre.overclock(1.3);

        System.out.println("3. " + anotherThinkCentre);

        // Test for equality again (should be false)
        System.out.print("The two " + thinkCentre.getManufacturerName() + "s are ");
        if(thinkCentre.equals(anotherThinkCentre)) {
            System.out.println("equivalent!");
        } else {
            System.out.println("not equivalent!");
        }

        // Test overclocking
        System.out.print("How much would you like to overclock the " + dellPC.getManufacturerName() + " by? Enter the scaling factor: ");
        dellPC.overclock(input.nextDouble());

        System.out.println("1. " + dellPC);

        System.out.print("How much would you like to overclock the " + thinkCentre.getManufacturerName() + " by? Enter the scaling factor: ");
        thinkCentre.overclock(input.nextDouble());
        
        System.out.println("2. " + thinkCentre);

        input.close();
    }
}

