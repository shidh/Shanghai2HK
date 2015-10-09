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
		
    	//generateRandom();
    	reverse("asdfghjkl");
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
	
    private static void generateRandom(){
    	int count = 0;
    	Random random = new Random();
    	HashSet<Integer> set = new HashSet<Integer>();
    	while(count <900){
    		int randomNr = random.nextInt(1000);
    		if(!set.contains(randomNr)){
    			set.add(randomNr);
        		count++;
        		System.out.println(randomNr);
    		}
    	}
    }

    public static String reverse(String s) {
    		//asdfghjkl
    	   if (s.length() == 1) {
    	    return s;
    	   }
    	   //get the last bit of the string, l
    	   String str = s.substring(s.length() - 1, s.length());
    	   System.out.println("#"+str);
    	   String reverseTheRest = reverse(s.substring(0, s.length() - 1));
    	   System.out.println("#"+reverseTheRest);
    	   str = str + reverseTheRest;
    	   System.out.println(str);
    	  
    	   return str;
    	}
    
    /**
     * 1、生成窗口最大值数组
	有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右边滑一个位置。
	例如，数组为[4,3,5,4,3,3,6,7]，窗口大小为3时：
	
	[4 3 5] 4 3 3 6 7 窗口中最大值为5
	4 [3 5 4] 3 3 6 7 窗口中最大值为5
	4 3 [5 4 3] 3 6 7 窗口中最大值为5
	4 3 5 [4 3 3] 6 7 窗口中最大值为4
	4 3 5 4 [3 3 6] 7 窗口中最大值为6
	4 3 5 4 3 [3 6 7] 窗口中最大值为7
	
	如果数组长度为n，窗口大小为w，则一共产生n-w+1个窗口的最大值。请实现一个函数，给定一个数组arr，窗口大小w。
	返回一个长度为n-w+1的数组res,res[i]表示每一种窗口状态下的最大值。以本题为例，结果应该返回[5,5,5,4,6,7]。
     */
	public int generateMax(){
		int max = 0;
		LinkedList<Integer> qmax = new LinkedList<Integer>();
		qmax.peekLast();
		qmax.peekFirst();
		return max;
	}
	
	
	/**
	 * 2、最大值减去最小值小于或等于num的子数组数量
给定数组arr和整数num，返回有多少个子数组满足如下情况:
max(arr[i..j]) - min(arr[i..j]) <= num
max(arr[i..j])表示子数组arr[i..j]中的最大值，min(arr[i..j])表示子数组arr[i..j]中的最小值。如果数组长度为 N，请实现时间复杂度为 O(N)的解法。


3、复制含有随机指针节点的链表
一种特殊的链表节点类描述如下:
class Node {
public int value;
public Node next;
public Node rand;
public Node(int data) {
this.value = data;
} 
}
Node类中的value是节点值，next指针和正常单链表中next指针的意义一样，都指向下一个节点，rand指针是Node类中新增的指针，这个指针可能指向链表中的任意一个节点，也可能指向null。给定一个由Node节点类型组成的无环单链表的头节点 head，请实现一个函数完成这个链表中所有结构的复制，并返回复制的新链表的头节点。要求除了需要返回的东西外，不再使用额外的数据结构并且在时间复杂度为 O(N)内完成原问题要实现的函数。



4、一种怪异的节点删除方式
链表节点值类型为int型，给定一个链表中的节点node，但不给定整个链表的头节点，如何在链表中删除node？请实现这个函数,并分析这么会出现哪些问题。要求时间复杂度为O(1)。



5、设计可以变更的缓存结构
【题目】
设计一种缓存结构，该结构在构造时确定大小，假设大小为 K，并有两个功能：
1，set(key,value)：将记录(key,value)插入该结构。
2，get(key)：返回key对应的value值。
【要求】
1，set和get方法的时间复杂度为O(1)。
2，某个key的set或get操作一旦发生，认为这个 key 的记录成了最经常使用的。 
3，当缓存的大小超过K时，移除最不经常使用的记录，即set或get最久远的。
【举例】
假设缓存结构的实例是cache，大小为3，并依次发生如下行为:
1，cache.set("A",1)。最经常使用的记录为("A",1)。 
2，cache.set("B",2)。最经常使用的记录为(“B”,2)，(“A”,1)变为最不经常的。
3，cache.set("C",3)。最经常使用的记录为(“C”,2)，(“A”,1)还是最不经常的。 
4，cache.get("A")。最经常使用的记录为(“A”,1)，(“B”,2)变为最不经常的。 
5，cache.set("D",4)。大小超过了 3，所以移除此时最不经常使用的记录(“B”,2)，加入记录(“D”,4)，并且为最经常使用的记录，然后("C",2)变为最不经常使用的记录。
	 */
	
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