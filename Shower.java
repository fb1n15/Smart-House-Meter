
public abstract class Shower extends Appliance{
	
	public Shower(float elUse,float gaUse, float watUse, int time, String appName){
		super(elUse,gaUse,watUse,time,appName,"shower", 0);
	}
	
	public boolean use(Person user) {
		if (!this.getState()) {
			this.setCurrentUser(user);	
			this.setState(true);
		} else {
			System.out.println(user.getName() + " showers at the " + this.getName());
		}
		return true;
	}
	
}
