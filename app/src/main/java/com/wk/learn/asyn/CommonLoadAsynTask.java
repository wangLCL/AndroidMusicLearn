package com.wk.learn.asyn;

import android.os.AsyncTask;

public class CommonLoadAsynTask<T> extends AsyncTask<Object,Void,Object> {
    private Runnable doGround;
    private Runnable doreturn;
    public CommonLoadAsynTask(Runnable doGround, Runnable doreturn){
        this.doGround = doGround;
        this.doreturn = doreturn;
    }

    @Override
    protected String doInBackground(Object... strings) {
        doGround.run();
        return "null";
    }

    @Override
    protected void onPostExecute(Object s) {
        doreturn.run();
        super.onPostExecute(s);
    }
}
