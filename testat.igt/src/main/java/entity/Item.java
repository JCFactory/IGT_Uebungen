package entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "ITEM")
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "I_ID")
	private long i_id;
	
	@Column(name = "I_NAME")
	private String i_name;
	
	@Column(name = "I_PRICE")
	private double i_price;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private ItemOrder itemOrder;
	
	public Item() {}

	public long getI_id() {
		return i_id;
	}

	public void setI_id(long i_id) {
		this.i_id = i_id;
	}

	public String getI_name() {
		return i_name;
	}

	public void setI_name(String i_name) {
		this.i_name = i_name;
	}

	public double getI_price() {
		return i_price;
	}

	public void setI_price(double i_price) {
		this.i_price = i_price;
	}

	public ItemOrder getItemOrder() {
		return itemOrder;
	}

	public void setItemOrder(ItemOrder itemOrder) {
		this.itemOrder = itemOrder;
	}
	
	
}
