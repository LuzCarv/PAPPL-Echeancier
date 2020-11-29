/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.DaoCreation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextField;
import models.EcheanceSimplifiee;

/**
 *
 * @author 96441
 */
public class ConCreation {
    public void enregistrerRedevable(JTable table,JTextField mailRedevable, JTextField nomRedevable,JTextField libelle, JTextField montant, JTextField infoComplementaire, JTextField mailAgent,JTextField nomAgent){
        ArrayList<EcheanceSimplifiee> echeances=new ArrayList<EcheanceSimplifiee>();
        EcheanceSimplifiee e=new EcheanceSimplifiee();
        for (int i=0;i<table.getRowCount();i++){
            e.setMontant((Double)table.getValueAt(i, 2));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            e.setDateDeadLine(LocalDate.parse((String)(table.getValueAt(i,1)),formatter));
            echeances.add(e);
        }
        DaoCreation daocreation=new DaoCreation();
        daocreation.CreationRedevable(mailRedevable.getText(), nomRedevable.getText(), echeances, libelle.getText(), montant.getText(), infoComplementaire.getText(), mailAgent.getText(), nomAgent.getText());
    }
}
