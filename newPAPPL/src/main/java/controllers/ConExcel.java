/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.DaoExcel;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import models.DetteSimplifiee;
import models.EcheanceDetaillee;
import models.EcheanceSimplifiee;

/**
 *
 * @author Luz
 */
public class ConExcel {
    private DaoExcel daoExcel;
    
    public ConExcel() {
        daoExcel = new DaoExcel();
    }
    
    
    public File chosirFichier(JPanel optionsCreation){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers xlsx" ,"xlsx");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(optionsCreation);
        return chooser.getSelectedFile();
 
    }
    
    public void ajouterDonnees(File fichier,JTextField nom, JTextField libelle, JTextField montant, JTable echeancesT){
        DetteSimplifiee detSimp = new DetteSimplifiee();
        detSimp = daoExcel.lireExcel(fichier);
        nom.setText(detSimp.getRedev().getNom());
        libelle.setText(detSimp.getLibelle());
        montant.setText(String.valueOf(detSimp.getMontant()));
        //dateCration.setText .........
        ArrayList<EcheanceSimplifiee> echeances = detSimp.getEs();
        Object[] ligneS = new Object[3];
        DefaultTableModel model = (DefaultTableModel)echeancesT.getModel();
        model.setRowCount(0);
        int i = 1;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for(EcheanceSimplifiee echeance: echeances){
                ligneS[0]= "Deadline " + i;
                ligneS[1] = formatter.format(echeance.getDateDeadLine());
                ligneS[2] = String.valueOf(echeance.getMontant());
                model.addRow(ligneS);
                i++;
            }
    }
    
    
}
