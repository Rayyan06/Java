// 20 sided die capable of getting a critical value (the highest possible value)
public class CritDie extends Die {
    public CritDie() {
        // call the parent class's constructor
        super(20);
    }

    public boolean isCrit() {
        return this.getCurrentValue() == 20;
    }
}