/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.DaoActif;
import java.util.ArrayList;
import javax.swing.JTextArea;
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
}
