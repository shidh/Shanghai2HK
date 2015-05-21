package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Stock extends Model{
	private Date date;
	private String dailyRanking;
	private String stockCode;
	private String stockName;
	private Number buying;
	private Number selling;
	private Number totalAmount;

	//@ManyToOne
	public DailyStat dailyDtat;
	
	public Stock(){
		super();
	}

	
	
	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public DailyStat getDailyDtat() {
		return dailyDtat;
	}



	public void setDailyDtat(DailyStat dailyDtat) {
		this.dailyDtat = dailyDtat;
	}



	public String getDailyRanking() {
		return dailyRanking;
	}

	public void setDailyRanking(String dailyRanking) {
		this.dailyRanking = dailyRanking;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Number getBuying() {
		return buying;
	}

	public void setBuying(Number buying) {
		this.buying = buying;
	}

	public Number getSelling() {
		return selling;
	}

	public void setSelling(Number selling) {
		this.selling = selling;
	}

	public Number getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Number totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	

	
	
}
