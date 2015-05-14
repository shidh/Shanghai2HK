package controllers;

import play.*;
import play.mvc.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

import models.*;

public class Application extends Controller {
	private static String baseUrl = "http://sc.hkex.com.hk/gb/www.hkex.com.hk/chi/csm/dailystat/";
	//d20150401c
	private static String dailyData = "d20150512c.htm";
    public static void index() {
        String html = "<html><head><title>First parse</title></head>"
        		  + "<body><p>Parsed HTML into a doc.</p></body></html>";
        //Document doc = Jsoup.parse(html);

        dailyData = setData();
        Gson gson = new Gson();
        Document doc = null;
		try {
			doc = Jsoup.connect(baseUrl + dailyData).get();
//			  .data("query", "Java")   // 请求参数
//			  .userAgent("I ’ m jsoup") // 设置 User-Agent 
//			  .cookie("auth", "token") // 设置 cookie 
//			  .timeout(3000)           // 设置连接超时时间
//			  .post();                 // 使用 POST 方法访问 URL 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Probably " +  dailyData + " is holiday :)");
			
		}

        
		String time = "";
		String all ="";
		int numberOfTable = 1;
		HashMap<String, String> table1 = new HashMap<String, String>();
		List<Stock> table2 = new ArrayList<Stock>();
		
		HashMap<String, String> table3 = new HashMap<String, String>();
		List<Stock> table4 = new ArrayList<Stock>();


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
				    }
				}
			    
			    
		    }
		    // trtd now contains the desired array for this table
		    numberOfTable++;

		}
		//all = time+"</br>"+table1+"</br>"+table2+"</br>"+table3+"</br>"+table4;


		
//		String rParameters = "";
//		ArrayList<String> parmsList = new ArrayList<String>();
//		parmsList = gson.fromJson(jsonParms, ArrayList.class);
//		for (String str : parmsList) {
//			rParameters = rParameters + str + " ";
//		}


		if (table1 != null) {
			System.out.println("Json to js: " + gson.toJson(table1));
			renderJSON(gson.toJson(table2));
		} else {
			renderHtml("empty");
		}
		//render(all);
		
		
		
		
		
		//version 2
//		for (Element td : tds) { //for each table
//			Elements trs = td.select("tr");
//			for (Element tr : trs) { //for each record
//				Elements keyValues = tr.select("font.font1");
//				for (Element kv : keyValues) {
//					String text = kv.text();
//					System.out.println(text);
//					all = all +text+" </br> ";
//				}
//			}
//		}
//		
		
		//version 1
//		Elements keyValues = doc.select("font.font1");
//		for (Element kv : keyValues) {
//			String text = kv.text();
//			System.out.println(text);
//		}
		
		
		
//		int i=1;
//		for (Element td : tds) { //for each table
//			
//	        switch (i) {
//	            case 3:  
//	            	Elements keyValues = td.select("font.font1");
//	            	for (Element kv : keyValues) {
//	            		String text = kv.text();
//	            		time = time +text+": ";
//	            	}
//	    			break;
//	    			
//	            case 5:  
//            		Elements keyValues5 = td.select("font.font1");
//	            	for (Element kv : keyValues5) {
//	            		String text = kv.text();
//	            		table1 = table1 +text+": ";
//	            	}
//		            break;
//		            
//	            case 6:
//	    			Elements trs = td.select("tr");
//            	    String[][] trtd = new String[trs.size()][];
//
//	    			for (int j = 0; j < trs.size(); j++) { //for each record
//	    				Elements tds6 = trs.get(j).select("td");
//	    				trtd[j] = new String[tds6.size()];
//	    				for (int k = 0; k < tds6.size(); k++) {
//	    					trtd[j][k] = tds6.get(k).text(); 
//	    				}
//	    				table2 = table2 +"</br>";
//	    				all =trtd[2][2];
//	    			}
//	            	
//	            	
//                    break;
//
//	            
//	            case 7:  
//	            	Elements keyValues7 = td.select("font.font1");
//	            	for (Element kv : keyValues7) {
//	            		String text = kv.text();
//	            		table3 = table3 +text+": ";
//	            	}
//	    			break;
//	    			
//	            case 8:  
//                    break;
//	        }
			
				
				
//			Elements trs = td.select("tr");
//			for (Element tr : trs) { //for each record
//				Elements keyValues = tr.select("font.font1");
//				for (Element kv : keyValues) {
//					String text = kv.text();
//					System.out.println(text);
//					all = all +text+" </br> ";
//
//				}
//			}
//			i++;
//		}
		
    }
    
    private static String setData(){
	    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	    Date date = new Date();
	    return( "d"+dateFormat.format(date)+"c.htm");
    }
    
    

}