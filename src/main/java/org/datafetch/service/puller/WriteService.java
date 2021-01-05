package org.datafetch.service.puller;

import org.datafetch.service.utils.Queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WriteService {
    private Queue queue = Queue.getInstance();
    private ExecutorService pullExecutor = Executors.newFixedThreadPool(2);


    public void write(){
        while (!queue.isEmpty()){
            pullExecutor.execute(new Writer(queue.dequeue()));
        }
    }
}
