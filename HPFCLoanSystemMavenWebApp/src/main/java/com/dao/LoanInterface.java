package com.dao;

import java.util.Date;

import com.bean.LoanApplication;


public interface LoanInterface {

	public int newLoan(LoanApplication la);
	public String loginvalidate(String username, String password);
	public boolean updateDecision(int id, String decision);
	public int getUserid(String name);
	public int newEMIPayment(int userid, double totalemi, double fine, Date today);
}
