package entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "WAREHOUSE")
public class Warehouse {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "W_ID")
	private long w_id;

	@Column(name = "W_NAME")
	private String w_name;

	public Warehouse() {}

	public long getW_id() {
		return w_id;
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
	
	
}



