package com.youotech.entity.dto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class AddDeviceDTO {
	
	private Long systemId;
	
    private Long fq;
    
    private Long typesetId;
    
    private String name;

    private String sbzrrName;

    private String sbwhrName;

    private String xlh;
    
    private String sbxh;
    
    private String sccj;
    
    private String ywdj;	

    private Long pgId;
    
    private Long facId;

    private String remark;
    
    private String portArr;
    
    private String scfj;
    
    private String snmpRead;
    
    private String snmpPort;


	public Long getSystemId() {
		return systemId;
	}

	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}

	public Long getFq() {
		return fq;
	}

	public void setFq(Long fq) {
		this.fq = fq;
	}

	public Long getTypesetId() {
		return typesetId;
	}

	public void setTypesetId(Long typesetId) {
		this.typesetId = typesetId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSbzrrName() {
		return sbzrrName;
	}

	public void setSbzrrName(String sbzrrName) {
		this.sbzrrName = sbzrrName;
	}

	public String getSbwhrName() {
		return sbwhrName;
	}

	public void setSbwhrName(String sbwhrName) {
		this.sbwhrName = sbwhrName;
	}

	public String getXlh() {
		return xlh;
	}

	public void setXlh(String xlh) {
		this.xlh = xlh;
	}

	public String getSbxh() {
		return sbxh;
	}

	public void setSbxh(String sbxh) {
		this.sbxh = sbxh;
	}

	public String getSccj() {
		return sccj;
	}

	public void setSccj(String sccj) {
		this.sccj = sccj;
	}

	public String getYwdj() {
		return ywdj;
	}

	public void setYwdj(String ywdj) {
		this.ywdj = ywdj;
	}

	public Long getPgId() {
		return pgId;
	}

	public void setPgId(Long pgId) {
		this.pgId = pgId;
	}

	public Long getFacId() {
		return facId;
	}

	public void setFacId(Long facId) {
		this.facId = facId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPortArr() {
		return portArr;
	}

	public void setPortArr(String portArr) {
		this.portArr = portArr;
	}

	public String getScfj() {
		return scfj;
	}

	public void setScfj(String scfj) {
		this.scfj = scfj;
	}

	public String getSnmpRead() {
		return snmpRead;
	}

	public void setSnmpRead(String snmpRead) {
		this.snmpRead = snmpRead;
	}

	public String getSnmpPort() {
		return snmpPort;
	}

	public void setSnmpPort(String snmpPort) {
		this.snmpPort = snmpPort;
	}

	
	@Override
	public String toString() {
		return "AddDeviceDTO [systemId=" + systemId + ", fq=" + fq + ", typesetId=" + typesetId + ", name=" + name
				+ ", sbzrrName=" + sbzrrName + ", sbwhrName=" + sbwhrName + ", xlh=" + xlh + ", sbxh=" + sbxh
				+ ", sccj=" + sccj + ", ywdj=" + ywdj + ", pgId=" + pgId + ", facId=" + facId + ", remark=" + remark
				+ ", portArr=" + portArr + ", scfj=" + scfj + ", snmpRead=" + snmpRead + ", snmpPort=" + snmpPort + "]";
	}

	public AddDeviceDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
