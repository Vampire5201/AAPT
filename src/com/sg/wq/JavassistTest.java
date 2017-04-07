package com.sg.wq;

import java.io.File;

import com.sg.aapt.util.FileUtil;

import javassist.ClassPool;
import javassist.CtClass;

public class JavassistTest {
	
	static String path = FileUtil.getProjectRootPath() + File.separator + "apk_file" + File.separator + "com\\sg\\a_test\\MainActivity.java";
	static String path2 = FileUtil.getProjectRootPath() + File.separator + "apk_file" + File.separator + "com\\sg\\a_test\\MainActivity";
	static String mainPath3 = "E:\\workspaceAll\\workspace_cocos\\A_WQAAPT\\apk_file\\bin\\com\\sg\\a_test\\MainActivity.class";
	static String mainPath = "E:\\workspaceAll\\workspace_cocos\\A_WQAAPT\\apk_file\\bin";
	
	
	public static void main(String[] args) {
		editClass("com.sg.qq.Test",mainPath, mainPath);
	}
	
	private static void editClass(String className, String writePath, String outPath){
		try {
			ClassPool pool = ClassPool.getDefault();  
			pool.insertClassPath(writePath);
			CtClass cls = pool.getCtClass("com.sg.a_test.MainActivity");
			cls.setName(className);
			cls.writeFile(outPath);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
        
	}
}
