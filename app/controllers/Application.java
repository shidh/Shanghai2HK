package controllers;

import play.*;
import play.mvc.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import jobs.UpdateData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

import models.*;

public class Application extends Controller {
	
    public static void index() {

    	Gson gson = new Gson();
    	List<DailyStat> DailyStats = DailyStat.findAll();
    	
    	
		if (DailyStats != null) {
			Logger.info("DailyStats Json to js: " + gson.toJson(DailyStats));
			renderJSON(gson.toJson(DailyStats));
		} else {
			renderHtml("empty");
		}
		//render(all);
    }
    
    public static void updateDataNow() {
        Logger.info(setDate(new Date())+":"+new Date().getHours()+":"+new Date().getMinutes());

    	new UpdateData().now();
    	}
    
	private static String setDate(Date date){
	    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	    return( dateFormat.format(date));
	}


}