package com.fitranda.qurbanku.Model;

public class Notifikasi {
    private Integer idnotif;
    private String notif;
    private String tanggal;
    private String waktu;
    private String gambar;

    public Notifikasi(Integer idnotif, String notif, String tanggal, String waktu, String gambar) {
        this.idnotif = idnotif;
        this.notif = notif;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.gambar = gambar;
    }

    public Integer getIdnotif() {
        return idnotif;
    }

    public void setIdnotif(Integer idnotif) {
        this.idnotif = idnotif;
    }

    public String getNotif() {
        return notif;
    }

    public void setNotif(String notif) {
        this.notif = notif;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
