
public class WashingMachine extends ApplianceTime {

	public WashingMachine(float elUse, float gaUse, float waUse) {
		super(elUse, gaUse, waUse, 8, "washingmachine", "dowashing", 0);
	}

	public WashingMachine(float elUse, float waUse) {
		super(elUse, 0, waUse, 8, "washingmachine", "dowashing", 0);
	}

	public WashingMachine(float elUse) {
		super(elUse, 0, 1, 8, "washingmachine", "dowashing", 0);
	}

	public WashingMachine() {
		super(2, 0, 1, 8, "washingmachine", "dowashing", 0);
	}

	public String[] getMeters() {
		String[] meters = { "electricmeter", "watermeter" };
		return meters;
	}

	@Override
	/*
	 * @see Appliance#use(Person)
	 */
	public boolean use(Person user) {
		if (!this.getState()) {
			this.setCurrentUser(user);	
			this.setState(true);
		} else {
			System.out.println(user.getName() + " is doing the washing.");
		}
		return true;

	}
}
