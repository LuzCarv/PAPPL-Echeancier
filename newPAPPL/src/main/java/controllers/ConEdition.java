/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import models.DetteDetaillee;
import models.EcheanceDetaillee;

/**
 *
 * @author Luz
 */
public class ConEdition {
    
    public void afficherDonneesEditionAH(DetteDetaillee detteDetail, JTable table,JTextField idDette,JTextField nom,JTextField mail,JTextField libelle,JTextField montant,JTextField info,JTextField actionEntreprendre,JTextField actionEffectuee){
        nom.setText(detteDetail.getRedev().getNom());
        mail.setText(detteDetail.getRedev().getAdresseMail());
        libelle.setText(detteDetail.getLibelle());
        montant.setText(String.valueOf(detteDetail.getMontant()));
        info.setText(detteDetail.getInfoComplementaire());
        actionEntreprendre.setText(detteDetail.getActionEntreprendre());
        actionEffectuee.setText(detteDetail.getActionEffectuee());
        idDette.setText(detteDetail.getIdDette());
        
        Object[] ligneS = new Object[7]; 
        ArrayList<EcheanceDetaillee> echeances = detteDetail.getEd();
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        
        int i=1;
        for(EcheanceDetaillee echeance: echeances){
            ligneS[0]= "Deadline " + i;
            ligneS[1] = echeance.getDateDeadLine();
            ligneS[2] = echeance.getMontant();
            ligneS[3] = echeance.isStatutPaiement();
            ligneS[4] = echeance.getDatePaiement();
            ligneS[5] = echeance.isStatutAnnulation();
            ligneS[6] = echeance.getRaisonAnnulation();
            model.addRow(ligneS);
            i++;
        }
    }
}
