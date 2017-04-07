package com.sg.aapt.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.List;

/**
 * 文件工具
 * 
 * @author fengyoutian
 * 
 */
public class FileUtil {
	
	/*
	 * @author yanglihang
	 */
	public static String getProjectRootPath() {
		try {
			String jarWholePath = FileUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			jarWholePath = URLDecoder.decode(jarWholePath, "UTF-8");
			String jarPath = new File(jarWholePath).getParentFile().getAbsolutePath();
			return jarPath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
     * 递归删除目录下的所有文件及子目录下所有文件
     * 
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    public static boolean clearDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = clearDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
    
    /**
     * 遍历目录及其子目录下的所有文件
     * 
     * @param path 目录全路径
     * @param list 列表：保存文件对象
     */
    public static void list(File path, List<File> list) {
        if (!path.exists()){
            return;
        }
        
        if (path.isFile()){
            list.add(path);
        } else{
            File[] files = path.listFiles();
            for (File file : files){
            	list(file, list);
            }
        }
    }
    
    /**
     * 将is流写到descFile文件中
     * 
     * @param is
     * @param descFile
     * 
     */
    public static void replaceFile(InputStream is, File descFile) {
    	OutputStream os = null;
		
    	try {
			os = new FileOutputStream(descFile);
			write(is, os);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
	    	if (os != null) {
	            try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }   
	        if (is != null) {   
	            try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
		}
    }
    
    /**
     * 将srcFile文件写到descFile文件中
     * 
     * @param srcFile
     * @param descFile
     * 
     */
    public static void replaceFile(File srcFile, File descFile) {
    	OutputStream os = null;
		InputStream is = null;
		
    	try {
			os = new FileOutputStream(descFile);
			is = new FileInputStream(srcFile);
			write(is, os);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
	    	if (os != null) {
	            try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }   
	        if (is != null) {   
	            try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
		}
    }
    
    public static String readFile(File file) throws IOException {
    	if (file.exists() && file.isFile()) {
    		return new String(getByte(file));
    	} else {
    		return null;
    	}
    }
    
    public static void write(InputStream input, OutputStream output) throws IOException {
		byte[] buff = new byte[1024];
		int bytesRead = 0;
		while ((bytesRead = input.read(buff)) != -1) {
			output.write(buff, 0, bytesRead);
		}
		input.close();
	}
    
    public static void writeToFile(String content, File file) throws IOException {
    	File parentDir = file.getParentFile();
    	if (!parentDir.exists()) {
    		parentDir.mkdirs();
    	}
    	if (!file.exists()) {
    		file.createNewFile();
    	}
    	FileWriter fw = new FileWriter(file, false);
    	BufferedWriter bw = new BufferedWriter(fw);
    	bw.write(content);
    	
    	bw.close();
    	fw.close();
	}
    
    /**
     * 将文本文件中的内容读入到buffer中
     * 
     * @param buffer 		buffer
     * @param filePath 		文件路径
     * @throws IOException
     */
    public static void readToBuffer(StringBuffer buffer, File file) throws IOException {
        InputStream is = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = reader.readLine()) != null) {
            buffer.append(line).append("\n");
        }
        reader.close();
        is.close();
    }
    
    /**
     * 把一个文件转化为字节
     *
     * @param file
     * @return byte[]
     * @throws IOException 
     */
    public static byte[] getByte(File file) throws IOException {
        byte[] bytes = null;
        if (null != file && file.exists()) {
            InputStream is = new FileInputStream(file);
            int length = (int) file.length();
            // 当文件的长度超过了int的最大值
            if (length > Integer.MAX_VALUE) {
                is.close();
                return null;
            }
            bytes = new byte[length];
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
            // 如果得到的字节长度和file实际的长度不一致就可能出错了
            if (offset < bytes.length) {
                is.close();
                return null;
            }
            is.close();
        }
        return bytes;
    }
    
}
