package com.it15306.servicesImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it15306.dto.dashboard.BienDoDHang;
import com.it15306.dto.dashboard.BienDoDThu;
import com.it15306.dto.dashboard.TongHopDonHang;
import com.it15306.repository.CategoryRepository;
import com.it15306.repository.OrderRepository;
import com.it15306.repository.ProductRepository;
import com.it15306.repository.UserRepository;
import com.it15306.services.thongKeService;
@Service
public class thongKeServiceImpl implements thongKeService{
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderRepository  orderRepository;
	@Override
	public Float thongKeDoanhThu() {
		
		return orderRepository.thongKeOrderDoanhThu(4);
	}

	@Override
	public Integer thongKeDonHang() {
		Long soluong=orderRepository.count();
		Integer soluongI=soluong.intValue();
		return soluongI;
	}

	@Override
	public Integer thongKeKhachHang() {
		
		return  userRepository.thongKeCountUser(1);
	}

	@Override
	public Integer thongKeSanPham() {
		
		return productRepository.thongKeCountProduct(1);
	}

	@Override
	public TongHopDonHang thongKetTongHopDonHang() {
		TongHopDonHang donHang =new TongHopDonHang();
		Integer choXH= orderRepository.thongKeOrderCount(1);
		Integer daXN= orderRepository.thongKeOrderCount(2);
		Integer dangVChuyen= orderRepository.thongKeOrderCount(3);
		Integer hoanThanh= orderRepository.thongKeOrderCount(4);
		Integer khachHuy= orderRepository.thongKeOrderCount(5);
		Integer cuaHangHuy= orderRepository.thongKeOrderCount(6);
		donHang.setChoXacNhan(choXH != null ? choXH:0);
		donHang.setDaXacNhan(daXN !=null ?daXN:0);
		donHang.setDangVanChuyen(dangVChuyen !=null ?dangVChuyen:0);
		donHang.setHoanThanh(hoanThanh !=null ?hoanThanh:0);
		donHang.setKhachHuy(khachHuy !=null ?khachHuy:0);
		donHang.setCuaHangHuy(cuaHangHuy !=null ?cuaHangHuy:0);
		return donHang;
	}
	public List<String> thuTrongTuan() {
		List<String> thus=new ArrayList<String>();
		thus.add("Monday");
		thus.add("Tuesday");
		thus.add("Wednesday");
		thus.add("Thursday");
		thus.add("Friday");
		thus.add("Saturday");
		thus.add("Sunday");
		return thus;
	}
	@Override
	public BienDoDHang thongKetBienDoDonHang() {
		String thuNow; 
		Integer index=null;
		List<String> ngayNow=new ArrayList<String>();
		BienDoDHang bienDoDHang=new BienDoDHang();		
		
		List<Integer> countday=new ArrayList<Integer>();
		List<String> thus =thuTrongTuan();
		bienDoDHang.setThu(thus);				

//		------------------------------------------
		  SimpleDateFormat fmY=new SimpleDateFormat("yyyy-MM-dd");;
	        String nam = fmY.format(new Date());

		Date date3 = new Date();
		LocalDate localDate = date3.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year  = localDate.getYear();
		int month = localDate.getMonthValue();
		int dayN   = localDate.getDayOfMonth();
	        System.out.println(year);
	        System.out.println(month);
	        System.out.println(dayN);
	      //data that
		LocalDate date2 = LocalDate.of(year,month,dayN);
	        
	     //data test
		//LocalDate date2 = LocalDate.of(2021,12,2);
        DayOfWeek day = date2.getDayOfWeek();
      
       
        thuNow=day.getDisplayName(TextStyle.FULL, Locale.getDefault());
        System.out.println(thuNow);
        for (int i = 0; i < thus.size(); i++) {
        	System.out.println("---"+i+"--: "+thus.get(i));
			if(thus.get(i).equalsIgnoreCase(thuNow)) {
				index=i;
			}
			
		}
        Calendar c1 = Calendar.getInstance();
        //data that
        ngayNow.add(nam);
        //data tesst
        //ngayNow.add("2021-12-2");
        System.out.println("index: "+index);
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD"); 
        for (int i = 0; i < index; i++) {
        	
        	Date date4;
        	System.out.println("nam: "+nam);
			try {
				//date4 =(Date) fmY.format(nam);
				date4=new SimpleDateFormat("yyyy-MM-dd").parse(nam);  
				System.out.println("date4: "+date4);
				c1.setTime(date4);
				// //data test tang ngay
				//c1.roll(Calendar.DATE, +6);
//				---------
	        	c1.roll(Calendar.DATE, -(i+1));
	        	ngayNow.add(fmY.format(c1.getTime()));
			} catch (ParseException e) {

				e.printStackTrace();
			}
        	
			
		}
        
        System.out.println("end "+ ngayNow.size());
        ngayNow.forEach(n ->{
        	System.out.println(n.toString());
        });
        
			for (int i = ngayNow.size()-1; i >=0; i--) {      	
		        Integer so;
			so = orderRepository.thongKeBienDoDonHang(4, ngayNow.get(i)+" 00:00:00",  ngayNow.get(i)+" 23:59:59");
			System.out.println("so: "+so);
			System.out.println("fm: "+ngayNow.get(i)+" 00:00:00");
	        countday.add(so);
	        
			}
	        while (countday.size() < 7) {
	        	countday.add(0);
				
			}

	        bienDoDHang.setThu(thus);
	        bienDoDHang.setSoLuongDHang(countday);
       
		return bienDoDHang;
	}

	@Override
	public BienDoDThu thongKetBienDoDoanhThu() {
		String thuNow; 
		Integer index=null;
		List<String> ngayNow=new ArrayList<String>();
		BienDoDThu bienDoDThu=new BienDoDThu();		
		
		List<Float> countday=new ArrayList<Float>();
		List<String> thus =thuTrongTuan();
		bienDoDThu.setThu(thus);				

//		------------------------------------------
		  SimpleDateFormat fmY=new SimpleDateFormat("yyyy-MM-dd");;
	        String nam = fmY.format(new Date());

		Date date3 = new Date();
		LocalDate localDate = date3.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year  = localDate.getYear();
		int month = localDate.getMonthValue();
		int dayN   = localDate.getDayOfMonth();
	        System.out.println(year);
	        System.out.println(month);
	        System.out.println(dayN);
	      //data that
		LocalDate date2 = LocalDate.of(year,month,dayN);
	        
	     //data test
		//LocalDate date2 = LocalDate.of(2021,12,2);
        DayOfWeek day = date2.getDayOfWeek();
      
       
        thuNow=day.getDisplayName(TextStyle.FULL, Locale.getDefault());
        System.out.println(thuNow);
        for (int i = 0; i < thus.size(); i++) {
        	System.out.println("---"+i+"--: "+thus.get(i));
			if(thus.get(i).equalsIgnoreCase(thuNow)) {
				index=i;
			}
			
		}
        Calendar c1 = Calendar.getInstance();
        //data that
        ngayNow.add(nam);
        //data tesst
        //ngayNow.add("2021-12-2");
        System.out.println("index: "+index);
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD"); 
        for (int i = 0; i < index; i++) {
        	
        	Date date4;
        	System.out.println("nam: "+nam);
			try {
				//date4 =(Date) fmY.format(nam);
				date4=new SimpleDateFormat("yyyy-MM-dd").parse(nam);  
				System.out.println("date4: "+date4);
				c1.setTime(date4);
				// //data test tang ngay
				//c1.roll(Calendar.DATE, +6);
//				---------
	        	c1.roll(Calendar.DATE, -(i+1));
	        	ngayNow.add(fmY.format(c1.getTime()));
			} catch (ParseException e) {

				e.printStackTrace();
			}
        	
			
		}
        
        System.out.println("end "+ ngayNow.size());
        ngayNow.forEach(n ->{
        	System.out.println(n.toString());
        });
        
			for (int i = ngayNow.size()-1; i >=0; i--) {      	
				Float so;
			so = orderRepository.thongKeBienDoDoanhThu(4, ngayNow.get(i)+" 00:00:00",  ngayNow.get(i)+" 23:59:59");
			System.out.println("so: "+so);
			System.out.println("fm: "+ngayNow.get(i)+" 00:00:00");
	        countday.add(so == null ? 0:so);
	        
			}
	        while (countday.size() < 7) {
	        	countday.add(Float.valueOf(0));
				
			}

	        bienDoDThu.setThu(thus);
	        bienDoDThu.setSumDoanhThu(countday);
       
		return bienDoDThu;
	}
	
//	Thứ hai – Monday. Viết tắt: Mon.
//	Thứ ba – Tuesday. Viết tắt: Tue.
//	Thứ tư – Wednesday. Viết tắt: Wed.
//	Thứ năm – Thursday. Viết tắt: Thu.
//	Thứ sáu – Friday. Viết tắt: Fri.
//	Thứ bảy – Saturday. ...
//	Chủ nhật – Sunday.

}
