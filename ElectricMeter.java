
public class ElectricMeter extends Meter {
	/*
	 * @see super
	 */
	public ElectricMeter(float defaultConsumed) {
		super(defaultConsumed, true);
	}
	
	public ElectricMeter(boolean canGenerate) {
		super(0, canGenerate);
	}
	
	public ElectricMeter(float defaultConsumed, boolean canGenerate) {
		super(defaultConsumed, canGenerate);
	}
	
	public ElectricMeter() {
		super(0, true);
	}
	@Override
	/*
	 * @see Meter#getType()
	 */
	public String getType() {
		return "electric";
	}

	@Override
	/*
	 * @see Meter#getMeterName()
	 */
	public String getMeterName() {
		return "electricmeter";
	}
}
