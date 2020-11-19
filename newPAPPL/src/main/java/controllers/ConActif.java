/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.DaoActif;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import models.DetteDetaillee;
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
    
    public void showListeActif(JTextArea listactif){
        ArrayList<DetteSimplifiee> actif=daoact.demandeListeActifs();
        listactif.setText(actif.toString());
    }
    
    public void showDetailActif(JTextField nom,JTextField mail,JTextField libelle,JTextField montant,JTextField info,JTextField action_entreprendre,JTextField action_effectuee){
        DetteDetaillee detailactif=daoact.voirDetailActif();
        nom.setText(detailactif.getRedev().getNom());
        mail.setText(detailactif.getRedev().getAdresseMail());
        libelle.setText(detailactif.getLibelle());
        montant.setText(String.valueOf(detailactif.getMontant()));
        
    }
}
