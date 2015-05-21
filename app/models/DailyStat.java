package models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.CollectionTable;
import javax.persistence.JoinColumn;
import javax.xml.bind.annotation.XmlTransient;

import play.db.jpa.Model;

@Entity
public class DailyStat extends Model{
	
	private Date date;
	
	@ElementCollection
    @MapKeyColumn(name="key")
    @Column(name="value")
    @CollectionTable(name="dailyStat_table1")
	private Map<String, Number> table1 = new HashMap<String, Number>(); // maps from attribute key to value

	@OneToMany(mappedBy = "dailyDtat", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@XmlTransient 
	private List<Stock> table2;
	
	@ElementCollection
    @MapKeyColumn(name="name")
    @Column(name="value")
    @CollectionTable(name="dailyStat_table3")
	private Map<String, Number> table3 = new HashMap<String, Number>();;
	
	@OneToMany(mappedBy = "dailyDtat", cascade = CascadeType.ALL)
	private List<Stock> table4;
	
	public DailyStat(){
		super();
	}
	
	public DailyStat(Date date, HashMap<String, Number> table1,
			List<Stock> table2, HashMap<String, Number> table3,
			List<Stock> table4) {
		super();
		this.date = date;
		this.table1 = table1;
		this.table2 = table2;
		this.table3 = table3;
		this.table4 = table4;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Map<String, Number> getTable1() {
		return table1;
	}
	public void setTable1(HashMap<String, Number> table1) {
		this.table1 = table1;
	}
	public List<Stock> getTable2() {
		return table2;
	}
	public void setTable2(List<Stock> table2) {
		this.table2 = table2;
	}
	public Map<String, Number> getTable3() {
		return table3;
	}
	public void setTable3(HashMap<String, Number> table3) {
		this.table3 = table3;
	}
	public List<Stock> getTable4() {
		return table4;
	}
	public void setTable4(List<Stock> table4) {
		this.table4 = table4;
	}
	
	
	
	

}
