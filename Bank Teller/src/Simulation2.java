import java.util.Scanner;
import java.util.Random;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Simulation2 {
	Scanner kb = new Scanner(System.in);
	
	//gets the inter-arrival time for the customer
		public int interTime(int mean, int variant) {	
			Random rand = new Random();
			int small = mean-variant;
			int range = 2*variant+1;
			return small + rand.nextInt(range);
		}
		
		//gets the customer service time for the customer
		public int customerService(int mean, int variant) {
			Random rand = new Random();
			int small = mean-variant;
			int range = 2*variant+1;
			return small + rand.nextInt(range);
		}
		
		//starting the simulation
		public void start() {
			
			//Event Queue
			PriorityQueue<Customer> Event = new PriorityQueue<Customer>();
			
			//inputs
			String numVal;
			int numTeller;
			int meanInter;
			int varInter;
			int meanService;
			int varService;
			
			//things for the simulation 
			int clock = 0;
			int loc = 0;
			int smallest =0;
			int service;
			int inter;
			
			//outputs
			int customerCount = 0;
			int totalInter = 0;
			int totalService = 0;
			int waitTime=0;
			int maxWait=0;
			int totalWait=0;
			int maxQueue=0;
			int customerLeft = 0;
			
			Customer temp;
	
			int count = 1;
			
			//calculate how many tellers for the bank
			System.out.println("Welcome to the Bank simulation. Enter for the following things");
			System.out.print("Enter the number of bank tellers from 1 to 9: ");
	        numVal = kb.next();
	        numTeller = check(numVal);
	        while(numTeller == -1) {
	        	System.out.print("Enter a valid number of bank tellers from 1 to 9: ");
	        	numVal = kb.next();
	        	numTeller = check(numVal);
	        }
	        
	        
	        
			
			//array of the bank idle time
			int[] bankIdle = new int[numTeller];
			
			/**
			 * For this, the program ask the user for inputs to get the program working.
			 */
			System.out.print("Enter the mean for the inter-arrival times: ");
		    meanInter = check2();
			System.out.print("Enter the variance for the inter-arrival times: ");
			varInter = check2();
			System.out.print("Enter the mean of the customer service times: ");
			meanService = check2();
			System.out.print("Enter the variance of the customer service times: ");
			varService = check2();
		    System.out.print("Enter the time limit that you want the simulation to run: ");
		    int tLimit = check2();
		    
		    //Creating the Priority Queue to store the EventItem
		    LinkedList<Customer>[] bankTeller = new LinkedList[numTeller];
		    
		    for(int counter = 0; counter < bankTeller.length; counter++) {
		    	bankTeller[counter] = new LinkedList<Customer>();
		    }
		    
		    inter = interTime(meanInter,varInter);
		    service = customerService(meanService, varService);
		    totalInter+=inter;
		    totalService+=service;
		    
		    Customer c1 = new Customer(inter, service, -1);
		    Event.add(c1);
		    
		    System.out.println();
		    
		    /**
		     * This is the start of the Event Driven Simulation
		     */
		    while(clock < tLimit) {
		   
		    	temp = Event.remove();
		    	
		    	/**
		    	 * Prints out the customer count in each bank teller and the number of people in the Event
		    	 * Queue every 500 seconds that's passed. 
		    	 */
		    	
		    	//Start of output that prints every 500 seconds
		    	if((int)(clock/(500*count)) == 1) {
	    			System.out.println("Clock: " + clock);
	    			System.out.println("o Customer Count: ");
	    			for(int many = 0; many < numTeller; many++) {
	    				System.out.println("-Bank Teller " + (many+1) + ": " + bankTeller[many].size());
	    			}
	    			System.out.println("Number of Items in Event Queue: " + Event.size());  
	    			
	    			System.out.println();
	    			count++;
	    		}
		    	
		    	//end of output that prints every 500 seconds
		    	
		    	if(temp.getEvent() == -1) {
		    	for(int i = 0; i < numTeller; i++) { // Start idleTime For-Loop
		    		if(bankTeller[i].isEmpty() == true) {
		    			if(temp.getEvent() == -1) {
		    			bankIdle[i] += (temp.getDay()-clock);
		 
		    			}
		    			else {
		    				bankIdle[i]+=(bankTeller[temp.getEvent()].getFirst().getDay()-clock);
		    			}
		    		}
		    	}
		    	}
		    	
		    	
		    	clock = temp.getDay();
		    	
		    	//If the node is an arrival node start
		    	if(temp.getEvent() == -1) {
		    		smallest = 0;
		    		smallest = bankTeller[0].size();
		    		loc = 0;
		    		for(int b = 1; b < numTeller; b++) {
		    			if(bankTeller[b].size() < smallest) {
		    				smallest = bankTeller[b].size();
		    				loc = b;
		    			}	
		    		}
		    		bankTeller[loc].add(temp);
		    		
		    		if(bankTeller[loc].size() == 1) {
		    			Event.add(new Customer(clock+temp.serviceTime(), temp.serviceTime(), loc));
		    		}
		    		
		    		inter = interTime(meanInter,varInter);
				    service = customerService(meanService, varService);
				    totalInter+=inter;
				    totalService+=service;
				    Event.add(new Customer(clock+inter, service, -1));
				    if(bankTeller[loc].size() > maxQueue) {
				    	maxQueue=bankTeller[loc].size();
				    }
				    
		    	}
		    	//End of arrival node
		    	
		    	//If it's a Departure Node start
		    	else {
		    		customerCount++;
		    		
		    		waitTime = clock-(bankTeller[temp.getEvent()].getFirst().getDay()+temp.serviceTime());
		    		if(maxWait < waitTime) {
		    			maxWait = waitTime;
		    		}
		    		totalWait+=waitTime;
		    		
		    		bankTeller[temp.getEvent()].remove();
		    		if(bankTeller[temp.getEvent()].isEmpty() == false) {
		    			Event.add(new Customer(clock + bankTeller[temp.getEvent()].getFirst().serviceTime(),bankTeller[temp.getEvent()].getFirst().serviceTime() ,temp.getEvent()));
		    		}
		    		
		    	}
		    	//end of Departure node 
		    	
		    	}
		    
		    /**
		     * End of the Event Driven Simulation
		     */
		    	
		    
		    /**
		     * Prints the number of customer in each of the bank teller and
		     * the number of customer that still in the priority queue after the time limit
		     * has been reach.
		     */
		    System.out.println("Clock: " + tLimit);
			System.out.println("o Customer Count: ");
			for(int many = 0; many < numTeller; many++) {
				System.out.println("-Bank Teller " + (many+1) + ": " + bankTeller[many].size());
			}
			System.out.println("Number of Items in Event Queue: " + Event.size());    	
		    
		    System.out.println();
		    
		  //Calculate how many customer is left
	    	for(int c = 0; c < numTeller; c++) { //Start For
	    		customerLeft += bankTeller[c].size();
	    	} //End For
	    	customerLeft+= Event.size();
	    	
	    	System.out.println();
	    	
	    	/*
	    	 * This is the output where it displays the total number of customers that were processed,
	    	 * the average inter-arrival time, the average serice time, the average wait time, the percentage
	    	 * of the idle time for each of the cashiers, the maximum customer wait time, the max queue length of 
	    	 * any bank teller, and the total number of people that left in the queue at the end of the simulation. 
	    	 */
	    
	    System.out.println("Time: " + tLimit);
	
	    System.out.println("Statistics of Bank Simulation: ");
	    System.out.println("1. Total Number of Customers processed: " + customerCount);
	    System.out.println("2. Average Inter-Arrival Time: " + ((float)totalInter/(float)customerCount));
	    System.out.println("3. Average Service Time: " + ((float)totalService/(float)customerCount));
	    System.out.println("4. Average Wait Time: " + ((float)totalWait/(float)customerCount));
	    System.out.println("5. Percent of Idle Time for Each of the Cashiers: ");
	    for(int d = 0; d < numTeller; d++) {
	    	System.out.println("-Bank Teller " + (d+1) + ": " + ((float)bankIdle[d]/(float)clock)*100+"%");
	    }
	    System.out.println("6. Maximum Customer Wait Time: " + maxWait);
	    System.out.println("7. Maximum Queue Length of Any Bank Teller: " +maxQueue);
	    for(int a = 0; a < numTeller; a++) {
	    	System.out.println("-Teller " + (a+1) + " Customer Left: " + bankTeller[a].size());
	    }
	    System.out.println("8. Total Number of People left in the queues at the end of the Simulation: " + customerLeft);
		}

		/**
		 * Checks if the user enter a valid number and not something random
		 * @param s
		 * @return the number of bank tellers or -1 if the input that the user typed is
		 * not acceptable
		 */
		public int check(String s) {
			int val = 0;
			if(s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4") || s.equals("5") || s.equals("6") || 
					s.equals("7") || s.equals("8") || s.equals("9")) {
				val = Integer.parseInt(s);
				return val;
			}
			else {
				return -1;
			}
		}
		
		/**
		 * Checks if the user input a valid number for the program. If the user input is less than
		 * or equal 0, it will keep calling the do-while loop. If the user enter a value that can
		 * cause an error, the try catch would catch the error and would run the do while loop again.
		 * the do-while keeps looping until the user enter a valid number for the program.
		 * @return the time that the user assigned.
		 */
		public int check2() {
			int val = 0;
			do {
			try {
				String num = kb.next();
				val = Integer.parseInt(num);
				
			}
			catch(NumberFormatException e) {
				System.out.println("That's not a valid input");
			}
			
			if(val <= 0) {
				System.out.print("Enter a number greater than 0: ");
			}
			}
			while(val <= 0);
			return val;
		}
		
		
	
		
		/**
		 * This method calls the start method to start the program
		 * @param args
		 */
		public static void main(String[] args) {
			Simulation2 s1 = new Simulation2();
			s1.start();
		}
		
}
