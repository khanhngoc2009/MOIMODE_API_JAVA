package com.it15306.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.it15306.dto.Voucherdto;
//import com.it15306.entities.Category;
import com.it15306.entities.User;

@Service
public interface VoucherService {
	List<Voucherdto> getAllVouchers();
	
	Voucherdto getByIdVoucher(Integer voucher_id);
	
	Voucherdto getByTitleVoucher(String title);

	List<Voucherdto> getByTypeDiscount(Integer type_discount);
	
	List<Voucherdto> getByStatus(Integer status);
	
	List<Voucherdto> getByBetweenTime(Date start_time,Date end_time);
	
	Voucherdto saveVoucher(Voucherdto voucher);
	Voucherdto create(Voucherdto voucherdto);
	Voucherdto update(Voucherdto voucherdto);
	Integer delete(Integer voucher_id);
	
}
