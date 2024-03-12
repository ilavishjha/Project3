package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.PaymentDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

/**
 * Interface of Payment model
 * @author Lavish Ojha
 *
 */
public interface PaymentModelInt {
public long add(PaymentDTO dto)throws ApplicationException,DuplicateRecordException;
public void delete(PaymentDTO dto)throws ApplicationException;
public void update(PaymentDTO dto)throws ApplicationException,DuplicateRecordException;
public List list()throws ApplicationException;
public List list(int pageNo,int pageSize)throws ApplicationException;
public List search(PaymentDTO dto)throws ApplicationException;
public List search(PaymentDTO dto,int pageNo,int pageSize)throws ApplicationException;
public PaymentDTO fingByPK(long pk)throws ApplicationException;

}
