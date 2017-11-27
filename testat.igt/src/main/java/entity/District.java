package entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "DISTRICT")
public class District {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "D_ZIP")
	private long d_zip;

	@Column(name = "D_NAME")
	private String d_name;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Warehouse warehouse;

	public District() {}

	public District(String d_name) {
    	this.d_name = d_name;
    }
	
	public long getDistrict_zip() {
		return d_zip;
	}

	public void setDistrict_zip(long d_zip) {
		this.d_zip = d_zip;
	}

	public String getDistrict_name() {
		return d_name;
	}

	public void setDistrict_name(String d_name) {
		this.d_name = d_name;
	}
	
	

}
