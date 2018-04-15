package com.youotech.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DeviceModel {
    private Long id;

    private Long fq;

    private String name;

    private Long typesetId;

    private String sbzrrName;

    private String sbwhrName;

    private String sbxh;

    private String xlh;

    private Long pgId;

    private String remark;

    private Long facId;

    private Long topologyId;

    private Long systemId;

    private String ip;

    private BigDecimal del;

    private BigDecimal fq1;

    private BigDecimal fq2;

    private BigDecimal fq3;

    private BigDecimal fq4;

    private String ywdj;

    private BigDecimal snmpVersion;

    private String snmpRead;

    private String snmpWrite;

    private String snmpUsername;

    private String snmpPassword;

    private BigDecimal snmpSecuritylevel;

    private String itemIds;

    private String macAddress;

    private String snmpPort;

    private Date updataTime;

    private String networkStatus;

    private Date hostSyntime;

    private BigDecimal mainPort;

    private String scanDetaild;

    private Long bjsb;
    
    private String sccj;
    
    private String scfj;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFq() {
        return fq;
    }

    public void setFq(Long fq) {
        this.fq = fq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getTypesetId() {
        return typesetId;
    }

    public void setTypesetId(Long typesetId) {
        this.typesetId = typesetId;
    }

    public String getSbzrrName() {
        return sbzrrName;
    }

    public void setSbzrrName(String sbzrrName) {
        this.sbzrrName = sbzrrName == null ? null : sbzrrName.trim();
    }

    public String getSbwhrName() {
        return sbwhrName;
    }

    public void setSbwhrName(String sbwhrName) {
        this.sbwhrName = sbwhrName == null ? null : sbwhrName.trim();
    }

    public String getSbxh() {
        return sbxh;
    }

    public void setSbxh(String sbxh) {
        this.sbxh = sbxh == null ? null : sbxh.trim();
    }

    public String getXlh() {
        return xlh;
    }

    public void setXlh(String xlh) {
        this.xlh = xlh == null ? null : xlh.trim();
    }

    public Long getPgId() {
        return pgId;
    }

    public void setPgId(Long pgId) {
        this.pgId = pgId;
    }

    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getFacId() {
        return facId;
    }

    public void setFacId(Long facId) {
        this.facId = facId;
    }

    public Long getTopologyId() {
        return topologyId;
    }

    public void setTopologyId(Long topologyId) {
        this.topologyId = topologyId;
    }

    public Long getSystemId() {
        return systemId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public BigDecimal getDel() {
        return del;
    }

    public void setDel(BigDecimal del) {
        this.del = del;
    }

    public BigDecimal getFq1() {
        return fq1;
    }

    public void setFq1(BigDecimal fq1) {
        this.fq1 = fq1;
    }

    public BigDecimal getFq2() {
        return fq2;
    }

    public void setFq2(BigDecimal fq2) {
        this.fq2 = fq2;
    }

    public BigDecimal getFq3() {
        return fq3;
    }

    public void setFq3(BigDecimal fq3) {
        this.fq3 = fq3;
    }

    public BigDecimal getFq4() {
        return fq4;
    }

    public void setFq4(BigDecimal fq4) {
        this.fq4 = fq4;
    }

    public String getYwdj() {
        return ywdj;
    }

    public void setYwdj(String ywdj) {
        this.ywdj = ywdj == null ? null : ywdj.trim();
    }

    public BigDecimal getSnmpVersion() {
        return snmpVersion;
    }

    public void setSnmpVersion(BigDecimal snmpVersion) {
        this.snmpVersion = snmpVersion;
    }

    public String getSnmpRead() {
        return snmpRead;
    }

    public void setSnmpRead(String snmpRead) {
        this.snmpRead = snmpRead == null ? null : snmpRead.trim();
    }

    public String getSnmpWrite() {
        return snmpWrite;
    }

    public void setSnmpWrite(String snmpWrite) {
        this.snmpWrite = snmpWrite == null ? null : snmpWrite.trim();
    }

    public String getSnmpUsername() {
        return snmpUsername;
    }

    public void setSnmpUsername(String snmpUsername) {
        this.snmpUsername = snmpUsername == null ? null : snmpUsername.trim();
    }

    public String getSnmpPassword() {
        return snmpPassword;
    }

    public void setSnmpPassword(String snmpPassword) {
        this.snmpPassword = snmpPassword == null ? null : snmpPassword.trim();
    }

    public BigDecimal getSnmpSecuritylevel() {
        return snmpSecuritylevel;
    }

    public void setSnmpSecuritylevel(BigDecimal snmpSecuritylevel) {
        this.snmpSecuritylevel = snmpSecuritylevel;
    }

    public String getItemIds() {
        return itemIds;
    }

    public void setItemIds(String itemIds) {
        this.itemIds = itemIds == null ? null : itemIds.trim();
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress == null ? null : macAddress.trim();
    }

    public String getSnmpPort() {
        return snmpPort;
    }

    public void setSnmpPort(String snmpPort) {
        this.snmpPort = snmpPort == null ? null : snmpPort.trim();
    }

    public Date getUpdataTime() {
        return updataTime;
    }

    public void setUpdataTime(Date updataTime) {
        this.updataTime = updataTime;
    }

    public String getNetworkStatus() {
        return networkStatus;
    }

    public void setNetworkStatus(String networkStatus) {
        this.networkStatus = networkStatus == null ? null : networkStatus.trim();
    }

    public Date getHostSyntime() {
        return hostSyntime;
    }

    public void setHostSyntime(Date hostSyntime) {
        this.hostSyntime = hostSyntime;
    }

    public BigDecimal getMainPort() {
        return mainPort;
    }

    public void setMainPort(BigDecimal mainPort) {
        this.mainPort = mainPort;
    }

    public String getScanDetaild() {
        return scanDetaild;
    }

    public void setScanDetaild(String scanDetaild) {
        this.scanDetaild = scanDetaild == null ? null : scanDetaild.trim();
    }

	public Long getBjsb() {
		return bjsb;
	}

	public void setBjsb(Long bjsb) {
		this.bjsb = bjsb;
	}

	public String getSccj() {
		return sccj;
	}

	public void setSccj(String sccj) {
		this.sccj = sccj;
	}

	public String getScfj() {
		return scfj;
	}

	public void setScfj(String scfj) {
		this.scfj = scfj;
	}
	
}