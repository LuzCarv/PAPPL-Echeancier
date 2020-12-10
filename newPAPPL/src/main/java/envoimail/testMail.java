/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package envoimail;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;


/**
 *
 * @author 96441
 */
public class testMail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            SendMail sendmail=new SendMail();
            
            sendmail.send("zhang99662@gmail.com");
        } catch (MessagingException ex) {
            Logger.getLogger(testMail.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
    }
    
}
