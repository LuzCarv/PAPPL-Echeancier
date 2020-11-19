/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.DaoActif;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import models.DetteDetaillee;
import javax.swing.table.DefaultTableModel;
import models.DetteSimplifiee;

/**
 *
 * @author 96441
 */
public class ConActif {
    private DaoActif daoact;
    public ConActif(){
        this.daoact=new DaoActif();
    }
    
    public void showListeActif(JTable listactif){
        ArrayList<DetteSimplifiee> actif=daoact.demandeListeActifs();
        
        DefaultTableModel model = (DefaultTableModel)listactif.getModel();
        model.setRowCount(0);
        Object[] ligne = new Object[6]; 
        for(DetteSimplifiee dette: actif){
            ligne[0]= dette.getRedev().getNom();
            ligne[1] = dette.getLibelle();
            ligne[2] = dette.getDateCreation();
            ligne[3] = dette.getMontant();
            ligne[4] = dette.getDetteActuelle();
            ligne[5] = dette.getIdDette();
            model.addRow(ligne);
        }
        listactif.getColumnModel().getColumn(5).setMinWidth(0);
        listactif.getColumnModel().getColumn(5).setMaxWidth(0);
        listactif.getColumnModel().getColumn(5).setWidth(0);
    }
    
    public void showDetailActif(JTable table,JTextField nom,JTextField mail,JTextField libelle,JTextField montant,JTextField info,JTextField actionEntreprendre,JTextField actionEffectuee){
        int ligne = table.getSelectedRow();
        String id = (String)table.getValueAt(ligne, 5);
        DetteDetaillee detailactif=daoact.voirDetailActif(id);
        nom.setText(detailactif.getRedev().getNom());
        mail.setText(detailactif.getRedev().getAdresseMail());
        libelle.setText(detailactif.getLibelle());
        montant.setText(String.valueOf(detailactif.getMontant()));
        info.setText(detailactif.getInfoComplementaire());
        actionEntreprendre.setText(detailactif.getActionEntreprendre());
        actionEffectuee.setText(detailactif.getActionEffectuee());
    }

}
