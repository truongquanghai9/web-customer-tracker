package com.luv2code.springdemo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name = "first_name")
	@NotNull(message="is required!")
	@Size(min=1,message="is required!")
	private String firstName;
	
	@Column(name="last_name")
	@NotNull(message="is required!")
	@Size(min=1,message="is required!")
	private String lastName;
	
	@Column(name="email")
	@Pattern(regexp="^(.+)@(.+)$",message="Invalid Email!")
	private String email;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="customer_detail_id")
	@Valid
	private CustomerDetail customerDetail;
	
	public Customer() {
		
	}

	public Customer(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	// id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	// firstName
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	// lastName
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	// email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// customerDetail
	public CustomerDetail getCustomerDetail() {
		return customerDetail;
	}

	public void setCustomerDetail(CustomerDetail customerDetail) {
		this.customerDetail = customerDetail;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
}
