/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.DaoMail;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author 96441
 */
public class ConMail {
    private DaoMail daomail;

    public ConMail(DaoMail daomail) {
        this.daomail = daomail;
    }
    
    
    public void enregistrerInfo(JTextField jourAvant,JTextField jourApres,JTextArea messageAvant,JTextArea messageapres,boolean type){
        
    }
}
