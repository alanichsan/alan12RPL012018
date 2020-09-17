package com.food.alan12rpl012018;

public class ModelList {
    private String code;
    private String jenis;
    private String jumlah;
    private String nama;

    public ModelList(String nama, String jenis, String jumlah, String code) {
        this.nama = nama;
        this.jumlah = jumlah;
        this.jenis = jenis;
        this.code = code;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
