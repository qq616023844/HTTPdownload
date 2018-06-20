package test;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.util.Date;

import download.Downthread;
import download.Downthread2;
import download.Filedown;
import download.FiledownProxy;

public class DownthreadTest2 {

	public static void main(String[] args) throws MalformedURLException {
		Date date = new Date();
		System.out.println(date.getTime());
		
		String filename = "11";
		String type = "11";
		URL sourceurl = new URL("http://localhost:8080/123/11.mkv");
		String fileurl = "D:/";
		long filesize =  1812379587;
		FiledownProxy filedownProxy = new FiledownProxy(new Filedown(filename, type, sourceurl, fileurl, filesize));
		filedownProxy.setStart(0);
		filedownProxy.setEnd(1012379587);
		
		FiledownProxy filedownProxy2 = new FiledownProxy(new Filedown(filename, type, sourceurl, fileurl, filesize));
		filedownProxy2.setStart(1012379588);
		filedownProxy2.setEnd(1812379586);
		
		
		new Downthread(filedownProxy).run();
		new Downthread(filedownProxy2).run();
		System.out.println(new Date().getTime());
		

	
	}
}
