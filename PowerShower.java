public class PowerShower extends Shower {

	public PowerShower(float elUse, float gaUse, float waUse) {
		super(elUse, gaUse, waUse, 1, "ElectricShower");
	}

	public PowerShower(float gaUse, float watUse) {
		super(0, gaUse, watUse, 1, "ElectricShower");
	}

	public PowerShower(float gaUse) {
		super(0, gaUse, 5, 1, "ElectricShower");
	}

	public PowerShower() {
		super(0, 10, 5, 1, "ElectricShower");
	}
/*
 * @see Appliance#getMeters()
 */
	public String[] getMeters() {
		String[] meters = { "gasmeter", "watermeter" };
		return meters;
	}

}
