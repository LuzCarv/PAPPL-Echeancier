/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.github.lgooddatepicker.tableeditors.DateTableEditor;
import daos.DaoEdition;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import models.AgentComptable;
import models.DetteDetaillee;
import models.DetteSimplifiee;
import models.EcheanceDetaillee;
import models.Redevable;

/**
 *
 * @author Luz
 */
public class ConEdition {
    private DaoEdition daoEdit;
    
    public ConEdition(){
        this.daoEdit=new DaoEdition();
    }
    public void afficherDonneesEditionAH(DetteDetaillee detteDetail, JTable table,JTextField idDette,JTextField nom,JTextField mail,JTextField libelle,JTextField montant,JTextField info,JTextField actionEntreprendre,JTextField actionEffectuee, JTextField agentComptable){

        nom.setText(detteDetail.getRedev().getNom());
        mail.setText(detteDetail.getRedev().getAdresseMail());
        libelle.setText(detteDetail.getLibelle());
        montant.setText(String.valueOf(detteDetail.getMontant()));
        info.setText(detteDetail.getInfoComplementaire());
        actionEntreprendre.setText(detteDetail.getActionEntreprendre());
        actionEffectuee.setText(detteDetail.getActionEffectuee());
        idDette.setText(detteDetail.getIdDette());
        agentComptable.setText(detteDetail.getAgent().getNom());
        
        Object[] ligneS = new Object[7]; 
        ArrayList<EcheanceDetaillee> echeances = detteDetail.getEd();
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        ZoneId defaultZoneId = ZoneId.systemDefault();
        try{
            int i=1;
            for(EcheanceDetaillee echeance: echeances){
                ligneS[0]= "Deadline " + i;
                Date date = Date.from(echeance.getDateDeadLine().atStartOfDay(defaultZoneId).toInstant());
                ligneS[1] = DateFormat.getDateInstance(DateFormat.SHORT).format(date);
                ligneS[2] = String.valueOf(echeance.getMontant());
                ligneS[3] = echeance.getStatutPaiement();
              
                if (echeance.getDatePaiement()!=null){
                    System.out.println("holigdfg " + echeance.getDatePaiement());
                    date = Date.from(echeance.getDatePaiement().atStartOfDay(defaultZoneId).toInstant());
                    ligneS[4] = DateFormat.getDateInstance(DateFormat.SHORT).format(date);
                }
                ligneS[5]=echeance.getStatutAnnulation();
              
                if (echeance.getRaisonAnnulation()!=null){
                    ligneS[6] = String.valueOf(echeance.getRaisonAnnulation());
                }
                model.addRow(ligneS);
                i++;
            }
        }catch(java.lang.NullPointerException e){
                
        }
        
    }
      public void afficherDonneesEditionAH(DetteDetaillee detteDetail, JTable table,JTextField idDette,JTextField nom,JTextField mail,JTextField libelle,JTextField montant,JTextField info,JTextField actionEntreprendre,JTextField actionEffectuee, JComboBox agentComptable){
        
        TableColumn dateColonne = table.getColumnModel().getColumn(1);
        TableColumn dateColonne2 = table.getColumnModel().getColumn(4);
        DateTableEditor a = new DateTableEditor();
        a.getDatePickerSettings().setFormatForDatesCommonEra(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        table.setDefaultEditor(LocalDate.class, a);
        table.setDefaultRenderer(LocalDate.class, a);
        dateColonne.setCellEditor(table.getDefaultEditor(LocalDate.class));
        dateColonne.setCellRenderer(table.getDefaultRenderer(LocalDate.class));
        dateColonne2.setCellEditor(table.getDefaultEditor(LocalDate.class));
        dateColonne2.setCellRenderer(table.getDefaultRenderer(LocalDate.class));
          
          
        nom.setText(detteDetail.getRedev().getNom());
        mail.setText(detteDetail.getRedev().getAdresseMail());
        libelle.setText(detteDetail.getLibelle());
        montant.setText(String.valueOf(detteDetail.getMontant()));
        info.setText(detteDetail.getInfoComplementaire());
        actionEntreprendre.setText(detteDetail.getActionEntreprendre());
        actionEffectuee.setText(detteDetail.getActionEffectuee());
        idDette.setText(detteDetail.getIdDette());
        ArrayList<AgentComptable> agents = daoEdit.obtenirAgents();
        agentComptable.removeAllItems();
        
        for(AgentComptable agent: agents){
            agentComptable.addItem(agent.getNom());
            if(agent.getNom().equals(detteDetail.getAgent().getNom())){
                agentComptable.setSelectedItem(agent.getNom());
            }
        }
        
        Object[] ligneS = new Object[7]; 
        ArrayList<EcheanceDetaillee> echeances = detteDetail.getEd();
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        ZoneId defaultZoneId = ZoneId.systemDefault();
        try{
            int i=1;
            for(EcheanceDetaillee echeance: echeances){
                ligneS[0]= "Deadline " + i;
                Date date = Date.from(echeance.getDateDeadLine().atStartOfDay(defaultZoneId).toInstant());
                ligneS[1] = DateFormat.getDateInstance(DateFormat.SHORT).format(date);
                ligneS[2] = String.valueOf(echeance.getMontant());
                ligneS[3] = echeance.getStatutPaiement();
                if (echeance.getDatePaiement()!=null){
                    date = Date.from(echeance.getDatePaiement().atStartOfDay(defaultZoneId).toInstant());
                    ligneS[4] = DateFormat.getDateInstance(DateFormat.SHORT).format(date);
                }else{
                    ligneS[4]="";
                }
                ligneS[5] = echeance.getStatutAnnulation();
                if (echeance.getRaisonAnnulation()!=null){
                    ligneS[6] = String.valueOf(echeance.getRaisonAnnulation());
                }
                model.addRow(ligneS);
                i++;
            }
        }catch(java.lang.NullPointerException e){
                
        }
        
    }
   
    public DetteDetaillee update(JTable table,JTextField idDette,JTextField nom,JTextField mail,JTextField libelle,JTextField montant,JTextField info,JTextField actionEntreprendre,JTextField actionEffectuee, JComboBox agentComptable) throws ParseException{
        
        DetteDetaillee detDetail = new DetteDetaillee();
        AgentComptable agent = new AgentComptable();
        agent.setNom((String)agentComptable.getSelectedItem());
        agent.setId(daoEdit.obtenirIdAgent((String)agentComptable.getSelectedItem()));
        Redevable redevable = new Redevable();
        redevable.setAdresseMail(mail.getText());
        redevable.setNom(nom.getText());
        detDetail.setAgent(agent);
        detDetail.setLibelle(libelle.getText());
        detDetail.setRedev(redevable);
        detDetail.setMontant(Double.parseDouble(montant.getText()));
        detDetail.setIdDette(idDette.getText());
        detDetail.setActionEffectuee(actionEffectuee.getText());
        detDetail.setActionEntreprendre(actionEntreprendre.getText());
        detDetail.setInfoComplementaire(info.getText());
        ArrayList <EcheanceDetaillee> echeances = new ArrayList <>();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < table.getRowCount(); i++) {
            
            EcheanceDetaillee echeance = new EcheanceDetaillee();
            try{
                Date date = DateFormat.getDateInstance(DateFormat.SHORT).parse((String)(table.getValueAt(i,1)));
                echeance.setDateDeadLine(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            }catch(java.lang.ClassCastException ex){
                echeance.setDateDeadLine((LocalDate)table.getValueAt(i,1));
            }
            echeance.setMontant(Double.parseDouble((String) table.getValueAt(i, 2)));
            if(((Boolean)(table.getValueAt(i, 3))) != null){
                echeance.setStatutPaiement((Boolean)(table.getValueAt(i, 3)));
            }else{
                echeance.setStatutPaiement(false);
            }
            if (table.getValueAt(i,4)!= null ){
                try{
                    Date date = DateFormat.getDateInstance(DateFormat.SHORT).parse((String)(table.getValueAt(i,4)));
                    echeance.setDatePaiement(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                }catch(java.lang.ClassCastException ex){
                   echeance.setDatePaiement((LocalDate)table.getValueAt(i,4));
                }catch(java.text.ParseException e){
                    
                }
            }
            System.out.println("aca " + i + " " + echeance.getDatePaiement());
            if(((Boolean)(table.getValueAt(i, 5))) != null){
                echeance.setStatutAnnulation((Boolean)(table.getValueAt(i, 5)));
            }else{
                echeance.setStatutAnnulation(false);
            }
            echeance.setRaisonAnnulation((String)table.getValueAt(i, 6));  
            echeances.add(echeance);
        }
        detDetail.setEd(echeances);
             
        return detDetail;
    } 
    
    public void effacerEcheances(JTextField idDette){
        daoEdit.effacerEcheance(idDette.getText());
    }

    
    
}
