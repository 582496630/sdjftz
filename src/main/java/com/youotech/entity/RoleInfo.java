package com.youotech.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("all")
@Table(name = "SDTZ_SYS_ROLE")
public class RoleInfo implements Serializable{
	@Id
	private int id;
	private String rolename;
	private String dec;
	private String authodec;
	private String isuseredit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getDec() {
		return dec;
	}

	public void setDec(String dec) {
		this.dec = dec;
	}

	public String getAuthodec() {
		return authodec;
	}

	public void setAuthodec(String authodec) {
		this.authodec = authodec;
	}

	public String getIsuseredit() {
		return isuseredit;
	}

	public void setIsuseredit(String isuseredit) {
		this.isuseredit = isuseredit;
	}

}
