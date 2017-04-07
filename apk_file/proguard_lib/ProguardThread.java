package com.sg.aapt.proguard;

import java.io.File;

import com.sg.aapt.util.Constants;

public class ProguardThread extends Thread {
	
	public static final String PROGUARD_DIR = "\"D:\\Program Files\\adt-bundle-windows\\sdk\\tools\\proguard\\lib\\proguard.jar\"";
	public static final String BUILD_BIN = Constants.DIR_BIN;

	public ProguardThread() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		try {
			System.out.println("混淆线程：" + Thread.currentThread().getName() +  + Thread.currentThread().getId());
			StringBuffer buffer2 = new StringBuffer();
			buffer2.append("cmd.exe /c java -jar ");
			buffer2.append(PROGUARD_DIR).append(" @proguard-project2.txt ");
			Runtime.getRuntime().exec(buffer2.toString(), null, new File(BUILD_BIN));
			System.out.println(BUILD_BIN + "，混淆jar包：" + buffer2);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
