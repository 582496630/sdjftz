package com.youotech.entity;

public class CjModel {
    private Long id;

    private String name;

    private Long typesetId;

    private Long del;

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

    public Long getTypesetId() {
        return typesetId;
    }

    public void setTypesetId(Long typesetId) {
        this.typesetId = typesetId;
    }

    public Long getDel() {
        return del;
    }

    public void setDel(Long del) {
        this.del = del;
    }
}