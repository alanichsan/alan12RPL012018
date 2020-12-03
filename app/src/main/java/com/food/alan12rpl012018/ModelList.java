package com.food.alan12rpl012018;

public class ModelList {
    private int profile;

    private String code;
    private String jenis;
    private String merk;
    private String hargasewa;
    private String warna;
    private String id;


    public ModelList(String hargasewa, String jenis, String merk, String code, String warna) {
        this.hargasewa = hargasewa;
        this.merk = merk;
        this.jenis = jenis;
        this.code = code;
        this.warna = warna;

    }
    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public String getHargasewa() {
        return hargasewa;
    }

    public void setHargasewa(String hargasewa) {
        this.hargasewa = hargasewa;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
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
    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
