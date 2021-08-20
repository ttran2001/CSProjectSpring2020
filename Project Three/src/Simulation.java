import java.util.Hashtable;
import java.util.ArrayList;
/**
 * 
 * @author tt553
 * 
 */
public class Simulation {
	public static void main(String[] args) {
		
		/*
		 * Stores the number of successful searches and unsuccess searches
		 * for all four cases.
		 */
		
		
		/**
		 * Array that stores the total amount of probes of successful searches and unsuccessful searches
		 * for all four hash tables.
		 */
		int[] lp1total = new int[2];
		int[] lp2total = new int[2];
		int[] qp1total = new int[2];
		int[] qp2total = new int[2];
		
		/**
		 * keeps count the number of successful searches and
		 * unsuccessful searches
		 */
		
		int lp1success = 0;
		int lp1unsuccess = 0;
		
		int lp2success = 0;
		int lp2unsuccess = 0;
		
		int qp1success = 0;
		int qp1unsuccess = 0;
		
		int qp2success = 0;
		int qp2unsuccess = 0;

		/**
		 * Stores the random numbers into these hashtables
		 */
		Hashtable<Integer, Integer> lp1 = new Hashtable<Integer, Integer>(1019);
		Hashtable<Integer, Integer> lp2 = new Hashtable<Integer, Integer>(1019);
		Hashtable<Integer, Integer> qp1 = new Hashtable<Integer, Integer>(1019);
		Hashtable<Integer, Integer> qp2 = new Hashtable<Integer, Integer>(1019);

		int key = 0;	
		int val = 0;
		
		//start of linear probing
		
		//60% Hash Table
		while(lp1.size() != 611) {
			val = (int)(Math.random()*10000+1);
			while(lp1.containsValue(val) == true) {
				val = (int)(Math.random()*10000+1);
			}
			key = (int)val%1019;
			while(lp1.containsKey(key) == true) {
				key++;
				if(key > 1018) {
					key = 0;
				}
			}
			lp1.put(key, val);
		}
		
		//80% Hash Table
		while(lp2.size() != 815) {
			val = (int)(Math.random()*10000+1);
			while(lp2.containsValue(val) == true) {
				val = (int)(Math.random()*10000+1);
			}
			key = (int)val%1019;
			while(lp2.containsKey(key) == true) {
				key++;
				if(key > 1018) {
					key = 0;
				}
			}
			lp2.put(key, val);
		}
		
		//end of linear probing
		
		//start of quadratic probing
		
		int hash = 0;
		int count = 0;
		
		//60% Hash Table
		while(qp1.size() != 611) {
			count = 0;
			val = (int)(Math.random()*10000+1);
			while(qp1.containsValue(val) == true) {
				val = (int)(Math.random()*10000+1);
			}
			key = (int)val%1019;
			hash = val;
			while(qp1.containsKey(key) == true) {
				count++;
				key = (hash+(count*count))%1019;
				if(key > 1018) {
					key-=1018;
				}
				if(qp1.containsKey(key) == true) {
				    key = (hash-(count*count))%1019;
					}
				if(key > 1018) {
					key-=1018;
				}
			}
			qp1.put(key, val);
		}
		
		//80% Hash Table
		while(qp2.size() != 815) {
			count = 0;
			val = (int)(Math.random()*10000+1);
			while(qp2.containsValue(val) == true) {
				val = (int)(Math.random()*10000+1);
			}
			key = (int)val%1019;
			hash = val;
			while(qp2.containsKey(key) == true) {
				count++;
				key = (hash+(count*count))%1019;
				if(key > 1018) {
					key-=1018;
				}
				if(qp2.containsKey(key) == true) {
				    key = (hash-(count*count))%1019;
					}
				if(key > 1018) {
					key-=1018;
				}
			}
			qp2.put(key, val);
		}
		
		//end of quadratic probing
			
			//This for loops
			int store;
			for(int j = 1; j < 10001; j++) {
				store =search(lp1, j);
				if(store > 0) {
					lp1total[0]+=store;
					lp1success++;
				}
				else {
					lp1total[1]-=store;
					lp1unsuccess++;
				}
				
				store =search(lp2, j);
				if(store > 0) {
					lp2total[0]+=store;
					lp2success++;
				}
				else {
					lp2total[1]-=store;
					lp2unsuccess++;
				}
				
				store =search2(qp1, j);
				if(store > 0) {
					qp1total[0]+=store;
					qp1success++;
				}
				else {
					qp1total[1]-=store;
					qp1unsuccess++;
				}
				
				store =search2(qp2, j);
				if(store > 0) {
					qp2total[0]+=store;
					qp2success++;
				}
				else {
					qp2total[1]-=store;
					qp2unsuccess++;
				}
			}
			
			
		/**
		 * This is the output result of the successful and unsuccessful searches
		 * for both linear and quadratic probing when the hashtable is 60% and
		 * 80%. It also give the average number of probes for a success and unsuccess
		 * for each cases. 
		 */
		System.out.println("Linear Probing: ");
		System.out.println("o Hash Table 60%: ");
		System.out.println("Success: " + lp1success  + " | Unsuccess: " + lp1unsuccess);
		System.out.println("Average Number of Probes For Success: "+ ((double)lp1total[0]/lp1success));
	    System.out.println("Average Number of Probes For Unsuccess: " + ((double)lp1total[1]/lp1unsuccess));
		System.out.println("o Hash Table 80%: ");
		System.out.println("Success: " + lp2success  + " | Unsuccess: " + lp2unsuccess);
		System.out.println("Average Number of Probes For Success: "+ ((double)lp2total[0]/lp2success));
		System.out.println("Average Number of Probes For Unsuccess: "+ ((double)lp2total[1]/lp2unsuccess));
		System.out.println();
		System.out.println("Quadratic Probing: ");
		System.out.println("o Hash Table 60%: ");
		System.out.println("Success: " + qp1success  + " | Unsuccess: " + qp1unsuccess);
		System.out.println("Average Number of Probes For Success: " + ((double)qp1total[0]/qp1success));
		System.out.println("Average Number of Probes For Unsuccess: " + ((double)qp1total[1])/qp1unsuccess);
		System.out.println("o Hash Table 80%: ");
		System.out.println("Success: " + qp2success  + " | Unsuccess: " + qp2unsuccess);
		System.out.println("Average Number of Probes For Success: "+ ((double)qp2total[0]/qp2success));
		System.out.println("Average Number of Probes For Unsuccess:"+ ((double)qp2total[1]/qp2unsuccess));
	
	}
	
	
	
	/**
	 * 
	 * @param table
	 * @param num
	 * @return the number it took for a successful search or the number it took
	 * 		   for it to realize that the number is not in the hash table. Returns a negative number if its not. This method is only use for hashtable that been
	 * 		   inserted by linear probing.
	 * 		
	 */
	public static int search(Hashtable<Integer, Integer> table, int num) {
		int value = (int)num%1019;
		int counter = 0;
		boolean status = false;
		while(status == false) {
		if(table.containsKey(value) == true) {
		if(table.get(value) != num) {
			counter++;
			value++;
		}
		else {
			status = true;
			counter++;
		}
		
		if(value > 1018) {
			value = 0;
		}
		}
		
		else{
			counter++;
			counter = 0 - counter;
			status = true;
		}
		
		}
		return counter;
	}
	
	/**
	 * 
	 * @param table
	 * @param num
	 * @return same thing as the first search but this is for the hashtable that were quadratic probing
	 */
	public static int search2(Hashtable<Integer, Integer> table, int num) {
		int value = (int)num%1019;
		int counter = 0;
		boolean status = false;
		int count = 0;
		int hash = num;
		
		while(status == false) { //start of while loop
			if(table.containsKey(value) == true) {
				if(table.get(value) == num) {
					counter++;
					status = true;
				}
				else { // start of first else statement
					count++;
					counter++;
					value = (int)((hash+(count*count))%1019);
					if(value > 1018) {
						value = value-1018;
					} 
					if(table.containsKey(value) == false) {
						counter++;
						counter = 0 - counter;
						status = true;
					}
					else if(table.get(value) == num){
						counter++;
						status = true;
					}
					else{
						value = (int)((hash-(count*count))%1019);
						if(value > 1018) {
							value = value-1018;
						}
					}
				}
			}
			else {
				counter++;
				counter = 0 - counter;
				status = true;
			}
		} // end of while loop
		return counter;
	}
}
