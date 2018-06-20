package download;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Filedao;

//负责管理全部的下载
public class DownloadController {
	private Map<String, FileResource> filemap = null;//维护文件资源
	private Map<String, ThreadController> threadmap = null;//一个文件对应一个线程管理器
	private Filedao filedao = null;
	//测试方法
	public Map<String, ThreadController> fff(){
		return threadmap;
	}
	//initial
	public DownloadController() {
		filemap = new HashMap<String, FileResource>();
		threadmap = new HashMap<String, ThreadController>();
		filedao = Filedao.getInstance();
	}
	//启动下载
	public void download(FileResource fileResource) {
		if (fileResource.isNewdown()==true) {
			filedao.saveFilemes(fileResource);	
		}
		String uid = fileResource.getUid();
		register(uid,fileResource);
		threadmap.get(uid).start();
	}
	//注册文件到Map维护表中
	private void register(String uid,FileResource fileResource) {
		filemap.put(uid, fileResource);
		threadmap.put(uid, new ThreadController(fileResource,this));
	}
	//取消注册
	public void cancelregister(String uid) {
		threadmap.remove(uid);
		filemap.remove(uid);
	}
	//停止下载
	public void stop(String uid) {
		List<Long> list = threadmap.get(uid).stop();
		cancelregister(uid);
		filedao.saveBreakpoint(uid,list);
	}
	//获取下载进度
	public long[] getprogress(String uid) {
		return threadmap.get(uid).getHasdownlodSizeOfAllthread();
		
	}

}
