// COMPOSITION
public class PercentileDicePair {
    private Die onesPlace;
    private Die tensPlace;


    public PercentileDicePair() {
        int[] ones = new int[10];
        int[] tens = new int[10];

        for (int i = 0; i < 10; i++) {
            ones[i] = i;
            tens[i] = i * 10;
        }

        onesPlace = new Die(ones);
        tensPlace = new Die(tens);
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
}