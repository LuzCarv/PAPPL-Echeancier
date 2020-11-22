/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.AgentComptable;
import models.DetteSimplifiee;
import models.Redevable;

/**
 *
 * @author 96441
 */
public class DaoEdition {
    public void EditionInfo(String idDette, String nomRedevable, String mailRedevable, String libelle, String montantDette, String nomAgent,String mailAgent, String infoComplementaire, String actionEffectuee, String actionEntreprendre ){
     try {
        Class.forName("org.postgresql.Driver");
        
         String url = "jdbc:postgresql://localhost/PAPPL";
     
         Connection conn = DriverManager.getConnection(url,"postgres", "zhang99662");
        // update redevable set nom_redevable =? where redevable.adresse_mail_redevable =?
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
        stmt.setString(1,"zhang99662@gmail.com");
        stmt.setString(2, "chenkai");
        stmt.setString(3,"1");
        stmt.setString(4,"eric@ec-nantes.fr");
        stmt.setString(5,"eric codeverte");
        stmt.setString(6,"1");
        stmt.setString(7,"libelle1");
        stmt.setDouble(8,102.5);
        stmt.setString(9,"infocomplementaire1");
        stmt.setString(10,"action1");
        stmt.setString(11,"action2");
        stmt.setString(12,"1");
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
