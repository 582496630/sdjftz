package com.youotech.entity;


/**
 * 屏柜与设备关系表
 * @author zhouyou
 * 2018-4-8 16:35:22
 */
public class DevicesAndRackModel {

	private Long id;
	
	private Long deviceId;
	
	private Long rackId;
	
	private Long onHeight;
	
	private Long frontRear;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Long getRackId() {
		return rackId;
	}

	public void setRackId(Long rackId) {
		this.rackId = rackId;
	}

	public Long getOnHeight() {
		return onHeight;
	}

	public void setOnHight(Long onHeight) {
		this.onHeight = onHeight;
	}

	public Long getFrontRear() {
		return frontRear;
	}

	public void setFrontRear(Long frontRear) {
		this.frontRear = frontRear;
	}

}
