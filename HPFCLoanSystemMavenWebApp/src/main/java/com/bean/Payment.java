package com.bean;

import java.util.Date;

public class Payment {

	private double totalEmi;
	private double emi;
	private double fine;
	private Date paymentDate;
	public double getTotalEmi() {
		return totalEmi;
	}
	public void setTotalEmi(double totalEmi) {
		this.totalEmi = totalEmi;
	}
	
	public double getEmi() {
		return emi;
	}
	public void setEmi(double emi) {
		this.emi = emi;
	}
	public double getFine() {
		return fine;
	}
	public void setFine(double fine) {
		this.fine = fine;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
}
