package com.youotech.entity;

import java.math.BigDecimal;

public class TypesetModel {
    private Long id;

    private String name;

    private String imgSrc;

    private BigDecimal type;

    private BigDecimal del;

    private BigDecimal isChild;

    private String dw;

    private BigDecimal classify;

    private String notifyType;

    private BigDecimal notifyInv;

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

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc == null ? null : imgSrc.trim();
    }

    public BigDecimal getType() {
        return type;
    }

    public void setType(BigDecimal type) {
        this.type = type;
    }

    public BigDecimal getDel() {
        return del;
    }

    public void setDel(BigDecimal del) {
        this.del = del;
    }

    public BigDecimal getIsChild() {
        return isChild;
    }

    public void setIsChild(BigDecimal isChild) {
        this.isChild = isChild;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw == null ? null : dw.trim();
    }

    public BigDecimal getClassify() {
        return classify;
    }

    public void setClassify(BigDecimal classify) {
        this.classify = classify;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType == null ? null : notifyType.trim();
    }

    public BigDecimal getNotifyInv() {
        return notifyInv;
    }

    public void setNotifyInv(BigDecimal notifyInv) {
        this.notifyInv = notifyInv;
    }
}