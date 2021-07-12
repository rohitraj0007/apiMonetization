package com.topcoderrohit.monetization.model;
/*
 * a model class to communicate about developer total share at month end
 */
public class DeveloperTotalShare {
	private String developerId;

	private double amount;

	public String getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(String developerId) {
		this.developerId = developerId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "DeveloperTotalShare [developerId=" + developerId + ", amount=" + amount + "]";
	}

	public DeveloperTotalShare() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
