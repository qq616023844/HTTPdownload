package test;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.util.Date;

import download.Downthread;
import download.Downthread2;
import download.Filedown;
import download.FiledownProxy;

public class DownthreadTest {

	public static void main(String[] args) throws MalformedURLException {
		Date date = new Date();
		System.out.println(date.getTime());
		
		String filename = "20180315_200252";
		String type = "mp4";
		URL sourceurl = new URL("http://localhost:8080/123/20180315_200252.mp4");
		String fileurl = "D:/";
		long filesize = 47634887;
		FiledownProxy filedownProxy = new FiledownProxy(new Filedown(filename, type, sourceurl, fileurl, filesize));
		filedownProxy.setStart(0);
		filedownProxy.setEnd(27634887);
		
		FiledownProxy filedownProxy2 = new FiledownProxy(new Filedown(filename, type, sourceurl, fileurl, filesize));
		filedownProxy2.setStart(27634888);
		filedownProxy2.setEnd(47634886);
		
		
		new Downthread(filedownProxy).run();
		new Downthread(filedownProxy2).run();
		System.out.println(new Date().getTime());
		

	
	}
}
