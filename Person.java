import java.util.HashMap;
import java.util.ArrayList;

public abstract class Person {

	String name;
	Integer age;
	char gender;
	String type; // GrownUp or Child
	Integer busyUntil; // based on the timeOn of an appliance until when he will
						// use it
	boolean taskDone = false; // if the task was found and done
	Integer currentStartTime; // the startTime of a task
	boolean isBusy; // if the person is performing another action
	/**
	 * The tasks allocated to this person
	 */
	HashMap<String, Integer> tasks = new HashMap<String, Integer>();

	/**
	 * @param peName
	 *            Person's name
	 * @param peAge
	 *            Person's Age
	 * @param peGender
	 * @param peType
	 *            GrownUp or Child
	 */
	public Person(String peName, Integer peAge, char peGender, String peType) {
		name = peName;
		age = peAge;
		gender = peGender;
		type = peType;
		busyUntil = -1;
	}

	public boolean isAdult() {
		return age>=18;
	}

	/**
	 * 
	 * @param applianceList
	 *            The list of existing appliance in the house passed from House
	 * @param currentTask
	 *            The name of the current task
	 */
	public void turnOnAppliance(ArrayList<Appliance> applianceList, String currentTask) {
		for (Appliance appliance : applianceList) {
			/*
			 * It compares the word after "turnon" with an appliance name
			 * eg.turnonTV
			 */
			if (currentTask.substring(6).equals(appliance.getName())) {
				appliance.turnOn(this); // turns the appliance on
				isBusy = true;
				taskDone = true;
			}
		}
	}

	/**
	 * 
	 * @param applianceList
	 *            The list of existing appliance in the house passed from House
	 * @param currentTask
	 *            The name of the current task
	 */
	public void turnOffAppliance(ArrayList<Appliance> applianceList, String currentTask) {
		for (Appliance appliance : applianceList) {
			/*
			 * It compares the word after "turnoff" with an appliance name
			 * eg.turnoffTV
			 */
			if (currentTask.substring(7).equals(appliance.getName())) {
				appliance.turnOff(this);
				isBusy = false;
				taskDone = true;
			}
		}
	}

	/**
	 * Matches the name of each task with the use() method in an appliance or
	 * the turnOn/turnoff for ApplianceOnOff. After it performs this action.
	 * 
	 * @param time
	 *            Global Time
	 * @param applianceList
	 *            The list of existing appliance in the house passed from House
	 */
	public void checkTasks(Integer time, ArrayList<Appliance> applianceList) {
		for (String currentTask : tasks.keySet()) {
			currentStartTime = tasks.get(currentTask);
			if (currentStartTime.equals(time)) { // If the tasks starts at this hour now then it continues
				if (currentTask.contains("turnon")) { // if the task is a turnon action
					this.turnOnAppliance(applianceList, currentTask);
				} else if (currentTask.contains("turnoff")) { // if the task is a turnoff action
					this.turnOffAppliance(applianceList, currentTask);
				} else { // else the task is a standard task from an appliance
					for (Appliance appliance : applianceList) {
						if (currentTask.equals(appliance.getTask())) { // It matches the task with an appliance
							if (!appliance.getState()) { // if the appliance and the person not busy it uses it
								if (busyUntil <= time && !isBusy) {
									appliance.use(this);
									busyUntil = appliance.getTimeOn() + time; //sets until when the person is going to be busy
									taskDone = true;
								} else {
									System.out.println(this.getName() + " is busy and cannot " + currentTask);
									/*
									 * If the task cannot be started now then it moves its operation
									 * one time unit into the future until the day ends or the person
									 * is not busy and there is an available machine
									 */
									tasks.put(currentTask, currentStartTime + 1);
								}
							}
						}
					}
					if (!taskDone) { // There is no free machine for the task
						System.out.println("There is no avalaible machine for " + currentTask);
						tasks.put(currentTask, currentStartTime + 1); // see above
					}
				}
			}
		}
	}
	/**
	 *  The time simulation of Person
	 */
	public void timePasses(Integer time, ArrayList<Appliance> applianceList) {
		if (!tasks.isEmpty()) {
			this.checkTasks(time, applianceList);
		}

	}
	/**
	 * It adds a task to this person
	 * @param name The name of the task
	 * @param startTime When the task needs to be started
	 */
	public void addTask(String name, Integer startTime) {
		tasks.put(name, startTime);
	}

	public void setIsBusy(boolean busy) {
		isBusy = busy;
	}

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}
}
