package com.fitranda.qurbanku.Model;

import java.util.List;

public class PelangganM {
    private Integer idpelanggan,status;
    private String pelanggan,telp,gambar,email,password,alamat,ttl,pesan;
//    List<PelangganM> data;

    public PelangganM(Integer idpelanggan, Integer status, String pelanggan, String telp, String gambar, String email, String password, String alamat, String ttl, String pesan) {
        this.idpelanggan = idpelanggan;
        this.status = status;
        this.pelanggan = pelanggan;
        this.telp = telp;
        this.gambar = gambar;
        this.email = email;
        this.password = password;
        this.alamat = alamat;
        this.ttl = ttl;
        this.pesan = pesan;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public Integer getIdpelanggan() {
        return idpelanggan;
    }

    public void setIdpelanggan(Integer idpelanggan) {
        this.idpelanggan = idpelanggan;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(String pelanggan) {
        this.pelanggan = pelanggan;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }
}
