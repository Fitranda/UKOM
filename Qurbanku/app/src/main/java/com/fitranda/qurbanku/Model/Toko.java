package com.fitranda.qurbanku.Model;

public class Toko {

    private String penjual;
    private String alamat;
    private String gambar;
    private Integer idpenjual;
    private String link;
    private String telp;
    private double ratings;

    public Toko(String penjual, String alamat, String gambar, Integer idpenjual, String link, String telp, double ratings) {
        this.penjual = penjual;
        this.alamat = alamat;
        this.gambar = gambar;
        this.idpenjual = idpenjual;
        this.link = link;
        this.telp = telp;
        this.ratings = ratings;
    }

    public Integer getIdpenjual() {
        return idpenjual;
    }

    public void setIdpenjual(Integer idpenjual) {
        this.idpenjual = idpenjual;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public String getPenjual() {
        return penjual;
    }

    public void setPenjual(String penjual) {
        this.penjual = penjual;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
