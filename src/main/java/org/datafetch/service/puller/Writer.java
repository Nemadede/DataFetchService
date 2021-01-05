package org.datafetch.service.puller;

import org.datafetch.service.dao.QBSchemaDoa;
import org.datafetch.service.utils.Queue;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Writer implements Runnable{

    private QBSchemaDoa doa = new QBSchemaDoa();

    private Object object;

    public Writer(Object object){
        System.out.println("In the Writer Constructor");
        this.object = object;
    }

    @Override
    public void run() {

        System.out.println("\n======= writing =======");

        synchronized (doa){
            doa.saveObject(object);
        }


    }
}
