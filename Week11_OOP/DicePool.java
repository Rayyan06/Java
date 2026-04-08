public class DicePool {
    private Die[] dice;

    public DicePool(int dieSize, int count) {
        dice = new Die[count];

        for(Die die:dice) {
            die = new Die(dieSize);
        }
    }

    private void rollAll() {
        for (Die die:dice) {
            die.roll();
        }
    }

    
    public int rollForHighest() {
        rollAll();

        int max = dice[0].getCurrentValue();;
        
        for (int i = 1; i < dice.length; i++) {
            if (dice[i].getCurrentValue() > max) {
                max = dice[i].getCurrentValue();
            }
        } 

        return max;
    }

    public int rollForLowest() {
        rollAll();

        int min = dice[0].getCurrentValue();;
        
        for (int i = 1; i < dice.length; i++) {
            if (dice[i].getCurrentValue() < min) {
                min = dice[i].getCurrentValue();
            }
        } 

        return min;
    }

    public double getTotalValue() {
        double sum = 0;

        for (Die die: dice) {
            sum += die.getCurrentValue();
        }

        return sum;
    }
}