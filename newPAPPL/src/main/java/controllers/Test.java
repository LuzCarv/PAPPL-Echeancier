/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DetteDetaillee;
import models.DetteSimplifiee;
import models.EcheanceSimplifiee;

/**
 *
 * @author Luz
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        www cone = new www();
        DetteSimplifiee detDetail;
       /* ArrayList<EcheanceSimplifiee> eches = new ArrayList<>();
        for (int i=0;i<4;i++){
            EcheanceSimplifiee eche= new EcheanceSimplifiee();
            eche.setMontant(400);
            eches.add(eche);
        } 
        detDetail.setEs(eches);
        cone.ecrireEcheancier(detDetail);
        */
        detDetail=cone.lireExcel();
        System.out.println(detDetail.getDateCreation());
        System.out.println(detDetail.getLibelle());
        System.out.println(detDetail.getMontant());
        System.out.println(detDetail.getEs().get(0).getDateDeadLine());
        System.out.println(detDetail.getEs().get(0).getMontant());
        System.out.println(detDetail.getEs().get(1).getDateDeadLine());
        System.out.println(detDetail.getEs().get(1).getMontant());
        System.out.println(detDetail.getEs().get(2).getDateDeadLine());
        System.out.println(detDetail.getEs().get(2).getMontant());
        System.out.println(detDetail.getRedev().getNom());
                
    }
    
}
