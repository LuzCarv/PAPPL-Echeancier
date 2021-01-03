/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 96441
 */
public class DaoMail {

    public DaoMail() {
    }
   
    public void enregistrerMail (String messageAvantRedevable, String jourAvantRedevable,String messageApresRedevable,String jourApresRedevable,String messageAvantAgent, String jourAvantAgent,String messageApresAgent,String jourApresAgent) throws IOException {
        

        try {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(DaoHistorique.url,"postgres", DaoHistorique.motDePass);
        String requete1 = "BEGIN; "
                 +"update mail set corps_message=?, jour=? where mail.id_mail='redevableAvant'; "
                 +"update mail set corps_message=?, jour=? where mail.id_mail='redevableApres'; "
                 +"update mail set corps_message=?, jour=? where mail.id_mail='agentAvant'; "
                 +"update mail set corps_message=?, jour=? where mail.id_mail='agentApres'; "
                 +"End;";
         
        PreparedStatement  stmt=conn.prepareStatement(requete1);
        stmt.setString(1,messageAvantRedevable);
        stmt.setInt(2,Integer.parseInt(jourAvantRedevable));
        stmt.setString(3,messageApresRedevable);
        stmt.setInt(4,Integer.parseInt(jourApresRedevable));
        stmt.setString(5,messageAvantAgent);
        stmt.setInt(6,Integer.parseInt(jourAvantAgent));
        stmt.setString(7,messageApresAgent);
        stmt.setInt(8,Integer.parseInt(jourApresAgent));

        stmt.executeUpdate();
        stmt.close() ;
        conn.close() ; 
         }
    catch (SQLException e) {
             e.printStackTrace();
    }
    catch (java.lang.ClassNotFoundException e) {
        e.printStackTrace();
    }   
       
 
    }
    
    public ArrayList<String> lireInformationMail() throws  IOException, SQLException{
           
        try {
            ArrayList<String>lignes=new ArrayList<>();
            Class.forName("org.postgresql.Driver");
            
            Connection conn = DriverManager.getConnection(DaoHistorique.url,"postgres", DaoHistorique.motDePass);
           String requete1 =  "SELECT corps_message,jour FROM mail";
            PreparedStatement  stmt = conn.prepareStatement(requete1) ;
        stmt.setBoolean(1, false);
        ResultSet res = stmt.executeQuery();
        
        if(res.next()){
           return lignes; 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
