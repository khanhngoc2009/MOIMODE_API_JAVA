package com.it15306.servicesImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it15306.dto.Voucherdto;
import com.it15306.entities.Voucher;
import com.it15306.repository.VoucherRepository;
import com.it15306.services.VoucherService;

@Service
public class VoucherServiceImpl implements VoucherService {

	@Autowired
	VoucherRepository voucherRepository;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<Voucherdto> getAllVouchers() {
		List<Voucherdto> list = new ArrayList<Voucherdto>();
		List<Voucher> listenti = voucherRepository.findAllVoucher();
		Voucherdto dto = new Voucherdto();
		try {
			for (Voucher voucher : listenti) {
				System.out.println(voucher.toString());
				dto = mapToModel(voucher, null);
				System.out.println("22222: " + dto.toString());
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Voucherdto getByIdVoucher(Integer voucher_id) {
		try {

			Optional<Voucher> optional = voucherRepository.findVoucherById(voucher_id);
			Voucherdto dto = new Voucherdto();
			if (optional.isPresent()) {
				Voucher enti = optional.get();
				dto = mapToModel(enti, null);
				return dto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Voucherdto getByTitleVoucher(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Voucherdto> getByTypeDiscount(Integer type_discount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Voucherdto> getByStatus(Integer status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Voucherdto> getByBetweenTime(Date start_time, Date end_date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Voucherdto saveVoucher(Voucherdto voucher) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Voucherdto create(Voucherdto voucherdto) {
		Voucher entity;
		try {
			entity = mapToEnyities(voucherdto, null);
			voucherRepository.save(entity);
			System.out.println("id voucher: " + entity.getVoucher_id());
			voucherdto.setVoucher_id(entity.getVoucher_id());
			return voucherdto;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public Voucherdto update(Voucherdto voucherdto) {
		try {

			Optional<Voucher> optional = voucherRepository.findById(voucherdto.getVoucher_id());
			Voucher vo = new Voucher();
			if (optional.isPresent()) {
				Voucher voucher = optional.get();
				vo =mapToEnyities(voucherdto, voucher);
				voucherRepository.save(vo);
				return mapToModel(vo, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer delete(Integer voucher_id) {
		try {

			Optional<Voucher> optional = voucherRepository.findById(voucher_id);
			Voucher vo = new Voucher();
			if (optional.isPresent()) {
				Voucher voucher = optional.get();
				voucherRepository.delete(voucher);
				return voucher.getVoucher_id();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Voucherdto mapToModel(Voucher enti, Voucherdto model)
			throws IllegalAccessException, InvocationTargetException {
		if (model == null)
			model = new Voucherdto();
		model = modelMapper.map(enti, Voucherdto.class);
		return model;
	}

	public Voucher mapToEnyities(Voucherdto model, Voucher enti)
			throws IllegalAccessException, InvocationTargetException {
		if (enti == null)
			enti = new Voucher();
		enti = modelMapper.map(model, Voucher.class);
		return enti;
	}

}
