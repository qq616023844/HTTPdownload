package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;


public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filepath = "D:/视频/20180315_200252.mp4";
		//47634887
		long start = 0;
		long end = 40634887;
		
		long start2 = 40634888;
		long end2 = 47634886;
		
		RandomAccessFile raf = null;
		FileChannel fc = null;
		MappedByteBuffer mbbf = null;
		MappedByteBuffer mbbf2 = null;
		try {
			raf = new RandomAccessFile(filepath, "rw");
			fc = raf.getChannel();
			mbbf = fc.map(MapMode.READ_WRITE, start,end);
			mbbf2 = fc.map(MapMode.READ_WRITE, start2,end2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
