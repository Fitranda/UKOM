package com.fitranda.qurbankupenjual.Model;

public class PenjualM {
    private Integer idpenjual,status;
    private String penjual,telp,gambar,email,password,alamat,pesan,link;

    public PenjualM(Integer idpenjual, Integer status, String penjual, String telp, String gambar, String email, String password, String alamat, String pesan, String link) {
        this.idpenjual = idpenjual;
        this.status = status;
        this.penjual = penjual;
        this.telp = telp;
        this.gambar = gambar;
        this.email = email;
        this.password = password;
        this.alamat = alamat;
        this.pesan = pesan;
        this.link = link;
    }

    public Integer getIdpenjual() {
        return idpenjual;
    }

    public void setIdpenjual(Integer idpenjual) {
        this.idpenjual = idpenjual;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPenjual() {
        return penjual;
    }

    public void setPenjual(String penjual) {
        this.penjual = penjual;
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

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
