
public class TV extends ApplianceOnOff {

	public TV(float elUse, float gaUse, float waUse) {
		super(elUse, gaUse, waUse, 0, "tv", 0);
	}

	public TV(float elUse, float gaUse) {
		super(elUse, gaUse, 0, 0, "tv", 0);
	}

	public TV(float elUse) {
		super(elUse, 0, 0, 0, "tv", 0);
	}

	public TV() {
		super(1, 0, 0, 0, "tv", 0);
	}
/*
 * @see Appliance#getMeters()
 */
	public String[] getMeters() {
		String[] meters = { "electricmeter" };
		return meters;
	}

	@Override
	/*
	 * @see ApplianceOnOff#turnOff(Person) 
	 */
	public boolean turnOff(Person user) {
		if (user.isAdult()) { // checks whether the user is an adult and therefore can turn off the appliance
			this.setState(false);
			this.use(currentUser);
			System.out.println(user.getName() + " turns off the TV.");
			return true;

		} else {
			System.out.println(user.getName() + " is not allowed to turn off the TV.");
			return false;
		}
	}

	

}
