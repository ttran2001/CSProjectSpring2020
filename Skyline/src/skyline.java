import java.util.ArrayList;

public class skyline {
	
	//start of client method
	public static void main(String[] args) {
		
		Algorithm a1 = new Algorithm();
		skyDat s1 = new skyDat();
		
		//This arraylist are used to store the skyline buildings by skylined by the induction algorithm or the divide algorithm
		ArrayList<ArrayList<Integer>> induction = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> divide = new ArrayList<ArrayList<Integer>>();
		
		
		//calls the skyline class to get the arraylist of buildings
		ArrayList<Integer[]> skydat1 = s1.skydat1();
		ArrayList<Integer[]> skydat2 = s1.skydat2();
		ArrayList<Integer[]> skydat3 = s1.skydat3();
		
		/**
		 * from line 23 to 28, what did do is that it calls the divide and conquerer algorithm and the induction algorithm and store the
		 * skyline version of the arraylist of bulidings into the induction arraylist and the divide arraylist
		 * 
		 */
		
		Integer[] val = new Integer[]{6, 7, 10};
		Integer[] val2 = new Integer[]{3, 6, 12};
		Integer[] val3 = new Integer[]{1, 9, 4};
		
		ArrayList<Integer> spike = new ArrayList<Integer>();
		
		divide.add(a1.reverseSkyline(a1.divide(skydat1)));
		divide.add(a1.reverseSkyline(a1.divide(skydat2)));
		divide.add(a1.reverseSkyline(a1.divide(skydat3)));
		induction.add(a1.induction(skydat1));
		induction.add(a1.induction(skydat2));
		induction.add(a1.induction(skydat3));
			
		//Output for the induction skyline. Prints three datasets that are turned into skylines.
		System.out.println("Skyline Problem: ");
		System.out.println("oInduction Algorithm ");
		for(int total = 0; total < induction.size(); total++) {
		System.out.print("-Sky" + (total+1) + ": (");
		for(int count = 0; count < induction.get(total).size()-1; count++) {
		System.out.print(induction.get(total).get(count) + ", ");
		}
		System.out.print(induction.get(total).get(induction.get(total).size()-1) + ")");
		System.out.println();
		System.out.println();
		}
		
		//Output for the divide and conqeuer skyline. Prints three datasets that are turned into skylines.
		System.out.println("oDivide & Conqeuer Algorithm: ");
		for(int total2 = 0; total2 <divide.size(); total2++) {
			System.out.print("-Sky" + (total2+1) + ": (");
			for(int count2 = 0; count2 < divide.get(total2).size()-1; count2++) {
				System.out.print(divide.get(total2).get(count2) + ", ");
			}
			System.out.print(divide.get(total2).get(divide.get(total2).size()-1) + ")");
			System.out.println();
			System.out.println();
		}
		
	}
	//end of the client method
}
