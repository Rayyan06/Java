
// 20 sided die capable of getting a critical value (the highest possible value)
public class CritDie extends PolyhedralDie {
    public CritDie() {
        // call the parent class's constructor
        super(20);
    }

    public boolean isCrit() {
        return this.getCurrentValue() == 20;
    }

    
    @Override
    public String toString() {
        if(isCrit()) {
            return "20-sided die (current value: CRITICAL HIT!)";
        }
        return String.format("20-sided die (current value %d)", getCurrentValue());
    }
}
