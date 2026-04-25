public class RandomGridObject extends GridObject {

    public RandomGridObject(int x, int y) {
        super(x, y);
    }

    @Override
    public void step() {
        // moves in a random direction each turn
        // generate random integer: 0, 1, 2, 3
        int direction = (int) (Math.random() * 4);

        switch (direction) {
            case 0: // left
                super.move(-1, 0);
                break;
            case 1: // down
                super.move(0, 1);
                break;
            case 2: // right
                super.move(1, 0);
                break;
            case 3: // up
                super.move(0, -1);
                break;
        }
    }

    @Override
    public char getChar() {
        return '?';
    }
}