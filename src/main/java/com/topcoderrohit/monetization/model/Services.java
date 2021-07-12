package com.topcoderrohit.monetization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/*
 * Api product will be saved here
 */
@Entity
@Table(name = "Services", uniqueConstraints = { @UniqueConstraint(columnNames = "name") })
public class Services {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@Column
	private String name;
	@Column(name = "is_freemium")
	private boolean freemium;
	@Column
	private String bundle;// bundlename as b1,b2 or just Independentservice
	@Column(name = "developer_id")
	private int developerId;
	//column for developer share
	@Column(name = "is_fixed_charge")
	private boolean fixedCharge;
	//column for developer share
	@Column
	private double charge;// when its fixed then charge otherwise check variable table

	public Services() {
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

	public boolean isFreemium() {
		return freemium;
	}

	public void setFreemium(boolean freemium) {
		this.freemium = freemium;
	}

	public String getBundle() {
		return bundle;
	}

	public void setBundle(String bundle) {
		this.bundle = bundle;
	}

	public int getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}

	public boolean isFixedCharge() {
		return fixedCharge;
	}

	public void setFixedCharge(boolean fixedCharge) {
		this.fixedCharge = fixedCharge;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}

	@Override
	public String toString() {
		return "Services [id=" + id + ", name=" + name + ", freemium=" + freemium + ", bundle=" + bundle
				+ ", developerId=" + developerId + ", fixedCharge=" + fixedCharge + ", charge=" + charge + "]";
	}
	
	
}