public class HorizontalGridObject extends GridObject {
    private boolean isMovingRight;

    public HorizontalGridObject(int x, int y) {
        super(x, y);
        // the horizontal grid object will start by moving right
        isMovingRight = true;
    }

    @Override
    public void step() {
        // moves right one space per turn until it hits the grid's right edge,
        // then moves left one space per turn until it hits the other (left) edge
        
        // move 1 unit right if we are moving right
        boolean moveSuccessful = super.move(isMovingRight ? 1 : -1, 0);

        if(!moveSuccessful) {
            // change the direction of the object if it hit the bounds
            // (the move was not successful)
            isMovingRight = !isMovingRight;
        }
    }

    @Override
    public char getChar() {
        return isMovingRight ? '>' : '<';
    }

}