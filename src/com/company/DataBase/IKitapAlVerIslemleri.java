
package com.company.DataBase;

public interface IKitapAlVerIslemleri {
   public void comboboxlariDoldur();
   public void oduncVerilenKitaplariTabloyaYerlestir();
   public void kitapGuncelleArttir(String kitapIsmi);
   public void oduncVerilenKitapSilme(String kitapIsmi); // ödünç verileni geri alan metot
   public void kitapOduncVer(String kitapIsmi,String alanKullanici,String verenYonetici,String tarih,String kullaniciTelefonNo);
   public void kitapAlVerGuncelle();
   //Listeleme işlemi ortak olduğu için BasePerson sınıfının içinde abstract olarak oluşturulmuştur.
}
