package com.sg.aapt.proguard;


public class ProguardJump {
	public static void main(String[] args) {
		new ProguardThread().start();
//		System.out.println("Hello world");
	}
	
	public ProguardJump() {
		// TODO Auto-generated constructor stub
	}
	
	public void proguardStart(){
		new ProguardThread().start();
	}

}
