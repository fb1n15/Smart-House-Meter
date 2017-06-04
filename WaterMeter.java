
public class WaterMeter extends Meter {

	public WaterMeter(float defaultConsumed) {
		super(defaultConsumed, false);
	}
	
	public WaterMeter(float defaultConsumed, boolean canGenerate) {
		super(defaultConsumed, canGenerate);
	}
	
	public WaterMeter(boolean canGenerate) {
		super(0, canGenerate);
	}
	
	public WaterMeter() {
		super(0, false);
	}

	@Override
	/*
	 * @see Meter#getType()
	 */
	public String getType() {
		return "water";
	}

	@Override
	/*
	 * @see Meter#getMeterName()
	 */
	public String getMeterName() {
		return "watermeter";
	}

}
