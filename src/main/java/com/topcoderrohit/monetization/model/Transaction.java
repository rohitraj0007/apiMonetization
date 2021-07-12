package com.topcoderrohit.monetization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/*
 * this table will be updated for every transaction
 */
@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private int id;
	@Column(name = "developer_service_id")
	private int developerServiceId;//service id from service table
	@Column(name = "transaction_total")
	private double transactionTotal;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDeveloperServiceId() {
		return developerServiceId;
	}

	public void setDeveloperServiceId(int developerServiceId) {
		this.developerServiceId = developerServiceId;
	}

	public double getTransactionTotal() {
		return transactionTotal;
	}

	public void setTransactionTotal(double transactionTotal) {
		this.transactionTotal = transactionTotal;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", developerServiceId=" + developerServiceId + ", transactionTotal="
				+ transactionTotal + "]";
	}

}
