package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import util.MappedByteBufferRelease;

public class Test {

	public void tess(long start,long end) {
		String srcpath = "D:/";
		String destpath = "E:/";
		String filename = "111.txt";
		// TODO Auto-generated method stub
		 File source = new File(srcpath+"/"+filename);
	     File dest = new File(destpath+"/"+filename);
	     FileChannel in = null, out = null;
	     try { 
	      in = new FileInputStream(source).getChannel();
	      out = new FileOutputStream(dest).getChannel();
	      long size = in.size();
	      MappedByteBuffer buf = in.map(FileChannel.MapMode.READ_ONLY, start, end-start+1);
	      out.write(buf);
	      in.close();
	      out.close();
	 
	     }catch(Exception e){
	      e.printStackTrace();
	     } finally {
	      try {
			in.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     }
	}
	public static void main(String[] args) {
		long l = 1525932364734l;
		long l2 = 1525932345857l;
		Time t = new Time(l-l2);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		System.out.println(simpleDateFormat.format(t));
		
	}

}
