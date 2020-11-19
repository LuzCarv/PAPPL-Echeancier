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
import models.*;

/**
 *
 * @author 96441
 */
public class DaoActif {
    public ArrayList<DetteSimplifiee>  demandeListeActifs (){
       ArrayList<DetteSimplifiee> actifs = new ArrayList<>();
      
      try {
        Class.forName("org.postgresql.Driver");
        
         String url = "jdbc:postgresql://localhost/PAPPL";
     
         Connection conn = DriverManager.getConnection(url,"postgres","zhang99662");
         
         String requete1 =  "SELECT redevable.nom_redevable, redevable.adresse_mail_redevable, dette.libelle,"
                 + "dette.montant_dette, dette.dette_actuelle, dette.date_creation, agent_comptable.nom_agent, agent_comptable.adresse_mail_agent, dette.id_dette,"
                 + " dette.info_complementaire FROM dette JOIN agent_comptable ON (dette.adresse_mail_agent = agent_comptable.adresse_mail_agent) "
                 + "JOIN redevable ON (dette.adresse_mail_redevable = redevable.adresse_mail_redevable) "
                 + "WHERE dette.statut_dette=?";
         
        PreparedStatement  stmt = conn.prepareStatement(requete1) ;
        stmt.setBoolean(1, false);
        ResultSet res = stmt.executeQuery();
        res.next();
        
         do{  
             AgentComptable agent = new AgentComptable();
             agent.setAdresseMail(res.getString("adresse_mail_agent"));
             agent.setNom(res.getString("nom_agent"));
             
             Redevable redevable = new Redevable();
             redevable.setAdresseMail(res.getString("adresse_mail_agent"));
             redevable.setNom(res.getString("nom_redevable"));
             
             DetteSimplifiee detSimpli = new DetteSimplifiee();
             detSimpli.setAgent(agent);
             detSimpli.setDateCreation( res.getTimestamp("date_creation").toLocalDateTime()); 
             detSimpli.setLibelle(res.getString("libelle"));
             detSimpli.setRedev(redevable);
             detSimpli.setMontant(res.getDouble("montant_dette"));
             detSimpli.setDetteActuelle(res.getDouble("dette_actuelle"));
             detSimpli.setIdDette(res.getString("id_dette"));
             actifs.add(detSimpli);
        }while (res.next()); 
            
        
         stmt.close() ;
         conn.close() ; 
        
         }
    catch (SQLException e) {
             e.printStackTrace();
    }
    catch (java.lang.ClassNotFoundException e) {
        e.printStackTrace();
    }   
       return actifs; 
    }
}
