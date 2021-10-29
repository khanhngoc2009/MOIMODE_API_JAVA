package com.it15306.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.it15306.config.DataResponse;
import com.it15306.config.DataResponseList;
import com.it15306.dto.option.OptionValueDTO;
import com.it15306.dto.option_value.BodyCreateOptionValue;
import com.it15306.dto.option_value.BodyDeleteOptionValue;
import com.it15306.dto.option_value.BodyUpdateOptionValue;
import com.it15306.dto.product.ProductDTO;
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
			
			if(optionsServiceImpl.checkOptionExist(body.getOption_id())) {
				Options option = optionsServiceImpl.getById(body.getOption_id());
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
				dataRes.setMessage("Khong ton tai");
				return new ResponseEntity<>(dataRes,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO: handle exception
			dataRes.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			dataRes.setMessage("Fail");
			return new ResponseEntity<>(dataRes,HttpStatus.FAILED_DEPENDENCY);
		}
	}
	
	@RequestMapping(value = "/admin/updateOptionValue", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> updateOptionValue(@RequestBody BodyUpdateOptionValue body) {
		DataResponse<String> dataRes = new DataResponse<String>();
		try {
			OptionValue option_value = optionValueServiceImpl.getById(body.getId());
			if(option_value != null) {
				option_value.setValue_name(body.getName());
				optionValueServiceImpl.saveOptionValue(option_value);
				dataRes.setCode(HttpStatus.OK.value());
				dataRes.setMessage("Thanh cong");
				
				return new ResponseEntity<>(dataRes,HttpStatus.OK);
			}
			else {
				dataRes.setCode(HttpStatus.NOT_FOUND.value());
				dataRes.setMessage("Khong ton tai");
				return new ResponseEntity<>(dataRes,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO: handle exception
			dataRes.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			dataRes.setMessage("Fail");
			return new ResponseEntity<>(dataRes,HttpStatus.FAILED_DEPENDENCY);
		}
	}
	

	@RequestMapping(value = "/admin/deleteOptionValue", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> deleteOptionValue(@RequestBody BodyDeleteOptionValue body) {
		DataResponse<String> dataRes = new DataResponse<String>();
		try {
			OptionValue option_value = optionValueServiceImpl.getById(body.getId());
			if(option_value != null) {
				optionValueServiceImpl.delete(body.getId());
				dataRes.setCode(HttpStatus.OK.value());
				dataRes.setMessage("Thanh cong");
				return new ResponseEntity<>(dataRes,HttpStatus.OK);
			}
			else {
				dataRes.setCode(HttpStatus.NOT_FOUND.value());
				dataRes.setMessage("Khong ton tai");
				return new ResponseEntity<>(dataRes,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO: handle exception
			dataRes.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			dataRes.setMessage("Fail");
			return new ResponseEntity<>(dataRes,HttpStatus.FAILED_DEPENDENCY);
		}
	}
	
	@RequestMapping(value = "/admin/findByOptionId/{option_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> findByOptionId(@PathVariable Integer option_id) {
		ModelMapper modelMapper = new ModelMapper();
		DataResponseList<OptionValueDTO> dataRes = new DataResponseList<OptionValueDTO>();
		try {
			if(optionsServiceImpl.checkOptionExist(option_id)) {
				Options option = optionsServiceImpl.getById(option_id);
				 List<OptionValue> listOptionValue =   optionValueServiceImpl.getAllOptionValueByOption(option);
				 int size = listOptionValue.size();
				 List<OptionValueDTO> optionVaDTOs = new ArrayList<OptionValueDTO>();
				 for(int i = 0;i<size;i++) {
					 OptionValueDTO value = (modelMapper.map(listOptionValue.get(i), OptionValueDTO.class));
					 value.setOption_id(option_id);
					 optionVaDTOs.add(value);
				 }
				dataRes.setCode(HttpStatus.OK.value());
				dataRes.setMessage("Thanh cong");
				dataRes.setCount(size);
				dataRes.setListData(optionVaDTOs);
				return new ResponseEntity<>(dataRes,HttpStatus.OK);
			}
			else {
				dataRes.setCode(HttpStatus.NOT_FOUND.value());
				dataRes.setMessage("Khong ton tai");
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
