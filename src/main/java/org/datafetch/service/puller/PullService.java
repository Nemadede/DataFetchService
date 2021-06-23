package org.datafetch.service.puller;

import org.datafetch.service.schema.User;
import org.datafetch.service.utils.Queue;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PullService implements Job {

    ExecutorService pullExecutor = Executors.newFixedThreadPool(2);


    List <Future> futures = new ArrayList<>();
    Queue queue = Queue.getInstance();
    int count;


    public void runPullService(User user){
//        List<String> modelNames = new ArrayList(Arrays.asList("Account" ));
        List<String> modelNames = new ArrayList(Arrays.asList("Account","Bill","BillPayment","Customer","Invoice", "Payment",
                "PaymentMethod", "RefundReceipt", "SalesReceipt","Vendor","VendorCredit" ));

        for(String modelName : modelNames){
            // Define a new runnable, the Class constructor receives 2 variables
            futures.add(pullExecutor.submit(new Query(user, modelName))) ;

        }

        for(Future<Object> future :  futures) {
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

         WriteService.getInstance().write();
    }


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        User user = (User) jobDetail.getJobDataMap().get("user");
        runPullService(user);
    }
}
