/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.io.IOException;
import java.time.LocalDate;
import javax.mail.MessagingException;

/**
 *
 * @author 96441
 */
public class test1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MessagingException, IOException {
       DaoInfo daoinfo=new DaoInfo(); // TODO code application logic here
       daoinfo.VerifierListe();
       LocalDate date=LocalDate.now();
       System.out.println(date);
    }
    
}
