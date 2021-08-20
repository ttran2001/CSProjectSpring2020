import java.util.ArrayList;


//Start of Algorithm class 
public class Algorithm {
	
	/**
	 * This is the induction algorithm. How this algorithm works is that it runs a for loop 
	 * @param buildings
	 * @return skyline that was created by using induction algorithm
	 */
	public ArrayList<Integer> induction(ArrayList<Integer[]> buildings){
		ArrayList<Integer> skyline = new ArrayList<Integer>(); 
		ArrayList<Integer> building = new ArrayList<Integer>();
		skyline = spike(buildings.get(0));
		for(int i = 1; i < buildings.size(); i++){
		building = spike(buildings.get(i));
		skyline = combineSpike(skyline, building);
		}
		skyline = reverse(skyline);
		return skyline;
		}
	
	/**
	 * This is the divide and conquer algorithm, what did algorithm do is that it halves the buildings ArrayList that stores Array of the
	 * values for each of the building until there's only two buildings left or less in the buildings ArrayList.
	 * @return the skyline by divide and conquer algorithm 
	 *
	 **/
	public ArrayList<Integer> divide(ArrayList<Integer[]> buildings){
		ArrayList<Integer> skyline = new ArrayList<Integer>();
		ArrayList<Integer> firstSky;
		ArrayList<Integer> secondSky;
		ArrayList<Integer[]> firstHalf = new ArrayList<Integer[]>();
		ArrayList<Integer[]> secondHalf = new ArrayList<Integer[]>();
		if(buildings.size() == 2) {
			firstSky = spike(buildings.get(0));
			secondSky = spike(buildings.get(1));
			skyline = combineSpike(firstSky, secondSky);
		}
		else if(buildings.size() == 1) {
			skyline = spike(buildings.get(0));
		}
		else {
			for(int i = 0; i < (int)(buildings.size()/2); i++) {
				firstHalf.add(buildings.get(i));
			}
			for(int j = (int)buildings.size()/2; j < buildings.size(); j++) {
				secondHalf.add(buildings.get(j));
			}
			firstSky = divide(firstHalf);
			secondSky = divide(secondHalf);
			skyline = combineSpike(firstSky, secondSky);
		}
		return skyline;
	}
	
	/**
	 * 
	 * @param build
	 * @return the final skyline. Only used for divide() method
	 */
	public ArrayList<Integer> reverseSkyline(ArrayList<Integer> build){
		return reverse(build);
	}
	
	/**
	 * 
	 * @param building
	 * @return the spiked version of the building
	 */
	public  static ArrayList<Integer> spike(Integer[] building){
		ArrayList<Integer> spikeNum = new ArrayList<Integer>();
		int total = (building[2] - building[0])+1;
		if(building[0] > 1) {
			for(int count = 1; count < building[0]; count++) {
				spikeNum.add(0);
			}
		}
		
		for(int i = 0; i < total-1; i++) {
			spikeNum.add(building[1]);
		}
		spikeNum.add(0);	
		return spikeNum;
	}
	
	/**
	 * 
	 * @param b1
	 * @param b2
	 * @return the combine spiked of the two buildings
	 */
	public  static ArrayList<Integer> combineSpike(ArrayList<Integer> b1, ArrayList<Integer> b2){
		int count = 0;
		ArrayList<Integer> combine = new ArrayList<Integer>();
		if(b1.size() > b2.size()) {
			count = b2.size();
		}
		else if(b1.size() < b2.size()){
			count = b1.size();
		}
		else {
			count = b1.size();
		}
		
		for(int i = 0; i < count; i++) {
			if(b1.get(i) > b2.get(i)) {
				combine.add(b1.get(i));
			}
			else if (b1.get(i) < b2.get(i)){
				combine.add(b2.get(i));
			}
			else {
				combine.add(b1.get(i));
			}
		}
		
		if(b1.size() > b2.size()) {
		while(count < b1.size()) {
			combine.add(b1.get(count));
			count++;
		}
		}
		else if (b1.size() < b2.size()){
			while(count < b2.size()) {
				combine.add(b2.get(count));
				count++;
			}
		}
		else {
			return combine;
		}
		return combine;
	}
	
	/**
	 * 
	 * @param build
	 * @return the skyline version of the spiked buildings
	 */
	public static ArrayList<Integer> reverse(ArrayList<Integer> build){
		int num = build.get(0);
		int count = 0;
		int start = 1;
		ArrayList<Integer> skyline = new ArrayList<Integer>();
		skyline.add(start);
		skyline.add(num);
		for(int b = 1; b < build.size(); b++) {
			if(build.get(b) == num) {
				count++;
			}
			else if(build.get(b) > num) {
				count++;
				skyline.add(start+count);
				start = b;
				skyline.add(build.get(b));
				count = 1;
				num = build.get(b);
			}
			else {
				count++;
				skyline.add(start+count);
				start = b;
				skyline.add(build.get(b));
				count = 1;
				num = build.get(b);
			}
		}
		return skyline;
	}
	
} //end of Algorithm class
