package entity;
import java.util.HashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "WAREHOUSE")
public class Warehouse {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "W_ID")
	private long w_id;

	@Column(name = "W_NAME")
	private String w_name;
	
	@Column(name = "STOCK")
	private HashMap<String, Integer> stock;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private ItemOrder itemOrder;

	public Warehouse() {}

	public long getW_id() {
		return w_id;
	}

	public HashMap<String, Integer> getStock() {
		return stock;
	}

	public void setStock(HashMap<String, Integer> stock) {
		this.stock = stock;
	}

	public void setW_id(long w_id) {
		this.w_id = w_id;
	}

	public String getW_name() {
		return w_name;
	}

	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
	
	public ItemOrder getItemOrder() {
		return this.itemOrder;
	}
	
	public void setItemOrder(ItemOrder itemOrder) {
		this.itemOrder = itemOrder;
	}
	
	
}



