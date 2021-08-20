
public class Customer implements Comparable<Customer>{
	private int time_of_day;
	private int service_time;
	private int Type_of_Event;
	
	/**
	 * 
	 * @param timeDay
	 * @param service
	 * @param event
	 */
	public Customer(int timeDay, int service, int event) {
		time_of_day = timeDay;
		service_time = service;
		Type_of_Event = event;
	}
	
	/*
	 * Compares another customer to see if th
	 */
	public int compareTo(Customer c1) {
		if(this.time_of_day == c1.time_of_day) {
			return 0;
		}
		if(this.time_of_day < c1.time_of_day) {
			return -1;
		}
		else {
			return 1;
		}
	}
	
	/**
	 * 
	 * @return the time of day of the customer
	 */
	public int getDay() {
		return time_of_day;
	}
	
	
	/**
	 * 
	 * @return the service time of the customer
	 */
	public int serviceTime() {
		return service_time;
	}
	
	/**
	 * 
	 * @return the type of event of the customer
	 */
	public int getEvent() {
		return Type_of_Event;
	}
	
}
