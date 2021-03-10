
package com.company.DataBase;

public interface IKullaniciIslemleri {
     public void kullanicilariTabloyaYerlestir();
     public void kullaniciKontrolu(String alinanKullaniciAdi);
     public void kullaniciSilme(String kullaniciAdi);
     public void kullaniciGuncelle(String yeniSifre, String telefonNo);
     
     //Listeleme işlemi ortak olduğu için BasePerson sınıfının içinde abstract olarak oluşturulmuştur.
}
