public class Die {
    /*
    What kind of information does a die need to have?
    Why do we even need a class here, for a six-sided die?
    In games, there might be far more complex dice with complex behaviors!
    */

    // member variables: store attributes
    private int[] sides;
    private int currentFace;

    // methods

    public Die(int[] sides) {
        this.sides = sides;
    }

    public Die (int sideCount) {
        sides = new int[sideCount];
        for (int i = 0; i < sideCount; i++) {
            sides[i] = i + 1; // numbers run from 1 to ...
        }
    }

    // mutators
    public int roll() {
        currentFace = sides[(int) (Math.random() * sides.length)];
        return currentFace;
    }

    // accessors

    public int getCurrentValue() {
        return currentFace;
    }

    public int getMin() {
        int min = sides[0];
        
        for (int i = 1; i < sides.length; i++) {
            if (sides[i] < min) {
                min = sides[i];
            }
        } 

        return min;
    }

    public int getMax() {
        int max = sides[0];
        
        for (int i = 1; i < sides.length; i++) {
            if (sides[i] > max) {
                max = sides[i];
            }
        } 

        return max;
    }

    public double getAverageValue() {
        // double se we don't have to cast in the return statemnet
        double sum = 0;

        for (int side: sides) {
            sum += side;
        }

        return sum / sides.length;
    }
}
