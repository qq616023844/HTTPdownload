package download;

import java.net.URL;
//文件信息类
public class FileResource {



	String uid = null;
	boolean legal = false;
	boolean newdown = true;
	String filename = null;
	String filetype = null;
	String fileclasspath = null;
	URL url = null;
	long size = -1;
	int threadsize = -1;
	int priority = 5;
	long[] breakpoint = null;
	
	public FileResource(String filename, String filetype, String fileclasspath, URL url,
			int threadsize, int priority) {
		super();
		this.filename = filename;
		this.filetype = filetype;
		this.fileclasspath = fileclasspath;
		this.url = url;
		this.threadsize = threadsize;
		this.priority = priority;
	}

	public FileResource(String uid, boolean newdown, String filename, String filetype,
			String fileclasspath, URL url, long size, int threadsize, int priority, long[] breakpoint) {
		super();
		this.uid = uid;
		this.newdown = newdown;
		this.filename = filename;
		this.filetype = filetype;
		this.fileclasspath = fileclasspath;
		this.url = url;
		this.size = size;
		this.threadsize = threadsize;
		this.priority = priority;
		this.breakpoint = breakpoint;
	}
	
	

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getFileclasspath() {
		return fileclasspath;
	}

	public void setFileclasspath(String fileclasspath) {
		this.fileclasspath = fileclasspath;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public int getThreadsize() {
		return threadsize;
	}

	public void setThreadsize(int threadsize) {
		this.threadsize = threadsize;
	}

	public boolean isLegal() {
		return legal;
	}

	public void setLegal(boolean legal) {
		this.legal = legal;
	}
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isNewdown() {
		return newdown;
	}

	public void setNewdown(boolean newdown) {
		this.newdown = newdown;
	}

	public long[] getBreakpoint() {
		return breakpoint;
	}

	public void setBreakpoint(long[] breakpoint) {
		this.breakpoint = breakpoint;
	}

	public String getFilepath() {
		return fileclasspath+filename+"."+filetype;
	}
	
}
