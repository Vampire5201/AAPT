package com.sg.aapt.core;

import java.io.File;

import com.sg.aapt.util.FileUtil;


/**
 * 路径管理
 * 
 * @author fengyoutian
 *
 */
public class PathManager {
	/*
	 *  工作空间 
	 *  	1.所有apk都放在此目录，以apk文件名+时间戳作为一个工程的区分
	 */
	private String workspaceDir;
	private String tempDir;			// 工作空间下的临时文件目录
	private String binDir;			// 最终打包出来的apk存储目录
	/*
	 *  工程目录
	 *  	1.此目录下存放apk的所有资源源文件(assets、lib、res、unknown、Manifest、classes.dex)
	 *  	2.除了res，其他文件均是和渠道文件合并后放一起(res需要利用aapt去重，需分开路径存放)
	 */
	private String projectDir;
	private String resDir;			// apk的res目录
	private String assetsDir;		// assets目录
	private String libDir;			// lib目录
	private String manifestPath;	// Manifest路径
	private String rJavaDir;		// R.java目录
	private String rClassDir;		// R.class目录
	private String rJarPath;		// R.jar路径
	private String rDexPath;		// R.dex路径
	private String classesDexPath;	// classes.dex路径
	private String unknownDir;		// 根目录文件存储目录
	private String aaptFilePath;	// 生成的res资源apk输出路径
	
	private String sgLaunchPath;	// 自定义闪屏xml保存路径
	
	/*
	 *  渠道文件解压目录
	 *  	1.此目录存放所有渠道的资源文件（解压后的目录名为：渠道名_版本号）(res、active、permission)
	 *  	2.active、permission用于生成Manifest
	 */
	private String channelDir;
	
	public PathManager(String workspace) {
		this.workspaceDir = workspace;

		this.binDir = workspace + File.separator + "bin";
		
		this.projectDir = workspace + File.separator + Constants_bak.PROJECT_DIR_NAME;
//		this.channelDir = workspace + File.separator + Constants.CHANNEL_DIR_NAME;
		
		this.resDir = projectDir + File.separator + Constants_bak.RES_DIR_NAME;
		this.assetsDir = projectDir + File.separator + Constants_bak.ASSETS_DIR_NAME;
		this.libDir = projectDir + File.separator + Constants_bak.LIB_DIR_NAME;
		this.manifestPath = projectDir + File.separator + Constants_bak.MANIFEST_FILE_NAME;
		this.classesDexPath = projectDir + File.separator + Constants_bak.CLASSES_DEX_FILE_NAME;
		this.unknownDir = projectDir + File.separator + Constants_bak.UNKNOWN_DIR_NAME;
		
		this.tempDir = workspace + File.separator + "temp";
		this.rJavaDir = tempDir + File.separator + Constants_bak.R_JAVA_DIR;
		this.rClassDir = tempDir + File.separator + Constants_bak.R_CLASS_DIR;
		this.rJarPath = tempDir + File.separator + Constants_bak.R_JAR_FILE_NAME;
		this.rDexPath = tempDir + File.separator + Constants_bak.R_DEX_FILE_NAME;
		this.aaptFilePath = tempDir + File.separator + Constants_bak.AAPT_FILE_NAME;
		
		this.sgLaunchPath = tempDir + File.separator + Constants_bak.SG_LAUNCH_FILE_NAME;
	}
	
	/**
	 * 清理工作空间
	 */
	public void clear() {
		FileUtil.clearDir(new File(channelDir));
		FileUtil.clearDir(new File(projectDir));
		FileUtil.clearDir(new File(tempDir));
	}

	public String getWorkspaceDir() {
		return workspaceDir;
	}

	public String getTempDir() {
		return tempDir;
	}

	public String getBinDir() {
		return binDir;
	}

	public String getProjectDir() {
		return projectDir;
	}

	public String getResDir() {
		return resDir;
	}

	public String getAssetsDir() {
		return assetsDir;
	}

	public String getLibDir() {
		return libDir;
	}

	public String getManifestPath() {
		return manifestPath;
	}

	public String getRJavaDir() {
		return rJavaDir;
	}

	public String getRClassDir() {
		return rClassDir;
	}

	public String getRJarPath() {
		return rJarPath;
	}

	public String getRDexPath() {
		return rDexPath;
	}

	public String getClassesDexPath() {
		return classesDexPath;
	}

	public void setClassesDexPath(String classesDexPath) {
		this.classesDexPath = classesDexPath;
	}

	public String getUnknownDir() {
		return unknownDir;
	}

	public String getAaptFilePath() {
		return aaptFilePath;
	}

	public String getChannelDir() {
		return channelDir;
	}

	public String getSgLaunchPath() {
		return sgLaunchPath;
	}
	
	
}
