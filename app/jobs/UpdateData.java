package jobs;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import models.DailyStat;
import models.Stock;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import play.Logger;

import com.google.gson.Gson;

import play.jobs.*;

// @Every("1d")
//@On("S M  H  D M W   *Y")
// Fire at 17:15am and 17:35 every Monday, Tuesday, Wednesday, Thursday and Friday
@On("0 15,35 17 ? * MON-FRI")
public class UpdateData extends Job {
	private static String baseUrl = "http://sc.hkex.com.hk/gb/www.hkex.com.hk/chi/csm/dailystat/";
	//d20150401c
	private static String dailyData = "d20150401c.htm";
	
	
	public void doJob() {
        Logger.info("Maintenance job ...");

		dailyData = "d"+setDate()+"c.htm";

		Gson gson = new Gson();
		Document doc = null;
		String time = "";
		String all ="";
		int numberOfTable = 1;
		
		HashMap<String, String> table1 = new HashMap<String, String>();
		List<Stock> table2 = new ArrayList<Stock>();
		HashMap<String, String> table3 = new HashMap<String, String>();
		List<Stock> table4 = new ArrayList<Stock>();
	
		
		DailyStat data = DailyStat.find("byDate", setDate()).first();

		//check if the data is already existing in DB
		if(data == null){
			try {
				doc = Jsoup.connect(baseUrl + dailyData).get();
			
				Elements tables = doc.select("td.bg3");
			
				//Elements tables = doc.select("table");
				for (Element table : tables) {
					if(numberOfTable == 3){
						Element date = table.select("font.font1").first();
						if (date != null){
							all = all + date+ "</br>";
							time = date.text();
						}
					}
				    Elements trs = table.select("tr");
				    
				    String[][] trtd = new String[trs.size()][];
				    if(trtd.length > 0){
				    	// 沪股通 Northbound Trading
						if(numberOfTable == 5 ){
							for (int i = 0; i < trs.size(); i++) { // row number
						    	//System.out.println("row "+i);
						        Elements tds = trs.get(i).select("td");
						        trtd[i] = new String[tds.size()];
						        for (int j = 0; j < tds.size(); j++) { // column number
							    	//System.out.println("column "+j);
						            trtd[i][j] = tds.get(j).text(); 
						            table1.put(trtd[i][0], trtd[i][1]);
						        }
						    }
						}
						if(numberOfTable == 6){
							//if(val%2==0) 
							for (int i = 1; i < trs.size(); i++) { // row number
						    	//System.out.println("row "+i);
						        Elements tds = trs.get(i).select("td");
						        trtd[i] = new String[tds.size()];
						        
						        Stock stock = new Stock();
						        for (int j = 0; j < tds.size(); j++) { // column number
						            trtd[i][j] = tds.get(j).text(); 
								    //all = all + trtd[i][j]+ "</br>";
						        }
						        stock.setDailyRanking(trtd[i][0]);
					            stock.setStockCode(trtd[i][1]);
					            stock.setStockName(trtd[i][2].trim());
					            stock.setBuying(trtd[i][3]);
					            stock.setSelling(trtd[i][4]);
					            stock.setTotalAmount(trtd[i][5]);
					            table2.add(stock);
					            stock.save();
						    }
						}
						
						
						//  港股通 Southbound Trading
						if( numberOfTable == 7){
							for (int i = 0; i < trs.size(); i++) { // row number
						        Elements tds = trs.get(i).select("td");
						        trtd[i] = new String[tds.size()];
						        for (int j = 0; j < tds.size(); j++) { // column number
						            trtd[i][j] = tds.get(j).text(); 
						            table3.put(trtd[i][0], trtd[i][1]);
						        }
						    }
						}
					    
						if(numberOfTable == 8){
							//if(val%2==0) 
							for (int i = 1; i < trs.size(); i++) { // row number
						    	//System.out.println("row "+i);
						        Elements tds = trs.get(i).select("td");
						        trtd[i] = new String[tds.size()];
						        
						        Stock stock = new Stock();
						        for (int j = 0; j < tds.size(); j++) { // column number
						            trtd[i][j] = tds.get(j).text(); 
								    //all = all + trtd[i][j]+ "</br>";
						        }
						        stock.setDailyRanking(trtd[i][0]);
					            stock.setStockCode(trtd[i][1]);
					            stock.setStockName(trtd[i][2].trim());
					            stock.setBuying(trtd[i][3]);
					            stock.setSelling(trtd[i][4]);
					            stock.setTotalAmount(trtd[i][5]);
					            table4.add(stock);
					            stock.save();
						    }
						}
					    
					    
				    }
				    // trtd now contains the desired array for this table
				    numberOfTable++;
			
				}
				//all = time+"</br>"+table1+"</br>"+table2+"</br>"+table3+"</br>"+table4;
				DailyStat daily = new DailyStat();
				daily.setDate(setDate());
				daily.setTable1(table1);
				daily.setTable2(table2);
				daily.setTable3(table3);
				daily.setTable4(table4);
				daily.save();
				Logger.info("saved");
			} catch (IOException e) {
				e.printStackTrace();
				Logger.warn("Probably " +  setDate() + " is holiday :)");
				//renderText("Probably " +  setDate() + " is holiday :)");

			}
		}else{
			Logger.info("The data for "+setDate()+" is already fetched.");
		}
	
	
		if (table1 != null) {
			Logger.info("Json to js: " + gson.toJson(table2));
		} else {
		}

	}

	

	private static String setDate(){
	    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	    Date date = new Date();
	    return(dateFormat.format(date));
	}



}
