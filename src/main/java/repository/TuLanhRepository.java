package repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.TuLanh;
import util.HibernateUtil;

public class TuLanhRepository {
	private final SessionFactory sessionFactory = HibernateUtil.getFactory();
	
	public List<TuLanh> getTuLanhList() {
		Session session = sessionFactory.openSession();
		Query<TuLanh> query = session.createQuery("FROM TuLanh tulanh WHERE tulanh.isDeleted = false", TuLanh.class);
		List<TuLanh> tuLanhs = query.getResultList();
		session.close();
		return tuLanhs;
	}
	public List<TuLanh> getAllTuLanhList() {
		Session session = sessionFactory.openSession();
		Query<TuLanh> query = session.createQuery("FROM TuLanh tulanh", TuLanh.class);
		List<TuLanh> tuLanhs = query.getResultList();
		session.close();
		return tuLanhs;
	}
	public TuLanh getTuLanhByID(Long id) {
		Session session = sessionFactory.openSession();
		TuLanh tulanh = session.find(TuLanh.class, id);
		return tulanh;
	}
	public void insertTuLanh(TuLanh tuLanh) throws Exception {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(tuLanh) ;
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			throw new Exception("Insert failed");
		}
		session.close();
	}
	public void updateTuLanh(TuLanh tuLanh) throws Exception{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(tuLanh);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception("Update failed");
			// TODO: handle exception
		}
	}
	public void deleteTuLanh(Long id) {
		TuLanh tuLanh = getTuLanhByID(id);
		tuLanh.setDeleted(true);
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(tuLanh);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			// TODO: handle exception
		}
	}
	public List<TuLanh> getTuLanhByFilter(String type) {
		Session session = sessionFactory.openSession();
		String hql = "SELECT tulanh FROM TuLanh tulanh WHERE tulanh.isDeleted = false ORDER BY tulanh."+type+" ASC ";
		Query<TuLanh> query = session.createQuery(hql, TuLanh.class);
		List<TuLanh> tuLanhs = query.getResultList();
		session.close();
		return tuLanhs;
	}
	public List<TuLanh> getTuLanhByFilter(String type,Boolean isDel) {
		Session session = sessionFactory.openSession();
		String hql = "SELECT tulanh FROM TuLanh tulanh WHERE tulanh.isDeleted = :isDel ORDER BY tulanh."+type+" ASC ";
		Query<TuLanh> query = session.createQuery(hql, TuLanh.class);
		query.setParameter("isDel", isDel);
		List<TuLanh> tuLanhs = query.getResultList();
		session.close();
		return tuLanhs;
	}
}
