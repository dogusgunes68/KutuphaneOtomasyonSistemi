
package com.company.DataBase;

public interface IKitapIslemleri {
    public void kitaplariTabloyaYerlestir();
     public void kitaplariTabloyaYerlestir2();
      public void kitapKontrolu(String kitapIsmi, String yazar);
      public void kitapGuncelle(String kitapIsmi,String yazar); //pol. kullanıldı
      public void kitapGuncelle(String kitapIsmi);
      public void kitapGuncellePanel();
      public void KitapSilme(String isim);
      
      //Listeleme işlemi ortak olduğu için BasePerson sınıfının içinde abstract olarak oluşturulmuştur.
}
