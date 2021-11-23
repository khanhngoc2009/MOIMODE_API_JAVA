package com.it15306.config;

public class ConfigOrder {

	public String renderStatusOrder(Integer status) {
		ConfigDefine config= new ConfigDefine();
		if(status == config.PENDDING ) {
			return " chờ xác nhận " ; 
		}else if(status == config.CONFIRM ) {
			return " chờ xác nhận " ; 
		}else if(status == config.SHIP ) {
			return " chờ xác nhận " ; 
		}else if(status == config.SHIP ) {
			return " chờ xác nhận " ; 
		}else if(status == config.CANCEL ) {
			return " chờ xác nhận " ; 
		}else if(status == config.DENY ) {
			return " chờ xác nhận " ; 
		}else {
			return "ERR";
		}
	}
	
}
