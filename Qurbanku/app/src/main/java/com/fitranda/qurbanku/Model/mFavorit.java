package com.fitranda.qurbanku.Model;

public class mFavorit {
    private String penjual;
    private String alamat;
    private String gambar;
    private Integer idpenjual;
    private String link;
    private String telp;
    private double ratings;
    private Integer like;
    private Integer idfavorit;

    public mFavorit(String penjual, String alamat, String gambar, Integer idpenjual, String link, String telp, double ratings, Integer like, Integer idfavorit) {
        this.penjual = penjual;
        this.alamat = alamat;
        this.gambar = gambar;
        this.idpenjual = idpenjual;
        this.link = link;
        this.telp = telp;
        this.ratings = ratings;
        this.like = like;
        this.idfavorit = idfavorit;
    }

    public Integer getIdfavorit() {
        return idfavorit;
    }

    public void setIdfavorit(Integer idfavorit) {
        this.idfavorit = idfavorit;
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

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }
}
