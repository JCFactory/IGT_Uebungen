package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "ITEM")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "I_ID")
	private long i_id;
	
	@Column(name = "I_NAME")
	private String i_name;
	
	@Column(name = "I_PRICE")
	private double i_price;
	
	public Item() {}

	public Item(String i_name, double i_price) {
		this.i_name = i_name;
		this.i_price = i_price;
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

	
	
	
	
}
