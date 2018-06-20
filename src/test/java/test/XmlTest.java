package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f = new File("D:/xx.xml");
		
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(f);
			Element root = document.getRootElement();
			
			root.elementByID("4").addElement("12333");
			
			
			new XMLWriter(new FileOutputStream(f)).write(document);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
