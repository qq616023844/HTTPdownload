package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import download.DownloadStarter;
import download.FileResource;

public class MainUi {
	DownloadStarter downloadStarter = null;
	FileResource fr = null;
	
	public MainUi() {
		downloadStarter = new DownloadStarter();
	}

	public void newdownload() {
		JFrame jFrame = new JFrame("http下载器");
		jFrame.setLayout(null);
		final JTextField jtf_url = new JTextField("url:http");
		final JTextField jtf_name = new JTextField("文件名");
		final JTextField jtf_type = new JTextField("文件类型");
		final JTextField jtf_thread = new JTextField("线程数");
		final JTextField jtf_proprity =new JTextField("优先级");
		final JTextField jtf_filepath = new JTextField("文件路径");
		JButton jb = new JButton("选择路径");
		JButton jb_download = new JButton("开始下载");
		
		jFrame.setSize(600, 600);
		jtf_url.setBounds(50, 10, 400, 40);
		jtf_name.setBounds(50, 60, 400, 40);
		jtf_type.setBounds(50, 110, 400, 40);
		jtf_thread.setBounds(50, 160, 400, 40);
		jtf_proprity.setBounds(50, 210, 400, 40);
		jtf_filepath.setBounds(50, 270, 400, 40);
		jb.setBounds(470, 270, 100, 40);
		jb_download.setBounds(200, 340, 100, 60);
		
		jb.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser("D:/");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fileChooser.showOpenDialog(fileChooser);
				if(returnVal == JFileChooser.APPROVE_OPTION){       
					jtf_filepath.setText(fileChooser.getSelectedFile().getAbsolutePath()+"\\");//这个就是你选择的文件夹的路径 
				}
			}
		});
		jb_download.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					fr = downloadStarter.download(new FileResource(
							jtf_name.getText(), 
							jtf_type.getText(), 
							jtf_filepath.getText(), 
							new URL(jtf_url.getText()), 
							Integer.parseInt(jtf_thread.getText()), 
							Integer.parseInt(jtf_proprity.getText())
							));
					if (fr!=null) {
						JOptionPane.showMessageDialog(new JFrame().getContentPane(),
								"下载成功", "提示信息", JOptionPane.INFORMATION_MESSAGE);
						new Gameover(fr,downloadStarter).start();
					}else {
						JOptionPane.showMessageDialog(new JFrame().getContentPane(),
								"下载失败", "提示信息", JOptionPane.INFORMATION_MESSAGE); 
					}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		jFrame.add(jtf_url);
		jFrame.add(jtf_name);
		jFrame.add(jtf_type);
		jFrame.add(jtf_thread);
		jFrame.add(jtf_proprity);
		jFrame.add(jtf_filepath);
		jFrame.add(jb);
		jFrame.add(jb_download);
		
		jFrame.setVisible(true);
	}
	public static void main(String[] args) {
		new MainUi().newdownload();
		
		
		
		
		

	}

}
