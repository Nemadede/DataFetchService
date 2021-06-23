package org.datafetch.service.puller;

import org.datafetch.service.utils.Queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WriteService {

    private WriteService(){}

    private static WriteService writeServiceInstance;

    public static WriteService getInstance(){
        if(writeServiceInstance == null){
            writeServiceInstance = new WriteService();
        }
        return writeServiceInstance;
    }
    private Queue queue = Queue.getInstance();
    private ExecutorService pullExecutor = Executors.newFixedThreadPool(2);


    public void write(){
        while (!queue.isEmpty()){
            pullExecutor.execute(new Writer(queue.dequeue()));
        }
    }
}
