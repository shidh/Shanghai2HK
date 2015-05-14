package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Stock extends Model{
	private String dailyRanking;
	private String stockCode;
	private String stockName;
	private String buying;
	private String selling;
	private String totalAmount;
	
	public Stock(){
		super();
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

	public String getBuying() {
		return buying;
	}

	public void setBuying(String buying) {
		this.buying = buying;
	}

	public String getSelling() {
		return selling;
	}

	public void setSelling(String selling) {
		this.selling = selling;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	

	
	
}
