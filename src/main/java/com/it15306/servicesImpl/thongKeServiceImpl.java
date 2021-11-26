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

	@Override
	public BienDoDHang thongKetBienDoDonHang() {
		String thuNow; 
		Integer index=null;
		List<String> ngayNow=new ArrayList<String>();
		BienDoDHang bienDoDHang=new BienDoDHang();		
		List<String> thus=new ArrayList<String>();
		List<Integer> countday=new ArrayList<Integer>();
		thus.add("Monday");
		thus.add("Tuesday");
		thus.add("Wednesday");
		thus.add("Thursday");
		thus.add("Friday");
		thus.add("Saturday");
		thus.add("Sunday");
		bienDoDHang.setThu(thus);				
		Date date=java.util.Calendar.getInstance().getTime();
		System.out.println(date);
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
		LocalDate date2 = LocalDate.of(year,month,dayN);
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
        ngayNow.add(nam);
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
				
	        	c1.roll(Calendar.DATE, -(i+1));
	        	ngayNow.add(fmY.format(c1.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
			
		}
        
        System.out.println("end "+ ngayNow.size());
        ngayNow.forEach(n ->{
        	System.out.println(n.toString());
        });
        
		try {
			for (int i = 0; i < ngayNow.size(); i++) {      	
		        Integer so;
		        System.out.println("ngay now"+ngayNow.get(i));
		        Date d5= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(ngayNow.get(i) + " 00:00:00"); 
		        System.out.println("d5: "+d5);
			so = orderRepository.thongKeBienDoDoanhThu(4, (Date) new SimpleDateFormat("yyyy-MM-dd").parse( ngayNow.get(i)+ " 00:00:00"), (Date) new SimpleDateFormat("yyyy-MM-dd").parse( ngayNow.get(i)+" 23:59:59"));
			System.out.println("so: "+so);
			System.out.println("fm: "+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse( ngayNow.get(i)+ " 00:00:00"));
	        countday.add(so);
			}
	        countday.forEach(n ->{
	        	System.out.println("count day: "+n.toString());
	        });
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
		return null;
	}
	
//	Thứ hai – Monday. Viết tắt: Mon.
//	Thứ ba – Tuesday. Viết tắt: Tue.
//	Thứ tư – Wednesday. Viết tắt: Wed.
//	Thứ năm – Thursday. Viết tắt: Thu.
//	Thứ sáu – Friday. Viết tắt: Fri.
//	Thứ bảy – Saturday. ...
//	Chủ nhật – Sunday.

}
