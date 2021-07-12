package com.topcoderrohit.monetization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/*
 * user will be saved here
 */
@Entity
@Table(name = "Customer",
uniqueConstraints = {
        @UniqueConstraint(columnNames = "email_id")
})
public class Customer {
	@Id 
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private int id;
	@Column
	private String name;
	@Column(name="email_id",unique = true)
	private String emailId;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", emailId=" + emailId + "]";
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
