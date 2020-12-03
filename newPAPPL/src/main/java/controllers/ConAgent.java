/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.DaoAgent;
import daos.DaoHistorique;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.AgentComptable;

/**
 *
 * @author Luz
 */
public class ConAgent {
    DaoAgent daoAgent;

    public ConAgent() {
        this.daoAgent = new DaoAgent();
    }
    
    public void afficherAgents(JTable tAgents){
        
        DefaultTableModel model = (DefaultTableModel)tAgents.getModel();
        model.setRowCount(0);
        Object[] ligne = new Object[3]; 
        ArrayList<AgentComptable> agents = daoAgent.obtenirAgents();
        for(AgentComptable agent: agents){
              ligne[0]= agent.getNom();
              ligne[1] = agent.getAdresseMail();
              ligne[2] = agent.isStatut();
              model.addRow(ligne);
        } 
    
    }
    
  
}
