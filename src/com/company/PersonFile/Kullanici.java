/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.PersonFile;

public class Kullanici extends BasePerson{

    private String kullaniciAdi;
    private String telefonno;

    public String getTelefonno() {
        return telefonno;
    }

    public void setTelefonno(String telefonno) {
        this.telefonno = telefonno;
    }
    public Kullanici(int id,String isim,String kullaniciAdi, String sifre,String telefonno) {
        super(id,isim, sifre);
        this.kullaniciAdi = kullaniciAdi;
        this.telefonno=telefonno;
        
    }

   

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    @Override
    public void kitapListele() {

    }

  
}

