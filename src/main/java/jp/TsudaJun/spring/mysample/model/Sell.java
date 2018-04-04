package jp.TsudaJun.spring.mysample.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sell")
public class Sell {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sellid;
	
	@Column(insertable = false, updatable = false)
	private Timestamp boughtymd;
	
	@Column
	private int includingtax;
	
	@Column
	private int quantity;
	
	@Column
	private int itemid;
	
	@Column
	private String userid;
	
	@Column
	private int addressid;
	
	public void setSellid(int sellid) {
		this.sellid = sellid;
	}
	
	public int getSellid() {
		return sellid;
	}
	
	
	public Timestamp getBoughtymd() {
		return boughtymd;
	}
	
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	
	public int getItemid() {
		return itemid;
	}
	
	public void setIncludingtax(int includingtax) {
		this.includingtax = includingtax;
	}
	
	public int getIncludingtax() {
		return includingtax;
	}
	
	public void setUserid(String userid){
		this.userid = userid;
	}

	public String getUserid(){
		return userid;
	}
	
	public void setAddressid(int addressid){
		this.addressid = addressid;
	}

	public int getAddressid(){
		return addressid;
	}
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
	}

}
