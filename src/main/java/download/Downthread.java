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

import util.Io;

//单个线程
public class Downthread extends Thread{
	//思考:如何implement的并且被注入new Thread同时可以调用本体添加的方法
	
	long i = 0;//用于断点续传记录数据
	boolean state = true;//用于判断是否继续线程
	
	
	URL url = null;
	long start = -1;
	long end = -1;
	String filepath = null;
	ThreadController threadController = null;

	public Downthread(URL url, long start, long end, String filepath,ThreadController threadController) {
		this.url = url;
		this.start = start;
		this.end = end;
		this.filepath = filepath;
		this.threadController = threadController;
	}

	public void run() {
		RandomAccessFile raf = null;
		FileChannel fc = null;
		MappedByteBuffer mbbf = null;
		BufferedInputStream bfis = null;
		try {
			URLConnection conn = url.openConnection();
			//设置请求头
			conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");//目前没有理解这个作用
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
            conn.setRequestProperty("Range", "bytes="+start+"-"+end);
            conn.setConnectTimeout(5000);  
            conn.setReadTimeout(5000);
            //建立连接
            conn.connect();
			//载入文件,选择"读写"方式,并开启内存文件映射来加速读写
            raf = new RandomAccessFile(filepath, "rw");
            fc = raf.getChannel();          
            mbbf = fc.map(MapMode.READ_WRITE, start,(end-start)+1);
            //获取流
			bfis = new BufferedInputStream(conn.getInputStream());
			//写入文件
			byte[] buf = new byte[1024];
			int size = 0;
			while(state&(size=bfis.read(buf))!=-1) {
				mbbf.put(buf, 0, size);
				i=i+size;
			}
			//强制数据刷新入磁盘并关闭映射
			mbbf.force();
			Io.unMapBuffer(mbbf, fc.getClass());
			//关闭流
			fc.close();
			raf.close();
			bfis.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				fc.close();
				raf.close();
				bfis.close();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}finally {
			threadController.cancelThread(this);
		}
	}

	public void threadstop() {
		state = false;
	}
	
	public long getHasdownloadSizeOfThread() {
		return i;
	}
}
