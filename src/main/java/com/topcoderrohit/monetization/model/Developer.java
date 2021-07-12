package com.topcoderrohit.monetization.model;

import javax.persistence.Column;
/*
 * developer who creted the api product will be saved here
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity
@Table(name = "Developer",
uniqueConstraints = {
        @UniqueConstraint(columnNames = "email_id")
})
public class Developer {
	@Id
	 @GeneratedValue(strategy= GenerationType.SEQUENCE)
	private int id;
	@Column
	private String name;
	@Column(name="email_id",unique = true)
	private String emailId;
	
	public Developer() {
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
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@Override
	public String toString() {
		return "Developer [id=" + id + ", name=" + name + ", emailId=" + emailId + "]";
	}

}
