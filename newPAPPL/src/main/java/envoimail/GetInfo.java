/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package envoimail;

import daos.DaoHistorique;
import daos.DaoHistorique;
import daos.DaoMail;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import models.AgentComptable;
import models.DetteSimplifiee;
import models.EcheanceDetaillee;
import models.Redevable;
import envoimail.SendMail;
import javax.mail.MessagingException;
/**
 *
 * @author 96441
 */
public class GetInfo{
    private DaoMail daomail;

    public GetInfo() {
        this.daomail =new DaoMail();
    }
    
    public void VerifierListe() throws IOException, MessagingException{
        SendMail sendmail=new SendMail(); 
        ArrayList<DateMail> datemail=this.getListeEchenaceMail();
        LocalDate date=LocalDate.now();
        ArrayList<String> jourMessage=new ArrayList<>();
        jourMessage=daomail.lireInformationMail();
       /*  jourAvantR.setText(infos.get(1));
         messageAvantR.setText(infos.get(0));
         jourApresR.setText(infos.get(3));
         messageApresR.setText(infos.get(2));
         jourAvantA.setText(infos.get(5));
         messageAvantA.setText(infos.get(4));
         jourApresA.setText(infos.get(7));
         messageApresA.setText(infos.get(6));*/
        for (DateMail dm: datemail){
           if  (date.plusDays(Long.parseLong(jourMessage.get(1))).isEqual(dm.getDatedeadline())){
            
               sendmail.send(dm.getMailredevable(),"Une echeance a payer",jourMessage.get(0));
           }
           if  (date.minusDays(Long.parseLong(jourMessage.get(3))).isEqual(dm.getDatedeadline())){
               
               sendmail.send(dm.getMailredevable(),"Une echeance oublie a payer",jourMessage.get(2));
           }
           if  (date.plusDays(Long.parseLong(jourMessage.get(5))).isEqual(dm.getDatedeadline())){
               
               sendmail.send(dm.getMailagent(),"Une echeance  a payer",jourMessage.get(4));
           }
           if  (date.minusDays(Long.parseLong(jourMessage.get(7))).isEqual(dm.getDatedeadline())){
              
             sendmail.send(dm.getMailagent(),"Une echeance oublie a payer",jourMessage.get(6));
           }
        } 
    }
    
    public ArrayList<DateMail> getListeEchenaceMail(){
          ArrayList<DateMail> datemail = new ArrayList<>();
      
      try {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/pappl","postgres", "zhang99662");
        String requete1 =  "SELECT echeance.date_deadline, dette.adresse_mail_redevable,dette.adresse_mail_agent "
                        +"FROM echeance JOIN dette ON (echeance.id_dette=dette.id_dette)"
                        +"WHERE echeance.statut_paiement=? ";
         
        PreparedStatement  stmt = conn.prepareStatement(requete1) ;
        stmt.setBoolean(1, false);
        ResultSet res = stmt.executeQuery();
        
        if(res.next()){
        
         do{  
             DateMail dm = new DateMail();
             dm.setMailagent(res.getString("adresse_mail_agent"));
             dm.setMailredevable(res.getString("adresse_mail_redevable"));
             dm.setDatedeadline(res.getDate("date_deadline").toLocalDate());
             datemail.add(dm);
        }while (res.next()); 
            
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
       return datemail; 
    }
}
