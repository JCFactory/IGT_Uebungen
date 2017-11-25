package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="CUSTOMER")
public class Customer {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CUSTOMER_ID")
	private long customer_id;
	
	@Column(name="CUSTOMER_NAME")
	private String customer_name;
	
	@Column(name="CUSTOMER_LASTNAME")
    private String customer_lastname;
    
	@Column(name="CUSTOMER_COMPANY")
    private String customer_company;
    
    public Customer() {}
    
    public Customer(String customer_name, String customer_lastname, String customer_company) {
    	this.customer_name = customer_name;
    	this.customer_lastname = customer_lastname;
    	this.customer_company = customer_company;
    }
    
	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long c_id) {
		this.customer_id = c_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String c_name) {
		this.customer_name = c_name;
	}

	public String getCustomer_lastname() {
		return customer_lastname;
	}

	public void setCustomer_lastname(String c_lastname) {
		this.customer_lastname = c_lastname;
	}

	public String getCustomer_company() {
		return customer_company;
	}

	public void setCustomer_company(String c_company) {
		this.customer_company = c_company;
	}

	
}

