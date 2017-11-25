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
    @Column(name="C_ID")
	private long customer_id;
	
	@Column(name="C_FORENAME")
	private String c_forename;
	
	@Column(name="C_SURNAME")
    private String c_surname;
    
	@Column(name="C_COMPANY")
    private String c_company;
    
    public Customer() {}
    
    public Customer(String c_forename, String c_lastname, String c_company) {
    	this.c_forename = c_forename;
    	this.c_surname = c_surname;
    	this.c_company = c_company;
    }
    
	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long c_id) {
		this.customer_id = c_id;
	}

	public String getCustomer_forename() {
		return c_forename;
	}

	public void setCustomer_forename(String c_forename) {
		this.c_forename = c_forename;
	}

	public String getCustomer_surname() {
		return c_surname;
	}

	public void setCustomer_surname(String c_surname) {
		this.c_surname = c_surname;
	}

	public String getCustomer_company() {
		return c_company;
	}

	public void setCustomer_company(String c_company) {
		this.c_company = c_company;
	}

	
}

