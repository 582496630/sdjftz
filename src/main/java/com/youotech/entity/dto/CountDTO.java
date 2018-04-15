package com.youotech.entity.dto;

public class CountDTO {
	private String name;
	private int num;
	private String flag;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Override
	public String toString() {
		return "CountDTO [name=" + name + ", num=" + num + ", flag=" + flag + "]";
	}
	public CountDTO(String name, int num, String flag) {
		super();
		this.name = name;
		this.num = num;
		this.flag = flag;
	}
	public CountDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
