package Algorithm;

import java.util.Comparator;


/**
 * This class aims to implement Construction in order to create a constractor according to our usefulness
 * 

 *
 */
public class distance_Comperator implements Comparator<Double> {
	

/**
 * comparator who knows how to compare two distance

 * @param Double o1 First Parameter 
 * @param Double o2 Second Parameter
 * @return 1 if o1>o2 ; -1 if o1<o2 ; else 0
 */
	@Override
	public int compare(Double o1, Double o2) {
		// TODO Auto-generated method stub
		if(o1 > o2) {
			return 1;
		}
		if (o1 < o2) {
			return -1;
		}
		
		return 0;
	}

}

	
	
	
	

