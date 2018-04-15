package com.youotech.entity.dto;

import java.util.List;

public class TreeDTO {
	private String name;
	private Boolean checked;
	private String icon;
	private Long id;
	private Long pId;
	private List<TreeDTO> children;
	private boolean isParent;
	private Integer flag;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}
	public List<TreeDTO> getChildren() {
		return children;
	}
	public void setChildren(List<TreeDTO> children) {
		this.children = children;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	public TreeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TreeDTO(String name, String icon, Long id, Long pId, Integer flag) {
		super();
		this.name = name;
		this.icon = icon;
		this.id = id;
		this.pId = pId;
		this.flag = flag;
	}
	
	public TreeDTO(String name, Boolean checked, String icon, Long id, Long pId, List<TreeDTO> children,
			boolean isParent, Integer flag) {
		super();
		this.name = name;
		this.checked = checked;
		this.icon = icon;
		this.id = id;
		this.pId = pId;
		this.children = children;
		this.isParent = isParent;
		this.flag = flag;
	}
	
}
