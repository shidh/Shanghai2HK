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
			//renderJSON(gson.toJson(DailyStats));
			render(DailyStats);
		} else {
			renderHtml("empty");
		}
		//render(all);
    }
    
    public static void daily() {
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
    
    public static void stocks() {
    	Gson gson = new Gson();
    	List<Stock> stocks = Stock.findAll();
    	
    	
		if (stocks != null) {
			Logger.info("DailyStats Json to js: " + gson.toJson(stocks));
			renderJSON(gson.toJson(stocks));
		} else {
			renderHtml("empty");
		}
		//render(all);
    }
    
	/**
	 * On Start Clicked
	 */
	public static void start() {
		Gson gson = new Gson();
	 	List<DailyStat> DailyStats = DailyStat.findAll();
			if (DailyStats != null) {
				Logger.info("DailyStats Json to js: " + gson.toJson(DailyStats));
				renderJSON(gson.toJson(DailyStats));
			} else {
				renderHtml("empty");
			}

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