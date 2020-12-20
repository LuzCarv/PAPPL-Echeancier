/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package envoimail;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


/**
 *
 * @author 96441
 */
public class TriggerMail{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       JobDetail job = JobBuilder.newJob(DailyJob.class).build();

		 Trigger trigger = TriggerBuilder
		 .newTrigger()
		 .withIdentity("verifier liste ehcneaces", "groupe1")
		 .withSchedule( CronScheduleBuilder.cronSchedule("0 * * * * ?"))
                 .build();
		Scheduler scheduler = null;
		try {
			scheduler = new StdSchedulerFactory().getDefaultScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);    
		} catch (SchedulerException e) {
			e.printStackTrace();
		} // TODO code application logic here
    }
    
}
