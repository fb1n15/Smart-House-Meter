
public class Kettle extends ApplianceTime {
	/*
	 * @see super
	 */
	public Kettle(float elUse, float gaUse, float waUse) {
		super(elUse, gaUse, waUse, 1, "kettle", "boil", 0);
	}

	public Kettle(float elUse, float waUse) {
		super(elUse, 0, waUse, 1, "kettle", "boil", 0);
	}

	public Kettle(float elUse) {
		super(elUse, 0, 1, 1, "kettle", "boil", 0);
	}

	public Kettle() {
		super(20, 0, 1, 1, "kettle", "boil", 0);
	}

	public String[] getMeters() {
		String[] meters = { "electricmeter", "watermeter" };
		return meters;
	}

	@Override
	/*
	 * @see Boiler#use(Person)
	 */
	public boolean use(Person user) {
		if (user.isAdult()) {
			if (!this.getState()) {
				this.setCurrentUser(user);	
				this.setState(true);
			} else {
				System.out.println(user.getName() + " is boiling some water.");
			}
			return true;
		} else {
			System.out.println(user.getName() + " is not allowed to use the kettle.");
			return false;
		}
	}

}
