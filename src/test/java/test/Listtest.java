package test;

import java.util.LinkedList;
import java.util.List;

public class Listtest {

	public static void main(String[] args) {
		List<Long> list = new LinkedList<Long>();
		
		list.add(1l); //1-5,6-11,11-17,18-20,22-23
		list.add(5l);//1-20,22-23
		list.add(6l);
		list.add(11l);
		list.add(11l);
		list.add(17l);
		list.add(18l);
		list.add(20l);
		list.add(22l);
		list.add(23l);
		
		int j = 0;
		int j2 = 0;
		while(j2<8) {
			if (list.get(j)==(list.get(j+1)-1)||list.get(j)==(list.get(j+1))) {
				list.remove(j);
				j2++;
				list.remove(j);
				j2++;
			}else {
				j2++;
				j++;
			}
		}
		/*for (int i = 0; i < list.size();) {
			if (list.get(i)==(list.get(i+1)-1)) {
				list.remove(i);
				list.remove(i);
				
			}else {
				i++;
			}
			
		}*/
		System.out.println(list.toString());

	}

}
