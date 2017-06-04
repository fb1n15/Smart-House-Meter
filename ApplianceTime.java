
public abstract class ApplianceTime extends Appliance {
	/**
	 * For the appliances that operate for a prespecified and limited time.
	 *  @param elUse The electric power consumed
	 * @param gaUse The gas consumed
	 * @param watUse The water consumed
	 * @param time The time the appliance should stay on
	 * @param appName The name of the appliance
	 * @param appName The task of the appliance
	 * @param elGen The electric power generated
	 */
	public ApplianceTime(float elUse,float gaUse, float watUse, int time, String appName, String appTask, float elGen){
		super(elUse,gaUse,watUse,time,appName,appTask, elGen);
	}
	
}
