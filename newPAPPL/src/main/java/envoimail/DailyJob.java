/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package envoimail;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author 96441
 */
public class DailyJob implements Job{
    public void execute(JobExecutionContext var1) throws JobExecutionException {
        try {
            GetInfo getinfo =new GetInfo();
            getinfo.VerifierListe();
            System.out.println("hello world");
        } catch (IOException ex) {
            Logger.getLogger(DailyJob.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(DailyJob.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
}
