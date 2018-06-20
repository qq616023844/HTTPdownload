package test;

import javax.swing.JFileChooser;

public class UiTest {

	public static void main(String[] args) {
		JFileChooser fileChooser = new JFileChooser("D:\\");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = fileChooser.showOpenDialog(fileChooser);
		if(returnVal == JFileChooser.APPROVE_OPTION){       
			String filePath= fileChooser.getSelectedFile().getAbsolutePath();//这个就是你选择的文件夹的路径 
			System.out.println(filePath);
		}
		
	}
}
