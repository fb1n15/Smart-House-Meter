
public class NightLight extends ApplianceOnOff{
	
	public NightLight(float elUse, float gaUse, float waUse){
		super(elUse,gaUse,waUse,0,"nightLight", 0);
	}
	
	public NightLight(float elUse, float gaUse){
		super(elUse,gaUse,0,0,"nightLight", 0);
	}
	
	public NightLight(float elUse){
		super(elUse,0,0,0,"nightLight", 0);
	}
	
	public NightLight(){
		super(1,0,0,0,"nightLight", 0);
	}
	
	public String[] getMeters(){
		String [] meters = {"electricmeter"};
		return meters;
	}
	
	@Override
	/*
	 * @see ApplianceOnOff#turnOn(Person)
	 */
	public boolean turnOn(Person user) {
		this.setState(true);
		this.setCurrentUser(user);
		System.out.println(user.getName()+" turns on the nightlight.");
		return true;
	}

	@Override
	/*
	 * @see ApplianceOnOff#turnOff(Person)
	 */
	public boolean turnOff(Person user) {
		this.setState(false);
		System.out.println(user.getName()+" turns off the nightlight.");
		return true;
	}

	
}
