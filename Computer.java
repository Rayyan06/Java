import java.util.Random;

public class Computer {    

    // Private variables

    // We don't typically refer to GB in fractional form, so leave the RAM and hard disk space size an int
    // everything else can be a double

    private String manufacturerName;// Ex: IBM, Lenovo, Dell
    private double CPUSpeed;        // CPU clock speed in GHz
    private int RAMSize;            // RAM Size in GB
    private int hardDiskSize;       // Storage size of the hard disk in GB
    private double powerConsumption; // Power consumed in Watts

    public Computer(String manufacturerName, double CPUSpeed, int RAMSize, int hardDiskSize, double powerConsumption) {
        this.manufacturerName = manufacturerName;
        this.CPUSpeed = CPUSpeed;
        this.RAMSize = RAMSize;
        this.hardDiskSize = hardDiskSize;
        this.powerConsumption = powerConsumption;
    }

    // Mutators
    public void overclock(double amount) {
        // Scale CPU speed by the amount
        CPUSpeed *= amount;
    }
    public String toString() {
        return manufacturerName;
    }

    public static void main(String[] args) {
        /*
        Create a new Dell Workstation with 3.85GHz clock, 64 GB RAM, 1TB storage, and 180W power.
        and a new Lenovo Desktop with 3.10Ghz clock, 16GB RAM, 512GB storage, and 50W power
        */
        Computer dellPC = new Computer("Dell", 3.85, 64, 1024, 180);
        Computer thinkCentre = new Computer("Lenovo", 3.10, 16, 512, 50);
    }
}

