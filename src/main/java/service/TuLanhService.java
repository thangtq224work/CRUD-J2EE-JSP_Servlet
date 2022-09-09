package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.TuLanh;
import repository.TuLanhRepository;
import util.HibernateUtil;

public class TuLanhService {

	private final TuLanhRepository tuLanhRepository = new TuLanhRepository();

	public List<TuLanh> getAllTuLanh() {

		return tuLanhRepository.getTuLanhList();
	}
	public List<TuLanh> getAllTuLanhAdmin() {

		return tuLanhRepository.getAllTuLanhList();
	}

	public TuLanh getTuLanhByID(Long id) {

		return tuLanhRepository.getTuLanhByID(id);
	}

	public void deleteTuLanh2(Long id) {
		TuLanh tuLanh = getTuLanhByID(id);
		SessionFactory sessionFactory = HibernateUtil.getFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(tuLanh);
			transaction.commit();
		} catch (Exception e) {
			transaction.commit();
			// TODO: handle exception
		}
	}

	public void deleteTuLanh(String id) {
		tuLanhRepository.deleteTuLanh(Long.parseLong(id));
	}

	public void updateTuLanh(TuLanh tuLanh) throws Exception {
		tuLanhRepository.updateTuLanh(tuLanh);
	}

	public void insertTuLanh(TuLanh tuLanh) throws Exception {
		tuLanhRepository.insertTuLanh(tuLanh);
	}

	public List<TuLanh> getTuLanhFilter(String type, String isDeleted) {
		SessionFactory sessionFactory = HibernateUtil.getFactory();
		Session session = sessionFactory.openSession();
		List<TuLanh> tuLanhs = null;
		boolean value = false;
		if (isDeleted.equals("all") && (type.equals("name") || type.equals("price") || type.equals("quantity"))) {
			tuLanhs = tuLanhRepository.getAllTuLanhList();
		} else if ((isDeleted.equals("true") || isDeleted.equals("false"))
				&& (type.equals("name") || type.equals("price") || type.equals("quantity"))) {
			value = isDeleted.equals("true") ? true : false;
			tuLanhs = tuLanhRepository.getTuLanhByFilter(type, value);
		}
		session.close();
		return tuLanhs;
	}

	public List<TuLanh> getTuLanhFilter(String type) {
		List<TuLanh> tuLanhs = null;
		if (type.equals("name") || type.equals("price") || type.equals("quantity")) {
			tuLanhs = tuLanhRepository.getTuLanhByFilter(type);
		}
		return tuLanhs;
	}

	public List<TuLanh> getTuLanhFilterAdmin(String isDel, String name, String from, String to) throws Exception {
		List<TuLanh> tuLanhs = null;
		if (isDel == null) {
			throw new Exception();
		}
		else if(!(isDel.trim().equals("all") || isDel.trim().equals("false") || isDel.trim().equals("true"))) {
			throw new Exception();
		}
		else {
			System.out.println("success");
			if (!from.trim().isEmpty() && !to.trim().isEmpty()) {
				try {
					BigDecimal pFrom = BigDecimal.valueOf(Double.parseDouble(from));
					BigDecimal pTo = BigDecimal.valueOf(Double.parseDouble(to));
					if (pFrom.compareTo(BigDecimal.valueOf(0)) <= 0 || pTo.compareTo(BigDecimal.valueOf(0)) <= 0) {
						throw new Exception();
					} else {
//						tuLanhs = tuLanhRepository.getAllTuLanhList();
						if (isDel.trim().equals("all")) {
							tuLanhs = new ArrayList<TuLanh>();;
							for (TuLanh tuLanh : tuLanhRepository.getAllTuLanhList()) {
								if (tuLanh.getName().contains(name) && pFrom.compareTo(tuLanh.getPrice()) <= 0
										&& pTo.compareTo(tuLanh.getPrice()) >= 0) {
									tuLanhs.add(tuLanh);
								}
							}
						}
						else {
							boolean check = isDel.trim().equals("true")?true:false;
							tuLanhs = new ArrayList<TuLanh>();;
							for (TuLanh tuLanh : tuLanhRepository.getAllTuLanhList()) {
								if (tuLanh.getName().contains(name) && tuLanh.getIsDeleted() == check && pFrom.compareTo(tuLanh.getPrice()) <= 0
										&& pTo.compareTo(tuLanh.getPrice()) >= 0) {
									tuLanhs.add(tuLanh);
								}
							}
						}

					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception();
					// TODO: handle exception
				}
			}
			else {
				if (isDel.trim().equals("all")) {
					tuLanhs = new ArrayList<TuLanh>();;
					for (TuLanh tuLanh : tuLanhRepository.getAllTuLanhList()) {
						if (tuLanh.getName().contains(name)) {
							tuLanhs.add(tuLanh);
						}
					}
				}
				else {
					boolean check = isDel.trim().equals("true")?true:false;
					tuLanhs = new ArrayList<TuLanh>();;
					for (TuLanh tuLanh : tuLanhRepository.getAllTuLanhList()) {
						if (tuLanh.getName().contains(name) && tuLanh.getIsDeleted() == check) {
							tuLanhs.add(tuLanh);
						}
					}
				}
			}
		}

		return tuLanhs;
	}

	public List<TuLanh> getTuLanhFilterOtherRole(String name, String from, String to) throws Exception {
		List<TuLanh> tuLanhs = null;
		if (!from.trim().isEmpty() && !to.trim().isEmpty()) {
			try {
				BigDecimal pFrom = BigDecimal.valueOf(Double.parseDouble(from));
				BigDecimal pTo = BigDecimal.valueOf(Double.parseDouble(to));
				if (pFrom.compareTo(BigDecimal.valueOf(0)) <= 0 || pTo.compareTo(BigDecimal.valueOf(0)) <= 0) {
					throw new Exception();
				} else {
//					tuLanhs = tuLanhRepository.getTuLanhList();
					System.out.println("logger");
					tuLanhs = new ArrayList<TuLanh>();
					;
					for (TuLanh tuLanh : tuLanhRepository.getTuLanhList()) {
						if (tuLanh.getName().contains(name) && pFrom.compareTo(tuLanh.getPrice()) <= 0
								&& pTo.compareTo(tuLanh.getPrice()) >= 0) {
							tuLanhs.add(tuLanh);
						}
					}
				}
			} catch (Exception e) {

				throw new Exception();
				// TODO: handle exception
			}
		} else {
			tuLanhs = new ArrayList<TuLanh>();
			System.out.println(tuLanhs.size());
			for (TuLanh tuLanh : tuLanhRepository.getTuLanhList()) {
				if (tuLanh.getName().contains(name.trim())) {
					tuLanhs.add(tuLanh);
				}
			}
		}
		return tuLanhs;
	}

}
