
public class GasCooker extends Cooker {
	/*
	 * @see super
	 */
	public GasCooker(float elUse, float gaUse, float watUse) {
		super(elUse, gaUse, watUse, 4, "GasCooker");
	}

	public GasCooker(float elUse, float gaUse) {
		super(elUse, gaUse, 0, 4, "GasCooker");
	}

	public GasCooker(float gaUse) {
		super(0, gaUse, 0, 4, "GasCooker");
	}

	public GasCooker() {
		super(0, 4, 0, 4, "GasCooker");
	}
	/*
	 * @see Appliance#getMeters()
	 */
	public String[] getMeters() {
		String[] meters = { "gasmeter" };
		return meters;
	}

}
