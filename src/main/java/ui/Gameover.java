package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import download.DownloadStarter;
import download.FileResource;

public class Gameover extends Thread{

	FileResource fr = null;
	boolean state = true;
	DownloadStarter downloadStarter = null;
	
	public Gameover(FileResource fr,DownloadStarter downloadStarter) {
		this.fr = fr;
		this.downloadStarter = downloadStarter;
	}
	@Override
	public void run() {
		JFrame jFrame = new JFrame();
		jFrame.setLayout(null);
		JTextArea jtArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(jtArea); 
		JScrollBar jsb = scroll.getVerticalScrollBar();
		JButton jb = new JButton("继续");
		JButton jb2 = new JButton("暂停");

		//分别设置水平和垂直滚动条自动出现 
		scroll.setHorizontalScrollBarPolicy( 
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll.setVerticalScrollBarPolicy( 
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 


		
		jFrame.setSize(400, 400);
		jtArea.setBounds(0, 0, 400, 250);
		scroll.setBounds(0, 0, 380, 250);
		jb.setBounds(50, 280, 100, 50);
		jb2.setBounds(200, 280, 100, 50);
		
		jb.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				state=true;
				downloadStarter.download(fr.getUid());
			}
		});
		
		jb2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				state = false;
				downloadStarter.stop(fr.getUid());
				
			}
		});
		
		jFrame.getContentPane().add(scroll);
		jFrame.add(jb);
		jFrame.add(jb2);
		
		jFrame.setVisible(true);
		
		
		
		long[] l = null;
		// 设置精确到小数点后2位  
		NumberFormat numberFormat = NumberFormat.getInstance();   
        numberFormat.setMaximumFractionDigits(4);  
        
        String uid = fr.getUid();
        System.out.println(uid);
        while(true) {
        	while (state==true) {
        		
    			l = downloadStarter.downloadcon.getprogress(uid);
    			
    			jtArea.append(
    					l[0]+"/"+l[1]+"\t\t"
    							+numberFormat.format((double)l[0]/(double)l[1]*100)+"%"+"\r\n"
    							);
    			//scroll.sets
    			jsb.setValue(jsb.getMaximum());
    			try {
    				Thread.sleep(50);
    			} catch (InterruptedException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
    		}
        	try {
				Thread.sleep(300);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
		
		
	}
}
