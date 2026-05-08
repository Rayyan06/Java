
// COMPOSITION
public class PercentileDicePair implements Die{
    private PolyhedralDie onesPlace;
    private PolyhedralDie tensPlace;


    public PercentileDicePair() {
        int[] ones = new int[10];
        int[] tens = new int[10];

        for (int i = 0; i < 10; i++) {
            ones[i] = i;
            tens[i] = i * 10;
        }

        onesPlace = new PolyhedralDie(ones);
        tensPlace = new PolyhedralDie(tens);
    }

    public int getCurrentValue() {
        if(onesPlace.getCurrentValue() == 0 && tensPlace.getCurrentValue() == 0) {
            return 100;
        } else {
            return onesPlace.getCurrentValue() + tensPlace.getCurrentValue();
        }
    }



    // mutators
    public int roll() {
        onesPlace.roll();
        tensPlace.roll();

        return getCurrentValue();        
    }

    @Override
    public String toString() {
        return String.format("Pair of percentile dice (current value %d)", getCurrentValue());
    }
}