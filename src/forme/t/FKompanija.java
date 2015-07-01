/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.t;

import domen.Kompanija;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import poslovnalogika.Kontroler;

/**
 *
 * @author student1
 */
public class FKompanija extends javax.swing.JFrame {

//    KolekcijaPartnera kp;
    /**
     * Creates new form FPoslovniPartner
     */
    public FKompanija() {
        initComponents();
        srediFormu();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jtxtPIB = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtxtMaticniBroj = new javax.swing.JTextField();
        jtxtNaziv = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtxtZR = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtxtDatumOsnivanja = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jtxtUlicaIBroj = new javax.swing.JTextField();
        jbtnSacuvaj = new javax.swing.JButton();
        errMatBroj = new javax.swing.JLabel();
        errDatum = new javax.swing.JLabel();
        jtxtPoruka = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Unos kompanije proizvođača");

        jLabel1.setText("PIB:");

        jtxtPIB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtPIBActionPerformed(evt);
            }
        });

        jLabel2.setText("*Matični broj:");

        jtxtMaticniBroj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtMaticniBrojActionPerformed(evt);
            }
        });

        jtxtNaziv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtNazivActionPerformed(evt);
            }
        });

        jLabel3.setText("Naziv:");

        jtxtZR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtZRActionPerformed(evt);
            }
        });

        jLabel4.setText("Žiro račun:");

        jtxtDatumOsnivanja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtDatumOsnivanjaActionPerformed(evt);
            }
        });

        jLabel5.setText("Datum osnivanja:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Sedište"));

        jLabel7.setText("Adresa:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jtxtUlicaIBroj))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jtxtUlicaIBroj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jbtnSacuvaj.setText("Sačuvaj");
        jbtnSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSacuvajActionPerformed(evt);
            }
        });

        errMatBroj.setForeground(new java.awt.Color(204, 0, 51));
        errMatBroj.setText(" ");

        errDatum.setForeground(new java.awt.Color(204, 0, 0));
        errDatum.setText(" ");

        jtxtPoruka.setEditable(false);
        jtxtPoruka.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtPIB))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtMaticniBroj))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtZR))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errMatBroj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtxtNaziv)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errDatum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jtxtDatumOsnivanja, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jtxtPoruka)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(jbtnSacuvaj)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxtPIB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtxtMaticniBroj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errMatBroj)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtxtNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtxtZR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtxtDatumOsnivanja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errDatum)
                .addGap(3, 3, 3)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtxtPoruka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnSacuvaj, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtPIBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtPIBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtPIBActionPerformed

    private void jtxtMaticniBrojActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtMaticniBrojActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtMaticniBrojActionPerformed

    private void jtxtNazivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtNazivActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtNazivActionPerformed

    private void jtxtZRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtZRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtZRActionPerformed

    private void jtxtDatumOsnivanjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtDatumOsnivanjaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtDatumOsnivanjaActionPerformed

    private void jbtnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSacuvajActionPerformed
        String pib = jtxtPIB.getText().trim();
        String matBroj = jtxtMaticniBroj.getText().trim();
        String ziroRacun = jtxtZR.getText().trim();
        String naziv = jtxtNaziv.getText().trim();
        String sDatum = jtxtDatumOsnivanja.getText().trim();
        String ulicaIBroj = jtxtUlicaIBroj.getText().trim();

        boolean validacija = true;
        Date datum = null;

        if (!sDatum.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                datum = sdf.parse(sDatum);
            } catch (ParseException ex) {
                validacija = false;
                errDatum.setText("Datum mora biti u formatu: dd/MM/yyyy. ");
            }
        }
        if (matBroj.isEmpty()) {
            errMatBroj.setText("Morate uneti maticni broj. ");
            validacija = false;
        }
        if (validacija == true) {
            Kompanija pp = new Kompanija();
            pp.setPib(pib);
            pp.setMaticniBroj(matBroj);
            pp.setNaziv(naziv);
            pp.setDatumOsnivanja(datum);

            pp.setZr(ziroRacun);
            pp.setAdresa(ulicaIBroj);
            resetujLabele();
            try {

                Kontroler.getInstance().dodajKompaniju(pp);
                JOptionPane.showMessageDialog(this, "Kompanija je uspešno dodata");
                jtxtPoruka.setVisible(true);
                jtxtPoruka.setText("Kompanija je uspesno sacuvana");
            } catch (Exception ex) {
                jtxtPoruka.setText(ex.getMessage());
                JOptionPane.showMessageDialog(this, "Sistem ne može da kreira novu kompaniju", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jbtnSacuvajActionPerformed

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
            java.util.logging.Logger.getLogger(FKompanija.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FKompanija.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FKompanija.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FKompanija.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FKompanija().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel errDatum;
    private javax.swing.JLabel errMatBroj;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtnSacuvaj;
    private javax.swing.JTextField jtxtDatumOsnivanja;
    private javax.swing.JTextField jtxtMaticniBroj;
    private javax.swing.JTextField jtxtNaziv;
    private javax.swing.JTextField jtxtPIB;
    private javax.swing.JTextField jtxtPoruka;
    private javax.swing.JTextField jtxtUlicaIBroj;
    private javax.swing.JTextField jtxtZR;
    // End of variables declaration//GEN-END:variables

    private void srediFormu() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JOptionPane.showMessageDialog(this, "Kompanija je uspešno dodata");
        JOptionPane.showMessageDialog(this, "Sistem ne može da kreira novu kompaniju", "Greška", JOptionPane.ERROR_MESSAGE);
    }

    private void resetujLabele() {
        errDatum.setText("");
        errMatBroj.setText("");
        jtxtPoruka.setText("");
    }

}
