package download;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

public class SourceCheck {
	
	
	
	
	void check(FileResource fileresource) {
		//判断源地址是否有效
		long i = checkSource(fileresource.getUrl(),0);
		if (i>-1) {
			fileresource.setLegal(true);
			fileresource.setSize(i);
		}else {
			fileresource.setLegal(false);
			return;
		}
		
		//判断新文件是否重复,旧文件是否存在
		File file = new File(fileresource.getFilepath());
		if (fileresource.isNewdown()==true) {
			if(file.exists()) {
				//如果文件重复,则加后缀
				fileresource.setFilename(fileresource.getFilename()+UUID.randomUUID().toString());
			}
			//创建文件
			file = new File(fileresource.getFilepath());
			try {
				fileresource.setLegal(file.createNewFile());
			} catch (IOException e) {
				e.printStackTrace();
				fileresource.setLegal(false);
				return;
			}
			fileresource.setUid(UUID.randomUUID().toString());
		}else {
			if (file.exists()) {
				return;
			}else {
				//创建文件
				file = new File(fileresource.getFilepath());
				try {
					fileresource.setLegal(file.createNewFile());
				} catch (IOException e) {
					e.printStackTrace();
					fileresource.setLegal(false);
					return;
				}
				
				fileresource.setNewdown(true);
				return;
			}
		}

		
	}
	
	private long checkSource(URL url,int i) {
		HttpURLConnection conn;
		try {
			conn = (HttpURLConnection) url.openConnection();
			//设置请求头
			conn.setRequestProperty("accept", "*/*");  
	        conn.setRequestProperty("connection", "Keep-Alive");//目前没有理解这个作用
	        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
	        conn.setConnectTimeout(5000);  
	        conn.setReadTimeout(5000);
	        //建立连接
	        conn.connect();
	        //获取响应头
	        if (conn.getResponseCode()==200) {	        	
	        	return conn.getContentLength();
	        }else if(conn.getResponseCode()==302&i<5) {
	        	i++;
				checkSource(new URL(conn.getHeaderField("Location")),i);
			}else {
				return -1;
			}
	        return -1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
        
	}
}
