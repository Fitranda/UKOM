package com.fitranda.qurbanku.Model;

import com.google.gson.annotations.SerializedName;

public class PostDelHewan {
    @SerializedName("pesan")
    private String pesan;
    @SerializedName("data")
    ModelKewan mkewan;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public ModelKewan getMkewan() {
        return mkewan;
    }

    public void setMkewan(ModelKewan mkewan) {
        this.mkewan = mkewan;
    }
}
