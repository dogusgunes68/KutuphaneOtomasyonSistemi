/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.OduncVerilenKitapModeli;

public class OduncVerilenKitap {
    
    private int id;
    private String kitapIsmi;
    private String alanKullanici;
    private String verenYonetici;
    private String tarih;
    private String kullaniciTelefonNo;

    public OduncVerilenKitap(int id, String kitapIsmi, String alanKullanici,String verenYonetici, String tarih, String kullaniciTelefonNo) {
        this.id = id;
        this.kitapIsmi = kitapIsmi;
        this.alanKullanici = alanKullanici;
        this.tarih = tarih;
        this.verenYonetici = verenYonetici;
        this.kullaniciTelefonNo = kullaniciTelefonNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKitapIsmi() {
        return kitapIsmi;
    }

    public void setKitapIsmi(String kitapIsmi) {
        this.kitapIsmi = kitapIsmi;
    }

    public String getAlanKullanici() {
        return alanKullanici;
    }

    public void setAlanKullanici(String alanKullanici) {
        this.alanKullanici = alanKullanici;
    }

    public String getVerenYonetici() {
        return verenYonetici;
    }

    public void setVerenYonetici(String verenYonetici) {
        this.verenYonetici = verenYonetici;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getKullaniciTelefonNo() {
        return kullaniciTelefonNo;
    }

    public void setKullaniciTelefonNo(String kullaniciTelefonNo) {
        this.kullaniciTelefonNo = kullaniciTelefonNo;
    }
    
    
    
}
