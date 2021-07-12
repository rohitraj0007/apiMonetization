package com.topcoderrohit.monetization.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/*
 * this model class will be used to subscribe api product plan
 */
@ApiModel(description = "this model class will be used to subscribe api product plan")
public class SubscribeModel {

	private int serviceId;
	@ApiModelProperty(notes = "freemiumType are N,duration-D,volume-V,both-B")
	private Character freemiumType;// N,duration-D,volume-V,both-B//
	private int volume;// used in case of freemium
	private String duration;// used in only case of freemium!=N
	private int customerId;
	@ApiModelProperty(notes = "planType are unit/metered=U,M=monthly,Q=quarterly,Y=yearly,D=daily")
	private char planType;// unit/metered=U,M=monthly,Q=quarterly,Y=yearly,D=daily

	public SubscribeModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "SubscribeModel [serviceId=" + serviceId + ", freemiumType=" + freemiumType + ", volume=" + volume
				+ ", duration=" + duration + ", customerId=" + customerId + ", planType=" + planType + "]";
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public Character getFreemiumType() {
		return freemiumType;
	}

	public void setFreemiumType(Character freemiumType) {
		this.freemiumType = freemiumType;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public char getPlanType() {
		return planType;
	}

	public void setPlanType(char planType) {
		this.planType = planType;
	}

}
