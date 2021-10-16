package com.it15306.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

//import com.it15306.entities.Category;
import com.it15306.entities.User;
import com.it15306.entities.Voucher;

public interface VoucherService {
	List<Voucher> getAllVouchers();
	
	Optional<Voucher> getByIdVoucher(Integer voucher_id);
	
	Voucher getByTitleVoucher(String title);

	List<Voucher> getByTypeDiscount(Integer type_discount);
	
	List<Voucher> getByStatus(Integer status);
	
	List<Voucher> getByBetweenTime(Date start_time,Date end_date);
	
	Voucher saveVoucher(Voucher voucher);

	void delete(String voucher_id);
}
