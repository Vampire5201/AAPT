package com.sg.aapt.test;



import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.sg.aapt.build.HandlManifest;
import com.sg.aapt.proguard.ProguardJump;
import com.sg.aapt.util.Constants;
import com.sg.aapt.util.FileUtil;


import javassist.ClassPool;
import javassist.CtClass;



public class Aapt {
	
	String keystoreName = "";
	
	static String mainPath = Constants.DIR_BIN;
	static String proguardPath = FileUtil.getProjectRootPath() + File.separator + "src\\com\\sg\\aapt\\proguard";
	
	static String android_jar_path;
	static String sdklib_jar_path;
	public static String proguard_jar_path;
	
	public static void main(String[] args) {
		try {
			Properties property = new Properties();
			InputStream in = new FileInputStream(Constants.DIR_CONFIG + File.separator + "config.properties");
			property.load(in);
			in.close();
			android_jar_path = property.getProperty("ANDROID_JAR_PATH");
			sdklib_jar_path = property.getProperty("SDKLIB_JAR_PATH");
			proguard_jar_path = property.getProperty("PROGUARD_JAR_PATH");
			
			
			
			
			
			System.out.println("" + Constants.DIR_APK_FILE);
			Aapt aapt = new Aapt();
			aapt.buildRes();
			Thread.sleep(3000);
			
			aapt.buildClass();
			Thread.sleep(3000);
			
			editClass("com.sg.a_test.MainActivity", "com.sg.qq.Test",mainPath, mainPath);
			Thread.sleep(5000);
			
			aapt.buildDex();
			Thread.sleep(3000);

			aapt.editManifest("com.sg.qq", "com.sg.qq.Test");
			Thread.sleep(3000);
			
			aapt.buildManifest();
			Thread.sleep(3000);
			
			aapt.buildApk();
			Thread.sleep(3000);
			
			aapt.createKeystore();
			// 签名文件生成等待3秒
			Thread.sleep(3000);
			
			aapt.signApk();
			String str = aapt.getClassPath(Constants.DIR_APK_FILE);
			System.out.println(str);
			System.out.println("END!");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	// 生成R文件
	private void buildRes(){
		
		try {
			// 源文件存放路径
			StringBuffer buffer = new StringBuffer();
			buffer.append("cmd.exe /c aapt package -f -m -J ./gen -S res -M ")
				  .append(Constants.FILE_MANIFEST)
				  .append(" -I ").append(android_jar_path);
//			buffer.append("cmd.exe /c copy C:\\Users\\Vampire\\Desktop\\1.txt C:\\Users\\Vampire\\Desktop\\1");
			
			Process process = Runtime.getRuntime().exec(buffer.toString(), null, new File(Constants.DIR_APK_FILE));
//			ProcessUtil.print(process);
			System.out.println(Constants.DIR_APK_FILE + "，生成R文件："+ buffer);
			
			InputStream execOut = process.getInputStream();
            InputStreamReader execReader = new InputStreamReader( execOut );


            final int OUTPUT_BUFFER_SIZE = 1024;
            String aExecute;
            StringBuffer aOutputBuffer = new StringBuffer();
            
            char[] buffer1 = new char[ OUTPUT_BUFFER_SIZE ];
            int bytes_read = execReader.read( buffer1 );

            while( bytes_read > 0 ) {
                aOutputBuffer.append( buffer1, 0, bytes_read );
                bytes_read = execReader.read( buffer1 );
            }
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// 生成class文件
	private void buildClass(){
		try {
			StringBuffer buffer = new StringBuffer();
			// javac -bootclasspath "D:\Program Files\adt-bundle-windows\sdk\platforms\android-19\android.jar" -d bin src\com\sg\a_test\*.java gen\com\sg\a_test\R.java
			buffer.append("cmd.exe /c javac -bootclasspath ")
				  .append(android_jar_path)
				  .append(" -d ").append(Constants.FILE_BIN)
				  .append(" src\\com\\sg\\a_test\\*.java")
				  .append(" gen\\com\\sg\\a_test\\R.java");
//			buffer.append("cmd.exe /c copy C:\\Users\\Vampire\\Desktop\\1.txt C:\\Users\\Vampire\\Desktop\\1");
			
			Process process = Runtime.getRuntime().exec(buffer.toString(), null, new File(Constants.DIR_APK_FILE));
			System.out.println(Constants.DIR_APK_FILE + "，生存Class文件："+ buffer);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// 修改class文件名
	private static void editClass(String sourceClass, String desClass, String sourcePath, String desPath){
		try {
			ClassPool pool = ClassPool.getDefault();  
			pool.insertClassPath(sourcePath);
			CtClass cls = pool.getCtClass(sourceClass);
			cls.setName(desClass);
			cls.writeFile(desPath);
			System.out.println("修改Class文件");
		} catch (Exception e) {
			System.out.println(e);
		}
        
	}

	// 编译dex文件
	private void buildDex(){
		try {
			// 把class文件打成jar包
			StringBuffer buffer = new StringBuffer();
			buffer.append("cmd.exe /c jar cvf proguard_lib/demo.jar -C bin/ com/ ");// TODO
			Runtime.getRuntime().exec(buffer.toString(), null, new File(Constants.DIR_APK_FILE));
			System.out.println(Constants.DIR_APK_FILE + ",class文件打包成jar：" + buffer);
			
			Thread.sleep(3000);
			// 混淆jar包
			/*StringBuffer buffer2 = new StringBuffer();
			buffer2.append("cmd.exe /c java -jar ");
			buffer2.append(proguardDir).append(" @proguard-project2.txt ");
			Runtime.getRuntime().exec(buffer2.toString(), null, new File(builderBin));
			System.out.println(builderBin + "，混淆jar包：" + buffer2);*/
			System.out.println("主线程：" + Thread.currentThread().getName() + Thread.currentThread().getId());
//			new ProguardThread().start();
			
			// 使用命令行执行java进行中间跳转
			// 编译
			StringBuffer buffer2 = new StringBuffer();
			buffer2.append("cmd.exe /c javac -cp aapt_lib.jar -encoding utf-8 *.java ");
			Runtime.getRuntime().exec(buffer2.toString(), null, new File(Constants.DIR_PROGUARD_LIB_ADD));
			System.out.println(Constants.DIR_PROGUARD_LIB_ADD + ",Proguard跳转编译：" + buffer2);
			Thread.sleep(2000);
			
			// 执行
			StringBuffer buffer3 = new StringBuffer();
			buffer3.append("cmd.exe /c java -cp aapt_lib.jar com.sg.aapt.proguard.ProguardJump ");
			Runtime.getRuntime().exec(buffer3.toString(), null, new File(Constants.DIR_PROGUARD_LIB_ADD));
			System.out.println(Constants.DIR_PROGUARD_LIB_ADD + ",Proguard跳转执行：" + buffer3);
			
//			new ProguardJump().proguardStart();
			
			int dexTime = 0;
//			Thread.sleep(10000);
			while(!existFile(Constants.DIR_PROGUARD_LIB_ADD, "demo_en.jar")){
				Thread.sleep(1000);
				dexTime++;
				System.out.println(dexTime);
			}
			
			// 生成dex文件
			StringBuffer buffer1 = new StringBuffer();
			buffer1.append("cmd.exe /c dx --dex --output proguard_lib\\classes.dex proguard_lib\\demo_en.jar ");
			Runtime.getRuntime().exec(buffer1.toString(), null, new File(Constants.DIR_APK_FILE));
			System.out.println(Constants.DIR_APK_FILE + "，生存dex文件：" + buffer1);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void editManifest(String packageName, String mainActivityName){
		HandlManifest h = new HandlManifest(Constants.DIR_MANIFEST);
		h.editPackageName(packageName, mainActivityName);
	}
	
	private void buildManifest(){
		try {
			// 源文件存放路径
			StringBuffer buffer = new StringBuffer();
			// aapt package -f -M AndroidManifest.xml -S res -I "D:\Program Files\adt-bundle-windows\sdk\platforms\android-19\android.jar" -F bin\resources.ap_
			buffer.append("cmd.exe /c aapt package -f -M AndroidManifest.xml -S res")
				  .append(" -I ").append(android_jar_path)
				  .append(" -F bin\\resources.ap_");
			Runtime.getRuntime().exec(buffer.toString(), null,  new File(Constants.DIR_APK_FILE));
			System.out.println(Constants.DIR_APK_FILE + "，编译manifest文件：" + buffer);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void buildApk(){
		try {
			// 源文件存放路径
			StringBuffer buffer = new StringBuffer();
			// java -cp "D:\Program Files\adt-bundle-windows\sdk\tools\lib\sdklib.jar" com.android.sdklib.build.ApkBuilderMain demo.apk  -v -u -z bin\resources.ap_ -f bin\classes.dex -rf src
			buffer.append("cmd.exe /c java -cp ")
				  .append(sdklib_jar_path)
				  .append(" com.android.sdklib.build.ApkBuilderMain demo.apk -v -u -z bin\\resources.ap_ -f proguard_lib\\classes.dex -rf src");
			Runtime.getRuntime().exec(buffer.toString(), null,  new File(Constants.DIR_APK_FILE));
			System.out.println(Constants.DIR_APK_FILE + "，编译成apk：" + buffer);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void createKeystore(){
	   try {
		   keystoreName = System.currentTimeMillis() + "";
		   StringBuffer cmd = new StringBuffer();  
		   // cmd.append("C:\\jdk1.7.0_06\\bin\\");  
		   cmd.append("cmd.exe /c keytool -genkey -v -alias ");  
		   cmd.append(keystoreName);
		   cmd.append(" -keyalg RSA -keysize 1024 -validity 365 ");
		   cmd.append("-keystore ").append(Constants.DIR_APK_FILE).append("\\"); 
		   cmd.append(keystoreName).append(".keystore");
		   cmd.append(" -keypass 123456789 -storepass 123456789 ");  
		   cmd.append("-dname \"CN=localhost,OU=cn,O=cn,L=cn,ST=cn,C=cn\"");  
		   Process ps = Runtime.getRuntime().exec(cmd.toString(),null, new File(Constants.DIR_APK_FILE));  
		   System.out.println(Constants.DIR_APK_FILE + "，创建签名文件：" + cmd);
		
		} catch (Exception e) {
			// TODO: handle exception
		}
   }
	
	private void signApk(){
		try {
			// 源文件存放路径
			StringBuffer buffer = new StringBuffer();
			// java -cp "D:\Program Files\adt-bundle-windows\sdk\tools\lib\sdklib.jar" com.android.sdklib.build.ApkBuilderMain demo.apk  -v -u -z bin\resources.ap_ -f bin\classes.dex -rf src
			buffer.append("cmd.exe /c jarsigner -verbose -digestalg SHA1 -sigalg MD5withRSA -keystore ");
			buffer.append(keystoreName).append(".keystore");
			buffer.append(" -storepass 123456789 -keypass 123456789 -signedjar demosigned.apk demo.apk ").append(keystoreName);
			Runtime.getRuntime().exec(buffer.toString(), null, new File(Constants.DIR_APK_FILE));
			System.out.println(Constants.DIR_APK_FILE + "，签名：" + buffer);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private String getClassPath(String path){
	    StringBuffer temp=new StringBuffer();
	    File jarDir=new File(path);
	    File[] jarFiles=jarDir.listFiles();
	    for(File file:jarFiles){
	        if(file.isFile()&&file.getName().endsWith(".java")){
	            temp.append(file.getAbsolutePath()+";");
	            // jarNum++;
	        }
	        else if(file.isDirectory()){
	            temp.append(getClassPath(file.getAbsolutePath()));
	        }
	    }
	    return (temp.toString());
	}
	
	private boolean existFile(String path, String fileName){
		String filePath = path + File.separator + fileName;
		File file = new File(filePath);
		if(file.exists()){
			return true;
		}
		return false;
	}
	
}
