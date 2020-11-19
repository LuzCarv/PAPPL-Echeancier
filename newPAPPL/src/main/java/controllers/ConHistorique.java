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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
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
    
    
    public boolean showListeHistorique(JTable table,JComboBox annee, JComboBox mois1, JComboBox mois2, JTextField nom){
        
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
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            model.setRowCount(0);
            Object[] ligne = new Object[5]; 
            for(DetteSimplifiee dette: dettes){
                ligne[0]= dette.getRedev().getNom();
                ligne[1] = dette.getLibelle();
                ligne[2] = dette.getDateCreation();
                ligne[3] = dette.getMontant();
                ligne[4] = dette.getIdDette();
                model.addRow(ligne);
            }
            recherche = true;
        }
        /*
        table.getColumnModel().getColumn(4).setMinWidth(0);
        table.getColumnModel().getColumn(4).setMaxWidth(0);
        table.getColumnModel().getColumn(4).setWidth(0);
        */
        
        return recherche;
    }
    
    public void voirDetail(){
    
}
}
