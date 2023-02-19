package com.example.loginbtl;

import java.io.Serializable;

public class MonAn implements Serializable {
    int ID;
    byte[] hinhAnh;
    String tenMonAn;
    String congThuc;

    public MonAn(int ID, byte[] hinhAnh, String tenMonAn, String congThuc) {
        this.ID = ID;
        this.hinhAnh = hinhAnh;
        this.tenMonAn = tenMonAn;
        this.congThuc = congThuc;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public String getCongThuc() {
        return congThuc;
    }

    public void setCongThuc(String congThuc) {
        this.congThuc = congThuc;
    }
}
