package com.youotech.entity;

import java.math.BigDecimal;

public class SystemModel {
    private Long id;

    private String name;

    private BigDecimal del;

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

    public BigDecimal getDel() {
        return del;
    }

    public void setDel(BigDecimal del) {
        this.del = del;
    }

	@Override
	public String toString() {
		return "SystemModel [id=" + id + ", name=" + name + ", del=" + del + "]";
	}
    
}