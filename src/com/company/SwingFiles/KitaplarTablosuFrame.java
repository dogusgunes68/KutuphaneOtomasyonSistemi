/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.SwingFiles;

import com.company.DataBase.DbHelper;
import com.company.KitapModeli.BaseKitap;
import java.awt.Point;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Dogus
 */
public class KitaplarTablosuFrame extends javax.swing.JFrame {

  
    DefaultTableModel model;
    public KitaplarTablosuFrame(Point location) {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(location);
        kitaplariTabloyaYerlestir();
    }

    private KitaplarTablosuFrame() {
        
    }
    
    public ArrayList<BaseKitap> kitaplariAl() throws java.sql.SQLException, java.sql.SQLException, IOException {
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
    public void kitaplariTabloyaYerlestir(){
        model = (DefaultTableModel)tblSecilecekKitaplar.getModel();
        model.setRowCount(0);
        try {

            ArrayList<BaseKitap> alinanKitaplar = kitaplariAl();
        
            for(BaseKitap kitap : alinanKitaplar){
             
                Object[] row = {kitap.getAdi(),kitap.getYazari(),kitap.getBasimYeri(),
                        kitap.getBasimYili(),kitap.getAdedi(),kitap.getTuru()};
                model.addRow(row);
              
            }
        } catch (java.sql.SQLException exception) {
           exception.printStackTrace();
        } catch (IOException ex) {
             Logger.getLogger(AnaMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
         }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblSecilecekKitaplar = new javax.swing.JTable();
        btnKitapSec = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtKitapAra = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblSecilecekKitaplar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "İsim", "Yazar", "Basım Yeri", "Basım Yılı", "Adedi", "Türü"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSecilecekKitaplar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSecilecekKitaplarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSecilecekKitaplar);

        btnKitapSec.setText("SEÇ");
        btnKitapSec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKitapSecActionPerformed(evt);
            }
        });

        jLabel1.setText("Kitap Ara : ");

        txtKitapAra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKitapAraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtKitapAra))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(btnKitapSec, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtKitapAra, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnKitapSec, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKitapSecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKitapSecActionPerformed
        
    }//GEN-LAST:event_btnKitapSecActionPerformed

    String kitapIsmi = "";
    int row = 0;
    private void txtKitapAraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKitapAraActionPerformed
         String searchKey = txtKitapAra.getText();
        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<DefaultTableModel>(model);
        tblSecilecekKitaplar.setRowSorter(tableRowSorter);
        tableRowSorter.setRowFilter(RowFilter.regexFilter(searchKey));
    }//GEN-LAST:event_txtKitapAraActionPerformed

    private void tblSecilecekKitaplarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSecilecekKitaplarMouseClicked
        btnKitapSec.setVisible(true);
        row = tblSecilecekKitaplar.getSelectedRow();
        kitapIsmi = (String) tblSecilecekKitaplar.getModel().getValueAt(row, 0);
    }//GEN-LAST:event_tblSecilecekKitaplarMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KitaplarTablosuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KitaplarTablosuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KitaplarTablosuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KitaplarTablosuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KitaplarTablosuFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKitapSec;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSecilecekKitaplar;
    private javax.swing.JTextField txtKitapAra;
    // End of variables declaration//GEN-END:variables
}
