// Increments from a start to an end
public abstract class IncrementingGridObject extends GridObject {
    private int index;

    // ASCII character codes denoting the start and end of the sequence
    private final char startChar;
    private final char endChar;

    public IncrementingGridObject(int x, int y, char startChar, char endChar) {
        super(x, y);
        this.startChar = startChar;
        this.endChar = endChar;

        // Note: we must initialize the index at -1 because in MovementGrid,
        // the grid steps before the grid is printed
        index = -1;
    }

    @Override
    public void step() {
        if(getChar() == endChar) {
            index = 0; // restart at the beginning
        }
        else {
            index++;
        }
    }

    @Override
    public char getChar() {
        // works because of ASCII character codes
        // note: we need type casting from int to char here
        return (char) (startChar + index);
    }
}