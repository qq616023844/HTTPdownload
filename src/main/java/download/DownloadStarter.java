package download;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.spec.DSAGenParameterSpec;
import java.text.NumberFormat;
import java.util.Map;

import dao.Filedao;

public class DownloadStarter {
	private SourceCheck sourceCheck;
	public DownloadController downloadcon = null; 
	private Filedao filedao= null;
	
	//initial
	public DownloadStarter() {
		sourceCheck = new SourceCheck();
		downloadcon = new DownloadController();
		filedao = Filedao.getInstance();
	}
	
	//下载新文件
	public FileResource download(FileResource fileresource) {
		sourceCheck.check(fileresource);
		boolean b = downloadassist(fileresource);
		if (b==true) {
			return fileresource;
		}else {
			return null;
		}
	}
	//续传文件
	public boolean download(String uid) {
		FileResource fileresource = filedao.getFile(uid);
		sourceCheck.check(fileresource);
		return downloadassist(fileresource);
	}
	//暂停下载
	public void stop(String uid) {
		downloadcon.stop(uid);
	}
	//下载/续传辅助方法
	private boolean downloadassist(FileResource fileresource) {
		if (fileresource.isLegal() != false) {
			downloadcon.download(fileresource);
			return true;
		}else {
			return false;
		}
	}
	/*public static void main(String[] args) {
		try {		
			DownloadStarter downloadStarter = new DownloadStarter();
			FileResource fileResource = new FileResource(
					"11",
					"mkv",
					"D:/test/",
					new URL("http://localhost:8080/123/11.mkv"),
					3,
					5);
			
			boolean b = downloadStarter.download(fileResource);
			System.out.println(b);
			Thread.sleep(2000);
			
			String uid = fileResource.getUid();
	
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
/*	public static void main(String[] args) {
		DownloadStarter downloadStarter = new DownloadStarter();
		downloadStarter.download("e869b461-b5a3-4781-a0d8-8eb97e998668");
		
		try {		
			DownloadStarter downloadStarter = new DownloadStarter();
			FileResource fileResource = new FileResource(
					"20180315_200252",
					"mp4",
					"D:/test/",
					new URL("http://localhost:8080/123/20180315_200252.mp4"),
					3,
					5);
			
			boolean b = downloadStarter.download(fileResource);
			
			String uid = fileResource.getUid();
			long[] l = null;
			
			// 设置精确到小数点后2位  
			NumberFormat numberFormat = NumberFormat.getInstance();   
	        numberFormat.setMaximumFractionDigits(4);  		
			
			for (int i = 0; i < 50; i++) {
				l = downloadStarter.downloadcon.getprogress(uid);
				System.out.println(
						l[0]+"/"+l[1]+"\t\t"
				+numberFormat.format((double)l[0]/(double)l[1]*100)+"%"
				);
				Thread.sleep(10);
			}
			
						
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	
	/*public static void main(String[] args) {
		try {
			FileResource fileResource = new FileResource("20180315_200252", "mp4", "D:/", new URL("http://localhost:8080/123/20180315_200252.mp4"), 3, 5);
			boolean b = new DownloadStarter().newdownload(fileResource);
			System.out.println(b);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	//
/*	public static void main(String[] args) {
		String uurl = "http://desk.fd.zol-img.com.cn/t_s1680x1050c5/g5/M00/0A/07/ChMkJlrNf5KIIscqAAPYeY1soAcAAnkYwN-kDkAA9iR844.jpg?downfile=1526372101500.jpg";
		try {
			FileResource fileResource = new FileResource("111", "jpg", "D:/", new URL(uurl), 3, 5);
			boolean b = new DownloadStarter().newdownload(fileResource);
			System.out.println(b);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
