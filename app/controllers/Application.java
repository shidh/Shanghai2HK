package controllers;

import play.*;
import play.mvc.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
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
    	//List<DailyStat> DailyStats = DailyStat.findAll();
    	//List<DailyStat> DailyStats = DailyStat.all().fetch(5);

    	int statsCount = (int) DailyStat.count();
    	List<DailyStat> dailyStats = DailyStat.all().from(statsCount-10).fetch(5);
		
		
		
		if (dailyStats != null) {
			//Logger.info("DailyStats Json to js: " + gson.toJson(dailyStats));
			//renderJSON(gson.toJson(dailyStats));
			render(dailyStats);
		} else {
			renderHtml("empty");
		}
    }
    
    public static void monthly() {
       	Gson gson = new Gson();
    	//List<DailyStat> DailyStats = DailyStat.findAll();
    	//List<DailyStat> DailyStats = DailyStat.all().fetch(5);

    	int statsCount = (int) DailyStat.count();
    	List<DailyStat> dailyStats = DailyStat.all().from(statsCount-21).fetch(21);
		
		
		
		if (dailyStats != null) {
			Logger.info("DailyStats Json to js: " + gson.toJson(dailyStats));
			renderJSON(gson.toJson(dailyStats));
			//render(dailyStats);
		} else {
			renderHtml("empty");
		}
    }
    
    public static void weekly() {
       	Gson gson = new Gson();
    	//List<DailyStat> DailyStats = DailyStat.findAll();
    	//List<DailyStat> DailyStats = DailyStat.all().fetch(5);

    	int statsCount = (int) DailyStat.count();
    	List<DailyStat> dailyStats = DailyStat.all().from(statsCount-5).fetch(5);
		
		
		
		if (dailyStats != null) {
			Logger.info("DailyStats Json to js: " + gson.toJson(dailyStats));
			renderJSON(gson.toJson(dailyStats));
			//render(dailyStats);
		} else {
			renderHtml("empty");
		}
    }
    
    public static void all() {
       	Gson gson = new Gson();
    	List<DailyStat> dailyStats = DailyStat.findAll();
		
		if (dailyStats != null) {
			Logger.info("DailyStats Json to js: " + gson.toJson(dailyStats));
			renderJSON(gson.toJson(dailyStats));
			//render(dailyStats);
		} else {
			renderHtml("empty");
		}
    }
    
    
    
    public static void datesBetween(String from, String to) {
		Gson gson = new Gson();
		Date start = new Date();
		Date end = new Date();
		Calendar cal = Calendar.getInstance();
	
		List<DailyStat> dailyStats = new ArrayList<DailyStat>();
		List<Date> dates = new ArrayList<Date>();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		try {
			start = formatter.parse(from);
			end = formatter.parse(to);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		cal.setTime(start);

		//return the dates "between" the start and end dates, exclusively.
		//while (cal.getTime().before(end)) {
		
		//return the dates "between" the start and end dates, inclusively 
		while (!cal.getTime().after(end)) {
		    dates.add(cal.getTime());
		    cal.add(Calendar.DATE, 1);
		}
		
		for(Date date: dates){
			DailyStat checkDate = DailyStat.find("byDate", date).first();
			if(checkDate != null){
				dailyStats.add(checkDate);
			}
		}
		
		
		
		if (dailyStats.size() >0) {
			Logger.info("DailyStats Json to js: " + gson.toJson(dailyStats));
			renderJSON(gson.toJson(dailyStats));
			//render(DailyStats);
		} else {
			renderHtml("empty");
		}
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
	 	List<DailyStat> dailyStats = DailyStat.findAll();
			if (dailyStats != null) {
				Logger.info("DailyStats Json to js: " + gson.toJson(dailyStats));
				renderJSON(gson.toJson(dailyStats));
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