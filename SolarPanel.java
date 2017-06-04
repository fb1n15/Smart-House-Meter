
public class SolarPanel extends ApplianceOn {

	public SolarPanel(float elUse, float gaUse, float waUse, float elGen) {
		super(0,0,0, "solar panel", elGen);
	}

	public SolarPanel(float elUse, float waUse, float elGen) {
		super(0, 0, 0, "solar panel", elGen);
	}

	public SolarPanel(float elUse, float elGen) {
		super(0,0,0, "solar panel", elGen);
	}

	public SolarPanel(float elGen) {
		super(0, 0, 0, "solar panel", elGen);
	}
	
	public SolarPanel() {
		super(0, 0, 0, "solar panel", 2);
	}


	@Override
	/*
	 * @see Appliance#getMeters()
	 */
	public String[] getMeters() {
		String[] meters = { "electricmeter"};
		return meters;
	}

}
