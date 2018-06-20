package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UiTest2 {

	public static void main(String[] args) throws InterruptedException {
		JFrame jFrame = new JFrame();
		jFrame.setLayout(null);
		JTextArea jtArea = new JTextArea();
		JScrollPane  scroll = new JScrollPane(jtArea); 
		JScrollBar jsb = scroll.getVerticalScrollBar();
		JButton jb = new JButton("继续");
		JButton jb2 = new JButton("暂停");

		//分别设置水平和垂直滚动条自动出现 
		scroll.setHorizontalScrollBarPolicy( 
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll.setVerticalScrollBarPolicy( 
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		
		//分别设置水平和垂直滚动条总是出现 
	/*	scroll.setHorizontalScrollBarPolicy( 
		JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		scroll.setVerticalScrollBarPolicy( 
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); */

		jFrame.setSize(400, 400);
		jtArea.setBounds(0, 0, 400, 250);
		scroll.setBounds(0, 0, 380, 250);
		jb.setBounds(50, 280, 100, 50);
		jb2.setBounds(200, 280, 100, 50);
		
		jFrame.getContentPane().add(scroll);
		jFrame.add(jb);
		jFrame.add(jb2);
		
		jFrame.setVisible(true);
		
		while(true) {
			jtArea.append("1111111111"+"\r\n");
			jsb.setValue(jsb.getMaximum());
			Thread.sleep(40);
		}
		
		

	}

}
