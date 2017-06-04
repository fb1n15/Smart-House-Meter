
public class GasMeter extends Meter {
	/*
	 * @see super
	 */
	public GasMeter(float defaultConsumed) {
		super(defaultConsumed, false);
	}
	
	public GasMeter(float defaultConsumed, boolean canGenerate) {
		super(defaultConsumed, canGenerate);
	}
	
	public GasMeter(boolean canGenerate) {
		super(0, canGenerate);
	}
	
	public GasMeter() {
		super(0, false);
	}
	/*
	 * @see Meter#getType()
	 */
	@Override
	public String getType() {
		return "gas";
	}
	/*
	 * @see Meter#getMeterName()
	 */
	@Override
	public String getMeterName() {
		return "gasmeter";
	}

}
