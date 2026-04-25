public class GravityGridObject extends GridObject {
    public GravityGridObject(int x, int y) {
        super(x, y);
    }

    @Override
    public void step() {
        // moves one unit down until it hits the bottom, then stops
        
        super.move(0, 1);  // Note: y = 0 is the bottom of the grid, so increasing y moves down.
        // we don't have to worry about checking the bottom! (already done in move)
    }

    @Override
    public char getChar() {
        return 'O';
    }

}