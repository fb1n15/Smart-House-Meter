
public abstract class Meter {
	
	float consumed;
	float generated;
	boolean canGenerate;
	float price; //The price of each power unit
	
	/**
	 * 
	 * @param defaultConsumed The starting value of the meter
	 * @param isEnergyGenerated Whether the meter can record generated energy
	 */
	public Meter(float defaultConsumed , boolean isEnergyGenerated){
		canGenerate = isEnergyGenerated;
		generated = 0;
		consumed = defaultConsumed;
		price=0;
	}
	
	
	public void incrementConsumed(float energyConsumed){
		consumed = consumed+ energyConsumed;
	}
	
	public void incrementGenerated(float energyGenerated){
		generated = generated + energyGenerated;
	}
	
	public boolean canGenerate(){
		return canGenerate;
	}
	
	public float getConsumed(){
		return consumed;
	}
	
	public float getGenerated(){
		return generated;
	}
	/**
	 * @return Type of the meter
	 */
	public abstract String getType();
	/**
	 * @return The name of the meter
	 */
	public abstract String getMeterName();
	
	/**
	 * Sets the price/unit
	 * @param givenPrice The input price from the file
	 */
	public void setPrice(float givenPrice){
		price = givenPrice;
	}
	
	public float getCost(){
		return consumed*price;
	}
}
