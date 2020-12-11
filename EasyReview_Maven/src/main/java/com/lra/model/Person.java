package com.lra.model;

public class Person {
	
	private int id;
	private String name;
	private String info;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", info=" + info + "]";
	}
	
	

}
