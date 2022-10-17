package banka3;

import static banka3.Giris2.parola;
import static banka3.Giris2.sorgu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class KayitEkran extends Giris2 {
    
    public KayitEkran() {
        initComponents();
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        adGiris = new javax.swing.JTextField();
        soyAdGiris = new javax.swing.JTextField();
        sifreGiris = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        mesajKutu = new javax.swing.JTextArea();
        tamamButon = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(350, 300));

        jLabel1.setText("İsim");

        jLabel2.setText("Soyisim");

        jLabel3.setText("Şifre");

        mesajKutu.setColumns(20);
        mesajKutu.setRows(5);
        jScrollPane1.setViewportView(mesajKutu);

        tamamButon.setText("Tamam");
        tamamButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tamamButonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(adGiris, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tamamButon)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(soyAdGiris, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                    .addComponent(sifreGiris))))))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adGiris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(soyAdGiris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sifreGiris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(14, 14, 14)
                .addComponent(tamamButon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tamamButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tamamButonActionPerformed
       try{      
        String ad=adGiris.getText();
        String soyad=soyAdGiris.getText();
        String sifre=String.valueOf(sifreGiris.getPassword());     
        Connection c = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/banka?user=root&password=1234");
        String query = "INSERT INTO hesap(ad, soyad,sifre) VALUES (?,?,?)";
        PreparedStatement pt = c.prepareStatement(query);
        pt.setString(1,ad);
        pt.setString(2,soyad);
        pt.setString(3,sifre);
        pt.executeUpdate();
        
        int yeniHesap=0;
        int yeniBakiye=0;
       
        int sayac=0;
        ResultSet rs = pt.executeQuery("SELECT * FROM hesap");                       
        while(rs.next()){
              sayac++;            
        }
        pt.close();        
        int sayac2=0;
        Connection c2 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/banka?user=root&password=1234");
        Statement st2 = c2.createStatement();
        ResultSet rs2 = st2.executeQuery("SELECT * FROM hesap");
         while(rs2.next()){             
            if(sayac2+1==sayac){
               yeniHesap= rs2.getInt("idhesap");
               yeniBakiye=rs2.getInt("bakiye");          
            }                
          sayac2++;
        }         
        mesajKutu.setText("Yeni hesap oluşturuldu.\n Hesap No:  "+yeniHesap+"\n Ad:  "+ad+
                      "\n soy ad:  "+soyad+"\n şifre:  "+sifre+"\n bakiye: "+yeniBakiye);
                      
  }   
   catch (Exception e){
           System.out.println("bağlantı hatası kayıt ekranı"+e);   
   }
    }//GEN-LAST:event_tamamButonActionPerformed

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
            java.util.logging.Logger.getLogger(KayitEkran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KayitEkran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KayitEkran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KayitEkran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KayitEkran().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adGiris;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea mesajKutu;
    private javax.swing.JPasswordField sifreGiris;
    private javax.swing.JTextField soyAdGiris;
    private javax.swing.JButton tamamButon;
    // End of variables declaration//GEN-END:variables
}
