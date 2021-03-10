package com.company.SwingFiles;

import com.company.DataBase.DbHelper;
import com.company.PersonFile.Kullanici;
import com.company.PersonFile.Yonetici;
import java.awt.Color;
import java.awt.Point;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class GirisFrame extends javax.swing.JFrame {

    private static GirisFrame girisFrame=null;
    GirisFrame() {
        initComponents();
        add(panel);
        panel.setBackground(Color.WHITE);
        
        setSize(600, 600);
        setTitle("Kütüphane Sistemi");
        setLocation(400,100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }

    public static GirisFrame getInstance(){
        
        if (girisFrame == null){
            girisFrame = new GirisFrame();
        }

        return girisFrame;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtYoneticiAdi = new javax.swing.JTextField();
        txtYoneticiSifre = new javax.swing.JPasswordField();
        btn2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtKullaniciSifre = new javax.swing.JPasswordField();
        txtKullaniciAdi = new javax.swing.JTextField();
        btn1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane3.setBackground(new java.awt.Color(204, 204, 204));
        jTabbedPane3.setForeground(new java.awt.Color(255, 51, 0));
        jTabbedPane3.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel3.setText("YÖNETİCİ ADI");
        jLabel3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel4.setText("ŞİFRE");
        jLabel4.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtYoneticiAdi.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        txtYoneticiAdi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtYoneticiAdiActionPerformed(evt);
            }
        });

        txtYoneticiSifre.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        txtYoneticiSifre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtYoneticiSifreActionPerformed(evt);
            }
        });

        btn2.setBackground(new java.awt.Color(204, 255, 255));
        btn2.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btn2.setText("GİRİŞ İÇİN TIKLAYINIZ");
        btn2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/company/SwingFiles/kitap1.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtYoneticiAdi, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                            .addComponent(txtYoneticiSifre)))
                    .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(164, Short.MAX_VALUE))
            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 553, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(txtYoneticiAdi))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYoneticiSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 301, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("YÖNETİCİ GİRİŞİ", jPanel2);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel1.setText("KULLANICI ADI");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel2.setText("ŞİFRE");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtKullaniciSifre.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtKullaniciAdi.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        btn1.setBackground(new java.awt.Color(204, 255, 255));
        btn1.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btn1.setText("GİRİŞ İÇİN TIKLAYINIZ");
        btn1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/company/SwingFiles/kitap1.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtKullaniciSifre, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                            .addComponent(txtKullaniciAdi)))
                    .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(192, Short.MAX_VALUE))
            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 553, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtKullaniciAdi))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtKullaniciSifre))
                .addGap(27, 27, 27)
                .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 303, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab(" KULLANICI GİRİŞİ", jPanel1);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtYoneticiAdiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtYoneticiAdiActionPerformed
        
    }//GEN-LAST:event_txtYoneticiAdiActionPerformed

    private void txtYoneticiSifreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtYoneticiSifreActionPerformed
        
    }//GEN-LAST:event_txtYoneticiSifreActionPerformed

  
   public ArrayList<Yonetici> yoneticileriAl(){
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
               
              try {
                  statement.close();
                  connection.close();
              } catch (SQLException ex) {
                  Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
              }
              
           }
           
           return yoneticiler;
   }
   
   public void yoneticiGirisi(String yoneticiAdi,String sifre){
       ArrayList<Yonetici> alinanYoneticiler = yoneticileriAl();
       boolean sifreYanlisMi=true;
       for(Yonetici yonetici : alinanYoneticiler){
           
           if(yonetici.getYoneticiAdi().equals(yoneticiAdi) && yonetici.getSifre().equals(sifre)){
               try {
            Point deger=getLocation();
            AnaMenuFrame anamenuFrame=new AnaMenuFrame(deger,true,yoneticiAdi,"");
            anamenuFrame.setVisible(true);
            setVisible(false);
            sifreYanlisMi=false;
            } catch (SQLException ex) {
                Logger.getLogger(GirisFrame.class.getName()).log(Level.SEVERE, null, ex);
             }
           }
           
       }
       if(sifreYanlisMi){
                JOptionPane.showMessageDialog(this,"Kullanıci Adi veya Sifre Hatalı");
           }
       
   }
   
   public ArrayList<Kullanici> kullanicilariAl(){
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
                        resultSet.getString("isim").toString().trim(),
                        resultSet.getString("kullaniciAdi").toString().trim(),
                        resultSet.getString("sifre").toString().trim(),
                        resultSet.getString("telefonno").toString().trim()
                        
                );
                
                kullanicilar.add(kullanici);
            }
            
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
           
           return kullanicilar;
   }
    
   public void kullaniciGirisi(String kullaniciAdi,String sifre){
       ArrayList<Kullanici> alinanKullanicilar = kullanicilariAl();
       
       boolean sifreYanlisMi=true;
       for(Kullanici kullanici : alinanKullanicilar){
           
           if(kullanici.getKullaniciAdi().equals(kullaniciAdi) && kullanici.getSifre().equals(sifre)){
               try {
            Point deger=getLocation();
            AnaMenuFrame anamenuFrame=new AnaMenuFrame(deger,false,"",kullaniciAdi);
            anamenuFrame.setVisible(true);
            setVisible(false);
            sifreYanlisMi=false;
           
            } catch (SQLException ex) {
                Logger.getLogger(GirisFrame.class.getName()).log(Level.SEVERE, null, ex);
             }
           }
           
       }
       if(sifreYanlisMi){
               
               JOptionPane.showMessageDialog(this,"Kullanıci Adi veya Sifre Hatalı");
           }
       
   }
    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        
        
        String kullaniciAdi = txtKullaniciAdi.getText().toString();
        String sifre = txtKullaniciSifre.getText().toString();
        kullaniciGirisi(kullaniciAdi, sifre);
        
      
        
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        
        
        String yoneticiAdi = txtYoneticiAdi.getText().toString();
        String sifre = txtYoneticiSifre.getText().toString();
        yoneticiGirisi(yoneticiAdi, sifre);
        
      
    }//GEN-LAST:event_btn2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GirisFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField txtKullaniciAdi;
    private javax.swing.JPasswordField txtKullaniciSifre;
    private javax.swing.JTextField txtYoneticiAdi;
    private javax.swing.JPasswordField txtYoneticiSifre;
    // End of variables declaration//GEN-END:variables
}

