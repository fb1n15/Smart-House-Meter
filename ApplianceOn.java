
public abstract class ApplianceOn extends Appliance {
	/**
	 * For the appliances that stay on all day
	 * @param elUse The electric power consumed
	 * @param gaUse The gas consumed
	 * @param watUse The water consumed
	 * @param appName The name of the appliance
	 * @param elGen The electric power generated
	 * -1: timeOn @see super
	 * "": task @see super 
	 */
	public ApplianceOn(float elUse,float gaUse, float watUse, String appName, float elGen){
		super(elUse,gaUse,watUse,-1,appName,"",elGen);
	}
	
	@Override
	/*
	 * @see Appliance#use(Person)
	 */
	 public boolean use(Person person) {
		System.out.println(this.getName() + " is running.");
		return true;
	}

	
}
