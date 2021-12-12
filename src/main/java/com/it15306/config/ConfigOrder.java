package com.it15306.config;

public class ConfigOrder {

	public String renderStatusOrder(Integer status) {
		ConfigDefine config= new ConfigDefine();
		if(status == config.PENDDING ) {
			return " chờ xác nhận " ; 
		}else if(status == config.CONFIRM ) {
			return " đã được xác nhận  " ; 
		}else if(status == config.SHIP ) {
			return " đang giao  " ; 
		}else if(status == config.SUCCESS ) {
			return " hoàn thành  " ; 
		}else if(status == config.CANCEL ) {
			return " huỷ " ; 
		}else if(status == config.DENY ) {
			return " từ chối  " ; 
		}else {
			return "ERR";
		}
	}
	
}
