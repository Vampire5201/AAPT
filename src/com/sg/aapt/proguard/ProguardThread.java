package com.sg.aapt.proguard;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.sg.aapt.test.Aapt;
import com.sg.aapt.util.Constants;
import com.sg.aapt.util.FileUtil;

public class ProguardThread extends Thread {
	
//	public static final String DIR_PROGUARD = "\"D:\\Program Files\\adt-bundle-windows\\sdk\\tools\\proguard\\lib\\proguard.jar\"";
	public static final String BUILD_BIN = Constants.DIR_BIN;

	public ProguardThread() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		try {
			
			String configPath = new File(FileUtil.getProjectRootPath()).getParentFile().getAbsolutePath() + File.separator + "config" + File.separator + "config.properties";
			
			Properties property = new Properties();
			InputStream in = new FileInputStream(configPath);
			property.load(in);
			in.close();
			String proguard_jar_path = property.getProperty("PROGUARD_JAR_PATH");
			
			System.out.println("混淆线程：" + Thread.currentThread().getName() +  + Thread.currentThread().getId());
			StringBuffer buffer2 = new StringBuffer();
			buffer2.append("cmd.exe /c java -jar ");
			buffer2.append(proguard_jar_path).append(" @proguard-project2.txt -ignorewarnings ");
			Runtime.getRuntime().exec(buffer2.toString(), null, new File(Constants.DIR_PROGUARD_LIB));
			System.out.println(Constants.DIR_PROGUARD_LIB + "，混淆jar包：" + buffer2);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
