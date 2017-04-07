package com.sg.aapt.util;

import java.io.File;

public class Constants {
	
	
//	public static final String ANDROID_JAR_PATH = "\"D:\\Program Files\\adt-bundle-windows\\sdk\\platforms\\android-19\\android.jar\"";
//	public static final String SDKLIB_JAR_PATH = "\"D:\\Program Files\\adt-bundle-windows\\sdk\\tools\\lib\\sdklib.jar\"";
//	public static final String DIR_PROGUARD = "\"D:\\Program Files\\adt-bundle-windows\\sdk\\tools\\proguard\\lib\\proguard.jar\"";
//	public static final String ANDROID_JAR_PATH = "\"E:\\Program Files Pro\\ADT_SDK\\sdk\\platforms\\android-19\\android.jar\"";
//	public static final String SDKLIB_JAR_PATH = "\"E:\\Program Files Pro\\ADT_SDK\\sdk\\tools\\lib\\sdklib.jar\"";
//	public static final String DIR_PROGUARD = "\"E:\\Program Files Pro\\ADT_SDK\\sdk\\tools\\proguard\\lib\\proguard.jar\"";
	
	public static final String DIR_BUILD_BIN = FileUtil.getProjectRootPath() + File.separator + "apk_file" + File.separator + "bin";
	public static final String DIR_RES = FileUtil.getProjectRootPath() + File.separator + "apk_file" + File.separator + "res";
	public static final String DIR_APK_FILE = FileUtil.getProjectRootPath() + File.separator + "apk_file";
	public static final String DIR_SRC = FileUtil.getProjectRootPath() + File.separator + "apk_file" + File.separator + "src";
	public static final String DIR_BIN = FileUtil.getProjectRootPath() + File.separator + "apk_file" + File.separator + "bin";
	public static final String DIR_CONFIG = FileUtil.getProjectRootPath() + File.separator + "apk_file" + File.separator + "config";
	public static final String DIR_MANIFEST = FileUtil.getProjectRootPath() + File.separator + "apk_file" + File.separator + "AndroidManifest.xml";
	public static final String DIR_PROGUARD_LIB = FileUtil.getProjectRootPath();
	public static final String DIR_PROGUARD_LIB_ADD = FileUtil.getProjectRootPath() + File.separator + "apk_file" + File.separator + "proguard_lib";
	
	public static final String FILE_MANIFEST = "AndroidManifest.xml";
	public static final String FILE_BIN = "bin";

}
