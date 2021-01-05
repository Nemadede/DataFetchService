package org.datafetch.service.controllers;

import org.datafetch.service.puller.PullService;
import org.datafetch.service.schema.User;
import org.datafetch.service.utils.Schedule;
import org.quartz.*;

import static org.quartz.JobKey.jobKey;
import static org.quartz.TriggerBuilder.newTrigger;

public class JobController {
    private Scheduler scheduler ;


    public void userJob(User user){
        JobBuilder jobBuilder = JobBuilder.newJob(PullService.class);
        JobDataMap data = new JobDataMap();
        data.put("user", user);
        Trigger trigger = newTrigger().withIdentity("myTrigger").startNow().build();

        builder(jobBuilder, data, "submit_user "+user.getId(), trigger);

    }

    private void builder(JobBuilder jobBuilder, JobDataMap data, String job_name, Trigger trigger) {
        JobDetail jobDetail = jobBuilder.usingJobData(data)
                .withIdentity(job_name, "group1")
                .storeDurably(true)
                .build();
        try {
            scheduler = Schedule.getInstance().getMyScheduler();

            scheduler.scheduleJob(jobDetail, trigger);

        }catch (ObjectAlreadyExistsException ex){
            try {
                scheduler.addJob(jobDetail, true);
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
        catch (SchedulerException e) {
            e.printStackTrace();
        }


    }



}
