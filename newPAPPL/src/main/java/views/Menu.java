/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.ConActif;
import controllers.ConHistorique;
import java.awt.CardLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;


/**
 *
 * @author Luz
 */
public class Menu extends javax.swing.JPanel {

    /**
     * Creates new form Panel2
     */
   private JPanel panel;
   private ConHistorique conhisto;
   private ConActif conactif;
   
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
    
    public Menu() {
        initComponents();
        this.conhisto = new ConHistorique();
        this.conactif = new ConActif();
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
        historique = new javax.swing.JButton();
        actif = new javax.swing.JButton();
        creationredevable = new javax.swing.JButton();

        jLabel1.setText("Menu");

        historique.setText("Historique des redevables");
        historique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historiqueActionPerformed(evt);
            }
        });

        actif.setText("Redevables Actifs");
        actif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actifActionPerformed(evt);
            }
        });

        creationredevable.setText("Créer un redevable");
        creationredevable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creationredevableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 192, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(182, 182, 182))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(historique, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(actif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(creationredevable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(creationredevable)
                .addGap(18, 18, 18)
                .addComponent(historique)
                .addGap(18, 18, 18)
                .addComponent(actif)
                .addContainerGap(112, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void historiqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historiqueActionPerformed
        // TODO add your handling code here:
         JComboBox annee = ((RechercheHisto)panel.getComponent(2)).getAnnee();
         JComboBox mois1 = ((RechercheHisto)panel.getComponent(2)).getMois1();
         JComboBox mois2 = ((RechercheHisto)panel.getComponent(2)).getMois2();
         conhisto.rechercheHistorique(annee, mois1, mois2);
        ((CardLayout)panel.getLayout()).show(panel, "p2");
    }//GEN-LAST:event_historiqueActionPerformed

    private void actifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actifActionPerformed
         JTable table = ((Actif)panel.getComponent(4)).getjTable1();
         conactif.showListeActif(table);
        ((CardLayout)panel.getLayout()).show(panel, "p4");
    }//GEN-LAST:event_actifActionPerformed

    private void creationredevableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creationredevableActionPerformed
          ((CardLayout)panel.getLayout()).show(panel, "p7");// TODO add your handling code here:
    }//GEN-LAST:event_creationredevableActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actif;
    private javax.swing.JButton creationredevable;
    private javax.swing.JButton historique;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

   


}
