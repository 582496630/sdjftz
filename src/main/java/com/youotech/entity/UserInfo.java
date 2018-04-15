package com.youotech.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("all")
@Table(name = "SDTZ_SYS_USER")
public class UserInfo implements Serializable {
	@Id
	private int id;

	/**
	 * 所属单位编号
	 */
	private int depid;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 创建时间
	 */
	@Column(name = "CTIME")
	private Date cTime;

	/**
	 * 修改时间
	 */
	@Column(name = "MTIME")
	private Date mTime;

	/**
	 * 错误数量
	 */
	@Column(name = "MISTAKENUM")
	private int mistakeNum;

	/**
	 * 状态
	 */
	private int state;

	/**
	 * 用户名
	 */
	@Column(name = "USERNAME")
	private String userName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 是否是管理员
	 */
	private int ismanager;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDepid() {
		return depid;
	}

	public void setDepid(int depid) {
		this.depid = depid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public Date getmTime() {
		return mTime;
	}

	public void setmTime(Date mTime) {
		this.mTime = mTime;
	}

	public int getMistakeNum() {
		return mistakeNum;
	}

	public void setMistakeNum(int mistakeNum) {
		this.mistakeNum = mistakeNum;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIsmanager() {
		return ismanager;
	}

	public void setIsmanager(int ismanager) {
		this.ismanager = ismanager;
	}

}
