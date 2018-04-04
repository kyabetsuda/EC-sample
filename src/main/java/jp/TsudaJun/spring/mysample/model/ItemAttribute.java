package jp.TsudaJun.spring.mysample.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="itemattribute")
public class ItemAttribute implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column
    private int itemattributeno;
	
	@Column
	private String itemattributenm;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="itemattribute", referencedColumnName = "itemattributenm" )
	private List<Item> items = null;
	
	public int getItemattributeno() {
		return itemattributeno;
	}
	
	public String getItemattributenm() {
		return itemattributenm;
	}
	
	

}
