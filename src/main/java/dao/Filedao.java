package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.jaxb.JAXBModifier;

import download.FileResource;
//单例模式
public class Filedao {
	String filepath = "D:/test/xx.xml";
	File files = null;
	SAXReader saxReader = null;
	XMLWriter xmlWriter = null;
	
	
	private static Filedao filedao = new Filedao();
	private Filedao() {
		files = new File(filepath);
		saxReader = new SAXReader();
	}
	public static Filedao getInstance() {
		return filedao;
	}
	public synchronized void saveFilemes(FileResource fileResource) {
		
		Document document;
		try {
			document = saxReader.read(files);
			Element root = document.getRootElement();
			Element file = root.addElement("file");
			file.addAttribute("ID", fileResource.getUid());
			file.addElement("newdown").setText("true");
			file.addElement("filename").setText(fileResource.getFilename());
			file.addElement("filetype").setText(fileResource.getFiletype());
			file.addElement("fileclasspath").setText(fileResource.getFileclasspath());
			file.addElement("url").setText(""+fileResource.getUrl());
			file.addElement("size").setText(""+fileResource.getSize());
			file.addElement("threadsize").setText(""+fileResource.getThreadsize());
			file.addElement("priority").setText(""+fileResource.getPriority());
			file.addElement("breakpoint").setText("null");;
			
			xmlWriter = new XMLWriter(new FileOutputStream(files));
			xmlWriter.write(document);
			xmlWriter.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void saveBreakpoint(String uid ,List<Long> list) {
		StringBuffer sb = new StringBuffer();
		for (Long l : list) {
			sb.append(l+",");
		}
		
		Document document;
		try {
			document = saxReader.read(files);
			Element root = document.getRootElement();
			Element file = root.elementByID(uid);
			file.element("newdown").setText("false");
			file.element("breakpoint").setText(sb.toString());
			
			xmlWriter = new XMLWriter(new FileOutputStream(files));
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized FileResource getFile(String uid) {
		Document document;
		FileResource fr = null;
		try {
			document = saxReader.read(files);
			Element root = document.getRootElement();
			Element file = root.elementByID(uid);
			//解析string类型的breakpoint为long[]
			Element breakpoint = file.element("breakpoint");
			String[] ls = breakpoint.getText().split(",");
			long[] l = new long[ls.length];
			for (int i = 0; i < ls.length; i++) {
				l[i] = Long.parseLong(ls[i]);
			}
			//new一个FileResource
			fr = new FileResource(uid, 
					Boolean.parseBoolean(file.element("newdown").getText()), 
					file.element("filename").getText(), 
					file.element("filetype").getText(), 
					file.element("fileclasspath").getText(), 
					new URL(file.element("url").getText()), 
					Long.parseLong(file.element("size").getText()), 
					Integer.parseInt(file.element("threadsize").getText()), 
					Integer.parseInt(file.element("priority").getText()), 
					l);
			
			return fr;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
