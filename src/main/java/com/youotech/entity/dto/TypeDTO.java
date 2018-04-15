package com.youotech.entity.dto;

public class TypeDTO {
	private Long typesetId;
	private String typesetName;
	private Long systemId;
	private String systemName;
	private Long deviceFQ;
	private String typesetImgSrc;
	
	public Long getTypesetId() {
		return typesetId;
	}
	public void setTypesetId(Long typesetId) {
		this.typesetId = typesetId;
	}
	public String getTypesetName() {
		return typesetName;
	}
	public void setTypesetName(String typesetName) {
		this.typesetName = typesetName;
	}
	public Long getSystemId() {
		return systemId;
	}
	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public Long getDeviceFQ() {
		return deviceFQ;
	}
	public void setDeviceFQ(Long deviceFQ) {
		this.deviceFQ = deviceFQ;
	}
	public String getTypesetImgSrc() {
		return typesetImgSrc;
	}
	public void setTypesetImgSrc(String typesetImgSrc) {
		this.typesetImgSrc = typesetImgSrc;
	}
	
}
