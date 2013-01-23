package com.dianping.test.serializable;

import java.io.Serializable;

public class Dog implements Serializable{
	
	

	
	
	
	//private static final long serialVersionUID = 6877369661682380801L;
	//private static final long serialVersionUID = 2889622698580126852L;
	private String name;
	private int age;
	//private int kk;
	
	public Dog(String name,int age){
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public int getAge() {
//		return age;
//	}
//
//	public void setAge(int age) {
//		this.age = age;
//	}
//	
	

}
