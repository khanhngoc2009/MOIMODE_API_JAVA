package com.it15306.servicesImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.it15306.entities.Voucher;
import com.it15306.repository.VoucherRepository;
import com.it15306.services.VoucherService;

public class VoucherServiceImpl implements VoucherService{
	
	@Autowired
	private VoucherRepository voucherRepository;
	
	@Override	
	public List<Voucher> getAllVouchers() {
		// TODO Auto-generated method stub
		return voucherRepository.findAll();
	}

	@Override
	public Optional<Voucher> getByIdVoucher(Integer voucher_id) {
		// TODO Auto-generated method stub
		return voucherRepository.findById(voucher_id);
	}

	@Override
	public Voucher getByTitleVoucher(String title) {
		// TODO Auto-generated method stub
		return voucherRepository.findByTitle(title);
	}

	@Override
	public List<Voucher> getByTypeDiscount(Integer type_discount) {
		// TODO Auto-generated method stub
		return voucherRepository.findByTypeDiscount(type_discount);
	}

	@Override
	public List<Voucher> getByStatus(Integer status) {
		// TODO Auto-generated method stub
		return voucherRepository.findByStatus(status);
	}

	@Override
	public List<Voucher> getByBetweenTime(Date start_time, Date end_date) {
		// TODO Auto-generated method stub
		return voucherRepository.findByBetweenTime(start_time, end_date);
	}

	@Override
	public Voucher saveVoucher(Voucher voucher) {
		// TODO Auto-generated method stub
		return voucherRepository.save(voucher);
	}

	@Override
	public void delete(String voucher_id) {
		// TODO Auto-generated method stub
		voucherRepository.deleteById(Integer.parseInt(voucher_id));
	}

}
