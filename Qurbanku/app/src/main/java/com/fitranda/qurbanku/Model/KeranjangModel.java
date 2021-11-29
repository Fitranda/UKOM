package com.fitranda.qurbanku.Model;

public class KeranjangModel {
    private String hewan;
    private String harga;
    private String gambar;

    public KeranjangModel(String hewan, String harga, String gambar) {
        this.hewan = hewan;
        this.harga = harga;
        this.gambar = gambar;
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

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
