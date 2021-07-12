package com.topcoderrohit.monetization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/*
 * different service rate plan will be saved here
 */
@Entity
@Table(name = "service_rate")
public class ServiceRate {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@Column(name = "service_id")
	private String serviceId;
	@Column(name = "rate_daily")
	private double rateDaily;
	@Column(name = "rate_monthly")
	private double rateMonthly;;
	@Column(name = "rate_yearly")
	private double rateYearly;
	@Column(name = "rate_quaterly")
	private double rateQuaterly;
	@Column(name = "meter_rate")
	private double meterRate;

	public ServiceRate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public double getRateDaily() {
		return rateDaily;
	}

	public void setRateDaily(double rateDaily) {
		this.rateDaily = rateDaily;
	}

	public double getRateMonthly() {
		return rateMonthly;
	}

	public void setRateMonthly(double rateMonthly) {
		this.rateMonthly = rateMonthly;
	}

	public double getRateYearly() {
		return rateYearly;
	}

	public void setRateYearly(double rateYearly) {
		this.rateYearly = rateYearly;
	}

	public double getMeterRate() {
		return meterRate;
	}

	public void setMeterRate(double meterRate) {
		this.meterRate = meterRate;
	}

	public double getRateQuaterly() {
		return rateQuaterly;
	}

	public void setRateQuaterly(double rateQuaterly) {
		this.rateQuaterly = rateQuaterly;
	}

	@Override
	public String toString() {
		return "ServiceRate [id=" + id + ", serviceId=" + serviceId + ", rateDaily=" + rateDaily + ", rateMonthly="
				+ rateMonthly + ", rateYearly=" + rateYearly + ", rateQuaterly=" + rateQuaterly + ", meterRate="
				+ meterRate + "]";
	}

	

}
