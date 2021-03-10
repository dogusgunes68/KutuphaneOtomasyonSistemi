package com.company.KitapModeli;

public class BaseKitap {
    
    private int id;
    private String adi;
    private String yazari;
    private String basimYeri;
    private String basimYili;
    private int adedi;
    private String turu;

    
    public BaseKitap(int id,String adi,
            String yazari,
            String basimYeri, 
            String basimYili, 
            int adedi,String turu) {
        
        this.id = id;
        this.adi = adi;
        this.yazari = yazari;
        this.basimYeri = basimYeri;
        this.basimYili = basimYili;
        this.adedi = adedi;
        this.turu = turu;
        

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getYazari() {
        return yazari;
    }

    public void setYazari(String yazari) {
        this.yazari = yazari;
    }

    public String getBasimYeri() {
        return basimYeri;
    }

    public void setBasimYeri(String basimYeri) {
        this.basimYeri = basimYeri;
    }

    public String getBasimYili() {
        return basimYili;
    }

    public void setBasimYili(String basimYili) {
        this.basimYili = basimYili;
    }

    public int getAdedi() {
        return adedi;
    }

    public void setAdedi(int adedi) {
        this.adedi = adedi;
    }

    public String getTuru() {
        return turu;
    }

    public void setTuru(String turu) {
        this.turu = turu;
    }

    @Override
    public String toString() {
        return "Kitabın, \n" +
                "Adı : "+adi+"\n"+
                "Yazarı : "+yazari+"\n"+
                "Basım Yeri : "+basimYeri+"\n"+
                "Basım Yılı : "+basimYili+"\n"+
                "Adedi : "+adedi+"\n"+
                "Türü : "+turu;
    }

}
