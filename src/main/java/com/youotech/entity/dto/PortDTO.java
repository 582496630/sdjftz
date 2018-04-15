package com.youotech.entity.dto;

public class PortDTO {
	private String ip;
	private String mac;
	private String name;
	private String pm;
	private Long star;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPm() {
		return pm;
	}
	public void setPm(String pm) {
		this.pm = pm;
	}
	
	public Long getStar() {
		return star;
	}
	public void setStar(Long star) {
		this.star = star;
	}
	@Override
	public String toString() {
		return "PortDTO [ip=" + ip + ", mac=" + mac + ", name=" + name + ", pm=" + pm + ", star=" + star + "]";
	}
	public PortDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
