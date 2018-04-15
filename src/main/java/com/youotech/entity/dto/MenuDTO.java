package com.youotech.entity.dto;

import java.util.List;

public class MenuDTO {
	private int id;
	private String text;
	private String icon;
	private String targetType;
	private String url;
	private List<MenuDTO> children;
	private boolean isHeader;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTargetType() {
		return targetType;
	}
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<MenuDTO> getChildren() {
		return children;
	}
	public void setChildren(List<MenuDTO> children) {
		this.children = children;
	}
	public boolean getIsHeader() {
		return isHeader;
	}
	public void setIsHeader(boolean isHeader) {
		this.isHeader = isHeader;
	}
	@Override
	public String toString() {
		return "MenuDTO [id=" + id + ", text=" + text + ", icon=" + icon + ", targetType=" + targetType + ", url=" + url
				+ ", children=" + children + ", isHeader=" + isHeader + "]";
	}
	public MenuDTO(int id, String text, String icon, String targetType, String url, List<MenuDTO> children,
			boolean isHeader) {
		super();
		this.id = id;
		this.text = text;
		this.icon = icon;
		this.targetType = targetType;
		this.url = url;
		this.children = children;
		this.isHeader = isHeader;
	}
	public MenuDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
