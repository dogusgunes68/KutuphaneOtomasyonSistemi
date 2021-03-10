/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.PersonFile;

public class Yonetici extends BasePerson{
    final static String baseAdminIsim="admin";

    public static String getBaseAdminIsim() {
        return baseAdminIsim;
    }

    public static String getBaseAdminSifre() {
        return baseAdminSifre;
    }
    final static String baseAdminSifre="admin123";
    private String yoneticiAdi;

    public Yonetici(int id,String isim,String yoneticiAdi, String şifre) {
        super(id,isim, şifre);
        this.yoneticiAdi = yoneticiAdi;
    }

    public String getYoneticiAdi() {
        return yoneticiAdi;
    }

    public void setYoneticiAdi(String yoneticiAdi) {
        this.yoneticiAdi = yoneticiAdi;
    }


    @Override
    public void kitapListele() {

    }
    
}


