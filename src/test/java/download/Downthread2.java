package download;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.Date;

//单个线程
public class Downthread2 implements Runnable{
	FiledownProxy filedownProxy = null;
	
	
	public Downthread2(FiledownProxy filedownProxy) {
		this.filedownProxy = filedownProxy;
	}
	public void run() {
		URL url = filedownProxy.getUrl();
		
		RandomAccessFile raf = null;
		BufferedInputStream bfis = null;
		try {
			URLConnection conn = url.openConnection();
			//设置请求头
			conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");//目前没有理解这个作用
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
            conn.setRequestProperty("Range", "bytes="+filedownProxy.getStart()+"-"+filedownProxy.getEnd());
            conn.setConnectTimeout(5000);  
            conn.setReadTimeout(5000);
            //建立连接
            conn.connect();
			//载入文件,选择"读写"方式,并开启内存文件映射来加速读写
            raf = new RandomAccessFile(filedownProxy.getFilepath(), "rw");
            System.out.println("start="+filedownProxy.getStart());
            raf.seek(filedownProxy.getStart());
            //获取流
			bfis = new BufferedInputStream(conn.getInputStream());
			//写入文件
			byte[] buf = new byte[1024];
			int size = 0;
			while((size=bfis.read(buf))!=-1) {
				raf.write(buf, 0, size);
			}
			//关闭流
			//fc.close();
			//raf.close();
			//bfis.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}finally {
			
		}
	}

	
}
