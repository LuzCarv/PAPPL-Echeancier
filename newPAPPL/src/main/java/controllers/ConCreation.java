/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.DaoCreation;
import daos.DaoEdition;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import models.AgentComptable;
import models.DetteSimplifiee;
import models.EcheanceSimplifiee;
import models.Redevable;

/**
 *
 * @author 96441
 */
public class ConCreation {
    DaoEdition daoEdit;
    DaoCreation daocreation;
    
    public ConCreation(){
        this.daoEdit=new DaoEdition();
        this.daocreation=new DaoCreation();
    }
    
    public DetteSimplifiee enregistrerRedevable(JTable table,JTextField mailRedevable, JTextField nomRedevable,JTextField libelle, JTextField montant, JTextField infoComplementaire,JComboBox nomAgent) throws ParseException{
        ArrayList<EcheanceSimplifiee> echeances=new ArrayList<EcheanceSimplifiee>();
        EcheanceSimplifiee e=new EcheanceSimplifiee();
        for (int i=0;i<table.getRowCount();i++){

            e.setMontant(Double.parseDouble((String)table.getValueAt(i, 2)));
          //  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            Date date = DateFormat.getDateInstance(DateFormat.SHORT).parse((String)(table.getValueAt(i,1)));
            e.setDateDeadLine(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            echeances.add(e);
        }
        daocreation.CreationRedevable(mailRedevable.getText(), nomRedevable.getText(), echeances, libelle.getText(), montant.getText(), infoComplementaire.getText(),  nomAgent.getSelectedItem().toString());
        DetteSimplifiee detSimp = new DetteSimplifiee();
        detSimp.setLibelle(libelle.getText());
        System.out.println(detSimp.getLibelle());
        detSimp.setMontant(Double.parseDouble(montant.getText()));
        Redevable red = new Redevable();
        System.out.println("nombre " + nomRedevable.getText());
        red.setNom(nomRedevable.getText());
        detSimp.setRedev(red);
        detSimp.setEs(echeances);
        
        return detSimp;
    }
    
    
    public JComboBox afficherAgent(JComboBox agentComptable){
        ArrayList<AgentComptable> agents = daoEdit.obtenirAgents();
        agentComptable.removeAllItems();
        for(AgentComptable agent: agents){
            agentComptable.addItem(agent.getNom());
        }
        return agentComptable;
    }
}
