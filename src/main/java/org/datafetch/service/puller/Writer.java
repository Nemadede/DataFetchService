package org.datafetch.service.puller;

import org.datafetch.service.dao.QBSchemaDoa;

public class Writer implements Runnable{

    private QBSchemaDoa doa = new QBSchemaDoa();

    private Object object;

    public Writer(Object object){
        this.object = object;
    }

    // TODO: Change to Bulk Insert

    @Override
    public void run() {
        synchronized (doa){
            doa.saveObject(object);
        }

    }
}
