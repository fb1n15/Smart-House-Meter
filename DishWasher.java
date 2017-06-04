
public class DishWasher extends ApplianceTime {
	/*
	 * @see super
	 */
	public DishWasher(float elUse, float gaUse, float waUse){
		super(elUse,gaUse,waUse,6,"dishwasher", "washdishes",0);
	}
	
	public DishWasher(float elUse, float waUse){
		super(elUse,0,waUse,6,"dishwasher", "washdishes",0);
	}
	
	public DishWasher(float elUse){
		super(elUse,0,1,6,"dishwasher", "washdishes",0);
	}
	
	public DishWasher(){
		super(2,0,1,6,"dishwasher", "washdishes",0);
	}
	/*
	 * @see Appliance#getMeters()
	 */
	public String[] getMeters(){
		String [] meters = {"electricmeter","watermeter"};
		return meters;
	}
	
	@Override
	/*
	 * @see Appliance#use(Person)
	 */
	public boolean use(Person person) {
		this.setCurrentUser(person);
		System.out.println(person.getName()+" washes some dishes.");
		return true;
	}
}
