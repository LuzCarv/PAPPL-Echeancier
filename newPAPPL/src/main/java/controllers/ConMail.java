/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.DaoMail;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author 96441
 */
public class ConMail {
    private DaoMail daomail;

    public ConMail() {
        this.daomail = new DaoMail();
    }

    public void affichageInfo(JTextField jourAvantR,JTextArea messageAvantR,JTextField jourApresR,JTextArea messageApresR,JTextField jourAvantA,JTextArea messageAvantA,JTextField jourApresA,JTextArea messageApresA) throws IOException{
         ArrayList<String> infos=new ArrayList<String>();
         infos=daomail.lireInformationMail();
         jourAvantR.setText(infos.get(0));
         messageAvantR.setText(infos.get(1));
         jourApresR.setText(infos.get(2));
         messageApresR.setText(infos.get(3));
         jourAvantA.setText(infos.get(4));
         messageAvantA.setText(infos.get(5));
         jourApresA.setText(infos.get(6));
         messageApresA.setText(infos.get(7));
         
    }
    
    public void enregistrerInfo(JTextField jourAvantR,JTextArea messageAvantR,JTextField jourApresR,JTextArea messageApresR,JTextField jourAvantA,JTextArea messageAvantA,JTextField jourApresA,JTextArea messageApresA) throws IOException{
        daomail.enregistrerMail(jourAvantR.getText(), messageAvantR.getText(), jourApresR.getText(),messageApresR.getText(), jourAvantA.getText(),messageAvantA.getText(),jourApresA.getText(), messageApresA.getText());
    }
}
