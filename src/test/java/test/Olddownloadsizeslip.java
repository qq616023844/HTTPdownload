package test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import download.Downthread;

public class Olddownloadsizeslip {

	private void downloaddis() {
		for(int i=0;i<threadsize;i++) {
			if (i==(threadsize-1)) {
				start = s+1;
				end = size;		
			}else {
				start = s+1;
				end = blocksize*(i+1);
				s=blocksize*(i+1);
			}
			initialThread(new Downthread(url, start, end, filepath));
		}
	}
	public void olddownloaddis() {
		long blocksize = size/threadsize;
		
		for (int i = 0; i < l.length; i++) {
			
		}
	}
	
	public List<Long> getneeds(long[] breakpoints,long size) {
		List<Long> pointneeds = new LinkedList<Long>();
		for (int i = 0; i < breakpoints.length+2; i++) {
			pointneeds.add(0l);
		}
		

		for (int i = 0; i < breakpoints.length; i=i+2) {
			pointneeds.set(i+1,breakpoints[i]-1);
			if (i>0) {
				pointneeds.set(i,breakpoints[i-1]+1);
			}
			
		}
		pointneeds.set(0, 0l);
		pointneeds.set(pointneeds.size()-2, breakpoints[breakpoints.length-1]+1);
		pointneeds.set(pointneeds.size()-1,size-1);
		
		getneedsassi(pointneeds);
		
		return pointneeds;
	}
	
	public void getneedsassi(List<Long> pointneeds) {
		for (int i = 0; i < pointneeds.size(); i=i+2) {
			if (pointneeds.get(i)>pointneeds.get(i+1)) {
				pointneeds.remove(i);
				pointneeds.remove(i);
			}
		}
	}
	public static void main(String[] args) {
		Olddownloadsizeslip o = new Olddownloadsizeslip();
		long[] l = {1l,5l,7l,9l,20l,99l};
		long size = 100;
		List<Long> list = o.getneeds(l, size);
		System.out.println(list.toString());
	}

}
