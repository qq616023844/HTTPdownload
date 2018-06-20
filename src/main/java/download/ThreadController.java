package download;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import util.ArrayUtil;

//管理本文件的线程
public class ThreadController {
	long beforedownloadsize = -1;
	long size = -1;
	
	FileResource fr = null;
	List<Thread> threadlist = null;//线程列表
	
	DownloadController downloadController = null;
	//initial
	public ThreadController(FileResource fileResource,DownloadController downloadController) {
		this.fr = fileResource;
		this.downloadController = downloadController;
		
		size = fileResource.getSize();
		
		threadlist = new ArrayList<Thread>();
		if (fr.isNewdown()==true) {
			downloaddis();
		}else {
			olddownloaddis();
		}
	}
	//开启线程
	void start() {
		for (Thread thread : threadlist) {
			thread.start();
		}
	}
	//设置线程优先级
	void setPriority(int priority) {
		for (Thread thread : threadlist) {
			thread.setPriority(priority);
		}
	}
	//停止线程
	List<Long> stop() {
		List<Long> list = new LinkedList<Long>();
		for (int i = 0; i < threadlist.size(); i++) {
			Downthread d = ((Downthread)threadlist.get(i));
			d.threadstop();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.add(d.start);
			list.add(d.i+d.start-1);
		}
		return stopassist(list);
	}
	//辅助其停止,并临时维护断点信息
	private List<Long> stopassist(List<Long> list){
		ArrayUtil.arrayorder(list);
		
		int s = list.size()-2;
		int j = 0;
		int j2 = 0;
		while(j2<s) {
			if (list.get(j)==(list.get(j+1)-1)||list.get(j)==(list.get(j+1))) {
				list.remove(j);
				j2++;
				list.remove(j);
				j2++;
			}else {
				j2++;
				j++;
			}
		}
		
		return list;
	}
	//新建下载,分配线程下载段
	private void downloaddis() {
		beforedownloadsize = 0;
		
		int threadsize = fr.getThreadsize();
		long size = fr.getSize();
		URL url = fr.getUrl();
		String filepath = fr.getFilepath();
		
		long blocksize = size/threadsize;
		long s = -1;
		long start = -1;
		long end = -1;
		
		for(int i=0;i<threadsize;i++) {
			if (i==(threadsize-1)) {
				start = s+1;
				end = size;		
			}else {
				start = s+1;
				end = blocksize*(i+1);
				s=blocksize*(i+1);
			}
			initialThread(new Downthread(url, start, end, filepath,this));
		}
	}
	//续传下载,分配线程下载段
	private void olddownloaddis() {
		URL url = fr.getUrl();
		String filepath = fr.getFilepath();
		
		List<Long> list = getneeds(fr.getBreakpoint(), fr.getSize());
		for (int i = 0; i < list.size(); i=i+2) {
			initialThread(new Downthread(url, list.get(i), list.get(i+1), filepath,this));
		}
	}
	//续传前处理断点信息
	private List<Long> getneeds(long[] breakpoints,long size) {
		
		
		List<Long> pointneeds = new LinkedList<Long>();
		for (int i = 0; i < breakpoints.length+2; i++) {
			pointneeds.add(0l);
		}
		for (int i = 0; i < breakpoints.length; i=i+2) {
			beforedownloadsize+=breakpoints[i+1]-breakpoints[i]+1;
			
			pointneeds.set(i+1,breakpoints[i]-1);
			if (i>0) {
				pointneeds.set(i,breakpoints[i-1]+1);
			}
		}
		pointneeds.set(0, 0l);
		pointneeds.set(pointneeds.size()-2, breakpoints[breakpoints.length-1]+1);
		pointneeds.set(pointneeds.size()-1,size-1);
		filterneeds(pointneeds);

		return pointneeds;
	}
	//辅助处理断点信息
	private void filterneeds(List<Long> pointneeds) {
		for (int i = 0; i < pointneeds.size(); i=i+2) {
			if (pointneeds.get(i)>pointneeds.get(i+1)) {
				pointneeds.remove(i);
				pointneeds.remove(i);
			}
		}
	}
	
	//初始化线程
	private void initialThread(Thread thread) {
		thread.setPriority(fr.getPriority());
		threadlist.add(thread);
	}
	//线程已结束,取消注册线程
	public void cancelThread(Thread thread) {
		threadlist.remove(thread);
		beforedownloadsize += ((Downthread)thread).i;
		if (threadlist.size()==0) {
			downloadController.cancelregister(fr.getUid());
		}
	}
	
	//获取线程已下载量总和,下载进度
	public long[] getHasdownlodSizeOfAllthread() {
		long[] l = new long[2];
		l[0] = beforedownloadsize;
		l[1] = size;
		for (Thread thread : threadlist) {
			l[0]+=((Downthread)thread).getHasdownloadSizeOfThread();
		}
		
		return l;
	}
	
}
