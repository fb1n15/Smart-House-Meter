
public abstract class Cooker extends Appliance {
	/*
	 * @see super
	 * "cook" :  The task for all the cookers
	 */
	public Cooker(float elUse, float gaUse, float watUse, int time, String appName) {
		super(elUse, gaUse, watUse, time, appName, "cook", 0);
	}

	@Override
	/*
	 * @see Boiler#use(Person)
	 */
	public boolean use(Person user) {
		if (user.isAdult()) { // Check if the user is an adult(age>=18) in order to use this appliance. Else shows an error message
			if (!this.getState()) {
				this.setCurrentUser(user);
				this.setState(true);
			} else {
				System.out.println(user.getName() + " is cooking at the " + this.getName());
			}
			return true;
		} else {
			System.out.println(user.getName() + " is not allowed to turn off the boiler.");
			return false;
		}
	}
}
