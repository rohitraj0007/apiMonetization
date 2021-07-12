package com.topcoderrohit.monetization.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/*
 * details for freemium plan subscribed will be saved here
 */
@Entity
public class Freemium {
@Id
@GeneratedValue(strategy= GenerationType.SEQUENCE)
private int id;
@Column(name="service_id")
private int serviceId;
@Column(name="is_active")
private boolean active;//customer is subscribed to the plan or not
@Column
private char type;//duration-d,volume-v,both-b//
@Column(name="type_post_freemium")
private char typePostFreemium;//after freemium plan
@Column
private int volume;
@Column
private LocalDateTime duration;
@Column(name="consumer_id")
private int consumerId;


public Freemium() {
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
	return serviceId;
}


public void setServiceId(int serviceId) {
	this.serviceId = serviceId;
}


public boolean isActive() {
	return active;
}


public void setActive(boolean active) {
	this.active = active;
}


public char getType() {
	return type;
}


public void setType(char type) {
	this.type = type;
}


public int getVolume() {
	return volume;
}


public void setVolume(int volume) {
	this.volume = volume;
}


public LocalDateTime getDuration() {
	return duration;
}


public void setDuration(LocalDateTime duration) {
	this.duration = duration;
}


public int getConsumerId() {
	return consumerId;
}


public void setConsumerId(int consumerId) {
	this.consumerId = consumerId;
}


public char getTypePostFreemium() {
	return typePostFreemium;
}


public void setTypePostFreemium(char typePostFreemium) {
	this.typePostFreemium = typePostFreemium;
}


@Override
public String toString() {
	return "Freemium [id=" + id + ", serviceId=" + serviceId + ", active=" + active + ", type=" + type + ", volume="
			+ volume + ", duration=" + duration + ", consumerId=" + consumerId + "]";
}



}
