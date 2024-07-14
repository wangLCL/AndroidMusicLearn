package com.wk.utilslib.utils.task;

public class Task<T> implements Runnable {

    @Override
    public void run() {
        doInBackground();
    }

    protected T doInBackground(){
        return null;
    }



}
