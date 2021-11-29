package com.fitranda.qurbanku.Model;

public class DPesanM {
    private String notransaksi,hewan,penjual,alamattujuan,gambar;
    private Integer idpelanggan,idpenjual,hargajual;

    public DPesanM(String notransaksi, String hewan, String penjual, String alamattujuan, String gambar, Integer idpelanggan, Integer idpenjual, Integer hargajual) {
        this.notransaksi = notransaksi;
        this.hewan = hewan;
        this.penjual = penjual;
        this.alamattujuan = alamattujuan;
        this.gambar = gambar;
        this.idpelanggan = idpelanggan;
        this.idpenjual = idpenjual;
        this.hargajual = hargajual;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getNotransaksi() {
        return notransaksi;
    }

    public void setNotransaksi(String notransaksi) {
        this.notransaksi = notransaksi;
    }

    public String getHewan() {
        return hewan;
    }

    public void setHewan(String hewan) {
        this.hewan = hewan;
    }

    public String getPenjual() {
        return penjual;
    }

    public void setPenjual(String penjual) {
        this.penjual = penjual;
    }

    public String getAlamattujuan() {
        return alamattujuan;
    }

    public void setAlamattujuan(String alamattujuan) {
        this.alamattujuan = alamattujuan;
    }

    public Integer getIdpelanggan() {
        return idpelanggan;
    }

    public void setIdpelanggan(Integer idpelanggan) {
        this.idpelanggan = idpelanggan;
    }

    public Integer getIdpenjual() {
        return idpenjual;
    }

    public void setIdpenjual(Integer idpenjual) {
        this.idpenjual = idpenjual;
    }

    public Integer getHargajual() {
        return hargajual;
    }

    public void setHargajual(Integer hargajual) {
        this.hargajual = hargajual;
    }
}
