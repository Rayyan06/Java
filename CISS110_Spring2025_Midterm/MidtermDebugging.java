public class MidtermDebugging {

	Scanner scanner;

	public static double inputDouble() {
		while(!scanner.hasNextDouble() {
			System.out.println("Invalid!");
		}
		return scanner.nextDouble();
	}
	
	public double inputPositive() {
		while(value > 0) {
			System.out.println("Value must be positive!");
		}
		return value;
	}

	public static PhysicsSphere inputSphere() {
		System.out.println("Please enter the horizontal position of the object")
		int x = inputDouble()
		System.out.println("Please enter the vertical position of the object")
		int y = inputDouble()
		System.out.println("Please enter the mass of the object")
		int mass = inputPositive()
		System.out.println("Please enter the radius of the object")
		int radius = inputPositive()
		PhysicsSphere(mass, radius, x, y)
	}

	public static void main(String[] args) {
		System.out.println("First object")
		PhysicsSphere p1 = inputSphere();

		System.out.println("Second object")
		PhysicsSphere p2 = inputSphere();
		if(p1.intersects(p2)) {
			System.out.println("Error: object 2 intersects object 1");
			return false;
		}

		System.out.println("Third object")
		PhysicsSphere p3 = inputSphere();
		if(p1.intersects(p2)) {
			System.out.println("Error: object 3 intersects object 1");
			return false;
		}
		if(p2.intersects(p3)) {
			System.out.println("Error: object 3 intersects object 2");
			return false;
		}
		
		ForceVector force12 = p1.forceBetween(p2);
		ForceVector force23 = p2.forceBetween(p3);
		ForceVector force31 = p3.forceBetween(p2);

		System.out.println("Total force on object 1: " + force12.add(force31));
		System.out.println("Total force on object 2: " + force23.add(force12));
		System.out.println("Total force on object 3: " + force31.add(force23));
	}
}
