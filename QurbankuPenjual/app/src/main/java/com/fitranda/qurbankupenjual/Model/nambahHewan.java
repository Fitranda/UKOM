package com.fitranda.qurbankupenjual.Model;

public class nambahHewan {
    private String idhewan,hewan,rincian,gambar;
    private Integer idkategori,idpenjual,harga;

    public nambahHewan(String idhewan, String hewan, String rincian, String gambar, Integer idkategori, Integer idpenjual, Integer harga) {
        this.idhewan = idhewan;
        this.hewan = hewan;
        this.rincian = rincian;
        this.gambar = gambar;
        this.idkategori = idkategori;
        this.idpenjual = idpenjual;
        this.harga = harga;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getIdhewan() {
        return idhewan;
    }

    public void setIdhewan(String idhewan) {
        this.idhewan = idhewan;
    }

    public String getHewan() {
        return hewan;
    }

    public void setHewan(String hewan) {
        this.hewan = hewan;
    }

    public String getRincian() {
        return rincian;
    }

    public void setRincian(String rincian) {
        this.rincian = rincian;
    }

    public Integer getIdkategori() {
        return idkategori;
    }

    public void setIdkategori(Integer idkategori) {
        this.idkategori = idkategori;
    }

    public Integer getIdpenjual() {
        return idpenjual;
    }

    public void setIdpenjual(Integer idpenjual) {
        this.idpenjual = idpenjual;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }
}
