package com.it15306.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.it15306.dto.category.CategoryDTO;
import com.it15306.dto.payment.PaymentDTO;
import com.it15306.dto.payment.RequestPaymentPage;
import com.it15306.entities.Category;
import com.it15306.entities.Payment;
import com.it15306.repository.PaymentRepository;
import com.it15306.services.PaymentService;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;
@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public List<PaymentDTO> getAllPayment(RequestPaymentPage data) {
		System.out.println("------------------");
		if(data.getPage() == null ) {
			data.setPage(0);
		}
		if(data.getTake() == null || data.getTake().equals("")|| data.getTake() == 0) {
			data.setTake(10);
		}
		Pageable paging =  PageRequest.of(data.getPage(), data.getTake());
		Page<Payment> list=paymentRepository.findAllPayment(paging);
		List<PaymentDTO> listdto=new ArrayList<PaymentDTO>();
		//List<Payment> list=paymentRepository.findAll();
		list.getContent().forEach(l ->{
			PaymentDTO pm=	modelMapper.map(l, PaymentDTO.class);
			listdto.add(pm);
		});
		return listdto;
	}

	@Override
	public PaymentDTO getById(String payment_id) {
		
		return null;
	}

	@Override
	public PaymentDTO getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentDTO getByType(Integer type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentDTO getByStatus(Integer status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentDTO savePaymnet(PaymentDTO payment) {
		Payment entity =modelMapper.map(payment, Payment.class);
		paymentRepository.save(entity);
		entity.setPayment_id(entity.getPayment_id());
		return modelMapper.map(entity, PaymentDTO.class);
	}

	@Override
	public Integer delete(Integer payment_id) {
	Optional<Payment> optional=	paymentRepository.findById(payment_id);
		if(optional.isPresent()) {
			paymentRepository.delete(optional.get());
			return optional.get().getPayment_id();
		}
		return null;
	}

	@Override
	public PaymentDTO update(PaymentDTO data) {
		Optional<Payment> optional= paymentRepository.findById(data.getId());
		try {
			if(optional.isPresent()) {
				Payment enti=optional.get();
				Payment ct=mapToEnyities(data, enti);
				paymentRepository.save(ct);
			}
			return data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Payment mapToEnyities(PaymentDTO model, Payment enti){
		if (enti == null)
			enti = new Payment();
		enti = modelMapper.map(model, Payment.class);
		return enti;
	}

}
