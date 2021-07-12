package com.topcoderrohit.monetization.model;
/*
 * a model class to communicate for developer share
 */
public class DeveloperShareOutput {
private String developerId;
private String serviceId;
private double amount;


public DeveloperShareOutput() {
	super();
	// TODO Auto-generated constructor stub
}
public String getDeveloperId() {
	return developerId;
}
public void setDeveloperId(String developerId) {
	this.developerId = developerId;
}
public String getServiceId() {
	return serviceId;
}
public void setServiceId(String serviceId) {
	this.serviceId = serviceId;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
@Override
public String toString() {
	return "DeveloperShareOutput [developerId=" + developerId + ", serviceId=" + serviceId + ", amount=" + amount + "]";
}

}
