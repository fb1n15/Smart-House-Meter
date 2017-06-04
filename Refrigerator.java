
public class Refrigerator extends ApplianceOn {
	
	public Refrigerator(float elUse,float gaUse,float waUse){
		super(elUse,gaUse,waUse,"refrigerator", 0);
	}
	
	public Refrigerator(float elUse,float gaUse){
		super(elUse,gaUse,0,"refrigerator", 0);
	}
	
	public Refrigerator(float elUse){
		super(elUse,0,0,"refrigerator", 0);
	}
	public Refrigerator(){
		super(1,0,0,"refrigerator", 0);
	}
	/*
	 * @see Appliance#getMeters()
	 */
	public String[] getMeters(){
		String [] meters = {"electricmeter"};
		return meters;
	}



}
