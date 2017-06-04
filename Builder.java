import java.util.Arrays;

/**
 * Gets each line from the file and decides what to do or which object to build
 *
 */
public class Builder {

	FileHandling input; // The input file
	String[] lineArray; // Each line of the file after .split()
	House ourHouse; // Our current House

	/*
	 * Temporary variables used to transfer data from the file to the
	 * constructors
	 */
	float num1;
	float num2;
	float num3;

	public Builder(FileHandling file, House newHouse) {
		input = file;
		ourHouse = newHouse;

	}

	/**
	 * Runs the house builder
	 */
	public void runBuilder() {

		if (hasHouse()) {
			boolean nextIsNull = false; // If next line of the input is empty
			lineArray = input.getLine().toLowerCase().split(":");
			/**
			 * Loops over the 4 builder based on the first word of each line to
			 * create objects
			 */
			while (lineArray != null && !nextIsNull) {
				if (buildAppliance() && this.limitAppliances(ourHouse)) { // The
																			// house
																			// cannot
																			// hold
																			// more
																			// than
																			// 25
																			// appliances
				} else if (buildMeter()) {
				} else if (buildPerson()) {
				} else if (buildTask()) {
				} else if (getPrice()) {
				} else {
					System.out.println(Arrays.toString(lineArray) + "\n ERROR: Input cannot be recognized.");
				}
				/**
				 * Reads next line of the input
				 */
				if (input.isReady()) {
					lineArray = input.getLine().toLowerCase().split(":");
				} else {
					nextIsNull = true;
				}
			}
		}
	}

	/**
	 * Check if the format for the appliances is appName:[n1,n2,n3] where
	 * []optional
	 */
	public void checkAppliances() {
		if (lineArray.length > 3) {
			System.out.println("The input doesn't match with the wanted format for appliances.");
			System.exit(0);
		}
	}

	/**
	 * Check if the format for the meters is meterName:[n1,n2] where []optional
	 */
	public void checkMeters() {
		if (lineArray.length > 2) {
			System.out.println("The input doesn't match with the wanted format for meters.");
			System.exit(0);
		}
	}

	/**
	 * Check if the format for the people is name:n1,n2,n3
	 */
	public void checkPeople() {
		if (lineArray.length != 3) {
			System.out.println("The input doesn't match with the wanted format for people.");
			System.exit(0);
		}
	}

	/**
	 * Check if the format for the tasks is taskName:n1
	 */
	public void checkTasks() {
		if (lineArray.length != 2) {
			System.out.println("The input doesn't match with the wanted format for appliances.");
			System.exit(0);
		}
	}

	/**
	 * @param ourHouse
	 * @return If there are more than 25 appliances created in the house
	 */
	public boolean limitAppliances(House ourHouse) {
		if (ourHouse.getAmountOfAppliances() >= 25) {
			System.out.println("Cannot exceed 25 appliances in a single house.");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Adds tasks to each person in the house
	 * 
	 * @return If a tasks was added
	 */
	public boolean buildTask() {
		for (String name : ourHouse.getTasks()) { // Loops through the avalaible
													// tasks in the house
			if (lineArray[0].contains(name)) { // if it finds a match the task
												// is added to the last created
												// person
				if (ourHouse.peopleExist()) { // Checks if people were created
												// first
					checkTasks();
					Integer startTime = Integer.parseInt(lineArray[1]);
					ourHouse.getLastPerson().addTask(lineArray[0], startTime); // gets
																				// the
																				// last
																				// person
																				// created
					return true;
				} else {
					System.out.println("ERROR:No person is created for the task.");
					System.exit(0);
				}
			}
		}
		return false;
	}
	/**
	 * Gets the given price/unit for a meter
	 */
	public boolean getPrice() {
		if (!ourHouse.getMeters().isEmpty()) { // At least a meter must have been created
			if (lineArray[0].equals("cost")) {
				for (Meter meter : ourHouse.getMeters()) {
					if (meter.getMeterName().equals(lineArray[1])) {
						num1 = Float.parseFloat(lineArray[2]);
						meter.setPrice(num1);
						return true;
					}
					System.out.println("ERROR:There is no existing meter for this price");
				}
			}
		} else {
			System.out.println("No meters to set the cost");
		}
		return false;

	}

	/**
	 * Creates a person in the house
	 * 
	 * @return If a person is created
	 */
	public boolean buildPerson() {
		String name;
		Integer age;
		char gender;
		if (lineArray[0].equals("person")) {
			lineArray = lineArray[1].split(","); // Splits the string after :
													// based on the accepted
													// format
			age = Integer.parseInt(lineArray[1]);
			name = Character.toUpperCase(lineArray[0].charAt(0)) + lineArray[0].substring(1); // makes
																								// the
																								// first
																								// letter
																								// of
																								// the
																								// name
																								// capital
			gender = lineArray[2].charAt(0);
			if (num1 >= 18) { // checks if it has to create an grownup or child
								// based on age
				ourHouse.addPerson(new GrownUp(name, age, gender));
			} else {
				ourHouse.addPerson(new Child(name, age, gender));
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return Boolean - If there is a House in the first line of the file
	 */
	public boolean hasHouse() {
		lineArray = input.getLine().toLowerCase().split(":");
		if (!lineArray[0].equals("house")) {
			System.out.println(lineArray[0] + "ERROR: No house is created");
			System.exit(0);
			return false;

		} else {
			return true;
		}
	}

	/**
	 * Builds a meter in the house
	 * 
	 * @return If a meter was created
	 */
	public boolean buildMeter() {
		switch (lineArray[0]) { // checks whether the word before : is the name
								// of a meter
		case "electricmeter":
			if (lineArray.length == 2) {
				lineArray = lineArray[1].split(",");
				checkMeters();
				switch (lineArray.length) { // based on the amount/type and the
											// accepted format of input it picks
											// a constructor
				case 1:
					switch (lineArray[0]) {
					case "true":
						ourHouse.addMeter(new ElectricMeter(true));
						break;
					case "false":
						ourHouse.addMeter(new ElectricMeter(false));
						break;
					default:
						num1 = Float.parseFloat(lineArray[0]);
						ourHouse.addMeter(new ElectricMeter(num1));
						break;
					}
					break;
				case 2:
					num1 = Float.parseFloat(lineArray[0]);
					switch (lineArray[1]) {
					case "true":
						ourHouse.addMeter(new ElectricMeter(num1, true));
						break;
					case "false":
						ourHouse.addMeter(new ElectricMeter(num1, false));
					}
					break;
				}
			} else {
				ourHouse.addMeter(new ElectricMeter());
			}
			return true;
		case "gasmeter":
			if (lineArray.length == 2) {
				lineArray = lineArray[1].split(",");
				checkMeters();
				switch (lineArray.length) {
				case 1:
					switch (lineArray[0]) {
					case "true":
						ourHouse.addMeter(new GasMeter(true));
						break;
					case "false":
						ourHouse.addMeter(new GasMeter(false));
						break;
					default:
						num1 = Float.parseFloat(lineArray[0]);
						ourHouse.addMeter(new GasMeter(num1));
						break;
					}
					break;
				case 2:
					num1 = Float.parseFloat(lineArray[0]);
					switch (lineArray[1]) {
					case "true":
						ourHouse.addMeter(new GasMeter(num1, true));
						break;
					case "false":
						ourHouse.addMeter(new GasMeter(num1, false));
						break;
					}
					break;
				}
			} else {
				ourHouse.addMeter(new GasMeter());
			}
			return true;
		case "watermeter":
			if (lineArray.length == 2) {
				lineArray = lineArray[1].split(",");
				checkMeters();
				switch (lineArray.length) {
				case 1:
					switch (lineArray[0]) {
					case "true":
						ourHouse.addMeter(new WaterMeter(true));
						break;
					case "false":
						ourHouse.addMeter(new WaterMeter(false));
						break;
					default:
						num1 = Float.parseFloat(lineArray[0]);
						ourHouse.addMeter(new WaterMeter(num1));
						break;
					}
					break;
				case 2:
					num1 = Float.parseFloat(lineArray[0]);
					switch (lineArray[1]) {
					case "true":
						ourHouse.addMeter(new WaterMeter(num1, true));
						break;
					case "false":
						ourHouse.addMeter(new WaterMeter(num1, false));
						break;
					}
					break;
				}
			} else {
				ourHouse.addMeter(new WaterMeter());
			}
			return true;
		default:
			return false;
		}
	}

	/**
	 * Creates an appliance in the house
	 * 
	 * @return If an appliance was created
	 */
	public boolean buildAppliance() {
		switch (lineArray[0]) { // checks whether the word before : is the name
								// of an appliance
		case "washingmachine":
			if (lineArray.length == 2) {
				lineArray = lineArray[1].split(",");
				checkAppliances();
				switch (lineArray.length) { // based on the amount/type and the
											// accepted format of input it picks
											// a constructor
				case 1:
					num1 = Float.parseFloat(lineArray[0]);
					ourHouse.addAppliance(new WashingMachine(num1));
					break;
				case 2:
					num1 = Float.parseFloat(lineArray[0]);
					num2 = Float.parseFloat(lineArray[1]);
					ourHouse.addAppliance(new WashingMachine(num1, num2));
					break;
				case 3:
					num1 = Float.parseFloat(lineArray[0]);
					num2 = Float.parseFloat(lineArray[1]);
					num3 = Float.parseFloat(lineArray[2]);
					ourHouse.addAppliance(new WashingMachine(num1, num2, num3));
					break;
				}
			} else {
				ourHouse.addAppliance(new WashingMachine());
			}
			return true;
		case "refrigerator":
			if (lineArray.length == 2) {
				lineArray = lineArray[1].split(",");
				checkAppliances();
				switch (lineArray.length) {
				case 1:
					num1 = Float.parseFloat(lineArray[0]);
					ourHouse.addAppliance(new Refrigerator(num1));
					break;
				case 2:
					num1 = Float.parseFloat(lineArray[0]);
					num2 = Float.parseFloat(lineArray[1]);
					ourHouse.addAppliance(new Refrigerator(num1, num2));
					break;
				case 3:
					num1 = Float.parseFloat(lineArray[0]);
					num2 = Float.parseFloat(lineArray[1]);
					num3 = Float.parseFloat(lineArray[2]);
					ourHouse.addAppliance(new Refrigerator(num1, num2, num3));
					break;
				}
			} else {
				ourHouse.addAppliance(new Refrigerator());
			}
			return true;
		case "kettle":
			if (lineArray.length == 2) {
				lineArray = lineArray[1].split(",");
				checkAppliances();
				switch (lineArray.length) {
				case 1:
					num1 = Float.parseFloat(lineArray[0]);
					ourHouse.addAppliance(new Kettle(num1));
					break;
				case 2:
					num1 = Float.parseFloat(lineArray[0]);
					num2 = Float.parseFloat(lineArray[1]);
					ourHouse.addAppliance(new Kettle(num1, num2));
					break;
				case 3:
					num1 = Float.parseFloat(lineArray[0]);
					num2 = Float.parseFloat(lineArray[1]);
					num3 = Float.parseFloat(lineArray[2]);
					ourHouse.addAppliance(new Kettle(num1, num2, num3));
					break;
				}
			} else {
				ourHouse.addAppliance(new Kettle());
			}
			return true;
		case "boiler":
			if (lineArray.length == 2) {
				lineArray = lineArray[1].split(",");
				checkAppliances();
				switch (lineArray.length) {
				case 1:
					num1 = Float.parseFloat(lineArray[0]);
					ourHouse.addAppliance(new Boiler(num1));
					break;
				case 2:
					num1 = Float.parseFloat(lineArray[0]);
					num2 = Float.parseFloat(lineArray[1]);
					ourHouse.addAppliance(new Boiler(num1, num2));
					break;
				case 3:
					num1 = Float.parseFloat(lineArray[0]);
					num2 = Float.parseFloat(lineArray[1]);
					num3 = Float.parseFloat(lineArray[2]);
					ourHouse.addAppliance(new Boiler(num1, num2, num3));
					break;
				}
			} else {
				ourHouse.addAppliance(new Boiler());
			}
			return true;
		case "dishwasher":
			if (lineArray.length == 2) {
				lineArray = lineArray[1].split(",");
				checkAppliances();
				switch (lineArray.length) {
				case 1:
					num1 = Float.parseFloat(lineArray[0]);
					ourHouse.addAppliance(new DishWasher(num1));
					break;
				case 2:
					num1 = Float.parseFloat(lineArray[0]);
					num2 = Float.parseFloat(lineArray[1]);
					ourHouse.addAppliance(new DishWasher(num1, num2));
					break;
				case 3:
					num1 = Float.parseFloat(lineArray[0]);
					num2 = Float.parseFloat(lineArray[1]);
					num3 = Float.parseFloat(lineArray[2]);
					ourHouse.addAppliance(new DishWasher(num1, num2, num3));
					break;
				}
			} else {
				ourHouse.addAppliance(new DishWasher());
			}
			return true;
		case "tv":
			if (lineArray.length == 2) {
				lineArray = lineArray[1].split(",");
				checkAppliances();
				switch (lineArray.length) {
				case 1:
					num1 = Float.parseFloat(lineArray[0]);
					ourHouse.addAppliance(new TV(num1));
					break;
				case 2:
					num1 = Float.parseFloat(lineArray[0]);
					num2 = Float.parseFloat(lineArray[1]);
					ourHouse.addAppliance(new TV(num1, num2));
					break;
				case 3:
					num1 = Float.parseFloat(lineArray[0]);
					num2 = Float.parseFloat(lineArray[1]);
					num3 = Float.parseFloat(lineArray[2]);
					ourHouse.addAppliance(new TV(num1, num2, num3));
					break;
				}
			} else {
				ourHouse.addAppliance(new TV());
			}
			return true;
		case "nighlight":
			if (lineArray.length == 2) {
				lineArray = lineArray[1].split(",");
				checkAppliances();
				switch (lineArray.length) {
				case 1:
					num1 = Float.parseFloat(lineArray[0]);
					ourHouse.addAppliance(new NightLight(num1));
					break;
				case 2:
					num1 = Float.parseFloat(lineArray[0]);
					num2 = Float.parseFloat(lineArray[1]);
					ourHouse.addAppliance(new NightLight(num1, num2));
					break;
				case 3:
					num1 = Float.parseFloat(lineArray[0]);
					num2 = Float.parseFloat(lineArray[1]);
					num3 = Float.parseFloat(lineArray[2]);
					ourHouse.addAppliance(new NightLight(num1, num2, num3));
					break;
				}
			} else {
				ourHouse.addAppliance(new NightLight());
			}
			return true;
		case "electriccooker":
			if (lineArray.length == 2) {
				lineArray = lineArray[1].split(",");
				checkAppliances();
				switch (lineArray.length) {
				case 1:
					num1 = Float.parseFloat(lineArray[0]);
					ourHouse.addAppliance(new ElectricCooker(num1));
					break;
				case 2:
					num1 = Float.parseFloat(lineArray[0]);
					num2 = Float.parseFloat(lineArray[1]);
					ourHouse.addAppliance(new ElectricCooker(num1, num2));
					break;
				case 3:
					num1 = Float.parseFloat(lineArray[0]);
					num2 = Float.parseFloat(lineArray[1]);
					num3 = Float.parseFloat(lineArray[2]);
					ourHouse.addAppliance(new ElectricCooker(num1, num2, num3));
					break;
				}
			} else {
				ourHouse.addAppliance(new ElectricCooker());
			}
			return true;
		case "solarpanel":
			if (lineArray.length == 2) {
				lineArray = lineArray[1].split(",");
				checkAppliances();
				switch (lineArray.length) {
				case 1:
					num1 = Float.parseFloat(lineArray[0]);
					ourHouse.addAppliance(new SolarPanel(num1));
					break;
				case 2:
					num1 = Float.parseFloat(lineArray[0]);
					num2 = Float.parseFloat(lineArray[1]);
					ourHouse.addAppliance(new SolarPanel(num1, num2));
					break;
				case 3:
					num1 = Float.parseFloat(lineArray[0]);
					num2 = Float.parseFloat(lineArray[1]);
					num3 = Float.parseFloat(lineArray[2]);
					ourHouse.addAppliance(new SolarPanel(num1, num2, num3));
					break;
				}
			} else {
				ourHouse.addAppliance(new SolarPanel());
			}
			return true;
		case "gascooker":
			if (lineArray.length == 2) {
				lineArray = lineArray[1].split(",");
				checkAppliances();
				switch (lineArray.length) {
				case 1:
					num1 = Float.parseFloat(lineArray[0]);
					ourHouse.addAppliance(new GasCooker(num1));
					break;
				case 2:
					num1 = Float.parseFloat(lineArray[0]);
					num2 = Float.parseFloat(lineArray[1]);
					ourHouse.addAppliance(new GasCooker(num1, num2));
					break;
				case 3:
					num1 = Float.parseFloat(lineArray[0]);
					num2 = Float.parseFloat(lineArray[1]);
					num3 = Float.parseFloat(lineArray[2]);
					ourHouse.addAppliance(new GasCooker(num1, num2, num3));
					break;
				}
			} else {
				ourHouse.addAppliance(new GasCooker());
			}
			return true;
		case "electricshower":
			if (lineArray.length == 2) {
				lineArray = lineArray[1].split(",");
				checkAppliances();
				switch (lineArray.length) {
				case 1:
					num1 = Float.parseFloat(lineArray[0]);
					ourHouse.addAppliance(new ElectricShower(num1));
					break;
				case 2:
					num1 = Float.parseFloat(lineArray[0]);
					num2 = Float.parseFloat(lineArray[1]);
					ourHouse.addAppliance(new ElectricShower(num1, num2));
					break;
				case 3:
					num1 = Float.parseFloat(lineArray[0]);
					num2 = Float.parseFloat(lineArray[1]);
					num3 = Float.parseFloat(lineArray[2]);
					ourHouse.addAppliance(new ElectricShower(num1, num2, num3));
					break;
				}
			} else {
				ourHouse.addAppliance(new ElectricShower());
			}
			return true;
		case "powershower":
			if (lineArray.length == 2) {
				lineArray = lineArray[1].split(",");
				checkAppliances();
				switch (lineArray.length) {
				case 1:
					num1 = Float.parseFloat(lineArray[0]);
					ourHouse.addAppliance(new PowerShower(num1));
					break;
				case 2:
					num1 = Float.parseFloat(lineArray[0]);
					num2 = Float.parseFloat(lineArray[1]);
					ourHouse.addAppliance(new PowerShower(num1, num2));
					break;
				case 3:
					num1 = Float.parseFloat(lineArray[0]);
					num2 = Float.parseFloat(lineArray[1]);
					num3 = Float.parseFloat(lineArray[2]);
					ourHouse.addAppliance(new PowerShower(num1, num2, num3));
					break;
				}
			} else {
				ourHouse.addAppliance(new PowerShower());
			}
			return true;
		default:
			return false;
		}

	}

}
