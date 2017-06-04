import java.util.ArrayList;

public abstract class Appliance {

	/*
	 * Values that are consumed by the appliance
	 */
	float electricUse;
	float gasUse;
	float waterUse;
	/*
	 * Values that are generates by the appliance
	 */
	float electricGen;
	
	Integer onlineTime; // The real amount of time an appliance spends on each time.
	Integer timeOn;  // The time an appliance should stay on.
	boolean currentState; //true-Appliance is on , off-Appliance is off
	String name;
	String task; // The task that the appliance is doing
	String[] meters; // The name of the meters each appliance is connected to
	Person currentUser; // Who is using the appliance

	public Appliance(float elUse, float gaUse, float watUse, Integer time, String appName, String appTask,
			float elGen) {
		electricUse = elUse;
		gasUse = gaUse;
		waterUse = watUse;
		timeOn = time;
		name = appName;
		task = appTask;
		onlineTime = 0;
		electricGen = elGen;
		/*
		 * For appliances that stay On all day the timeOn is -1 
		 */
		if (time.equals(-1)) {
			currentState = true;
		} else {
			currentState = false;
		}
	}

	public void setState(boolean state) {
		currentState = state;
	}

	public boolean getState() {
		return currentState;
	}

	public String getName() {
		return name;
	}

	public String getTask() {
		return task;
	}
	/*
	 * returns the amount of time an appliance should stay on.
	 */
	public Integer getTimeOn() {
		return timeOn;
	}

	public void setCurrentUser(Person currentPerson) {
		currentUser = currentPerson;
	}
	/**
	 * Loops through the active meters and increase their values.
	 * @param metersList An array from the House that contains the active meters in it.
	 */
	public void increaseMeters(ArrayList<Meter> metersList) {
		for (Meter meter : metersList) {
			for (String appMeter : this.getMeters()) { // each appMeter represents the meter name that the appliance returns 
				if (meter.getMeterName().equals(appMeter)) {
					switch (meter.getMeterName()) { //Decides which variables to pass at the meters
					case "electricmeter":
						meter.incrementConsumed(electricUse);
						if (meter.canGenerate()) {
							meter.incrementGenerated(electricGen);
						}
						break;
					case "gasmeter":
						meter.incrementConsumed(gasUse);
						break;
					case "watermeter":
						meter.incrementConsumed(waterUse);
						break;
					}
				}
			}
		}
	}
	/**
	 * Does the task that the appliance is responsible for
	 * @param person The person that calls the appliance
	 * @return whether the use of the appliance was permitted or completed
	 */
	public abstract boolean use(Person person);
	
	/**
	 * Loops through the state of each appliance.If on it may switch it off or increase its meters
	 * @param metersList The array with the active meters in the house
	 */
	public void timePasses(ArrayList<Meter> metersList) {
		if (this.getState()) {
			onlineTime++; //Increases the time the appliance is currently on
			this.increaseMeters(metersList); // Loops through the meters the appliance is connected
			this.use(currentUser); // The appliance is being used

			if (timeOn.equals(onlineTime)) {
				this.setState(false);
				onlineTime = 0;
			}
		}
	}
	/**
	 * @return The name of the meters each appliance is connect to
	 */
	public abstract String[] getMeters();
	/**
	 * @param user The person that uses the appliance
	 * @return if whether the use of the appliance was permitted or completed
	 */
	public boolean turnOn(Person user) {
		System.out.println(user.getName() + " turns on " + this.getName());
		this.setState(true);
		return true;
	}
	/**
	 * @param user The person that uses the appliance
	 * @return if whether the use of the appliance was permitted or completed
	 */
	public boolean turnOff(Person user) {
		System.out.println(user.getName() + " turns off " + this.getName());
		this.setState(false);
		return true;
	}

}
