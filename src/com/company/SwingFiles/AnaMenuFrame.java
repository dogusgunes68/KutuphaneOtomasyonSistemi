
package com.company.SwingFiles;

import com.company.SwingFiles.*;
import com.company.DataBase.DbHelper;
import com.company.DataBase.*;
import com.company.OduncVerilenKitapModeli.*;
import com.company.KitapModeli.BaseKitap;
import com.company.PersonFile.Kullanici;
import com.company.PersonFile.Yonetici;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.RowFilter;
import javax.swing.SpinnerListModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class AnaMenuFrame extends javax.swing.JFrame 
        implements IYoneticiIslemleri,
        IKullaniciIslemleri,
        IKitapAlVerIslemleri,
        IKitapIslemleri{
       
     DefaultTableModel modelKitaplar;
     DefaultTableModel modelKullanicilar;
     DefaultTableModel modelY;
     DefaultTableModel modelOduncVerilenKitaplar;
     String yoneticiAdi = "";
     
     //final String baseAdminIsim="admin";
     //final String baseAdminSifre="admin123";
    public AnaMenuFrame(Point location,Boolean yoneticiMi,String gelenYoneticiAdi,String gelenKullaniciAdi)  throws java.sql.SQLException {
       
          initComponents();
         
        yoneticiAdi = gelenYoneticiAdi;  
        setSize(1000, 700);
        setTitle("İşlemler");
        Point alinanLocation = location;
        setLocation(alinanLocation);
        pnl1.setBackground(Color.black);
        pnl1.setForeground(Color.WHITE);
        
        String Calibri = null;
        pnl1.setFont(new FontImpl(Calibri,Font.BOLD,12));
        if(yoneticiMi!=true){
             this.pnl1.remove(pnlKitapEkle);
            this.pnl1.remove(pnlKitapOduncVer);
            this.pnl1.remove(pnlKitapGuncelle);
            this.pnl1.remove(pnlKullaniciEkle); 
            this.pnl1.remove(pnlKullaniciListele); 
            this.pnl1.remove(pnlYoneticiEkle); 
            this.pnl1.remove(pnlYoneticiListele);
            this.pnl1.remove(jPanel4);
            this.pnl1.remove(jPanel2);
            this.pnl1.remove(pnl1);
            this.pnl1.remove(jPanel7);
            this.pnl1.remove(jPanel8);
            this.pnl1.remove(jPanel9);
            lblHesapIsim.setText(kullaniciAdi);
            
            pnl1.setFont(new FontImpl(Calibri,Font.BOLD,15)); 
            lblHesapIsim.setText(gelenKullaniciAdi);
            
        }else{
            lblHesapIsim.setText(yoneticiAdi);
        }
       
        

        kitaplariTabloyaYerlestir();
        kullanicilariTabloyaYerlestir();
        YoneticileriTabloyaYerlestir();
        kitaplariTabloyaYerlestir2();
        oduncVerilenKitaplariTabloyaYerlestir(); 
        comboboxlariDoldur();
        
        
    }
    
    
    @Override
    public void comboboxlariDoldur(){
         try {
             
             ArrayList<BaseKitap> kitaplar = null;
             try {
                 kitaplar = kitaplariAl();
             } catch (SQLException ex) {
                 Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
             }
             String[] kitapIsimleri = new String[kitaplar.size()];
             
                 for(int i=0;i<kitaplar.size();i++){
                     kitapIsimleri[i] = kitaplar.get(i).getAdi();   
                 }
                 
                 cmbKitaplar.setModel(new DefaultComboBoxModel(kitapIsimleri));
                 
             ArrayList<Kullanici> kullanicilar;
             try {
                 kullanicilar = kullanicilariAl();
                 String[] kullaniciIsimleri = new String[kullanicilar.size()];
                 for(int i=0;i<kullanicilar.size();i++){
                     kullaniciIsimleri[i] = kullanicilar.get(i).getKullaniciAdi();   
                 }
                 cmbKullanicilar.setModel(new DefaultComboBoxModel(kullaniciIsimleri));
                 txtTelefonNo.setText(kullanicilar.get(0).getTelefonno());
             } catch (SQLException ex) {
                 Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
             }
              
         } catch (IOException ex) {
             Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
       
        
    }
    @Override
     public void kitaplariTabloyaYerlestir(){
        modelKitaplar = (DefaultTableModel)tblBooks.getModel();
        modelKitaplar.setRowCount(0);
        try {

            ArrayList<BaseKitap> alinanKitaplar = kitaplariAl();
        
            for(BaseKitap kitap : alinanKitaplar){
             
                Object[] row = {kitap.getId(),kitap.getAdi().trim(),kitap.getYazari().trim(),kitap.getBasimYeri().trim(),
                        kitap.getBasimYili().trim(),kitap.getAdedi(),kitap.getTuru().trim()};
                modelKitaplar.addRow(row);
              
            }
        } catch (SQLException exception) {
           exception.printStackTrace();
        } catch (IOException ex) {
             Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
         }

    }
     
     @Override
     public void kitaplariTabloyaYerlestir2(){
        modelKitaplar = (DefaultTableModel)tblKitapSil.getModel();
        modelKitaplar.setRowCount(0);
        try {

            ArrayList<BaseKitap> alinanKitaplar = kitaplariAl();
        
            for(BaseKitap kitap : alinanKitaplar){
             
                Object[] row = {kitap.getId(),kitap.getAdi().trim(),kitap.getYazari().trim(),kitap.getBasimYeri().trim(),
                        kitap.getBasimYili().trim(),kitap.getAdedi(),kitap.getTuru().trim()};
                modelKitaplar.addRow(row);
              
            }
        } catch (SQLException exception) {
           exception.printStackTrace();
        } catch (IOException ex) {
             Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
         }

    }
         
     @Override
     public void kullanicilariTabloyaYerlestir(){
        modelKullanicilar = (DefaultTableModel)tblKullanicilar.getModel();
        modelKullanicilar.setRowCount(0);
        try {

            ArrayList<Kullanici> alinanKullanicilar = kullanicilariAl();
        
            for(Kullanici kullanici : alinanKullanicilar){
             
                Object[] row = {kullanici.getIsim().trim(),kullanici.getKullaniciAdi().trim(),kullanici.getTelefonno().trim()};
                modelKullanicilar.addRow(row);
              
            }
        } catch (SQLException exception) {
           exception.printStackTrace();
        } catch (IOException ex) {
             Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
         }

    }
     
     @Override
      public void oduncVerilenKitaplariTabloyaYerlestir(){
        modelOduncVerilenKitaplar = (DefaultTableModel)tblKitapIadeAl.getModel();
        modelOduncVerilenKitaplar.setRowCount(0);
        try {

            ArrayList<OduncVerilenKitap> alinanKitaplar = oduncVerilenKitaplariAl();
        
            for(OduncVerilenKitap kitap : alinanKitaplar){
             
                Object[] row = {kitap.getKitapIsmi(),kitap.getAlanKullanici(),kitap.getVerenYonetici(),
                        kitap.getTarih(),kitap.getKullaniciTelefonNo()};
                modelOduncVerilenKitaplar.addRow(row);
              
            }
        } catch (SQLException exception) {
           exception.printStackTrace();
        } catch (IOException ex) {
             Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
         }

    }
      
      public ArrayList<OduncVerilenKitap> oduncVerilenKitaplariAl() throws SQLException, java.sql.SQLException, IOException {
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        Statement statement = null;
        ResultSet resultSet;
        ArrayList<OduncVerilenKitap> oduncVerilenKitaplar = null;
        
           try{
            connection = (Connection) dbHelper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from oduncverilenkitaplar");
            
            oduncVerilenKitaplar = new ArrayList<>();
            
            while(resultSet.next()){
                OduncVerilenKitap oduncVerilenKitap = new OduncVerilenKitap(
                        resultSet.getInt("id"),
                        resultSet.getString("kitapismi").toString(),
                        resultSet.getString("alankullanici").toString(),
                        resultSet.getString("verenyonetici").toString(),
                        resultSet.getString("tarih").toString(),
                        resultSet.getString("kullanicitelefonno").toString()
                        
                );
                
                oduncVerilenKitaplar.add(oduncVerilenKitap);
            }
            
           }catch(java.sql.SQLException exception){
               dbHelper.showErrorMessage(exception);
           }finally{
              statement.close();
              connection.close();
           }
           
           return oduncVerilenKitaplar;
        
    }
      
      @Override
     public void kitapKontrolu(String kitapIsmi, String yazar){
        modelKitaplar = (DefaultTableModel)tblBooks.getModel();
        modelKitaplar.setRowCount(0);
        boolean kitapKayitliMi = false;
        try {

            ArrayList<BaseKitap> alinanKitaplar = kitaplariAl();
           
            for(BaseKitap kitap : alinanKitaplar){
             
                if(kitap.getAdi().trim().equals(kitapIsmi) && kitap.getYazari().trim().equals(yazar)){
                    
                    kitapGuncelle(kitapIsmi, yazar);
                    kitapKayitliMi = true;
                    break;
                }
               
            }
      if(kitapKayitliMi == false)  {
          Connection connection = null;
      DbHelper helper = new DbHelper();
      PreparedStatement statement = null;
     
      String isim = txtIsim.getText();
      String yazari = txtYazar.getText();
      String basimYeri = txtBasimYeri.getText();
      String basimYili = txtBasimYili.getText();
      int adedi = 0;
      boolean hata = false;
      
      try{
          adedi = Integer.parseInt(txtAdedi.getText());
      }catch(NumberFormatException e){
          lblEkleMessage.setText("Lütfen tam sayi değeri giriniz.");
          hata = true;
      }
      
      String turu = txtTuru.getText();

      if(!hata){
           if(isim.equals("") || yazari.equals("") || basimYeri.equals("") || basimYili.equals("") || adedi == 0){
               lblEkleMessage.setText("Alanlar boş bırakılamaz");
           }else{
               try{
          connection = helper.getConnection();
          String sql = "insert into kitaplar (isim,yazar,basimyeri,basimyili,adedi,turu) values(?,?,?,?,?,?)";
          statement = connection.prepareStatement(sql);
          statement.setString(1, isim);
          statement.setString(2, yazari);
          statement.setString(3, basimYeri);
          statement.setString(4,basimYili);
          statement.setInt(5, adedi);
          statement.setString(6, turu);
          int result = statement.executeUpdate();
          lblEkleMessage.setText("Kayıt Başarılı");
          kitaplariTabloyaYerlestir();
          
          
      }catch(SQLException exception){
          helper.showErrorMessage(exception);
      }finally{
          try {
              statement.close(); 
              connection.close();
          } catch (SQLException ex) {
             lblEkleMessage.setText(ex.getMessage());
          }
    }  
           }
                                                
   }
      }    
       
        } catch (SQLException exception) {
           exception.printStackTrace();
        } catch (IOException ex) {
             Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
         }

       System.out.print(kitapKayitliMi);
    }
     
     @Override
     public void kullaniciKontrolu(String alinanKullaniciAdi){
        modelKullanicilar = (DefaultTableModel)tblBooks.getModel();
        modelKullanicilar.setRowCount(0);
        boolean kullaniciKayitliMi = false;
        try {

            ArrayList<Kullanici> alinanKullanicilar = kullanicilariAl();
           
            for(Kullanici kullanici : alinanKullanicilar){
             
                if(kullanici.getKullaniciAdi().trim().equals(alinanKullaniciAdi)){
                    kullaniciKayitliMi = true;
                    lblMessage3.setText("Kullanıcı adı kullanılmış! Farklı bir kullanıcı adı giriniz.");
                    break;
                }
               
            }
      if(kullaniciKayitliMi == false)  {
          Connection connection = null;
      DbHelper helper = new DbHelper();
      PreparedStatement statement = null;
     

    String kullaniciIsmi = txtKullaniciIsmi.getText();
    String kullaniciAdi = txtKullaniciAdi.getText().trim();
    String sifre = txtKullaniciSifre.getText().toString();
    String telefonno = txtKullaniciTelefonno.getText().toString();
           
    if(kullaniciIsmi.equals("") || kullaniciAdi.equals("") || sifre.equals("")){
        lblMessage3.setText("Alanlar boş bırakılamaz");
    }else{
        try{
          connection = helper.getConnection();
          String sql = "insert into kullanicilar (isim,kullaniciadi,sifre,telefonno) values(?,?,?,?)";
          statement = connection.prepareStatement(sql);
          statement.setString(1, kullaniciIsmi);
          statement.setString(2,kullaniciAdi );
          statement.setString(3, sifre);
          statement.setString(4, telefonno);
          int result = statement.executeUpdate();
          kullanicilariTabloyaYerlestir();
          lblMessage3.setText("Kayıt Başarılı");
          comboboxlariDoldur();
      }catch(SQLException exception){
          helper.showErrorMessage(exception);
      }finally{
             try {
                 statement.close(); 
                 connection.close();
             } catch (SQLException ex) {
                lblMessage3.setText(ex.getMessage());
             }
          }
    }
                                                  
      }    
       
        } catch (SQLException exception) {
           exception.printStackTrace();
        } catch (IOException ex) {
             Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
         }

       
    }

    private AnaMenuFrame() {
         //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void kitapGuncelle(String kitapIsmi,String yazar){
        Connection connection = null;
      DbHelper helper = new DbHelper();
      PreparedStatement statement = null;
        
           try{
          connection = helper.getConnection();
          String sql = "update kitaplar set adedi = adedi+1 where isim = ? && yazar = ?";
          statement = connection.prepareStatement(sql);
          statement.setString(1, kitapIsmi);
          statement.setString(2, yazar);
          
          int result = statement.executeUpdate();
          kitaplariTabloyaYerlestir();
          
          lblEkleMessage.setText("Ekleme Başarılı");
      }catch(SQLException exception){
          helper.showErrorMessage(exception);
      }finally{
          try {
              statement.close(); 
              connection.close();
          } catch (SQLException ex) {
             lblEkleMessage.setText(ex.getMessage());
          }
    } 
        
    }
    
    @Override
    public void kitapGuncelle(String kitapIsmi){ 
        // iki adet kitapGuncelle var polymorphism kullanıldı.
        Connection connection = null;
       DbHelper helper = new DbHelper();
       PreparedStatement statement = null;
        
           try{
          connection = helper.getConnection();
          String sql = "update kitaplar set adedi = adedi-1 where isim = ?";
          statement = connection.prepareStatement(sql);
          statement.setString(1, kitapIsmi);
          
          int result = statement.executeUpdate();
          kitaplariTabloyaYerlestir();
          
      }catch(SQLException exception){
          helper.showErrorMessage(exception);
      }finally{
          try {
              statement.close(); 
              connection.close();
          } catch (SQLException ex) {
             
          }
    } 
        
    }
    
    @Override
    public void kitapGuncelleArttir(String kitapIsmi){
        Connection connection = null;
      DbHelper helper = new DbHelper();
      PreparedStatement statement = null;
        
           try{
          connection = helper.getConnection();
          String sql = "update kitaplar set adedi = adedi+1 where isim = ?";
          statement = connection.prepareStatement(sql);
          statement.setString(1, kitapIsmi);
          
          int result = statement.executeUpdate();
          
      }catch(SQLException exception){
          helper.showErrorMessage(exception);
      }finally{
          try {
              statement.close(); 
              connection.close();
          } catch (SQLException ex) {
             
          }
    } 
        
    }
   
    
    public ArrayList<BaseKitap> kitaplariAl() throws SQLException, java.sql.SQLException, IOException {
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        Statement statement = null;
        ResultSet resultSet;
        ArrayList<BaseKitap> kitaplar = null;
        
           try{
            connection = (Connection) dbHelper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from kitaplar");
            
            kitaplar = new ArrayList<>();
            
            while(resultSet.next()){
                BaseKitap kitap = new BaseKitap(
                        resultSet.getInt("id"),
                        resultSet.getString("isim").toString(),
                        resultSet.getString("yazar").toString(),
                        resultSet.getString("basimyeri").toString(),
                        resultSet.getString("basimyili").toString(),
                        resultSet.getInt("adedi"),
                        resultSet.getString("turu").toString()
                );
                
                kitaplar.add(kitap);
            }
            
           }catch(java.sql.SQLException exception){
               dbHelper.showErrorMessage(exception);
           }finally{
              statement.close();
              connection.close();
           }
           
           return kitaplar;
        
    }
    
    @Override
    public void kitapGuncellePanel(){
         Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null;
        
        String mevcutKitapAdi = txtKitapGuncelleAdi.getText();
        String mevcutYazarAdi = txtKitapGuncelleYazari.getText();
        String yeniAdet = txtKitapGuncelleAdedi.getText();
        
        
           try{
               ArrayList<BaseKitap> kitaplar = kitaplariAl();
        
               for(BaseKitap kitap : kitaplar){
            
                   if(mevcutKitapAdi.equals(kitap.getAdi()) ){
                       if(mevcutYazarAdi.equals(kitap.getYazari())){
                           connection = (Connection) dbHelper.getConnection();
                           String sql = "update kitaplar set adedi = ? where isim = ?";
                           statement = connection.prepareStatement(sql);
     
                           statement.setString(1, yeniAdet);
                           statement.setString(2, mevcutKitapAdi);
                           kitaplariTabloyaYerlestir();
         
                           int result = statement.executeUpdate();
                           kitaplariTabloyaYerlestir();
                           kitaplariTabloyaYerlestir2();
    
                           lblKitapGuncelleMessage.setText("Kitap Güncellendi");
                           
                       }else{
                           lblKitapGuncelleMessage.setText("Yazar Adı Hatalı");
                           
                       }  
                      
                   }else{
                       lblMessage5.setText("Kitap mevcut değil");
                   }
                   
               }
  
            
           }catch(java.sql.SQLException exception){
               dbHelper.showErrorMessage(exception);
           } catch (IOException ex) {
             Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
         }finally{
             try {
                 statement.close();
                 connection.close();
             } catch (SQLException ex) {
                 Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
             }
              
           }
           
           
    }

        public ArrayList<BaseKitap> kitaplariAl(String kitapIsmi) throws SQLException, java.sql.SQLException, IOException {
        
            Connection connection = null;
            DbHelper dbHelper = new DbHelper();
            Statement statement = null;
            ResultSet resultSet;
            ArrayList<BaseKitap> kitaplar = null;
        
           try{
            connection = (Connection) dbHelper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from kitaplar");
            
            kitaplar = new ArrayList<>();
            
            while(resultSet.next()){
                BaseKitap kitap = new BaseKitap(
                        resultSet.getInt("id"),
                        resultSet.getString("isim").toString(),
                        resultSet.getString("yazar").toString(),
                        resultSet.getString("basimyeri").toString(),
                        resultSet.getString("basimyili").toString(),
                        resultSet.getInt("adedi"),
                        resultSet.getString("turu").toString()
                );
                
                kitaplar.add(kitap);
            }
            
           }catch(java.sql.SQLException exception){
               dbHelper.showErrorMessage(exception);
           }finally{
              statement.close();
              connection.close();
           }
           
           return kitaplar;
    }
        
        
    public ArrayList<Kullanici> kullanicilariAl() throws SQLException, java.sql.SQLException, IOException {
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        Statement statement = null;
        ResultSet resultSet;
        ArrayList<Kullanici> kullanicilar = null;
        
           try{
            connection = (Connection) dbHelper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from kullanicilar");
            
            kullanicilar = new ArrayList<>();
            
            while(resultSet.next()){
                Kullanici kullanici = new Kullanici(
                        resultSet.getInt("id"),
                        resultSet.getString("isim").toString(),
                        resultSet.getString("kullaniciadi").toString(),
                        resultSet.getString("sifre").toString(),
                        resultSet.getString("telefonno").toString()
                       
                );
                
                kullanicilar.add(kullanici);
            }
            
           }catch(java.sql.SQLException exception){
               dbHelper.showErrorMessage(exception);
           }finally{
              statement.close();
              connection.close();
           }
           
           return kullanicilar;
        
    }
    
    
    @Override
    public void kullaniciSilme(String kullaniciAdi){
         Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null;
        
           try{
            connection = (Connection) dbHelper.getConnection();
            String sql = "delete from kullanicilar where kullaniciadi = ?";
            statement = connection.prepareStatement(sql);
     
          statement.setString(1, kullaniciAdi);
            kullanicilariTabloyaYerlestir();
         
            int result = statement.executeUpdate();
            lblMessage4.setText("Silme işlemi başarılı");
           }catch(java.sql.SQLException exception){
               dbHelper.showErrorMessage(exception);
           }finally{
             try {
                 statement.close();
                 connection.close();
             } catch (SQLException ex) {
                 Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
             }
              
           }
           
           
    }
    

    @Override
    public void kullaniciGuncelle(String yeniSifre, String telefonNo) {
       
         Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null;
        
        boolean hataVarMi = true;
        
        String mevcutKullaniciAdi = txtKullaniciAdiGuncelleme.getText().toString();
        String eskiSifre = txtKullaniciEskiSifre.getText();
        
           try{
               ArrayList<Kullanici> kullanicilar = kullanicilariAl();
        
               for(Kullanici kullanici : kullanicilar){
            
                   if(mevcutKullaniciAdi.equals(kullanici.getKullaniciAdi())){
                       if(eskiSifre.equals(kullanici.getSifre())){
                           connection = (Connection) dbHelper.getConnection();
                            String sql = "";
                           if(!yeniSifre.equals("") && !telefonNo.equals("")){
                              sql = "update kullanicilar set sifre = ?,telefonno = ? where kullaniciadi = ?";
                              statement = connection.prepareStatement(sql);
     
                              statement.setString(1, yeniSifre);
                              statement.setString(2, telefonNo);
                              statement.setString(3, mevcutKullaniciAdi);
                              
                              int result = statement.executeUpdate();
                              kullanicilariTabloyaYerlestir();
                              lblMessage5.setText("Kullanıcı Güncellendi.");
                              hataVarMi = false;
                           }else if(!yeniSifre.equals("") && telefonNo.equals("")){
                               sql = "update kullanicilar set sifre = ? where kullaniciadi = ?";
                               statement = connection.prepareStatement(sql);
     
                              statement.setString(1, yeniSifre);
                              statement.setString(2, mevcutKullaniciAdi);
                               int result = statement.executeUpdate();
                              kullanicilariTabloyaYerlestir();
                              lblMessage5.setText("Kullanıcı Güncellendi.");
                              hataVarMi = false;
                           }else if(yeniSifre.equals("") && !telefonNo.equals("")){
                                sql = "update kullanicilar set telefonno = ? where kullaniciadi = ?";
                               statement = connection.prepareStatement(sql);    
                              statement.setString(1, telefonNo);
                              statement.setString(2, mevcutKullaniciAdi);
                               int result = statement.executeUpdate();
                              kullanicilariTabloyaYerlestir();
                              lblMessage5.setText("Kullanıcı Güncellendi.");
                              hataVarMi = false;
                           }else{
                               lblMessage5.setText("Alanları boş bırakmayınız");
                           }

                           
                       } 
                      
                   }
                   
               }
               
               if(hataVarMi == true){
                   lblMessage5.setText("Kullanıcı Mevcut Değil veya Şifre Yanlış");
               }
            
           }catch(java.sql.SQLException exception){
               dbHelper.showErrorMessage(exception);
           } catch (IOException ex) {
             
         }finally{
             try {
                 statement.close();
                 connection.close();
             } catch (SQLException ex) {
                 
             }
              
           }     
        
    }
    
    @Override
    public void YoneticileriTabloyaYerlestir() {
        modelY=(DefaultTableModel)tblYoneticiListele1.getModel();
        modelY.setRowCount(0);
         try {
             ArrayList<Yonetici> yoneticiler;
            try {
                yoneticiler = yoneticileriAl();
                for(Yonetici yonetici: yoneticiler){
                 Object[] row = {yonetici.getId(),
                     yonetici.getIsim().trim(),
                     yonetici.getYoneticiAdi().trim(),
                     yonetici.getSifre().trim()};
                modelY.addRow(row);
              
             }
            } catch (SQLException ex) {
                Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
             
         } catch (IOException ex) {
             Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
         }

    }
    
    @Override
     public void yoneticiKontrolu(String isim ,String yoneticiIsmi){
        modelY = (DefaultTableModel)tblYoneticiListele1.getModel();
        modelY.setRowCount(0);
        boolean yoneticiKayitliMi = false;
        try {

            ArrayList<Yonetici> alinanYoneticiler = yoneticileriAl();
           
            for(Yonetici yonetici : alinanYoneticiler){
             
                if(yonetici.getYoneticiAdi().equals(yoneticiIsmi) && yonetici.getIsim().equals(isim)){
                    
                    lblYoneticiEkleMessage.setText("Hata! Kullanıcı Zaten Mevcut");
                    yoneticiKayitliMi = true;
                    break;
                }
               
            }
      if(yoneticiKayitliMi == false)  {
          Connection connection = null;
            DbHelper helper = new DbHelper();
            PreparedStatement statement = null;
     
      String yoneticiIsim = txtYoneticiEkleİsim.getText();
      String yoneticiAdi = txtYoneticiEkleMail.getText();
      String yoneticiSifre = txtYoneticiEkleSifre.getText();
      //boolean hata = false;
      
      //if(!hata){
           
      try{
          connection = helper.getConnection();
          String sql = "insert into yoneticiler (isim,yoneticiadi,sifre) values(?,?,?)";
          statement = connection.prepareStatement(sql);
          statement.setString(1, yoneticiIsim.trim());
          statement.setString(2, yoneticiAdi.trim());
          statement.setString(3, yoneticiSifre.trim());
          int result = statement.executeUpdate();
          YoneticileriTabloyaYerlestir();
          
          lblYoneticiEkleMessage.setText("Kayıt Başarılı");
      }catch(SQLException exception){
          helper.showErrorMessage(exception);
      }finally{
          try {
              statement.close(); 
              connection.close();
          } catch (SQLException ex) {
             lblYoneticiEkleMessage.setText(ex.getMessage());
          }
    }                                            
  // }
      }    
       
        } catch (SQLException exception) {
           exception.printStackTrace();
        } catch (IOException ex) {
             Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
         }

       //System.out.print(kitapKayitliMi);
    }

    public ArrayList<Yonetici> yoneticileriAl() throws SQLException, java.sql.SQLException, IOException {
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        Statement statement = null;
        ResultSet resultSet;
        ArrayList<Yonetici> yoneticiler = null;
        
           try{
            connection = (Connection) dbHelper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from yoneticiler");
            
            yoneticiler = new ArrayList<>();
            
            while(resultSet.next()){
               Yonetici yonetici = new Yonetici(
                        resultSet.getInt("id"),
                        resultSet.getString("isim").toString().trim(),
                         resultSet.getString("yoneticiadi").toString().trim(),
                        resultSet.getString("sifre").toString().trim()
                );
                
                yoneticiler.add(yonetici);
            }
            
           }catch(java.sql.SQLException exception){
               dbHelper.showErrorMessage(exception);
           }finally{
              statement.close();
              connection.close();
           }
           
           return yoneticiler;
        
    }
    
    @Override
    public void yoneticiSilme(String yoneticiAdi){
         Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null;
        
        
           try{
            connection = (Connection) dbHelper.getConnection();
            String sql = "delete from yoneticiler where yoneticiadi = ?";
            statement = connection.prepareStatement(sql);
     
            statement.setString(1, yoneticiAdi);
            YoneticileriTabloyaYerlestir();
         
            int result = statement.executeUpdate();
            lblYoneticiSilMessage.setText("Silme işlemi başarılı");
           }catch(java.sql.SQLException exception){
               dbHelper.showErrorMessage(exception);
           }finally{
             try {
                 statement.close();
                 connection.close();
             } catch (SQLException ex) {
                 Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
             }
              
           }
           
    }
     
    @Override
    public void yoneticiGuncelle() {
         Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null;
        
        String mevcutYoneticiAdi = txtYoneticiGuncelleMail.getText();
        String yoneticiYeniSifre = txtYoneticiYeniPassword.getText();
        String yoneticiEskiSifre = txtYoneticiEskiPassword.getText();
        boolean hatavarmi=true;
           try{
               ArrayList<Yonetici> yoneticiler = yoneticileriAl();
        
               for(Yonetici yonetici : yoneticiler){
            
                   if(mevcutYoneticiAdi.equals(yonetici.getYoneticiAdi())){
                       hatavarmi=false;
                       if(yoneticiEskiSifre.equals(yonetici.getSifre())){
                           connection = (Connection) dbHelper.getConnection();
                           String sql = "update yoneticiler set sifre = ? where yoneticiadi = ?";
                           statement = connection.prepareStatement(sql);
     
                           statement.setString(1, yoneticiYeniSifre);
                           statement.setString(2, mevcutYoneticiAdi);
                           
         
                           int result = statement.executeUpdate();
                           lblYoneticiGuncelleMesaage.setText("Yönetici Güncellendi");
                           YoneticileriTabloyaYerlestir();
                           break;
                          
                       }else{
                           lblYoneticiGuncelleMesaage.setText("Eski Şifre Yanlış");
                           
                       }  
                      
                   }
                   
               }
               
              if(hatavarmi){
                       lblYoneticiGuncelleMesaage.setText("Yonetici mevcut değil");
                   }
            
           }catch(java.sql.SQLException exception){
               dbHelper.showErrorMessage(exception);
           } catch (IOException ex) {
             Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
         }finally{
             try {
                 statement.close();
                 connection.close();
             } catch (SQLException ex) {
                 Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
             }
              
           }
           
           
    }
    
    @Override
    public void KitapSilme(String isim){
         Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null;
        
           try{
            connection = (Connection) dbHelper.getConnection();
            String sql = "delete from kitaplar where isim = ?";
            statement = connection.prepareStatement(sql);
     
            statement.setString(1, isim);
            
         
            int result = statement.executeUpdate();
          
            lblKitapSilMesaage.setText("Silme işlemi başarılı");
            kitaplariTabloyaYerlestir();
            comboboxlariDoldur();
            kitaplariTabloyaYerlestir2();
           }catch(java.sql.SQLException exception){
               dbHelper.showErrorMessage(exception);
           }finally{
             try {
                 statement.close();
                 connection.close();
             } catch (SQLException ex) {
                 Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
             }
              
           }
           
           
    }
    
    @Override
    public void oduncVerilenKitapSilme(String kitapIsmi){
         Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null;
        
           try{
            connection = (Connection) dbHelper.getConnection();
            String sql = "delete from oduncverilenkitaplar where kitapismi = ?";
            statement = connection.prepareStatement(sql);
     
            statement.setString(1, kitapIsmi);
                   
            int result = statement.executeUpdate();
            oduncVerilenKitaplariTabloyaYerlestir();
            comboboxlariDoldur();
            lblIadeMesaji.setText("İade işlemi başarılı");
           }catch(java.sql.SQLException exception){
               dbHelper.showErrorMessage(exception);
           }finally{
             try {
                 statement.close();
                 connection.close();
             } catch (SQLException ex) {
                 Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
             }
              
           }
           
           
    }
    
    
    @Override
    public void kitapOduncVer(String kitapIsmi,String alanKullanici,String verenYonetici,String tarih,String kullaniciTelefonNo){
        
        boolean sahipMi=false;
        
        try{
            Connection connection = null;
            DbHelper helper = new DbHelper();
            PreparedStatement statement = null;
            ArrayList<OduncVerilenKitap> oduncVerilenKitaplar=oduncVerilenKitaplariAl();
            for(OduncVerilenKitap kitap:oduncVerilenKitaplar){
                if(kitap.getAlanKullanici().equals(alanKullanici) && kitap.getKitapIsmi().equals(kitapIsmi)){
                   lblKitapOduncVerMesaj.setText("KULLANICI BU KİTABA SAHİP!!!");
                   sahipMi=true;  
                   break;
                }
              
            }
            if(sahipMi==false){
                            try{
                connection = helper.getConnection();
                String sql = "insert into oduncverilenkitaplar (kitapismi,alankullanici,verenyonetici,tarih,kullanicitelefonno) values(?,?,?,?,?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, kitapIsmi);
                statement.setString(2, alanKullanici);
                statement.setString(3, verenYonetici);
                statement.setString(4, tarih);
                statement.setString(5, kullaniciTelefonNo);
                int result = statement.executeUpdate();
                
                oduncVerilenKitaplariTabloyaYerlestir();
                kitapGuncelle(kitapIsmi);
                lblKitapOduncVerMesaj.setText("Ödünç verildi");
            }catch(SQLException exception){
                helper.showErrorMessage(exception);
            }finally{
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException ex) {
                    
                }
            }
            }

        }catch(SQLException ex){
             Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null,ex);
      }  catch (IOException ex) {
             Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
  
    @Override
    public void kitapAlVerGuncelle() {
         Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null;
        
        String mevcutKitapAdi = txtVerilenKtpGuncelleIsim.getText();
        String kullaniciAdi = txtVerilenKtpGuncelleKullanici.getText();
        String yeniTarih = txtVerilenKtpGuncelleTarih.getText();
        boolean hatavarmi=true;
           try{
               ArrayList<OduncVerilenKitap> kitaplar = oduncVerilenKitaplariAl();
        
               for(OduncVerilenKitap kitap : kitaplar){
            
                   if(mevcutKitapAdi.equals(kitap.getKitapIsmi())){
                       hatavarmi=false;
                       if(kullaniciAdi.equals(kitap.getAlanKullanici())){
                           connection = (Connection) dbHelper.getConnection();
                           String sql = "update oduncverilenkitaplar set tarih = ? where kitapismi = ?";
                           statement = connection.prepareStatement(sql);
     
                           statement.setString(1, yeniTarih);
                           statement.setString(2, mevcutKitapAdi);
                           
         
                           int result = statement.executeUpdate();
                           lblVerilenKitapMessage.setText("Kitap Teslim Tarihi Güncellendi");
                           oduncVerilenKitaplariTabloyaYerlestir();
                           break;
                          
                       }else{
                           lblVerilenKitapMessage.setText("Alan Kullanıcı Bilgisi Yanlış");
                           
                       }  
                      
                   }
                   
               }
               
              if(hatavarmi){
                       lblVerilenKitapMessage.setText("Kitap Mevcut Değil");
                   }
            
           }catch(java.sql.SQLException exception){
               dbHelper.showErrorMessage(exception);
           } catch (IOException ex) {
             Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
         }finally{
             try {
                 statement.close();
                 connection.close();
             } catch (SQLException ex) {
                 Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
             }
              
           }
           
           
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        pnl1 = new javax.swing.JTabbedPane();
        pnlKitapListele = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBooks = new javax.swing.JTable();
        txtKitapListele = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        pnlKitapEkle = new javax.swing.JPanel();
        lblIsim = new javax.swing.JLabel();
        lblYazar = new javax.swing.JLabel();
        lblBasimYili = new javax.swing.JLabel();
        lblBasimYeri = new javax.swing.JLabel();
        lblTuru = new javax.swing.JLabel();
        lblAdedi = new javax.swing.JLabel();
        txtIsim = new javax.swing.JTextField();
        txtYazar = new javax.swing.JTextField();
        txtBasimYeri = new javax.swing.JTextField();
        txtBasimYili = new javax.swing.JTextField();
        txtAdedi = new javax.swing.JTextField();
        txtTuru = new javax.swing.JTextField();
        btnKitapEkle = new javax.swing.JButton();
        lblEkleMessage = new javax.swing.JLabel();
        pnlKitapGuncelle = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtKitapGuncelleAdi = new javax.swing.JTextField();
        txtKitapGuncelleYazari = new javax.swing.JTextField();
        txtKitapGuncelleAdedi = new javax.swing.JTextField();
        btnKitapGuncelle = new javax.swing.JButton();
        lblKitapGuncelleMessage = new javax.swing.JLabel();
        pnlKullaniciListele = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKullanicilar = new javax.swing.JTable();
        lblKullaniciAra = new javax.swing.JLabel();
        txtKullaniciAra = new javax.swing.JTextField();
        btnKullaniciSil = new javax.swing.JButton();
        lblMessage4 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        pnlKullaniciEkle = new javax.swing.JPanel();
        lblKullaniciIsmi = new javax.swing.JLabel();
        txtKullaniciIsmi = new javax.swing.JTextField();
        lblKullaniciAdi = new javax.swing.JLabel();
        txtKullaniciAdi = new javax.swing.JTextField();
        lblKullaniciSifre = new javax.swing.JLabel();
        txtKullaniciSifre = new javax.swing.JPasswordField();
        btnKullaniciEkle = new javax.swing.JButton();
        lblMessage3 = new javax.swing.JLabel();
        txtKullaniciTelefonno = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        pnlYoneticiListele = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtYoneticiListeleAra = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblYoneticiListele1 = new javax.swing.JTable();
        btnYoneticiSil = new javax.swing.JButton();
        lblYoneticiSilMessage = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        pnlYoneticiEkle = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtYoneticiEkleİsim = new javax.swing.JTextField();
        txtYoneticiEkleMail = new javax.swing.JTextField();
        txtYoneticiEkleSifre = new javax.swing.JTextField();
        btnYoneticiEkle = new javax.swing.JButton();
        lblYoneticiEkleMessage = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtBaseYoneticiAdi = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtBaseYoneticiSifre = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtYoneticiGuncelleMail = new javax.swing.JTextField();
        txtYoneticiEskiPassword = new javax.swing.JPasswordField();
        txtYoneticiYeniPassword = new javax.swing.JPasswordField();
        btnYoneticiGuncelle = new javax.swing.JButton();
        lblYoneticiGuncelleMesaage = new javax.swing.JLabel();
        pnlKitapOduncVer = new javax.swing.JPanel();
        btnKitapOduncVer = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtTarih = new javax.swing.JTextField();
        txtTelefonNo = new javax.swing.JTextField();
        cmbKitaplar = new javax.swing.JComboBox<>();
        cmbKullanicilar = new javax.swing.JComboBox<>();
        lblKitapOduncVerMesaj = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtKitapOduncVerSearch = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblKitapIadeAl = new javax.swing.JTable();
        btnKitapIadeAl = new javax.swing.JButton();
        lblIadeMesaji = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtVerilenKtpGuncelleIsim = new javax.swing.JTextField();
        txtVerilenKtpGuncelleKullanici = new javax.swing.JTextField();
        txtVerilenKtpGuncelleTarih = new javax.swing.JTextField();
        btnVerilenKitapGuncelle = new javax.swing.JButton();
        lblVerilenKitapMessage = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblKitapSil = new javax.swing.JTable();
        txtKitapSil = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btnKitapSil = new javax.swing.JButton();
        lblKitapSilMesaage = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        pnlYoneticiGuncelle = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtKullaniciAdiGuncelleme = new javax.swing.JTextField();
        btnGuncelle = new javax.swing.JButton();
        lblMessage5 = new javax.swing.JLabel();
        txtKullaniciEskiSifre = new javax.swing.JPasswordField();
        txtKullaniciYeniSifre = new javax.swing.JPasswordField();
        jLabel40 = new javax.swing.JLabel();
        txtKullaniciGuncelleTelefon = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        btnCikisYapActionPerformed = new javax.swing.JButton();
        lblHesapIsim = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel37.setText("jLabel37");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 663, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnl1.setBackground(new java.awt.Color(204, 204, 204));
        pnl1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnl1.setFont(new java.awt.Font("Tahoma", 3, 10)); // NOI18N

        pnlKitapListele.setBackground(new java.awt.Color(204, 204, 255));

        tblBooks.setBackground(new java.awt.Color(204, 255, 204));
        tblBooks.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        tblBooks.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        tblBooks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "İd", "İsim", "Yazar", "Basım Yeri", "Basım Yılı", "Adedi", "Türü"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblBooks);
        if (tblBooks.getColumnModel().getColumnCount() > 0) {
            tblBooks.getColumnModel().getColumn(0).setResizable(false);
            tblBooks.getColumnModel().getColumn(1).setResizable(false);
            tblBooks.getColumnModel().getColumn(2).setResizable(false);
            tblBooks.getColumnModel().getColumn(3).setResizable(false);
            tblBooks.getColumnModel().getColumn(4).setResizable(false);
            tblBooks.getColumnModel().getColumn(5).setResizable(false);
            tblBooks.getColumnModel().getColumn(6).setResizable(false);
        }

        txtKitapListele.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtKitapListele.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKitapListeleActionPerformed(evt);
            }
        });
        txtKitapListele.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKitapListeleKeyReleased(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(204, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel1.setText("Ara :");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/company/SwingFiles/book.jpg"))); // NOI18N
        jLabel38.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnlKitapListeleLayout = new javax.swing.GroupLayout(pnlKitapListele);
        pnlKitapListele.setLayout(pnlKitapListeleLayout);
        pnlKitapListeleLayout.setHorizontalGroup(
            pnlKitapListeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKitapListeleLayout.createSequentialGroup()
                .addGroup(pnlKitapListeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKitapListeleLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(pnlKitapListeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 866, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlKitapListeleLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtKitapListele, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlKitapListeleLayout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jLabel38)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        pnlKitapListeleLayout.setVerticalGroup(
            pnlKitapListeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKitapListeleLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlKitapListeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKitapListele, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        pnl1.addTab("KİTAP LİSTELE", pnlKitapListele);

        pnlKitapEkle.setBackground(new java.awt.Color(204, 204, 255));

        lblIsim.setBackground(new java.awt.Color(204, 255, 255));
        lblIsim.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        lblIsim.setText("Kitap İsmi :");
        lblIsim.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        lblYazar.setBackground(new java.awt.Color(204, 255, 255));
        lblYazar.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        lblYazar.setText("Yazar :");
        lblYazar.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        lblBasimYili.setBackground(new java.awt.Color(204, 255, 255));
        lblBasimYili.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        lblBasimYili.setText("Basım Yılı :");
        lblBasimYili.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        lblBasimYeri.setBackground(new java.awt.Color(204, 255, 255));
        lblBasimYeri.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        lblBasimYeri.setText("Basım Yeri :");
        lblBasimYeri.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        lblTuru.setBackground(new java.awt.Color(204, 255, 255));
        lblTuru.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        lblTuru.setText("Türü :");
        lblTuru.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        lblAdedi.setBackground(new java.awt.Color(204, 255, 255));
        lblAdedi.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        lblAdedi.setText("Adedi :");
        lblAdedi.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtIsim.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        txtIsim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIsimActionPerformed(evt);
            }
        });

        txtYazar.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtBasimYeri.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtBasimYili.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        txtBasimYili.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBasimYiliActionPerformed(evt);
            }
        });

        txtAdedi.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtTuru.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        txtTuru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTuruActionPerformed(evt);
            }
        });

        btnKitapEkle.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnKitapEkle.setText("EKLE");
        btnKitapEkle.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnKitapEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKitapEkleActionPerformed(evt);
            }
        });

        lblEkleMessage.setBackground(new java.awt.Color(255, 255, 255));
        lblEkleMessage.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N

        javax.swing.GroupLayout pnlKitapEkleLayout = new javax.swing.GroupLayout(pnlKitapEkle);
        pnlKitapEkle.setLayout(pnlKitapEkleLayout);
        pnlKitapEkleLayout.setHorizontalGroup(
            pnlKitapEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKitapEkleLayout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addGroup(pnlKitapEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKitapEkleLayout.createSequentialGroup()
                        .addComponent(btnKitapEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblEkleMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlKitapEkleLayout.createSequentialGroup()
                        .addComponent(lblIsim, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIsim, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlKitapEkleLayout.createSequentialGroup()
                        .addComponent(lblAdedi, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAdedi, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlKitapEkleLayout.createSequentialGroup()
                        .addComponent(lblBasimYili, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBasimYili, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlKitapEkleLayout.createSequentialGroup()
                        .addComponent(lblBasimYeri, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBasimYeri, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlKitapEkleLayout.createSequentialGroup()
                        .addComponent(lblYazar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtYazar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlKitapEkleLayout.createSequentialGroup()
                        .addComponent(lblTuru, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTuru, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(300, Short.MAX_VALUE))
        );
        pnlKitapEkleLayout.setVerticalGroup(
            pnlKitapEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKitapEkleLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(pnlKitapEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIsim, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIsim, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlKitapEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblYazar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYazar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlKitapEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBasimYeri, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBasimYeri, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlKitapEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBasimYili, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBasimYili, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlKitapEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAdedi, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdedi, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlKitapEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTuru, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTuru, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlKitapEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblEkleMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnKitapEkle, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
                .addContainerGap(270, Short.MAX_VALUE))
        );

        pnl1.addTab("KİTAP EKLE", pnlKitapEkle);

        pnlKitapGuncelle.setBackground(new java.awt.Color(204, 204, 255));

        jLabel31.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel31.setText("KİTAP ADI : ");
        jLabel31.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel32.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel32.setText("KİTAP YAZARI : ");
        jLabel32.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel33.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel33.setText("ADEDİ : ");
        jLabel33.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtKitapGuncelleAdi.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtKitapGuncelleYazari.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtKitapGuncelleAdedi.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        btnKitapGuncelle.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnKitapGuncelle.setText("GÜNCELLE");
        btnKitapGuncelle.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnKitapGuncelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKitapGuncelleActionPerformed(evt);
            }
        });

        lblKitapGuncelleMessage.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N

        javax.swing.GroupLayout pnlKitapGuncelleLayout = new javax.swing.GroupLayout(pnlKitapGuncelle);
        pnlKitapGuncelle.setLayout(pnlKitapGuncelleLayout);
        pnlKitapGuncelleLayout.setHorizontalGroup(
            pnlKitapGuncelleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKitapGuncelleLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(pnlKitapGuncelleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKitapGuncelleLayout.createSequentialGroup()
                        .addGroup(pnlKitapGuncelleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlKitapGuncelleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtKitapGuncelleAdi)
                            .addComponent(txtKitapGuncelleYazari)
                            .addComponent(txtKitapGuncelleAdedi, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
                    .addGroup(pnlKitapGuncelleLayout.createSequentialGroup()
                        .addComponent(btnKitapGuncelle, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblKitapGuncelleMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(473, Short.MAX_VALUE))
        );
        pnlKitapGuncelleLayout.setVerticalGroup(
            pnlKitapGuncelleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKitapGuncelleLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pnlKitapGuncelleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtKitapGuncelleAdi)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlKitapGuncelleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKitapGuncelleYazari, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlKitapGuncelleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(txtKitapGuncelleAdedi))
                .addGap(30, 30, 30)
                .addGroup(pnlKitapGuncelleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblKitapGuncelleMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnKitapGuncelle, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
                .addContainerGap(351, Short.MAX_VALUE))
        );

        pnl1.addTab("KİTAP GÜNCELLE", pnlKitapGuncelle);

        pnlKullaniciListele.setBackground(new java.awt.Color(204, 255, 204));

        tblKullanicilar.setBackground(new java.awt.Color(255, 204, 255));
        tblKullanicilar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblKullanicilar.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        tblKullanicilar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "İsim", "Mail", "Telefon"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKullanicilar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKullanicilarMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKullanicilar);
        if (tblKullanicilar.getColumnModel().getColumnCount() > 0) {
            tblKullanicilar.getColumnModel().getColumn(0).setResizable(false);
            tblKullanicilar.getColumnModel().getColumn(1).setResizable(false);
            tblKullanicilar.getColumnModel().getColumn(2).setResizable(false);
        }

        lblKullaniciAra.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        lblKullaniciAra.setText("Kullanıcı Ara : ");
        lblKullaniciAra.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtKullaniciAra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtKullaniciAra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKullaniciAraActionPerformed(evt);
            }
        });
        txtKullaniciAra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKullaniciAraKeyReleased(evt);
            }
        });

        btnKullaniciSil.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnKullaniciSil.setText("Kullanıcı Sil");
        btnKullaniciSil.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnKullaniciSil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKullaniciSilActionPerformed(evt);
            }
        });

        lblMessage4.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/company/SwingFiles/kütüphane.jpg"))); // NOI18N

        javax.swing.GroupLayout pnlKullaniciListeleLayout = new javax.swing.GroupLayout(pnlKullaniciListele);
        pnlKullaniciListele.setLayout(pnlKullaniciListeleLayout);
        pnlKullaniciListeleLayout.setHorizontalGroup(
            pnlKullaniciListeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKullaniciListeleLayout.createSequentialGroup()
                .addGroup(pnlKullaniciListeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKullaniciListeleLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblKullaniciAra, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtKullaniciAra, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlKullaniciListeleLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlKullaniciListeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMessage4, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                    .addGroup(pnlKullaniciListeleLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(btnKullaniciSil, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(pnlKullaniciListeleLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel41)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlKullaniciListeleLayout.setVerticalGroup(
            pnlKullaniciListeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKullaniciListeleLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(pnlKullaniciListeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKullaniciAra, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblKullaniciAra, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pnlKullaniciListeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKullaniciListeleLayout.createSequentialGroup()
                        .addComponent(btnKullaniciSil, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMessage4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        pnl1.addTab("KULLANICI LİSTELE / SİL", pnlKullaniciListele);

        pnlKullaniciEkle.setBackground(new java.awt.Color(204, 255, 204));

        lblKullaniciIsmi.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        lblKullaniciIsmi.setText("İsim :");
        lblKullaniciIsmi.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtKullaniciIsmi.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        lblKullaniciAdi.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        lblKullaniciAdi.setText("Mail :");
        lblKullaniciAdi.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtKullaniciAdi.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        txtKullaniciAdi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKullaniciAdiActionPerformed(evt);
            }
        });

        lblKullaniciSifre.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        lblKullaniciSifre.setText("Şifre :");
        lblKullaniciSifre.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtKullaniciSifre.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        txtKullaniciSifre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKullaniciSifreActionPerformed(evt);
            }
        });

        btnKullaniciEkle.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnKullaniciEkle.setText("Kullanıcı Ekle");
        btnKullaniciEkle.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnKullaniciEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKullaniciEkleActionPerformed(evt);
            }
        });

        lblMessage3.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N

        txtKullaniciTelefonno.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel9.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel9.setText("TELEFON NO :");
        jLabel9.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlKullaniciEkleLayout = new javax.swing.GroupLayout(pnlKullaniciEkle);
        pnlKullaniciEkle.setLayout(pnlKullaniciEkleLayout);
        pnlKullaniciEkleLayout.setHorizontalGroup(
            pnlKullaniciEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKullaniciEkleLayout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addGroup(pnlKullaniciEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKullaniciEkleLayout.createSequentialGroup()
                        .addGroup(pnlKullaniciEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblKullaniciSifre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblKullaniciAdi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblKullaniciIsmi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addGap(45, 45, 45)
                        .addGroup(pnlKullaniciEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtKullaniciAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKullaniciIsmi, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKullaniciSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKullaniciTelefonno, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlKullaniciEkleLayout.createSequentialGroup()
                        .addComponent(btnKullaniciEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblMessage3, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(203, Short.MAX_VALUE))
        );
        pnlKullaniciEkleLayout.setVerticalGroup(
            pnlKullaniciEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKullaniciEkleLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(pnlKullaniciEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKullaniciIsmi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblKullaniciIsmi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlKullaniciEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKullaniciAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKullaniciAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlKullaniciEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKullaniciSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblKullaniciSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(pnlKullaniciEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKullaniciTelefonno))
                .addGap(18, 18, 18)
                .addGroup(pnlKullaniciEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnKullaniciEkle, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(lblMessage3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(284, Short.MAX_VALUE))
        );

        pnl1.addTab("KULLANICI EKLE", pnlKullaniciEkle);

        pnlYoneticiListele.setBackground(new java.awt.Color(204, 255, 204));

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel8.setText("YÖNETİCİ ARA : ");
        jLabel8.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtYoneticiListeleAra.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        txtYoneticiListeleAra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtYoneticiListeleAraKeyReleased(evt);
            }
        });

        tblYoneticiListele1.setBackground(new java.awt.Color(255, 204, 255));
        tblYoneticiListele1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblYoneticiListele1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        tblYoneticiListele1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "İd", "İsim", "Mail", "Şifre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblYoneticiListele1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblYoneticiListele1MouseClicked(evt);
            }
        });
        tblYoneticiListele1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblYoneticiListele1KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tblYoneticiListele1);

        btnYoneticiSil.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnYoneticiSil.setText("YÖNETİCİ SİL");
        btnYoneticiSil.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnYoneticiSil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYoneticiSilActionPerformed(evt);
            }
        });

        lblYoneticiSilMessage.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/company/SwingFiles/kitap3.jpg"))); // NOI18N

        javax.swing.GroupLayout pnlYoneticiListeleLayout = new javax.swing.GroupLayout(pnlYoneticiListele);
        pnlYoneticiListele.setLayout(pnlYoneticiListeleLayout);
        pnlYoneticiListeleLayout.setHorizontalGroup(
            pnlYoneticiListeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlYoneticiListeleLayout.createSequentialGroup()
                .addGroup(pnlYoneticiListeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlYoneticiListeleLayout.createSequentialGroup()
                        .addGroup(pnlYoneticiListeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlYoneticiListeleLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtYoneticiListeleAra, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlYoneticiListeleLayout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(42, 42, 42)
                        .addGroup(pnlYoneticiListeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnYoneticiSil, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblYoneticiSilMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlYoneticiListeleLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jLabel42)))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        pnlYoneticiListeleLayout.setVerticalGroup(
            pnlYoneticiListeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlYoneticiListeleLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(pnlYoneticiListeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYoneticiListeleAra, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlYoneticiListeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlYoneticiListeleLayout.createSequentialGroup()
                        .addComponent(btnYoneticiSil, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblYoneticiSilMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        pnl1.addTab("YÖNETİCİ LİSTELE / SİL", pnlYoneticiListele);

        pnlYoneticiEkle.setBackground(new java.awt.Color(204, 255, 204));

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel2.setText("YÖNETİCİ ADI : ");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel3.setText("YÖNETİCİ MAİL veya KULLANICI ADI : ");
        jLabel3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel4.setText("ŞİFRE : ");
        jLabel4.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtYoneticiEkleİsim.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        txtYoneticiEkleİsim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtYoneticiEkleİsimActionPerformed(evt);
            }
        });

        txtYoneticiEkleMail.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtYoneticiEkleSifre.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        btnYoneticiEkle.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnYoneticiEkle.setText("YÖNETİCİ EKLE");
        btnYoneticiEkle.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnYoneticiEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYoneticiEkleActionPerformed(evt);
            }
        });

        lblYoneticiEkleMessage.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel29.setText("TEMEL ADMİN İSİM : ");
        jLabel29.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtBaseYoneticiAdi.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel30.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel30.setText("TEMEL ADMİN ŞİFRE : ");
        jLabel30.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtBaseYoneticiSifre.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlYoneticiEkleLayout = new javax.swing.GroupLayout(pnlYoneticiEkle);
        pnlYoneticiEkle.setLayout(pnlYoneticiEkleLayout);
        pnlYoneticiEkleLayout.setHorizontalGroup(
            pnlYoneticiEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlYoneticiEkleLayout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addGroup(pnlYoneticiEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlYoneticiEkleLayout.createSequentialGroup()
                        .addGroup(pnlYoneticiEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlYoneticiEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtYoneticiEkleMail)
                            .addComponent(txtYoneticiEkleSifre)
                            .addComponent(txtBaseYoneticiAdi)
                            .addComponent(txtBaseYoneticiSifre)
                            .addComponent(txtYoneticiEkleİsim, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlYoneticiEkleLayout.createSequentialGroup()
                        .addComponent(btnYoneticiEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblYoneticiEkleMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(221, Short.MAX_VALUE))
        );
        pnlYoneticiEkleLayout.setVerticalGroup(
            pnlYoneticiEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlYoneticiEkleLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(pnlYoneticiEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtYoneticiEkleİsim, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlYoneticiEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYoneticiEkleMail, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnlYoneticiEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtYoneticiEkleSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlYoneticiEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBaseYoneticiAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlYoneticiEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBaseYoneticiSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(pnlYoneticiEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnYoneticiEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblYoneticiEkleMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(177, Short.MAX_VALUE))
        );

        pnl1.addTab("YÖNETİCİ EKLE", pnlYoneticiEkle);

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        jLabel10.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel10.setText("YÖNETİCİ MAİL veya KULLANICI ADI :");
        jLabel10.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel11.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel11.setText("ESKİ ŞİFRE : ");
        jLabel11.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel12.setText("YENİ ŞİFRE : ");
        jLabel12.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtYoneticiGuncelleMail.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtYoneticiEskiPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtYoneticiYeniPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        btnYoneticiGuncelle.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnYoneticiGuncelle.setText("GÜNCELLE");
        btnYoneticiGuncelle.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnYoneticiGuncelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYoneticiGuncelleActionPerformed(evt);
            }
        });

        lblYoneticiGuncelleMesaage.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtYoneticiGuncelleMail)
                            .addComponent(txtYoneticiEskiPassword)
                            .addComponent(txtYoneticiYeniPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnYoneticiGuncelle, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblYoneticiGuncelleMesaage, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(260, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtYoneticiGuncelleMail, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYoneticiEskiPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtYoneticiYeniPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblYoneticiGuncelleMesaage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnYoneticiGuncelle, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(243, Short.MAX_VALUE))
        );

        pnl1.addTab("YÖNETİCİ GÜNCELLE", jPanel2);

        pnlKitapOduncVer.setBackground(new java.awt.Color(204, 204, 255));

        btnKitapOduncVer.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnKitapOduncVer.setText("KİTAP ÖDÜNÇ VER");
        btnKitapOduncVer.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnKitapOduncVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKitapOduncVerActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(204, 204, 255));

        jLabel14.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel14.setText("Kitap : ");
        jLabel14.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel15.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel15.setText("Kullanıcı : ");
        jLabel15.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel17.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel17.setText("Telefon No :");
        jLabel17.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel18.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel18.setText("Tarih :");
        jLabel18.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtTarih.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtTelefonNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cmbKitaplar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cmbKitaplar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKitaplarActionPerformed(evt);
            }
        });

        cmbKullanicilar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cmbKullanicilar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbKullanicilarItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefonNo)
                            .addComponent(txtTarih)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbKitaplar, 0, 295, Short.MAX_VALUE)
                            .addComponent(cmbKullanicilar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, 0))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cmbKitaplar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbKullanicilar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTelefonNo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTarih, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblKitapOduncVerMesaj.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N

        javax.swing.GroupLayout pnlKitapOduncVerLayout = new javax.swing.GroupLayout(pnlKitapOduncVer);
        pnlKitapOduncVer.setLayout(pnlKitapOduncVerLayout);
        pnlKitapOduncVerLayout.setHorizontalGroup(
            pnlKitapOduncVerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKitapOduncVerLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlKitapOduncVerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnKitapOduncVer, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblKitapOduncVerMesaj, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(238, Short.MAX_VALUE))
        );
        pnlKitapOduncVerLayout.setVerticalGroup(
            pnlKitapOduncVerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKitapOduncVerLayout.createSequentialGroup()
                .addGroup(pnlKitapOduncVerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKitapOduncVerLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlKitapOduncVerLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btnKitapOduncVer, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblKitapOduncVerMesaj, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(428, Short.MAX_VALUE))
        );

        pnl1.addTab("KİTAP ÖDÜNÇ VER", pnlKitapOduncVer);

        jPanel8.setBackground(new java.awt.Color(204, 204, 255));

        jLabel16.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel16.setText("Ara :");
        jLabel16.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtKitapOduncVerSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        txtKitapOduncVerSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKitapOduncVerSearchActionPerformed(evt);
            }
        });
        txtKitapOduncVerSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKitapOduncVerSearchKeyReleased(evt);
            }
        });

        tblKitapIadeAl.setBackground(new java.awt.Color(204, 255, 204));
        tblKitapIadeAl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblKitapIadeAl.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        tblKitapIadeAl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "İsim", "Alan Kullanıcı", "Veren Yönetici", "Tarih", "Kullanıcı Telefon No"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKitapIadeAl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKitapIadeAlMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblKitapIadeAl);
        if (tblKitapIadeAl.getColumnModel().getColumnCount() > 0) {
            tblKitapIadeAl.getColumnModel().getColumn(0).setResizable(false);
            tblKitapIadeAl.getColumnModel().getColumn(1).setResizable(false);
            tblKitapIadeAl.getColumnModel().getColumn(2).setResizable(false);
            tblKitapIadeAl.getColumnModel().getColumn(3).setResizable(false);
            tblKitapIadeAl.getColumnModel().getColumn(4).setResizable(false);
        }

        btnKitapIadeAl.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnKitapIadeAl.setText("KİTAP İADE AL");
        btnKitapIadeAl.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnKitapIadeAl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKitapIadeAlActionPerformed(evt);
            }
        });

        lblIadeMesaji.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/company/SwingFiles/kitap6.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtKitapOduncVerSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIadeMesaji, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(btnKitapIadeAl, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel45)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKitapOduncVerSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnKitapIadeAl, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblIadeMesaji, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        pnl1.addTab("KİTAP İADE AL", jPanel8);

        jPanel9.setBackground(new java.awt.Color(204, 204, 255));

        jLabel34.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel34.setText("KİTAP İSMİ : ");
        jLabel34.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel35.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel35.setText("ALAN KULLANICI : ");
        jLabel35.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel36.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel36.setText("YENİ TARİH : ");
        jLabel36.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtVerilenKtpGuncelleIsim.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        txtVerilenKtpGuncelleIsim.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtVerilenKtpGuncelleKullanici.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        txtVerilenKtpGuncelleKullanici.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtVerilenKtpGuncelleTarih.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        txtVerilenKtpGuncelleTarih.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        btnVerilenKitapGuncelle.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnVerilenKitapGuncelle.setText("GÜNCELLE");
        btnVerilenKitapGuncelle.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnVerilenKitapGuncelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerilenKitapGuncelleActionPerformed(evt);
            }
        });

        lblVerilenKitapMessage.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtVerilenKtpGuncelleIsim)
                            .addComponent(txtVerilenKtpGuncelleKullanici)
                            .addComponent(txtVerilenKtpGuncelleTarih, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btnVerilenKitapGuncelle, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblVerilenKitapMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(436, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVerilenKtpGuncelleIsim, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVerilenKtpGuncelleKullanici, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtVerilenKtpGuncelleTarih, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVerilenKitapGuncelle, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVerilenKitapMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(351, Short.MAX_VALUE))
        );

        pnl1.addTab("VERİLEN KİTABI GÜNCELLE", jPanel9);

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));

        tblKitapSil.setBackground(new java.awt.Color(204, 255, 204));
        tblKitapSil.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblKitapSil.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        tblKitapSil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "İd", "İsim", "Yazar", "Basım Yeri", "Basım Yılı", "Adedi", "Türü"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKitapSil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKitapSilMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblKitapSil);
        if (tblKitapSil.getColumnModel().getColumnCount() > 0) {
            tblKitapSil.getColumnModel().getColumn(0).setResizable(false);
            tblKitapSil.getColumnModel().getColumn(1).setResizable(false);
            tblKitapSil.getColumnModel().getColumn(2).setResizable(false);
            tblKitapSil.getColumnModel().getColumn(3).setResizable(false);
            tblKitapSil.getColumnModel().getColumn(4).setResizable(false);
            tblKitapSil.getColumnModel().getColumn(5).setResizable(false);
            tblKitapSil.getColumnModel().getColumn(6).setResizable(false);
        }

        txtKitapSil.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        txtKitapSil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKitapSilActionPerformed(evt);
            }
        });
        txtKitapSil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKitapSilKeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel13.setText("KİTAP ARA : ");
        jLabel13.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        btnKitapSil.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnKitapSil.setText("KİTAP SİL");
        btnKitapSil.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnKitapSil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKitapSilActionPerformed(evt);
            }
        });

        lblKitapSilMesaage.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/company/SwingFiles/kitap5.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtKitapSil))
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(599, 599, 599)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblKitapSilMesaage, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnKitapSil, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(txtKitapSil, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnKitapSil, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(lblKitapSilMesaage, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        pnl1.addTab("KİTAP SİL", jPanel4);

        pnlYoneticiGuncelle.setBackground(new java.awt.Color(204, 255, 204));

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel5.setText("Kullanıcı Adı veya Mail: ");
        jLabel5.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel6.setText("Yeni Şİfre : ");
        jLabel6.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel7.setText("Eski Şifre : ");
        jLabel7.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtKullaniciAdiGuncelleme.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        btnGuncelle.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnGuncelle.setText("Güncelle");
        btnGuncelle.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnGuncelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuncelleActionPerformed(evt);
            }
        });

        lblMessage5.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N

        txtKullaniciEskiSifre.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtKullaniciYeniSifre.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel40.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel40.setText("TELEFON NUMARASI :");
        jLabel40.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtKullaniciGuncelleTelefon.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlYoneticiGuncelleLayout = new javax.swing.GroupLayout(pnlYoneticiGuncelle);
        pnlYoneticiGuncelle.setLayout(pnlYoneticiGuncelleLayout);
        pnlYoneticiGuncelleLayout.setHorizontalGroup(
            pnlYoneticiGuncelleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlYoneticiGuncelleLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(pnlYoneticiGuncelleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlYoneticiGuncelleLayout.createSequentialGroup()
                        .addGroup(pnlYoneticiGuncelleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlYoneticiGuncelleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtKullaniciAdiGuncelleme, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtKullaniciEskiSifre)
                            .addComponent(txtKullaniciYeniSifre)
                            .addComponent(txtKullaniciGuncelleTelefon, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(pnlYoneticiGuncelleLayout.createSequentialGroup()
                        .addComponent(btnGuncelle, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblMessage5, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(390, 390, 390))
        );
        pnlYoneticiGuncelleLayout.setVerticalGroup(
            pnlYoneticiGuncelleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlYoneticiGuncelleLayout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addGroup(pnlYoneticiGuncelleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtKullaniciAdiGuncelleme)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlYoneticiGuncelleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKullaniciEskiSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(pnlYoneticiGuncelleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtKullaniciYeniSifre)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addGap(22, 22, 22)
                .addGroup(pnlYoneticiGuncelleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlYoneticiGuncelleLayout.createSequentialGroup()
                        .addComponent(txtKullaniciGuncelleTelefon)
                        .addGap(1, 1, 1))
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(pnlYoneticiGuncelleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuncelle, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMessage5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(197, 197, 197))
        );

        pnl1.addTab("KULLANICI GÜNCELLE", pnlYoneticiGuncelle);

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setLayout(null);

        jLabel19.setBackground(new java.awt.Color(255, 102, 102));
        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 51, 51));
        jLabel19.setText("     KÜTÜPHANE OTOMASYON SİSTEMİ");
        jLabel19.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jPanel6.add(jLabel19);
        jLabel19.setBounds(210, 20, 300, 60);

        jLabel20.setText("HAZIRLAYANLAR : ");
        jPanel6.add(jLabel20);
        jLabel20.setBounds(300, 200, 140, 30);

        jLabel21.setText("SON GÜNCELLEME TARİHİ : ");
        jPanel6.add(jLabel21);
        jLabel21.setBounds(300, 230, 180, 40);

        jLabel22.setText("DOĞUŞ GÜNEŞ - ORHUN ALP YAMEN");
        jPanel6.add(jLabel22);
        jLabel22.setBounds(480, 190, 260, 50);

        jLabel23.setText("28.12.2020");
        jPanel6.add(jLabel23);
        jLabel23.setBounds(480, 230, 100, 40);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 0, 51));
        jLabel24.setText("   HAKKIMIZDA");
        jLabel24.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel6.add(jLabel24);
        jLabel24.setBounds(420, 150, 130, 40);

        jLabel25.setText("TELEFON : ");
        jPanel6.add(jLabel25);
        jLabel25.setBounds(300, 270, 130, 30);

        jLabel26.setText("505 555 55 55");
        jPanel6.add(jLabel26);
        jLabel26.setBounds(480, 270, 140, 30);

        jLabel27.setText("MAİL : ");
        jPanel6.add(jLabel27);
        jLabel27.setBounds(300, 300, 90, 30);

        jLabel28.setText("GAZİTEF@GMAİL.COM");
        jPanel6.add(jLabel28);
        jLabel28.setBounds(480, 300, 190, 30);

        btnCikisYapActionPerformed.setBackground(new java.awt.Color(204, 255, 255));
        btnCikisYapActionPerformed.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnCikisYapActionPerformed.setText("ÇIKIŞ YAPMAK İÇİN TIKLAYIN");
        btnCikisYapActionPerformed.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnCikisYapActionPerformed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCikisYapActionPerformedActionPerformed(evt);
            }
        });
        jPanel6.add(btnCikisYapActionPerformed);
        btnCikisYapActionPerformed.setBounds(20, 270, 220, 50);

        lblHesapIsim.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.add(lblHesapIsim);
        lblHesapIsim.setBounds(160, 110, 170, 40);

        jLabel39.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel39.setText("KULLANICI ADI :");
        jLabel39.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel6.add(jLabel39);
        jLabel39.setBounds(20, 110, 120, 40);

        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/company/SwingFiles/kitap4.jpg"))); // NOI18N
        jPanel6.add(jLabel44);
        jLabel44.setBounds(230, 350, 300, 130);

        pnl1.addTab("HESAP BİLGİSİ", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl1)
                .addGap(10, 10, 10)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1019, 1019, 1019))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnl1, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKitapEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKitapEkleActionPerformed

        String kitapIsmi = txtIsim.getText().trim();
        String yazar = txtYazar.getText().trim();
        kitapKontrolu(kitapIsmi, yazar);
        kitaplariTabloyaYerlestir();
        kitaplariTabloyaYerlestir2();
        
        

    }//GEN-LAST:event_btnKitapEkleActionPerformed

    private void txtTuruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTuruActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTuruActionPerformed

    private void txtBasimYiliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBasimYiliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBasimYiliActionPerformed

    private void txtIsimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIsimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIsimActionPerformed

    private void btnKullaniciEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKullaniciEkleActionPerformed
        String alinanKullanici = txtKullaniciAdi.getText();
        kullaniciKontrolu(alinanKullanici);
    }//GEN-LAST:event_btnKullaniciEkleActionPerformed

    private void txtKullaniciSifreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKullaniciSifreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKullaniciSifreActionPerformed

    private void txtKitapListeleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKitapListeleKeyReleased
        String searchKey = txtKitapListele.getText();
        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<DefaultTableModel>(modelKitaplar);
        tblBooks.setRowSorter(tableRowSorter);
        tableRowSorter.setRowFilter(RowFilter.regexFilter(searchKey));
    }//GEN-LAST:event_txtKitapListeleKeyReleased

    private void txtKitapListeleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKitapListeleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKitapListeleActionPerformed

    int row = -1;
    String kullaniciAdi = "";
    private void btnKullaniciSilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKullaniciSilActionPerformed

        if(kullaniciAdi != ""){
            kullaniciSilme(kullaniciAdi);
            kullanicilariTabloyaYerlestir();
            comboboxlariDoldur();
            kullaniciAdi = "";
        }else{
            lblMessage4.setText("Kullanıcı seçilmedi!!");
        }

    }//GEN-LAST:event_btnKullaniciSilActionPerformed

    private void tblKullanicilarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKullanicilarMouseClicked

        row = tblKullanicilar.getSelectedRow();
        kullaniciAdi = (String) tblKullanicilar.getModel().getValueAt(row, 1);

    }//GEN-LAST:event_tblKullanicilarMouseClicked

    private void txtKullaniciAraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKullaniciAraKeyReleased
        String searchKey = txtKullaniciAra.getText();
        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<DefaultTableModel>(modelKullanicilar);
        tblKullanicilar.setRowSorter(tableRowSorter);
        tableRowSorter.setRowFilter(RowFilter.regexFilter(searchKey));
    }//GEN-LAST:event_txtKullaniciAraKeyReleased

    private void btnGuncelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuncelleActionPerformed
        
        String yeniSifre = txtKullaniciYeniSifre.getText().toString();
        String telefonNo = txtKullaniciGuncelleTelefon.getText().toString();
        String kullaniciAdiGuncelleme = txtKullaniciAdiGuncelleme.getText().toString();
        String eskiSifre = txtKullaniciEskiSifre.getText().toString();
        if(kullaniciAdiGuncelleme.equals("") || eskiSifre.equals("")){
            lblMessage5.setText("Kullanici adını veya eski sifreyi bos bıraktınız");
        }else{
            kullaniciGuncelle(yeniSifre,telefonNo);
        }
        
    }//GEN-LAST:event_btnGuncelleActionPerformed
   
    private void tblYoneticiListele1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblYoneticiListele1KeyReleased
        
    }//GEN-LAST:event_tblYoneticiListele1KeyReleased

    private void btnYoneticiEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYoneticiEkleActionPerformed
         if(txtBaseYoneticiAdi.getText().equals(Yonetici.getBaseAdminIsim()) && txtBaseYoneticiSifre.getText().equals(Yonetici.getBaseAdminSifre())){
            String yoneticiIsmi = txtYoneticiEkleİsim.getText().trim();
         String mail = txtYoneticiEkleMail.getText().trim();
         yoneticiKontrolu(yoneticiIsmi, mail);
         YoneticileriTabloyaYerlestir();
         lblYoneticiEkleMessage.setText("YÖNETİCİ EKLENDİ");
        }
        else{
            lblYoneticiEkleMessage.setText("TEMEL ŞİFRE VEYA TEMEL İSİM HATALI");
        }
    }//GEN-LAST:event_btnYoneticiEkleActionPerformed

    private void txtYoneticiListeleAraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtYoneticiListeleAraKeyReleased
        String searchKey = txtYoneticiListeleAra.getText();
       TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<DefaultTableModel>(modelY);
       tblYoneticiListele1.setRowSorter(tableRowSorter);
       tableRowSorter.setRowFilter(RowFilter.regexFilter(searchKey));
    }//GEN-LAST:event_txtYoneticiListeleAraKeyReleased
    
        int row2 = -1;
        String silinecekYonetici = "";
    private void btnYoneticiSilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYoneticiSilActionPerformed
        
         try {
             ArrayList<Yonetici> yoneticiler = yoneticileriAl();
             if(yoneticiler.size() == 1){
                 lblYoneticiSilMessage.setText("Silinemez.Tek bir yonetici var.");
             }else{
                 if(silinecekYonetici != ""){
                     yoneticiSilme(silinecekYonetici);
                     YoneticileriTabloyaYerlestir();
                     silinecekYonetici = "";
                 }else{
                     lblYoneticiSilMessage.setText("Kullanıcı seçilmedi!!");
                 }
             }} catch (SQLException ex) {
             Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }//GEN-LAST:event_btnYoneticiSilActionPerformed

    private void tblYoneticiListele1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblYoneticiListele1MouseClicked
        row2 = tblYoneticiListele1.getSelectedRow();
        silinecekYonetici = (String) tblYoneticiListele1.getModel().getValueAt(row2, 2);
    }//GEN-LAST:event_tblYoneticiListele1MouseClicked

    private void txtKullaniciAdiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKullaniciAdiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKullaniciAdiActionPerformed

    private void btnYoneticiGuncelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYoneticiGuncelleActionPerformed
        yoneticiGuncelle();
    }//GEN-LAST:event_btnYoneticiGuncelleActionPerformed
    int row3 = -1;
    String isim = "";
    private void btnKitapSilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKitapSilActionPerformed
        if(isim != ""){
            KitapSilme(isim);
            
            isim = "";
            comboboxlariDoldur();
            kitaplariTabloyaYerlestir();
            kitaplariTabloyaYerlestir2();
        }else{
            lblKitapSilMesaage.setText("Kitap seçilmedi!!");
        }
    }//GEN-LAST:event_btnKitapSilActionPerformed

    private void tblKitapSilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKitapSilMouseClicked
        row3 = tblKitapSil.getSelectedRow();
        isim = (String) tblKitapSil.getModel().getValueAt(row3, 1);
    }//GEN-LAST:event_tblKitapSilMouseClicked

    private void btnKitapOduncVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKitapOduncVerActionPerformed
        
        String kitapIsmi = (String) cmbKitaplar.getSelectedItem();
        String kullaniciAdi = (String) cmbKullanicilar.getSelectedItem();
        String telefonNo = txtTelefonNo.getText();
        String tarih = txtTarih.getText();
        ArrayList<BaseKitap> kitaplar = null;
         try {
             kitaplar = kitaplariAl(kitapIsmi);
         } catch (SQLException ex) {
             Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         for(BaseKitap kitap : kitaplar){
             if(kitap.getAdi().equals(kitapIsmi)){
                 if(kitap.getAdedi() > 0){
                    if(!kitapIsmi.equals("") && !kullaniciAdi.equals("") && !telefonNo.equals("") && !tarih.equals("")){
                        kitapOduncVer(kitapIsmi, kullaniciAdi, yoneticiAdi, tarih, telefonNo);
                        
                        
                    }else{
                         lblKitapOduncVerMesaj.setText("Alanlar boş bırakılamaz");
                    }
                 }else{
                    lblKitapOduncVerMesaj.setText("Kitap mevcut değil");
                }
                 break;
             }
         }

    }//GEN-LAST:event_btnKitapOduncVerActionPerformed

    private void txtKitapOduncVerSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKitapOduncVerSearchKeyReleased
        String searchKey = txtKitapOduncVerSearch.getText();
       TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<DefaultTableModel>(modelOduncVerilenKitaplar);
       tblKitapIadeAl.setRowSorter(tableRowSorter);
       tableRowSorter.setRowFilter(RowFilter.regexFilter(searchKey));
    }//GEN-LAST:event_txtKitapOduncVerSearchKeyReleased

    
    
    private void btnKitapIadeAlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKitapIadeAlActionPerformed
       
        if(silinecekKitap != ""){
            oduncVerilenKitapSilme(silinecekKitap);
            kitapGuncelleArttir(silinecekKitap);
            silinecekKitap = "";
            comboboxlariDoldur();
            
        }else{
            lblIadeMesaji.setText("Kitap seçilmedi!!");
        }
        
    }//GEN-LAST:event_btnKitapIadeAlActionPerformed

    int row4 = -1;
    String silinecekKitap = "";
    private void tblKitapIadeAlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKitapIadeAlMouseClicked
        row4 = tblKitapIadeAl.getSelectedRow();
        silinecekKitap = (String) tblKitapIadeAl.getModel().getValueAt(row4, 0);
    }//GEN-LAST:event_tblKitapIadeAlMouseClicked

    private void cmbKitaplarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKitaplarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKitaplarActionPerformed

    private void txtYoneticiEkleİsimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtYoneticiEkleİsimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtYoneticiEkleİsimActionPerformed

    private void btnKitapGuncelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKitapGuncelleActionPerformed
        kitapGuncellePanel();
    }//GEN-LAST:event_btnKitapGuncelleActionPerformed

    private void btnCikisYapActionPerformedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCikisYapActionPerformedActionPerformed
         setVisible(false);
        GirisFrame girisFrame=new GirisFrame();
        girisFrame.setVisible(true);
    }//GEN-LAST:event_btnCikisYapActionPerformedActionPerformed

    private void txtKitapSilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKitapSilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKitapSilActionPerformed

    private void txtKitapSilKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKitapSilKeyReleased
        String searchKey = txtKitapSil.getText();
       TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<DefaultTableModel>(modelKitaplar);
       tblKitapSil.setRowSorter(tableRowSorter);
       tableRowSorter.setRowFilter(RowFilter.regexFilter(searchKey));
    }//GEN-LAST:event_txtKitapSilKeyReleased

    private void btnVerilenKitapGuncelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerilenKitapGuncelleActionPerformed
        kitapAlVerGuncelle();
    }//GEN-LAST:event_btnVerilenKitapGuncelleActionPerformed

    private void cmbKullanicilarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbKullanicilarItemStateChanged

         try {
             String kullaniciAdi = (String) cmbKullanicilar.getSelectedItem();
             ArrayList<Kullanici> kullanicilar = kullanicilariAl();
             Kullanici alinanKullanici = null;
             for(Kullanici kullanici : kullanicilar){
                 if(kullanici.getKullaniciAdi().equals(kullaniciAdi)){
                     alinanKullanici = kullanici;
                     break;
                 }
             }
             
             txtTelefonNo.setText(alinanKullanici.getTelefonno());
         } catch (SQLException ex) {
             Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_cmbKullanicilarItemStateChanged

    private void txtKullaniciAraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKullaniciAraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKullaniciAraActionPerformed

    private void txtKitapOduncVerSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKitapOduncVerSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKitapOduncVerSearchActionPerformed

    
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AnaMenuFrame().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCikisYapActionPerformed;
    private javax.swing.JButton btnGuncelle;
    private javax.swing.JButton btnKitapEkle;
    private javax.swing.JButton btnKitapGuncelle;
    private javax.swing.JButton btnKitapIadeAl;
    private javax.swing.JButton btnKitapOduncVer;
    private javax.swing.JButton btnKitapSil;
    private javax.swing.JButton btnKullaniciEkle;
    private javax.swing.JButton btnKullaniciSil;
    private javax.swing.JButton btnVerilenKitapGuncelle;
    private javax.swing.JButton btnYoneticiEkle;
    private javax.swing.JButton btnYoneticiGuncelle;
    private javax.swing.JButton btnYoneticiSil;
    private javax.swing.JComboBox<String> cmbKitaplar;
    private javax.swing.JComboBox<String> cmbKullanicilar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblAdedi;
    private javax.swing.JLabel lblBasimYeri;
    private javax.swing.JLabel lblBasimYili;
    private javax.swing.JLabel lblEkleMessage;
    private javax.swing.JLabel lblHesapIsim;
    private javax.swing.JLabel lblIadeMesaji;
    private javax.swing.JLabel lblIsim;
    private javax.swing.JLabel lblKitapGuncelleMessage;
    private javax.swing.JLabel lblKitapOduncVerMesaj;
    private javax.swing.JLabel lblKitapSilMesaage;
    private javax.swing.JLabel lblKullaniciAdi;
    private javax.swing.JLabel lblKullaniciAra;
    private javax.swing.JLabel lblKullaniciIsmi;
    private javax.swing.JLabel lblKullaniciSifre;
    private javax.swing.JLabel lblMessage3;
    private javax.swing.JLabel lblMessage4;
    private javax.swing.JLabel lblMessage5;
    private javax.swing.JLabel lblTuru;
    private javax.swing.JLabel lblVerilenKitapMessage;
    private javax.swing.JLabel lblYazar;
    private javax.swing.JLabel lblYoneticiEkleMessage;
    private javax.swing.JLabel lblYoneticiGuncelleMesaage;
    private javax.swing.JLabel lblYoneticiSilMessage;
    private javax.swing.JPanel panel;
    private javax.swing.JTabbedPane pnl1;
    private javax.swing.JPanel pnlKitapEkle;
    private javax.swing.JPanel pnlKitapGuncelle;
    private javax.swing.JPanel pnlKitapListele;
    private javax.swing.JPanel pnlKitapOduncVer;
    private javax.swing.JPanel pnlKullaniciEkle;
    private javax.swing.JPanel pnlKullaniciListele;
    private javax.swing.JPanel pnlYoneticiEkle;
    private javax.swing.JPanel pnlYoneticiGuncelle;
    private javax.swing.JPanel pnlYoneticiListele;
    private javax.swing.JTable tblBooks;
    private javax.swing.JTable tblKitapIadeAl;
    private javax.swing.JTable tblKitapSil;
    private javax.swing.JTable tblKullanicilar;
    private javax.swing.JTable tblYoneticiListele1;
    private javax.swing.JTextField txtAdedi;
    private javax.swing.JTextField txtBaseYoneticiAdi;
    private javax.swing.JPasswordField txtBaseYoneticiSifre;
    private javax.swing.JTextField txtBasimYeri;
    private javax.swing.JTextField txtBasimYili;
    private javax.swing.JTextField txtIsim;
    private javax.swing.JTextField txtKitapGuncelleAdedi;
    private javax.swing.JTextField txtKitapGuncelleAdi;
    private javax.swing.JTextField txtKitapGuncelleYazari;
    private javax.swing.JTextField txtKitapListele;
    private javax.swing.JTextField txtKitapOduncVerSearch;
    private javax.swing.JTextField txtKitapSil;
    private javax.swing.JTextField txtKullaniciAdi;
    private javax.swing.JTextField txtKullaniciAdiGuncelleme;
    private javax.swing.JTextField txtKullaniciAra;
    private javax.swing.JPasswordField txtKullaniciEskiSifre;
    private javax.swing.JTextField txtKullaniciGuncelleTelefon;
    private javax.swing.JTextField txtKullaniciIsmi;
    private javax.swing.JPasswordField txtKullaniciSifre;
    private javax.swing.JTextField txtKullaniciTelefonno;
    private javax.swing.JPasswordField txtKullaniciYeniSifre;
    private javax.swing.JTextField txtTarih;
    private javax.swing.JTextField txtTelefonNo;
    private javax.swing.JTextField txtTuru;
    private javax.swing.JTextField txtVerilenKtpGuncelleIsim;
    private javax.swing.JTextField txtVerilenKtpGuncelleKullanici;
    private javax.swing.JTextField txtVerilenKtpGuncelleTarih;
    private javax.swing.JTextField txtYazar;
    private javax.swing.JTextField txtYoneticiEkleMail;
    private javax.swing.JTextField txtYoneticiEkleSifre;
    private javax.swing.JTextField txtYoneticiEkleİsim;
    private javax.swing.JPasswordField txtYoneticiEskiPassword;
    private javax.swing.JTextField txtYoneticiGuncelleMail;
    private javax.swing.JTextField txtYoneticiListeleAra;
    private javax.swing.JPasswordField txtYoneticiYeniPassword;
    // End of variables declaration//GEN-END:variables

    private static class FontImpl extends Font {

        public FontImpl(String name, int style, int size) {
            super(name, style, size);
        }
    }
}
