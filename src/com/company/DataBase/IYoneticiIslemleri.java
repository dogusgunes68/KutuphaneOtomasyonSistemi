
package com.company.DataBase;

public interface IYoneticiIslemleri {
    public void YoneticileriTabloyaYerlestir();
    public void yoneticiKontrolu(String isim ,String yoneticiIsmi);
    public void yoneticiSilme(String yoneticiAdi);
    public void yoneticiGuncelle();
    //Listeleme işlemi ortak olduğu için BasePerson sınıfının içinde abstract olarak oluşturulmuştur.
}
