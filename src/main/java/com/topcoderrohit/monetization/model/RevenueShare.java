package com.topcoderrohit.monetization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/*
 * Variable share for sevice provided by developer will be managed here
 */
@Entity
@Table(name = "revenue_share")
public class RevenueShare {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@Column(name = "developer_service_id")
	private int developerServiceId;
	@Column(name = "lower_limit")
	private double lowerLimit;
	@Column(name = "upper_limit")
	private double upperLimit;
	@Column(name = "variable_share")
	private double percentageVariableCharge;// % of transaction will be the developer share

	public RevenueShare() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getServiceId() {
		return developerServiceId;
	}

	public void setDeveloperServiceId(int developerServiceId) {
		this.developerServiceId = developerServiceId;
	}

	public double getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(double lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public double getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(double upperLimit) {
		this.upperLimit = upperLimit;
	}

	public double getPercentageVariableCharge() {
		return percentageVariableCharge;
	}

	public void setPercentageVariableCharge(double percentageVariableCharge) {
		this.percentageVariableCharge = percentageVariableCharge;
	}

	public int getDeveloperServiceId() {
		return developerServiceId;
	}

	@Override
	public String toString() {
		return "RevenueShare [id=" + id + ", developerServiceId=" + developerServiceId + ", lowerLimit=" + lowerLimit
				+ ", upperLimit=" + upperLimit + ", percentageVariableCharge=" + percentageVariableCharge + "]";
	}



}
