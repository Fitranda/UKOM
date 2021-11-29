package com.fitranda.qurbanku.Model;

public class Komens {
    private String pelanggan;
    private double rating;
    private String komen;
    private String tgl;
    private String gambar;

    public Komens(String pelanggan, double rating, String komen, String tgl, String gambar) {
        this.pelanggan = pelanggan;
        this.rating = rating;
        this.komen = komen;
        this.tgl = tgl;
        this.gambar = gambar;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(String pelanggan) {
        this.pelanggan = pelanggan;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getKomen() {
        return komen;
    }

    public void setKomen(String komen) {
        this.komen = komen;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }
}
