package in.co.rays.project_3.model;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.PaymentDTO;

import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

/**
 * Hibernate implements of marksheet model
 * @author Lavish Ojha
 *
 */
public class PaymentModelHibImp implements PaymentModelInt {





	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(PaymentDTO.class);
			if (pageSize > 0) {
				
				pageNo = ((pageNo - 1) * pageSize) + 1;

				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();

		} catch (Exception e) {

			throw new ApplicationException("Exception in  Payment List" + e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	

	

	

	@Override
	public long add(PaymentDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
				Session session = HibDataSource.getSession();
				Transaction tx = null;
				
				// get Student Name
				PaymentModelInt sModel = ModelFactory.getInstance().getPaymentModel();
			long pk = 0;
				try {
					tx = session.beginTransaction();
					session.save(dto);
					pk = dto.getId();
					tx.commit();

				} catch (HibernateException e) {
					e.printStackTrace();
					if (tx != null) {
						tx.rollback();
					}
					throw new ApplicationException("Exception in marksheet Add " + e.getMessage());
				} finally {
					session.close();
				}
				return pk;
			}
	

	@Override
	public void delete(PaymentDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		PaymentDTO dtoExist = fingByPK(dto.getId());
		if (dtoExist == null) {
			throw new ApplicationException("payment does not exist");
		}

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();

		} catch (HibernateException e) {

			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Marksheet Delete" + e.getMessage());
		} finally {
			session.close();
		}
	
	}

	@Override
	public void update(PaymentDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
				Session session = null;
				Transaction tx = null;
				
				//PaymentDTO dtoExist = findByRollNo(dto.getRollNo());
			
				// Check if updated Roll no already exist
			    
				/*
				 * if (dtoExist != null && dtoExist.getId() == dto.getId()) { throw new
				 * DuplicateRecordException("Roll No is already exist"); }
				 */ 

				// get Student Name 
			PaymentModelInt sModel = ModelFactory.getInstance().getPaymentModel();
				
				try {
					session = HibDataSource.getSession();
					tx = session.beginTransaction();
				session.update(dto);
					//session.merge(dto);
				//	session.saveOrUpdate(dto);
					tx.commit();

				} catch (HibernateException e) {
		e.printStackTrace();
					if (tx != null) {
						tx.rollback();
						throw new ApplicationException("Exception in Payment Update" + e.getMessage());
					}
				} finally {
					session.close();
				}
			}
		
	

	@Override
	public List search(PaymentDTO dto) throws ApplicationException {
		
			return search(dto, 0, 0);
		}
	

	@Override
	public List search(PaymentDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(PaymentDTO.class);
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));

			}
			if (dto.getType() != null && dto.getType().length() > 0) {
				criteria.add(Restrictions.like("rollNo", dto.getType() + "%"));
			}
			if ( dto.getAmount() > 0) {
				criteria.add(Restrictions.eq("name", dto.getAmount()));
			}
			
			if (pageSize > 0) {
				criteria.setFirstResult((pageNo - 1) * pageSize);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Payment Search " + e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public PaymentDTO fingByPK(long pk) throws ApplicationException {
				Session session = null;
				PaymentDTO dto = null;
				try {
					session = HibDataSource.getSession();
					dto = (PaymentDTO) session.get(PaymentDTO.class, pk);

				} catch (Exception e) {

					throw new ApplicationException("Exception in getting Payment by pk" + e.getMessage());

				} finally {
					session.close();
				}
				return dto;
			}
	
}


