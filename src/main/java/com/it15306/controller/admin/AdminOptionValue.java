package com.it15306.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.config.DataResponse;
import com.it15306.dto.option_value.BodyCreateOptionValue;
import com.it15306.entities.OptionValue;
import com.it15306.entities.Options;
import com.it15306.servicesImpl.OptionValueServiceImpl;
import com.it15306.servicesImpl.OptionsServiceImpl;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/miemode_api/v1")
public class AdminOptionValue {
	
	
	@Autowired
	OptionValueServiceImpl optionValueServiceImpl;
	@Autowired
	OptionsServiceImpl optionsServiceImpl;

	
	@RequestMapping(value = "/admin/createOptionValue", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> createOptionValue(@RequestBody BodyCreateOptionValue body) {
		DataResponse<String> dataRes = new DataResponse<String>();
		try {
			Options option = optionsServiceImpl.getById(body.getOption_id());
			if(option != null) {
				int size = body.getListValue().size();
				for(int i=0;i<size;i++) {
					OptionValue option_value = new OptionValue();
					option_value.setOption(option);
					option_value.setValue_name(body.getListValue().get(i).getName());
					optionValueServiceImpl.saveOptionValue(option_value);
				}
				dataRes.setCode(200);
				dataRes.setMessage("Thanh cong");
				return new ResponseEntity<>(dataRes,HttpStatus.OK);
			}
			else {
				dataRes.setCode(HttpStatus.NOT_FOUND.value());
				dataRes.setMessage("Thanh cong");
				return new ResponseEntity<>(dataRes,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO: handle exception
			dataRes.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			dataRes.setMessage("Fail");
			return new ResponseEntity<>(dataRes,HttpStatus.FAILED_DEPENDENCY);
		}
	}
}
