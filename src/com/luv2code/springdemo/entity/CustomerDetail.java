package com.luv2code.springdemo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name="customer_detail")
public class CustomerDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Min(value=0, message="Invalid")
	@Max (value=120, message = "Invalid")
	@Column(name="age")
	private Integer age;
	
	@Column(name="hobby")
	private String hobby;
	
	@OneToOne(mappedBy="customerDetail", cascade= {CascadeType.PERSIST,
												   CascadeType.DETACH,
												   CascadeType.MERGE,
												   CascadeType.REFRESH})
	private Customer customer;
	
	public CustomerDetail() {
		
	}

	public CustomerDetail(@Min(value = 0, message = "Invalid") @Max(value = 120, message = "Invalid") int age,
			String hobby) {
		this.age = age;
		this.hobby = hobby;
	}

	// id
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// age
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	// hobby
	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	// customer
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "CustomerDetail [id=" + id + ", age=" + age + ", hobby=" + hobby + "]";
	}	
	
}
