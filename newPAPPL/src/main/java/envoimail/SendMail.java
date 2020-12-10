/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package envoimail;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author 96441
 */
public class SendMail {

    public SendMail() {
    }
    
    public static void send(String recepient) throws MessagingException {
        System.out.println("preparing to send mail");
        Properties properties=new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port","587");
        String myAccountEmail="zhang99662@gmail.com";
        String password="zhangchenkai";
        Session session;
        session = Session.getInstance(properties, new Authenticator(){
           protected PasswordAuthentication getPasswordAuthentication(){
               return new PasswordAuthentication(myAccountEmail, password);
           }
        });
        Message message=prepareMessage(session,myAccountEmail,recepient);
        Transport.send(message);
        System.out.println("Message sent !");
    }
    private static Message prepareMessage(Session session,String myAccountEmail,String recepient) throws AddressException, MessagingException{
   
            Message message= new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("my first email from java app");
            message.setText("Hello world");
            return(message);
    }
}

