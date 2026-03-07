public class PhysicalObject {
    private double mass;
    private double x;
    private double y;
    private double z;
    
    public PhysicalObject(double mass, double x, double y, double z) {
        setMass(mass);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // this is why we use mutators! so we don't repeat ourself
    public void setMass(double newMass) {
        if (newMass <= 0) {
            // this is a checked exception, must be in try catch or throws declaration
            // wait, we don't actually need a ... throws declaration????
            // It only make sense for this error to occur during runtime -> RuntimeException
            // Specifically, this is an:
            throw new IllegalArgumentException("Mass must be positive");
        } else {
            mass = newMass;
        }
    }

    public static void createSomeObjects() {
        PhysicalObject p1 = new PhysicalObject(1, 1, 1, 1);
        PhysicalObject p2 = new PhysicalObject(1, -1, -1, -3);
        PhysicalObject p3 = new PhysicalObject(-3, 1, 1, 1);
        PhysicalObject p4 = new PhysicalObject(1, 1, 1, 1);

    }
    public static void main(String[] args) {
        createSomeObjects();
    }

}