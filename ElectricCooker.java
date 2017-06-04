
public class ElectricCooker extends Cooker {
	/*
	 * @see super
	 */
	public ElectricCooker(float elUse, float gaUse, float watUse) {
		super(elUse, gaUse, watUse, 4, "ElectricCooker");
	}

	public ElectricCooker(float elUse, float gaUse) {
		super(elUse, gaUse, 0, 4, "ElectricCooker");
	}

	public ElectricCooker(float elUse) {
		super(elUse, 0, 0, 4, "ElectricCooker");
	}

	public ElectricCooker() {
		super(5, 0, 0, 4, "ElectricCooker");
	}
	/*
	 * @see Appliance#getMeters()
	 */
	public String[] getMeters() {
		String[] meters = { "electricmeter" };
		return meters;
	}

}
