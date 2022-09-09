package repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Account;
import util.HibernateUtil;

public class AccountRepository {
	private final SessionFactory sessionFactory = HibernateUtil.getFactory();
	
	public Account getAccountByUsername(String username) {
		Session session = sessionFactory.openSession();
		Account account =  session.find(Account.class, username);
		session.close();
		return account;
	}
	public void insertAccount(Account account) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {

			session.save(account) ;
			transaction.commit();
		} catch (Exception e) {

			e.printStackTrace();
			transaction.rollback();
		}
		session.close();
	}
	public List<Account> getAccountList() {
		
		Session session = sessionFactory.openSession();
		Query< Account> query = session.createQuery("SELECT ac FROM Account ac WHERE ac.deleted = false", Account.class);
		List<Account> list =  query.getResultList();
		session.close();
		return list;
	}
}
