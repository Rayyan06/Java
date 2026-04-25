import java.util.ArrayList;

// You should not need to make any major changes to this file!
/* CHANGES made: 
 1. Renamed file name to MovementGrid to match class name
 2. Renamed all references to "GridCharacter", to "GridObject"
*/
public class MovementGrid {
	private int width;
	private int height;
	// Don't worry too much about how ArrayList; we'll talk about that in lesson 14.
	private ArrayList<GridObject> objects;

	public MovementGrid(int width, int height) {
		this.width = width;
		this.height = height;
		this.objects = new ArrayList<GridObject>();
	}

	public void step() {
		for(GridObject g : objects) {
			g.step();
		}
	}

	@Override
	public String toString() {
		char[][] grid = new char[width][height];
		for(int i=0;i<grid.length;i++) {
			for(int j=0; j<grid[i].length;j++) {
				grid[i][j] = '.';
			}
		}

		// renamed to GridObject
		for(GridObject g : objects) {
			grid[g.getY()][g.getX()] = g.getChar();
		}
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<grid.length;i++) {
			builder.append(grid[i]);
			builder.append("\n");
		}
		return builder.toString();
	}

	public void add(GridObject g) {
		objects.add(g);
		g.setBounds(width, height);
	}

	public static void main(String[] args) {
		MovementGrid grid = new MovementGrid(10, 10);
		
		grid.add(new HorizontalGridObject(5,5));
		grid.add(new AlphabetGridObject(3,7));
		grid.add(new RandomGridObject(2,2));
		// Extra credit: if you have any additional subclasses, you can add instances of them here
		// CounterGridObject: Counts from 0 to 9 repeatedly, resetting to 0 each time it overflows
		grid.add(new CounterGridObject(8, 4));
		// GravityGridObject: falls from the starting position, until it comes to rest at the bottom
		grid.add(new GravityGridObject(3, 0));

		for(int i=0;i<20;i++) {
			grid.step();
			System.out.println(grid);
		}
	}
}
