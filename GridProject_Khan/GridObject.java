public abstract class GridObject {
    private int x;
    private int y;

    private int gridWidth;
    private int gridHeight;

    public GridObject(int x, int y) {
        this.x = x;
        this.y = y;
    }
    // Moves by a specified offset. Returns true if move was successful 
    protected boolean move(int dx, int dy) {
        int newX = x + dx;
        int newY = y + dy;

        // if the character's new x or y position will be out of bounds, do not move the object
        if(newX < 0 || newX > gridWidth - 1 || newY < 0 || newY > gridHeight - 1) return false;
        
        x = newX;
        y = newY;

        return true;
    }

    // tells each GridObject the maximum width and height of the grid it will be placed on
    protected void setBounds(int gridWidth, int gridHeight) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
    }

    // abstract method - cannot be declared private
    // makes any necessory updates to the GridObject
    protected abstract void step();
    
    // returns the ASCII character that should be used to represent the object on the grid
    protected abstract char getChar();

    // ACCESSORS
    protected int getX() { return x; }
    protected int getY() { return y; }


}