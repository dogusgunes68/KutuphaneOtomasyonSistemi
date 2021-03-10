/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.PersonFile;

abstract class BasePerson {
    private String isim;
    private String sifre;
    private int id;

    public BasePerson(int id,String isim,String şifre) {
        this.id = id;
        this.isim=isim;
        this.sifre=şifre;
    }

    public abstract void kitapListele();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String şifre) {
        this.sifre = şifre;
    }
}
