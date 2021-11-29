package com.fitranda.qurbanku.Model;

public class Hewan {
    private String hewan;
    private Double harga;
    private String penjual;
    private String alamat;
    private String gambar;
    private String rincian;
    private String idhewan;
    private Double ratings;
    private Integer idpenjual;

    public Hewan(String hewan, Double harga, String penjual, String alamat, String gambar, String rincian, String idhewan, Double ratings, Integer idpenjual) {
        this.hewan = hewan;
        this.harga = harga;
        this.penjual = penjual;
        this.alamat = alamat;
        this.gambar = gambar;
        this.rincian = rincian;
        this.idhewan = idhewan;
        this.ratings = ratings;
        this.idpenjual = idpenjual;
    }

    public String getIdhewan() {
        return idhewan;
    }

    public void setIdhewan(String idhewan) {
        this.idhewan = idhewan;
    }

    public Integer getIdpenjual() {
        return idpenjual;
    }

    public void setIdpenjual(Integer idpenjual) {
        this.idpenjual = idpenjual;
    }

    public String getRincian() {
        return rincian;
    }

    public void setRincian(String rincian) {
        this.rincian = rincian;
    }

    public Double getRatings() {
        return ratings;
    }

    public void setRatings(Double ratings) {
        this.ratings = ratings;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getHewan() {
        return hewan;
    }

    public void setHewan(String hewan) {
        this.hewan = hewan;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
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
}
