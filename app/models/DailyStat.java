package models;

import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class DailyStat extends Model{
	
	private String date;
	private HashMap<String, String> table1;
	private List<Stock> table2;
	private HashMap<String, String> table3;
	private List<Stock> table4;
	
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public HashMap<String, String> getTable1() {
		return table1;
	}
	public void setTable1(HashMap<String, String> table1) {
		this.table1 = table1;
	}
	public List<Stock> getTable2() {
		return table2;
	}
	public void setTable2(List<Stock> table2) {
		this.table2 = table2;
	}
	public HashMap<String, String> getTable3() {
		return table3;
	}
	public void setTable3(HashMap<String, String> table3) {
		this.table3 = table3;
	}
	public List<Stock> getTable4() {
		return table4;
	}
	public void setTable4(List<Stock> table4) {
		this.table4 = table4;
	}
	
	
	
	

}
