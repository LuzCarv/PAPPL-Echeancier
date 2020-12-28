/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.ConCreation;
import controllers.ConExcel;
import java.awt.CardLayout;
import java.io.File;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Luz
 */
public class OptionsCreation extends javax.swing.JPanel {

    /**
     * Creates new form OptionsCreation
     */
    private JPanel panel;
    private ConExcel conExcel;
    private ConCreation conCreation;
    
      public void setPanel(JPanel panel) {
        this.panel = panel;
    }
    
    public OptionsCreation() {
        initComponents();
        conExcel = new ConExcel();
        conCreation = new ConCreation();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        manuel = new javax.swing.JButton();
        excel = new javax.swing.JButton();
        annuler = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setBackground(new java.awt.Color(0, 51, 102));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Logiciel Echéancier", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 204, 0))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 204, 0));
        jLabel1.setText("Creation d'une redevable");

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 204, 0));
        jLabel2.setText("Choisissez la manière dont vous allez créer le redevable: ");

        manuel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        manuel.setText("Saisi Manuel");
        manuel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manuelActionPerformed(evt);
            }
        });

        excel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        excel.setText("Import d'Excel");
        excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excelActionPerformed(evt);
            }
        });

        annuler.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        annuler.setText("Annuler");
        annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(manuel)
                        .addGap(104, 104, 104)
                        .addComponent(excel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(343, 343, 343)
                        .addComponent(annuler, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(jLabel2)))
                .addContainerGap(190, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addGap(74, 74, 74)
                .addComponent(jLabel2)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(excel)
                    .addComponent(manuel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(annuler)
                .addGap(71, 71, 71))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void annulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerActionPerformed
       ((CardLayout)panel.getLayout()).show(panel, "p1");
    }//GEN-LAST:event_annulerActionPerformed

    private void manuelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manuelActionPerformed
      JComboBox agentComptable = ((Creation)panel.getComponent(7)).getListeAgent();
      ((Creation)panel.getComponent(7)).setListeAgent(conCreation.afficherAgent(agentComptable));
      JComboBox nbEcheances =((Creation)panel.getComponent(7)).getNbEcheances();
      ((Creation)panel.getComponent(7)).setNbEcheances(conCreation.afficherEcheances(nbEcheances));
      ((CardLayout)panel.getLayout()).show(panel, "p7");
    }//GEN-LAST:event_manuelActionPerformed

    private void excelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excelActionPerformed
        File fichier = conExcel.chosirFichier(panel);
        JTextField libelle = ((Creation)panel.getComponent(7)).getLibelle();
        JTextField nom = ((Creation)panel.getComponent(7)).getNomRedevable();
        JTextField montant = ((Creation)panel.getComponent(7)).getMontant();
        JTable echeances = ((Creation)panel.getComponent(7)).getListeEcheances();
        JComboBox agentComptable = ((Creation)panel.getComponent(7)).getListeAgent();
        ((Creation)panel.getComponent(7)).setListeAgent(conCreation.afficherAgent(agentComptable));
        JComboBox nbEcheances =((Creation)panel.getComponent(7)).getNbEcheances();
        ((Creation)panel.getComponent(7)).setNbEcheances(conCreation.afficherEcheances(nbEcheances));
        conExcel.ajouterDonnees(fichier, nom, libelle, montant, echeances);
        ((CardLayout)panel.getLayout()).show(panel, "p7");
        
    }//GEN-LAST:event_excelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton annuler;
    private javax.swing.JButton excel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton manuel;
    // End of variables declaration//GEN-END:variables
}
