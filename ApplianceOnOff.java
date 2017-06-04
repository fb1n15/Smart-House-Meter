
public abstract class ApplianceOnOff extends Appliance{
	/**
	 * For the appliances that the person turns off and on in order to use them.
	 * @param elUse The electric power consumed
	 * @param gaUse The gas consumed
	 * @param watUse The water consumed
	 * @param time The time the appliance should stay on
	 * @param appName The name of the appliance
	 * @param elGen The electric power generated
	 * "onoff": task @see super
	 */
	public ApplianceOnOff(float elUse,float gaUse, float watUse, int time, String appName, float elGen){
		super(elUse,gaUse,watUse,time,appName,"onoff", elGen);
	}
	
	@Override
	public boolean use(Person user) {
		System.out.println( user.getName() + " uses the " + this.getName() + ".");
		return true;
	}
	/**
	 * @see super.turnOn(Person);
	 */
	public boolean turnOn(Person user) {
		this.setState(true);
		this.setCurrentUser(user);
		System.out.println(user.getName() + " turns on the " + this.getName() + ".");
		return true;
	}
	
	@Override
	/**
	 * @see super.turnOff(Person);
	 */
	public boolean turnOff(Person user) {
		this.setState(false);
		System.out.println(user.getName()+" turns off the " + this.getName() + ".");
		return true;
	}

}