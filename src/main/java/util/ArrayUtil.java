package util;

import java.util.List;

public class ArrayUtil {

	public static void arrayorder(long[] array) {
		for(int i = 0 ; i < array.length ; i ++) {
		    for(int j = i +1 ; j < array.length ; j ++) {
		    	if(array[i] > array[j]) {
		    		long temp = array[i];
		    		array[i] = array[j];
		    		array[j] = temp;
		    	}
		    }
		}
	}
	
	public static void arrayorder(List<Long> array) {
		for(int i = 0 ; i < array.size() ; i ++) {
		    for(int j = i +1 ; j < array.size() ; j ++) {
		    	if(array.get(i) > array.get(j)) {
		    		long temp = array.get(i);
		    		array.set(i, array.get(j));
		    		array.set(j, temp);
		    	}
		    }
		}
	}
}
