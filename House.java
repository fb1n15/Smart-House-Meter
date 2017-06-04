import java.util.ArrayList;

public class House {
	/*
	 * The live global time in the house
	 */
	Integer time = 1;

	ArrayList<Appliance> houseAppliances = new ArrayList<Appliance>(); // The
																		// existing
																		// appliances
																		// in
																		// the
																		// house
	ArrayList<Person> housePeople = new ArrayList<Person>();// The existing
															// people in the
															// house
	ArrayList<Meter> houseMeters = new ArrayList<Meter>();// The existing meters
															// in the house

	/*
	 * The avalaible tasks in the house
	 */
	String[] avalaibleTasks = { "dowashing", "boil", "turnon", "turnoff", "washdishes", "cook", "shower" };

	public House() {
	}

	/**
	 * @return How many appliances there are in the house
	 */
	public Integer getAmountOfAppliances() {
		return houseAppliances.size();
	}

	/**
	 * @return The last created person in the house
	 */
	public Person getLastPerson() {
		return housePeople.get(housePeople.size() - 1);
	}

	/**
	 * @return whether there are people in the house
	 */
	public boolean peopleExist() {
		return !housePeople.isEmpty();
	}

	/**
	 * @return The global time
	 */
	public Integer getTime() {
		return time;
	}

	/**
	 * @return All the tasks the house may contain
	 */
	public String[] getTasks() {
		return avalaibleTasks;
	}

	/**
	 * @return Existing meters in the house
	 */
	public ArrayList<Meter> getMeters() {
		return houseMeters;
	}

	/**
	 * @param newPerson
	 *            The person that is to be created
	 */
	public void addPerson(Person newPerson) {
		housePeople.add(newPerson);
	}

	public void addAppliance(Appliance newAppliance) {
		houseAppliances.add(newAppliance);
	}

	public void addMeter(Meter meterName) {
		houseMeters.add(meterName);
	}

	public void removeAppliance(String appName) {
		for (Appliance app : houseAppliances) {
			if (app.getName().equals(appName)) {
				houseAppliances.remove(app);
			}
		}
	}

	/**
	 * Prints the values of the consumed power in the house
	 */
	public void printHouseMetersConsumed() {
		for (Meter meter : houseMeters) {
			System.out.print(meter.getType() + ": " + meter.getConsumed() + " ");
		}
		System.out.println();
	}

	/**
	 * Prints the values of the generated power in the house
	 */
	public void printHouseMetersGenerated() {
		for (Meter meter : houseMeters) {
			if (meter.canGenerate()) {
				System.out.print(meter.getType() + ": " + meter.getGenerated() + " ");
			}
		}
		System.out.println();
		System.out.println();
	}

	/**
	 * Simulates the pass of each time unit
	 * 
	 * @param gets
	 *            the global time from the main method
	 */
	public void timePasses(Integer time) {
		System.out.println("*******Current Time: " + time + "*******");
		if (!housePeople.isEmpty()) {
			for (Person person : housePeople) { // loops through the avalaible
												// people and calls their time
												// simulation
				person.timePasses(time, houseAppliances);
			}
		}
		for (Appliance appliance : houseAppliances) { // loops through the
														// avalaible appliances
														// and calls their time
														// simulation
			appliance.timePasses(houseMeters);
		}

	}

	/**
	 * prints the total cost of the power consumed
	 */
	public void printCosts() {
		float tempCost;
		for (Meter meter : houseMeters) {
			tempCost = meter.getCost();
			if (tempCost == 0) {
				System.out.print(meter.getType() + ": " + " Not price is given.");
			} else {
				System.out.print(meter.getType() + ": " + meter.getCost() + " ");
			}
		}
		System.out.println();
	}

}
