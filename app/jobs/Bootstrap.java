package jobs;

import play.*;
import play.mvc.*;
import models.DailyStat;
import play.Logger;

import com.google.gson.Gson;

import play.jobs.*;

@OnApplicationStart
public class Bootstrap extends Job {
    
    public void doJob() {
        if(DailyStat.count() == 0) {
            Logger.info("Start to call initDB()");
            new UpdateData().initDB();
        }
    }
    
    public void onSuccess(){
        Logger.info("init the DB done");

    }
    
    public void onException(Throwable e){
    	e.printStackTrace();
        Logger.warn("init the DB failed");

    }
}