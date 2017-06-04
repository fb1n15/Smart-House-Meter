/**
 * The class that hold the main method of the simulation
 * @author ik5g15 - Iason koulas
 *
 */
public class Main {

	public static void main(String[] args) {

		House ourHouse = new House();

		Builder houseBuilder = new Builder(new FileHandling("input.txt"), ourHouse); // creates a builder for the house
		houseBuilder.runBuilder();
		
		/**
		 * Simulates the pass of the day and calls the house.timePasses() each time unit
		 */
		try {
			for (int time = 1; time <= 96; time++) {
				Thread.sleep(300);
				ourHouse.timePasses(time);
				System.out.print("@@  Current consumption: ");
				ourHouse.printHouseMetersConsumed();
				System.out.print("@@  Current power generation: ");
				ourHouse.printHouseMetersGenerated();
			}
			/**
			 * Prints the meters
			 */
			System.out.print("=====>  Final consumption of the house is: ");
			ourHouse.printHouseMetersConsumed();
			System.out.print("=====>  Final power generated of the house is: ");
			ourHouse.printHouseMetersGenerated();
			System.out.print("=====>  Total cost of the consumed power is: ");
			ourHouse.printCosts();
		} catch (InterruptedException e) {
			System.out.println("RunTime ERROR.");
		}
		
	}

}
