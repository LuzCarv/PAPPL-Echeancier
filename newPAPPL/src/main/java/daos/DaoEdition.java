/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import models.AgentComptable;
import models.DetteDetaillee;
import models.DetteSimplifiee;
import models.EcheanceDetaillee;
import models.Redevable;

/**
 *
 * @author 96441
 */
public class DaoEdition {
    public void editionInfo(DetteDetaillee dette){
     try {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(DaoHistorique.url,"postgres", DaoHistorique.motDePass);
        String requete1 = "BEGIN; "
                 +"UPDATE redevable "
                 +"SET adresse_mail_redevable=?, nom_redevable=?"
                 +"from dette where redevable.adresse_mail_redevable=dette.adresse_mail_redevable and dette.id_dette=?; "
                 +"update agent_comptable "
                 +"set adresse_mail_agent=?,nom_agent=? "
                 +"from dette where agent_comptable.adresse_mail_agent=dette.adresse_mail_agent and dette.id_dette=?; " 
                 +"update dette " 
                 +"set libelle=?,montant_dette=?,info_complementaire=?,action_effectuee=?,action_entreprendre=? " 
                 +"where dette.id_dette=?; " 
                 +"End;";
         
        PreparedStatement  stmt=conn.prepareStatement(requete1);
        stmt.setString(1,dette.getRedev().getAdresseMail());
        stmt.setString(2,dette.getRedev().getNom());
        stmt.setString(3,dette.getIdDette());
        stmt.setString(4,dette.getAgent().getAdresseMail());
        stmt.setString(5,dette.getAgent().getNom());
        stmt.setString(6,dette.getIdDette());
        stmt.setString(7,dette.getLibelle());
        stmt.setDouble(8,dette.getMontant());
        stmt.setString(9,dette.getInfoComplementaire());
        stmt.setString(10,dette.getActionEffectuee());
        stmt.setString(11,dette.getActionEntreprendre());
        stmt.setString(12,dette.getIdDette());
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
     this.ajouterEcheance(dette.getEd(), dette.getIdDette());
    }
   
    public void ajouterEcheance(ArrayList<EcheanceDetaillee> listEcheance, String idDette ){
        try {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(DaoHistorique.url,"postgres", DaoHistorique.motDePass);
        String requete1;
        PreparedStatement  stmt = null;
      
        for (EcheanceDetaillee e : listEcheance){
        requete1 = "INSERT INTO echeance(id_echeance,date_deadline,montant_echeance,statut_paiement,statut_annulation,date_paiement,raison_annulation,id_dette) "
                   +"VALUES (nextval('echeance_sequence'),?,?,?,?,?,?,?)";
        stmt=conn.prepareStatement(requete1);
        stmt.setDate(1,Date.valueOf(e.getDateDeadLine()));
        stmt.setDouble(2,e.getMontant());
        stmt.setBoolean(3,e.isStatutPaiement());
        stmt.setBoolean(4,e.isStatutAnnulation());
        if(e.getDatePaiement() == null){
            stmt.setTimestamp(5,null);
        }else{
            stmt.setDate(5,Date.valueOf(e.getDatePaiement()));
        }
        stmt.setString(6,e.getRaisonAnnulation());
        stmt.setString(7,idDette);
        stmt.executeUpdate();
        }
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
    
    public void effacerEcheance(String idDette){
         try {
        Class.forName("org.postgresql.Driver");
        
         Connection conn = DriverManager.getConnection(DaoHistorique.url,"postgres", DaoHistorique.motDePass);
       
         String requete1 = "DELETE FROM echeance "
                      +"where echeance.id_dette=? " ;

        PreparedStatement  stmt=conn.prepareStatement(requete1);
        stmt.setString(1,idDette);
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
}