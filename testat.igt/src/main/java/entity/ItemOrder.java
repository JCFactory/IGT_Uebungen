package entity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "ITEM_ORDER")
public class ItemOrder {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "IO_ID")
	private long io_id;
	
	@Column(name = "IO_SUM")
	private float io_sum;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Customer customer;
	
	public ItemOrder() {}

	public long getIo_id() {
		return io_id;
	}

	public void setIo_id(long io_id) {
		this.io_id = io_id;
	}

	public float getIo_sum() {
		return io_sum;
	}

	public void setIo_sum(float io_sum) {
		this.io_sum = io_sum;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
