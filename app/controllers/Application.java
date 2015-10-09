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
    	List<DailyStat> dailyStats = DailyStat.all().from(statsCount-5).fetch(5);
		
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
	
 
	
/**
	[
	   {
	      "date":"May 21, 2015 12:00:00 AM",
	      "dailyRanking":"1",
	      "stockCode":"600585",
	      "stockName":"海螺水泥　　　　",
	      "buying":332634698,
	      "selling":117690007,
	      "totalAmount":450324705,
	      "id":721
	   },
	   {
	      "date":"May 21, 2015 12:00:00 AM",
	      "dailyRanking":"2",
	      "stockCode":"601318",
	      "stockName":"中国平安　　　　",
	      "buying":252246457,
	      "selling":150704795,
	      "totalAmount":402951252,
	      "id":722
	   },
	   {
	      "date":"May 21, 2015 12:00:00 AM",
	      "dailyRanking":"3",
	      "stockCode":"600881",
	      "stockName":"亚泰集团　　　　",
	      "buying":3045062,
	      "selling":287808504,
	      "totalAmount":290853566,
	      "id":723
	   },
	   {
	      "date":"May 21, 2015 12:00:00 AM",
	      "dailyRanking":"4",
	      "stockCode":"600519",
	      "stockName":"贵州茅台　　　　",
	      "buying":219618533,
	      "selling":20340768,
	      "totalAmount":239959301,
	      "id":724
	   },
	   {
	      "date":"May 21, 2015 12:00:00 AM",
	      "dailyRanking":"5",
	      "stockCode":"600887",
	      "stockName":"伊利股份　　　　",
	      "buying":82591815,
	      "selling":101094727,
	      "totalAmount":183686542,
	      "id":725
	   },
	   {
	      "date":"May 21, 2015 12:00:00 AM",
	      "dailyRanking":"6",
	      "stockCode":"600036",
	      "stockName":"招商银行　　　　",
	      "buying":122343758,
	      "selling":54881489,
	      "totalAmount":177225247,
	      "id":726
	   },
	   {
	      "date":"May 21, 2015 12:00:00 AM",
	      "dailyRanking":"7",
	      "stockCode":"600009",
	      "stockName":"上海机场　　　　",
	      "buying":0,
	      "selling":176888372,
	      "totalAmount":176888372,
	      "id":727
	   },
	   {
	      "date":"May 21, 2015 12:00:00 AM",
	      "dailyRanking":"8",
	      "stockCode":"601398",
	      "stockName":"工商银行　　　　",
	      "buying":128868131,
	      "selling":35873252,
	      "totalAmount":164741383,
	      "id":728
	   },
	   {
	      "date":"May 21, 2015 12:00:00 AM",
	      "dailyRanking":"9",
	      "stockCode":"600030",
	      "stockName":"中信证券　　　　",
	      "buying":117708186,
	      "selling":25950504,
	      "totalAmount":143658690,
	      "id":729
	   },
	   {
	      "date":"May 21, 2015 12:00:00 AM",
	      "dailyRanking":"10",
	      "stockCode":"601166",
	      "stockName":"兴业银行　　　　",
	      "buying":108891638,
	      "selling":29678619,
	      "totalAmount":138570257,
	      "id":730
	   }
	]
**/

}