package com.sg.aapt.keystore;

import java.io.File;


public class Extension {  
   private String oid;  
   private boolean critical;  
   private byte[] value;  
 
   public String getOid() {  
       return oid;  
   }  
 
   public byte[] getValue() {  
       return value;  
   }  
 
   public boolean isCritical() {  
       return critical;  
   } 
   
   public static void main(String[] args) {
	   new Extension().createStorekey();
	
   }
   
   
   private void createStorekey(){
	   try {
		   StringBuffer cmd = new StringBuffer();  
		   // cmd.append("C:\\jdk1.7.0_06\\bin\\");  
		   cmd.append("cmd.exe /c keytool -genkey -v -alias");  
		   cmd.append(" alias").append(System.currentTimeMillis());
		   cmd.append(" -keyalg RSA -keysize 1024 -validity 365 ");
		   cmd.append("-keystore C:\\Users\\Vampire\\Desktop\\my"); 
		   cmd.append(System.currentTimeMillis()).append(".keystore");
		   cmd.append(" -keypass 123456789 -storepass 123456789 ");  
		   cmd.append("-dname \"CN=localhost,OU=cn,O=cn,L=cn,ST=cn,C=cn\"");  
		   System.out.println(cmd);
		   Process ps = Runtime.getRuntime().exec(cmd.toString(),null, new File("C:\\Users\\Vampire\\Desktop"));  
		
		} catch (Exception e) {
			// TODO: handle exception
		}
   }
}  