/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.DaoActif;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextField;
import models.*;
import javax.swing.table.DefaultTableModel;


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
            if (dette.getLibelle()!=null){ligne[1] = dette.getLibelle();}
            ligne[2] = dette.getDateCreation();
            ligne[3] = dette.getMontant();
            if (dette.getDetteActuelle()!=null){ligne[4] = dette.getDetteActuelle();}
            ligne[5] = dette.getIdDette();
            model.addRow(ligne);
        }
        listactif.getColumnModel().getColumn(5).setMinWidth(0);
        listactif.getColumnModel().getColumn(5).setMaxWidth(0);
        listactif.getColumnModel().getColumn(5).setWidth(0);
       
    }
    
    public DetteDetaillee showDetail(int colonneId, JTable tableE, JTable tableS,JTextField idDette,JTextField nom,JTextField mail,JTextField libelle,JTextField montant,JTextField info,JTextField actionEntreprendre,JTextField actionEffectuee){
        int ligneE = tableE.getSelectedRow();
        String id = (String)tableE.getValueAt(ligneE, colonneId);
        DetteDetaillee detailactif=daoact.voirDetailActif(id);
        nom.setText(detailactif.getRedev().getNom());
        mail.setText(detailactif.getRedev().getAdresseMail());
        libelle.setText(detailactif.getLibelle());
        montant.setText(String.valueOf(detailactif.getMontant()));
        info.setText(detailactif.getInfoComplementaire());
        actionEntreprendre.setText(detailactif.getActionEntreprendre());
        actionEffectuee.setText(detailactif.getActionEffectuee());
        idDette.setText(detailactif.getIdDette());
        
        
        Object[] ligneS = new Object[7]; 
        ArrayList<EcheanceDetaillee> echeances = detailactif.getEd();
        DefaultTableModel model = (DefaultTableModel)tableS.getModel();
        model.setRowCount(0);
        
        int i=1;
        for(EcheanceDetaillee echeance: echeances){
            ligneS[0]= "Deadline " + i;
            ligneS[1] = echeance.getDateDeadLine();
            ligneS[2] = echeance.getMontant();
            ligneS[3] = echeance.isStatutPaiement();
            if (echeance.getDatePaiement()!=null){ligneS[4] = echeance.getDatePaiement();}
            ligneS[5] = echeance.isStatutAnnulation();
            if (echeance.getRaisonAnnulation()!=null){ligneS[6] = echeance.getRaisonAnnulation();}
            model.addRow(ligneS);
            i++;
        }
        nom.setEditable(false);
        mail.setEditable(false);
        libelle.setEditable(false);
        montant.setEditable(false);
        info.setEditable(false);
        actionEntreprendre.setEditable(false);
        actionEffectuee.setEditable(false);
        idDette.setEditable(false);
        return detailactif;
    }
    
    public boolean dernierCard(JTable tableActif, JTextField idDette){
        
        int rowsActif = tableActif.getRowCount();
        String idDetails =idDette.getText();
        String idTable;
        boolean estActif=false;
        if (!(rowsActif == 0)){
            for(int i=0;i<rowsActif; i++){
                idTable = (String)tableActif.getValueAt(i, 5);
                if(idTable.equals(idDetails)){
                    estActif=true;
                }
            }   
        }
        return estActif;
    }

}
