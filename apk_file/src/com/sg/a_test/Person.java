package com.sg.a_test;

import android.util.Log;

public class Person {
	
	private String name;
	public Person(String name){
		this.name = name;
	}
	
	public void printName(){
		Log.i("Name", name);
	}
}
