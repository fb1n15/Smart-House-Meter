
public class Boiler extends ApplianceOnOff {
	/**
	 * @see super
	 */
	public Boiler(float elUse, float gaUse, float waUse) {
		super(elUse, gaUse, waUse, 0, "boiler",0);
	}

	public Boiler(float elUse, float gaUse) {
		super(elUse, gaUse, 0, 0, "boiler",0);
	}

	public Boiler(float gaUse) {
		super(0, gaUse, 0, 0, "boiler",0);
	}

	public Boiler() {
		super(0, 1, 0, 0, "boiler",0);
	}
	/*
	 * @see Appliance#getMeters()
	 */
	public String[] getMeters() {
		String[] meters = { "gasmeter" };
		return meters;
	}

	@Override
	/*
	 * @see ApplianceOnOff#turnOn(Person)
	 */
	public boolean turnOn(Person user) {
		if (user.isAdult()) { // Check if the user is an adult(age>=18) in order to turn on this appliance. Else shows an error message
			this.setState(true);
			this.setCurrentUser(user);
			System.out.println(user.getName() + " turns on the boiler.");
			return true;
		} else {
			System.out.println(user.getName() + " is not allowed to turn on the boiler.");
			return false;
		}
	}

	@Override
	/*
	 * @see ApplianceOnOff#turnOff(Person)
	 */
	public boolean turnOff(Person user) {
		if (user.isAdult()) { // Check if the user is an adult(age>=18) in order to turn off this appliance. Else shows an error message
			this.setState(false);
			System.out.println(user.getName() + " turns off the boiler.");
			return true;
		} else {
			System.out.println(user.getName() + " is not allowed to turn off the boiler.");
			return false;
		}
	}

	
}
