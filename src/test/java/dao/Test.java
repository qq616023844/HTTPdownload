package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

public class Test {
	public void addfile() {
		SAXReader saxReader=new SAXReader();
		try {
			/*URL url = this.getClass().getClassLoader().getResource("D:/test/xx3.xml");
			
			String xmlfile = url.getFile();*/
			
			File xmlfile = new File("D:/test/xx3.xml");
			
			
			
			
			Document doc=saxReader.read(xmlfile);
			Element files = doc.getRootElement();
			
			Element file = files.addElement("file");
			file.addElement("filename").setText("1");
			file.addElement("filetype").setText("1");
			file.addElement("sourceurl").setText("1");
			file.addElement("state").setText("2");
			file.addElement("fileurl").setText("1");
			file.addElement("filesize").setText("1");
			file.addElement("blocksize").setText("1");
			file.addElement("uncode").setText("1");
			
			XMLWriter writer = new XMLWriter(new FileOutputStream(xmlfile));
			writer.write(doc);
			writer.close();
			   
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
	public void addnode() {
		SAXReader saxReader=new SAXReader();
		  try {
		   URL url = this.getClass().getClassLoader().getResource("dd.xml");
		   Document doc=saxReader.read(url.getFile());
		   
		   Element root=doc.getRootElement();
		   System.out.println(root.getName());
		  // System.out.println(root.element("student").element("age").getText());
		   Element resource=root.addElement("student");
		   Element age=resource.addElement("age");
		   Element name=resource.addElement("name");
		   age.setText("05");
		   name.setText("王震");
		   OutputFormat opf=new OutputFormat("\t",true,"UTF-8");
		   opf.setTrimText(true);
		   XMLWriter writer=new XMLWriter(new FileOutputStream(url.getFile()),opf);
		   writer.write(doc);
		   writer.close();
		   List<Element> l = doc.getRootElement().elements();

		   //System.out.println(root.getName());
		  } catch (DocumentException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
	}
	public void etest() {
		SAXReader saxReader=new SAXReader();
		  try {
		   URL url = this.getClass().getClassLoader().getResource("dd.xml");
		   Document doc=saxReader.read(url.getFile());
		   
		   Element root=doc.getRootElement();
		   //System.out.println(root.getName());
		  // System.out.println(root.element("student").element("age").getText());
		   Element resource=root.addElement("student");
		   Element age=resource.addElement("age");
		   Element name=resource.addElement("name");
		   age.setText("05");
		   name.setText("王震");
		   OutputFormat opf=new OutputFormat("\t",true,"UTF-8");
		   opf.setTrimText(true);
		   XMLWriter writer=new XMLWriter(new FileOutputStream(url.getFile()),opf);
		   writer.write(doc);
		   writer.close();
		   List<Element> l = doc.getRootElement().elements();

		   //System.out.println(root.getName());
		  } catch (DocumentException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
	}
	public static void main(String[] args) {
		new Test().addfile();
		System.out.println("1111111");
	}
}
