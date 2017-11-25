package entity;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "ORDER")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "O_ID")
	private long o_id;
	
	@Column(name = "O_LIST")
	private ArrayList<Item> o_list;
	
	@Column(name = "O_SUM")
	private double o_sum;
	
	public Order() {}
	
	public Order(ArrayList<Item> o_list, double o_sum) {
		this.o_list = o_list;
		this.o_sum = o_sum;
	}
	
	public double getO_sum() {
		return o_sum;
	}

	public void setO_sum(double o_sum) {
		this.o_sum = o_sum;
	}

	
	
	public Order(ArrayList<Item> o_list) {
		this.o_list = o_list;
	}
	
	public ArrayList<Item> getO_list() {
		return o_list;
	}

	public void setO_list(ArrayList<Item> o_list) {
		this.o_list = o_list;
	}

	

}
