package test;

import java.text.NumberFormat;

public class Percenttest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		NumberFormat numberFormat = NumberFormat.getInstance();   
        numberFormat.setMaximumFractionDigits(4);
        
        long a = 1234;
        long b = 2345;
        
        String s = numberFormat.format((double)a/(double)b);
        
		System.out.println(s);
	}

}
