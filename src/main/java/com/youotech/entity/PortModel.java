package com.youotech.entity;

import java.util.Date;

public class PortModel {
    private Long id;

    private String name;

    private Long deviceId;

    private String ip;

    private String mac;

    private Long type;

    private Long ipzyId;

    private String tjId;

    private String itemIds;

    private Long del;

    private Long star;

    private String pm;

    private Date synTime;

    private Long snmpAvailable;

    private String snmpError;

    private Date updateTime;

    private Date lastSynDeviceScanTime;

    private Date snmpErrorsFrom;

    private Long alarmTempleteId;

    private Long icmpAvailable;

    private String icmpItemId;

    private Long sysuptimeAvailable;

    private String sysuptimeItemid;

    private Long fq;

    private String snmpAvailablestr;

    private String icmpAvailablestr;

    private String checkType;

    private Date icmpChangeErrorTime;

    private Date icmpLastClock;

    private Date snmpLastClock;

    private String itemidSysdescr;

    private Long ipzyId2;

    private Long scanDetailId;

    private Date scanDetailUpdatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getIpzyId() {
        return ipzyId;
    }

    public void setIpzyId(Long ipzyId) {
        this.ipzyId = ipzyId;
    }

    public String getTjId() {
        return tjId;
    }

    public void setTjId(String tjId) {
        this.tjId = tjId == null ? null : tjId.trim();
    }

    public String getItemIds() {
        return itemIds;
    }

    public void setItemIds(String itemIds) {
        this.itemIds = itemIds == null ? null : itemIds.trim();
    }

    public Long getDel() {
        return del;
    }

    public void setDel(Long del) {
        this.del = del;
    }

    public Long getStar() {
        return star;
    }

    public void setStar(Long star) {
        this.star = star;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm == null ? null : pm.trim();
    }

    public Date getSynTime() {
        return synTime;
    }

    public void setSynTime(Date synTime) {
        this.synTime = synTime;
    }

    public Long getSnmpAvailable() {
        return snmpAvailable;
    }

    public void setSnmpAvailable(Long snmpAvailable) {
        this.snmpAvailable = snmpAvailable;
    }

    public String getSnmpError() {
        return snmpError;
    }

    public void setSnmpError(String snmpError) {
        this.snmpError = snmpError == null ? null : snmpError.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getLastSynDeviceScanTime() {
        return lastSynDeviceScanTime;
    }

    public void setLastSynDeviceScanTime(Date lastSynDeviceScanTime) {
        this.lastSynDeviceScanTime = lastSynDeviceScanTime;
    }

    public Date getSnmpErrorsFrom() {
        return snmpErrorsFrom;
    }

    public void setSnmpErrorsFrom(Date snmpErrorsFrom) {
        this.snmpErrorsFrom = snmpErrorsFrom;
    }

    public Long getAlarmTempleteId() {
        return alarmTempleteId;
    }

    public void setAlarmTempleteId(Long alarmTempleteId) {
        this.alarmTempleteId = alarmTempleteId;
    }

    public Long getIcmpAvailable() {
        return icmpAvailable;
    }

    public void setIcmpAvailable(Long icmpAvailable) {
        this.icmpAvailable = icmpAvailable;
    }

    public String getIcmpItemId() {
        return icmpItemId;
    }

    public void setIcmpItemId(String icmpItemId) {
        this.icmpItemId = icmpItemId == null ? null : icmpItemId.trim();
    }

    public Long getSysuptimeAvailable() {
        return sysuptimeAvailable;
    }

    public void setSysuptimeAvailable(Long sysuptimeAvailable) {
        this.sysuptimeAvailable = sysuptimeAvailable;
    }

    public String getSysuptimeItemid() {
        return sysuptimeItemid;
    }

    public void setSysuptimeItemid(String sysuptimeItemid) {
        this.sysuptimeItemid = sysuptimeItemid == null ? null : sysuptimeItemid.trim();
    }

    public Long getFq() {
        return fq;
    }

    public void setFq(Long fq) {
        this.fq = fq;
    }

    public String getSnmpAvailablestr() {
        return snmpAvailablestr;
    }

    public void setSnmpAvailablestr(String snmpAvailablestr) {
        this.snmpAvailablestr = snmpAvailablestr == null ? null : snmpAvailablestr.trim();
    }

    public String getIcmpAvailablestr() {
        return icmpAvailablestr;
    }

    public void setIcmpAvailablestr(String icmpAvailablestr) {
        this.icmpAvailablestr = icmpAvailablestr == null ? null : icmpAvailablestr.trim();
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType == null ? null : checkType.trim();
    }

    public Date getIcmpChangeErrorTime() {
        return icmpChangeErrorTime;
    }

    public void setIcmpChangeErrorTime(Date icmpChangeErrorTime) {
        this.icmpChangeErrorTime = icmpChangeErrorTime;
    }

    public Date getIcmpLastClock() {
        return icmpLastClock;
    }

    public void setIcmpLastClock(Date icmpLastClock) {
        this.icmpLastClock = icmpLastClock;
    }

    public Date getSnmpLastClock() {
        return snmpLastClock;
    }

    public void setSnmpLastClock(Date snmpLastClock) {
        this.snmpLastClock = snmpLastClock;
    }

    public String getItemidSysdescr() {
        return itemidSysdescr;
    }

    public void setItemidSysdescr(String itemidSysdescr) {
        this.itemidSysdescr = itemidSysdescr == null ? null : itemidSysdescr.trim();
    }

    public Long getIpzyId2() {
        return ipzyId2;
    }

    public void setIpzyId2(Long ipzyId2) {
        this.ipzyId2 = ipzyId2;
    }

    public Long getScanDetailId() {
        return scanDetailId;
    }

    public void setScanDetailId(Long scanDetailId) {
        this.scanDetailId = scanDetailId;
    }

    public Date getScanDetailUpdatetime() {
        return scanDetailUpdatetime;
    }

    public void setScanDetailUpdatetime(Date scanDetailUpdatetime) {
        this.scanDetailUpdatetime = scanDetailUpdatetime;
    }
}