
public class ElectricShower extends Shower {
	/*
	 * @see super
	 */
	public ElectricShower(float elUse, float gaUse, float watUse) {
		super(elUse, 0, watUse, 1, "ElectricShower");
	}

	public ElectricShower(float elUse, float watUse) {
		super(elUse, 0, watUse, 1, "ElectricShower");
	}

	public ElectricShower(float elUse) {
		super(elUse, 0, 4, 1, "ElectricShower");
	}

	public ElectricShower() {
		super(12, 0, 4, 1, "ElectricShower");
	}
	/*
	 * @see Appliance#getMeters()
	 */
	public String[] getMeters() {
		String[] meters = { "electricmeter", "watermeter" };
		return meters;
	}


}
