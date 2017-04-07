package com.sg.aapt.build;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.sg.aapt.util.FileUtil;


public class HandlManifest {
	
	static String path = FileUtil.getProjectRootPath() + File.separator + "apk_file" + File.separator + "AndroidManifest.xml";
	static String classPath = FileUtil.getProjectRootPath() + File.separator + "apk_file" + File.separator + "src\\com\\sg\\a_test\\MainActivity.java";
	private String manifestPath;
	
	public HandlManifest(String manifestPath){
		this.manifestPath = manifestPath;
	}
	
	/**
	 * 修改包名和入口类名
	 * @param packageName 包名
	 * @param mainActivityName 入口类名
	 */
	public void editPackageName(String packageName, String mainActivityName){
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(new File(manifestPath));
			
			Element elmentRoot = document.getRootElement();
			Attribute packageAttr = elmentRoot.attribute("package");
			packageAttr.setValue(packageName);
			
			
			Element elment = document.getRootElement().element("application").element("activity");
			Attribute attrName = elment.attribute("name");
			attrName.setValue(mainActivityName);
			
			OutputFormat format = OutputFormat.createPrettyPrint();
	        // 利用格式化类对编码进行设置
	        format.setEncoding("utf-8");
	        FileOutputStream output;
	        // 生成的xml覆盖原文件
			output = new FileOutputStream(new File(path));
	        XMLWriter writer;
			writer = new XMLWriter(output, format);
			writer.write(document);
	        writer.flush();
	        writer.close();
			
//			 android:name="com.sg.a_test.MainActivity"
//			            android:label="@string/app_name" >
			
			/*List<Attribute> manifestAtrrs = manifest.attributes();
			
			for (Attribute manifestAtrr : manifestAtrrs) {
				if ("package".equals(manifestAtrr.getName())) {
					manifestAtrr.setValue(packageName);
					break;
				}
			}*/
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
}
