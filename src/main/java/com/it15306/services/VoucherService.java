package com.it15306.services;

import java.util.List;

//import com.it15306.entities.Category;
import com.it15306.entities.User;
import com.it15306.entities.Voucher;

public interface VoucherService {
	List<Voucher> getAllVouchers();
	Voucher getByIdVoucher(String voucher_id);

	Voucher saveVoucher(Voucher voucher);

	void delete(String voucher_id);
}
