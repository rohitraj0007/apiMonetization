package com.topcoderrohit.monetization.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/*
 * user related service and information will be saved here
 */
@Entity
@Table(name="Customer_service")
public class CustomerService {
	@Id
	 @GeneratedValue(strategy= GenerationType.SEQUENCE)
	private int id;
	@Column(name="customer_id")
	private int customerId;
	@Column(name="service_id")
	private int serviceId;
	@Column(name="plan_type")
	private char planType;// unit/metered=u,m=monthly,q=quarterly,y=yearly
	@Column(name="plan_end_date")
	private LocalDateTime planEndDate;
	@Column(name="is_active")
	private boolean active;
	@Column(name="is_freemium_active")
	private boolean freemiumActive;
	@Column(name="plan_end_volume")
	private int planEndVolume;
	@Column(name="freemium_type")
	private int freemiumType;

	public CustomerService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public char getPlanType() {
		return planType;
	}

	public void setPlanType(char planType) {
		this.planType = planType;
	}

	public LocalDateTime getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(LocalDateTime planEndDate) {
		this.planEndDate = planEndDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getPlanEndVolume() {
		return planEndVolume;
	}

	public void setPlanEndVolume(int planEndVolume) {
		this.planEndVolume = planEndVolume;
	}

	public int getFreemiumType() {
		return freemiumType;
	}

	public void setFreemiumType(int freemiumType) {
		this.freemiumType = freemiumType;
	}

	public boolean isFreemiumActive() {
		return freemiumActive;
	}

	public void setFreemiumActive(boolean freemiumActive) {
		this.freemiumActive = freemiumActive;
	}

	@Override
	public String toString() {
		return "CustomerService [id=" + id + ", customerId=" + customerId + ", serviceId=" + serviceId + ", planType="
				+ planType + ", planEndDate=" + planEndDate + ", active=" + active + ", planEndVolume=" + planEndVolume
				+ ", freemiumType=" + freemiumType + "]";
	}



}
