package com.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.bean.EMIPayment;
import com.bean.LoanApplication;

public class LoanDAO implements LoanInterface {

	private SessionFactory factory;
	private Configuration conf;

	@Override
	public int newLoan(LoanApplication la) {
		int x = 0;
		try {
			conf = new Configuration();
			factory = conf.configure("hibernate.cfg.xml").addAnnotatedClass(LoanApplication.class)
					.buildSessionFactory();

			Session ses = factory.openSession();
			Transaction tx = ses.beginTransaction();

			LoanApplication lapp = new LoanApplication();
			lapp.setName(la.getName());
			lapp.setUsername(la.getUsername());
			lapp.setPassword(la.getPassword());
			lapp.setAge(la.getAge());
			lapp.setAddress(la.getAddress());
			lapp.setContactnumber(la.getContactnumber());
			lapp.setLoanamount(la.getLoanamount());
			lapp.setTotalpayableamount(la.getTotalpayableamount());
			lapp.setDuration(la.getDuration());
			lapp.setDecision(la.getDecision());

			x = (int) ses.save(lapp);

			tx.commit();
			ses.close();
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		return x;
	}

	@Override
	public String loginvalidate(String username, String password) {
		String name = null;
		try {
			conf = new Configuration();
			factory = conf.configure("hibernate.cfg.xml").addAnnotatedClass(LoanApplication.class)
					.buildSessionFactory();

			Session ses = factory.openSession();

			Query q = ses.createQuery(
					"select l.name from LoanApplication l where l.username=:username and l.password=:password");
			q.setParameter("username", username);
			q.setParameter("password", password);
			List<String> list = q.getResultList();
			for (String s : list) {
				name = s;
			}
			return name;

		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
			return null;
		} finally {
			factory.close();
		}

	}

	@Override
	public boolean updateDecision(int id, String decision) {
		try {
			conf = new Configuration();
			factory = conf.configure("hibernate.cfg.xml").addAnnotatedClass(LoanApplication.class)
					.buildSessionFactory();

			Session ses = factory.openSession();
			Transaction tx = ses.beginTransaction();

			Query q = ses.createQuery("update LoanApplication la set la.decision=:decision where la.id=:id");
			q.setParameter("decision", decision);
			q.setParameter("id", id);
			int x = q.executeUpdate();
			tx.commit();
			if (x > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
			return false;
		} finally {
			factory.close();
		}

	}

	@Override
	public int getUserid(String name) {
		int userid=0;
		try {
			conf = new Configuration();
			factory = conf.configure("hibernate.cfg.xml").addAnnotatedClass(LoanApplication.class)
					.buildSessionFactory();

			Session ses = factory.openSession();
			Transaction tx=ses.beginTransaction();
			
			Query q=ses.createQuery("select la.id from LoanApplication la where la.name=:name");
			q.setParameter("name", name);
			List<Integer> list=q.getResultList();
			tx.commit();
			for(Integer i : list) {
				userid=i;
			}
			return userid;
			
		}catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		return userid;
	}

	@Override
	public int newEMIPayment(int userid, double totalemi, double fine, Date today) {
		int x = 0;
		try {
			conf = new Configuration();
			factory = conf.configure("hibernate.cfg.xml").addAnnotatedClass(EMIPayment.class)
					.buildSessionFactory();

			Session ses = factory.openSession();
			Transaction tx = ses.beginTransaction();
			
			EMIPayment ep1=new EMIPayment();
			ep1.setEmi(totalemi);
			ep1.setFine(fine);
			ep1.setUserid(userid);
			ep1.setPaymentDate(today);
			
			x = (int) ses.save(ep1);
			tx.commit();
			ses.close();
		}
		catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		return x;
	}

	

	
		

}
