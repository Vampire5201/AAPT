package com.sg.aapt.core;

import java.io.File;

import com.sg.aapt.util.FileUtil;


public class Constants_bak {

	// 打包所需要的工具的路径和目录
		public static final String BASE_PATH = new File(FileUtil.getProjectRootPath() + "/tools/SGWXActivity.jar".replace("/", File.separator)).exists() ? FileUtil.getProjectRootPath() : "D:\\";
		public static final String TOOLS_DIR = BASE_PATH + File.separator + "tools";
		public static final String JRE_BIN_DIR = TOOLS_DIR + File.separator + "jre" + File.separator + "bin";
		
//		public static final String ANDROID_JAR_PATH = TOOLS_DIR + File.separator + "android.jar";
		public static final String ANDROID_JAR_PATH = "\"D:\\Program Files\\adt-bundle-windows\\sdk\\platforms\\android-19\\android.jar\"";
		public static final String SDKLIB_JAR_PATH = "\"D:\\Program Files\\adt-bundle-windows\\sdk\\tools\\lib\\sdklib.jar\"";
		public static final String SGWXACTIVIY_JAR_PATH = TOOLS_DIR + File.separator + "SGWXActivity.jar"; // 生成微信的两个Activity需要
		public static final String SG_LAUNCH_FILE_NAME = "SGLaunch.xml";
		public static final String SG_LAUNCH_FILE_PATH = TOOLS_DIR + File.separator + SG_LAUNCH_FILE_NAME; // 自定义闪屏需要
	
	
	// Builder时使用到的文件和目录名称
		public static final String MANIFEST_FILE_NAME = "AndroidManifest.xml";
		public static final String RESOURCES_FILE_NAME = "resources.arsc";
		public static final String RES_DIR_NAME = "res";
		public static final String LIB_DIR_NAME = "lib";
		public static final String ARMEABI_FILE_DIR_NAME = "armeabi";
		public static final String ARMEABI_V7A_FILE_DIR_NAME = "armeabi-v7a";
		public static final String MIPS_FILE_DIR_NAME = "mips";
		public static final String X86_FILE_DIR_NAME = "x86";
		public static final String UNKNOWN_DIR_NAME = "unknown";
		public static final String ASSETS_DIR_NAME = "assets";
		public static final String META_INF_DIR_NAME = "META-INF";
		public static final String R_JAVA_DIR = "r_java";
		public static final String R_JAVA_FILE_NAME = "R.java";
		public static final String R_CLASS_DIR = "r_class";
		public static final String R_JAR_FILE_NAME = "R.jar";
		public static final String R_DEX_FILE_NAME = "R.dex";
		public static final String CLASSES_DEX_FILE_NAME = "classes.dex";
		public static final String AAPT_FILE_NAME = "aapt.apk";
		public static final String UNSIGN_APK_FILE_NAME = "_unsign.apk";
		public static final String SIGNED_APK_FILE_NAME = "_signed.apk";
		public static final String ZIPALIGN_APK_FILE_NAME = "_signed_zipalign.apk";
		// 渠道launch.xml、activity.xml、permission.xml文件名
		public static final String LAUNCH_FILE_NAME = "launch.xml";
		public static final String ACTIVITY_FILE_NAME = "activity.xml";
		public static final String PERMISION_FILE_NAME = "permission.xml";
		
		public static final String WORKSPACE_DIR_NAME = "workspace";
		public static final String PROJECT_DIR_NAME = "project";
		
}
