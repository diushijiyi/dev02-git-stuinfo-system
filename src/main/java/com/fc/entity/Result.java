package com.fc.entity;

public class Result {
    private Integer resId;

    private String stuId;

    private String subName;

    private Integer resNum;

    private String resTerm;

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId == null ? null : stuId.trim();
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName == null ? null : subName.trim();
    }

    public Integer getResNum() {
        return resNum;
    }

    public void setResNum(Integer resNum) {
        this.resNum = resNum;
    }

    public String getResTerm() {
        return resTerm;
    }

    public void setResTerm(String resTerm) {
        this.resTerm = resTerm == null ? null : resTerm.trim();
    }
}