package com.fc.entity;

public class Teacher {
    private String teaId;

    private String teaName;

    private String teaPass;

    private String teaSex;

    private String teaTele;

    public String getTeaId() {
        return teaId;
    }

    public void setTeaId(String teaId) {
        this.teaId = teaId == null ? null : teaId.trim();
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName == null ? null : teaName.trim();
    }

    public String getTeaPass() {
        return teaPass;
    }

    public void setTeaPass(String teaPass) {
        this.teaPass = teaPass == null ? null : teaPass.trim();
    }

    public String getTeaSex() {
        return teaSex;
    }

    public void setTeaSex(String teaSex) {
        this.teaSex = teaSex == null ? null : teaSex.trim();
    }

    public String getTeaTele() {
        return teaTele;
    }

    public void setTeaTele(String teaTele) {
        this.teaTele = teaTele == null ? null : teaTele.trim();
    }
}