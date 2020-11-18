/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.DaoHistorique;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import models.*;

/**
 *
 * @author Luz
 */

public class ConHistorique {
    
    private DaoHistorique daohisto;

    public ConHistorique() {
        this.daohisto = new DaoHistorique();
    }
    
    
    public void rechercheHistorique(JComboBox annee, JComboBox mois1, JComboBox mois2){
        
        mois1.removeAllItems();
        mois2.removeAllItems();
        annee.removeAllItems();
        mois1.addItem("");
        mois2.addItem("");
        for(int i=1;i<=12;i++){
            Month mois;
            mois =Month.of(i);
            mois1.addItem(i);
            mois2.addItem(i);
        }
        //Il faut ajouter la condition que mois1> mois2
        Calendar cal= Calendar.getInstance();
        annee.addItem("");
        int ann = cal.get(Calendar.YEAR);
        for(int i=2018;i<=ann;i++){
            annee.addItem(i);
        }      
    }
    
    
    public boolean showListeHistorique(JTextArea text,JComboBox annee, JComboBox mois1, JComboBox mois2, JTextField nom){
        
        int ann;
        int mo1;
        int mo2;
        boolean recherche;
        
        try{
          ann = (Integer)annee.getSelectedItem();  
        }catch(Exception e){
          ann = 0;
        }
        
        try{
          mo1 = (Integer)mois1.getSelectedItem();  
        }catch(Exception e){
          mo1 = 0;
        }
        try{
          mo2 = (Integer)mois2.getSelectedItem();  
        }catch(Exception e){
          mo2 = 0;
        }

        ArrayList<DetteSimplifiee> dettes = daohisto.demandeHistorique(nom.getText(), ann, mo1, mo2);
        if (dettes.size()==0){
            recherche = false;
        }else{
        text.setText(dettes.toString());
            recherche = true;
        }
        
        return recherche;
    }
    
}