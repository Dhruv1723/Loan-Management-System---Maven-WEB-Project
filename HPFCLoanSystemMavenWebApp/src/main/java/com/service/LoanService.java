package com.service;

import java.sql.Date;

import java.time.LocalDate;
import java.time.Period;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.bean.EMIPayment;
import com.bean.LoanApplication;
import com.bean.Payment;
import com.dao.LoanDAO;

public class LoanService {

	LoanDAO ldao=new LoanDAO();
	public String loginvalidate(String username, String password) {
		if(username == null || password == null) {
			return null;
		}
		else {
			return ldao.loginvalidate(username, password);
			
		}
		
	}
	
	public Payment calculateemi(String name) {
		Payment p=new Payment();
		SessionFactory factory;
		Configuration conf;
		
		int userid=0;
		double totalamount=0, year=0, emi=0;
		
		LocalDate ld=LocalDate.now();
		Date tabledate;
		
		try {
			conf = new Configuration();
			factory = conf.configure("hibernate.cfg.xml").addAnnotatedClass(LoanApplication.class)
					.buildSessionFactory();

			Session ses1 = factory.openSession();
			Transaction tx = ses1.beginTransaction();
			
			Query q=ses1.createQuery("from LoanApplication la where la.name=:name");
			q.setParameter("name", name);
			List<LoanApplication> list = q.getResultList();
			tx.commit();
			for(LoanApplication l : list) {
				userid=l.getId();
				totalamount=l.getTotalpayableamount();
				year=l.getDuration();
			}
			emi= totalamount/(12*year);
		//	System.out.println("emi:"+emi);
		//	System.out.println("id"+userid);
			
			Transaction tx1 = ses1.beginTransaction();
			Query q1=ses1.createQuery("from EMIPayment ep where ep.userid=:id order by ep.paymentid desc",EMIPayment.class);
			q1.setParameter("id", userid);
			List<EMIPayment> emipaymentlist =q1.getResultList();
			tx1.commit();
			Iterator<EMIPayment> itr = emipaymentlist.iterator();
			if(itr.hasNext()) {
				//Payment p=new Payment();
				EMIPayment ep = (EMIPayment)itr.next();
				tabledate=(Date) ep.getPaymentDate();
				LocalDate tdate=tabledate.toLocalDate();
				
				Period difference=Period.between(tdate, ld);
				int days=difference.getDays();
				
				if(days <= 30) {
					
					p.setEmi(emi);
					p.setTotalEmi(emi);
					p.setFine(0.0);
					
				}
				else if(days > 30) {
					p.setEmi(emi);
					double fine= (0.12*emi*(days/30));
					p.setFine(fine);
					double total=emi+fine;
					p.setTotalEmi(total);
					
				}
				return p;
			}
			else {
			//	Payment p=new Payment();
				System.out.println("Hi Dhruv EMI: "+emi);
				p.setEmi(emi);
				p.setFine(0.0);
				p.setTotalEmi(emi);
				return p;
			}
			
			
			
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
			
		}
		return p;
		
		
	}
	
}
