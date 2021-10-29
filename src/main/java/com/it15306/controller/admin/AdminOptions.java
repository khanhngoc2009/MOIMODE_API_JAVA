package com.it15306.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
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
import com.it15306.config.DataResponseList;
import com.it15306.dto.option.CreateOptionDto;
import com.it15306.dto.option.DeleteOptionDto;
import com.it15306.dto.option.OptionDTO;
import com.it15306.dto.option.OptionResponseDto;
import com.it15306.dto.option.UpdateOptionDto;
import com.it15306.dto.product.ProductDTO;
import com.it15306.entities.Option_Product;
import com.it15306.entities.Options;
import com.it15306.servicesImpl.OptionsProductsServiceImpl;
import com.it15306.servicesImpl.OptionsServiceImpl;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/miemode_api/v1")
public class AdminOptions {
	@Autowired
	private OptionsServiceImpl optionProductServiceImpl;
	
	@Autowired
	private OptionsProductsServiceImpl optionsProductsServiceImpl;
	
	@RequestMapping(value = "/admin/createOption", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> createOption(@RequestBody CreateOptionDto body) {
		DataResponse<OptionResponseDto> dataRes= new DataResponse<OptionResponseDto>();
		ModelMapper modelMapper = new ModelMapper();
		try {
			Options option = new Options();
			option.setDescription(body.getDescription());
			option.setName(body.getName());
			option.setStatus(1);
			option.setCreate_date(new Date());
			Options resOption =  optionProductServiceImpl.saveOptionProduct(option);
			dataRes.setCode(200);
			dataRes.setMessage("Thêm thành công ");
			dataRes.setData((modelMapper.map(resOption, OptionResponseDto.class)));
			return new ResponseEntity<>(dataRes,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			dataRes.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			dataRes.setMessage("Thất bại");
			return new ResponseEntity<>(dataRes,HttpStatus.FAILED_DEPENDENCY);
		}
	}
	@RequestMapping(value = "/admin/deleteOption", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> deleteOption(@RequestBody DeleteOptionDto dto) {
		DataResponse<String> dataRes= new DataResponse<String>();
		try {
			Options option = new Options();
			option.setId(dto.getOption_id());
			Option_Product op_pr = optionsProductsServiceImpl.getOption(option);
			if(op_pr != null ) {
				dataRes.setCode(HttpStatus.FOUND.value());
				dataRes.setMessage("Bạn không thể xoá option này ");
				return new ResponseEntity<>(dataRes,HttpStatus.FOUND);
			}else {
				optionProductServiceImpl.delete(option);
				dataRes.setCode(200);
				dataRes.setMessage("Xoá thành công");
				dataRes.setData("");
				return new ResponseEntity<>(dataRes,HttpStatus.OK);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			dataRes.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			dataRes.setMessage("Thất bại");
			return new ResponseEntity<>(dataRes,HttpStatus.FAILED_DEPENDENCY);
		}
		
	}
	@RequestMapping(value = "/admin/updateOption", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> updateOption(@RequestBody UpdateOptionDto dto) {
		DataResponse<Options> dataRes= new DataResponse<Options>();
		try {
			
			if(optionProductServiceImpl.checkOptionExist(dto.getId())) {
				Options option = optionProductServiceImpl.getById(dto.getId());
				option.setDescription(dto.getDescription());
				option.setStatus(dto.getStatus());
				option.setName(dto.getName());
				dataRes.setCode(200);
				dataRes.setMessage("Update thành công");
				dataRes.setData(option);
				return new ResponseEntity<>(dataRes,HttpStatus.OK);
			}else {
				dataRes.setMessage("Không tồn tại");
				dataRes.setCode(400);
				return new ResponseEntity<>(dataRes,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO: handle exception
			dataRes.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			dataRes.setMessage("Thất bại");
			return new ResponseEntity<>(dataRes,HttpStatus.FAILED_DEPENDENCY);
		}
		
	}
	@RequestMapping(value = "/admin/listOption", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> listOption() {
		DataResponseList<OptionResponseDto> dataRes= new DataResponseList<OptionResponseDto>();
		ModelMapper modelMapper = new ModelMapper();
		try {
			List<OptionResponseDto> optionDTOs = new ArrayList<OptionResponseDto>();
			List<Options> listOption = optionProductServiceImpl.getOptions(0, 10);
			int size = listOption.size();
			if (size > 0) {
				for (int i = 0; i < size; i++) {
					OptionResponseDto prDto = (modelMapper.map(listOption.get(i), OptionResponseDto.class));
					optionDTOs.add(prDto);
				}
			}
			dataRes.setCount(size);
			dataRes.setCode(200);
			dataRes.setMessage("Thành công");
			dataRes.setListData(optionDTOs);
			return new ResponseEntity<>(dataRes,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			dataRes.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			dataRes.setMessage("Thất bại");
			return new ResponseEntity<>(dataRes,HttpStatus.FAILED_DEPENDENCY);
		}
	}
	
	
	
	
	
	
	
}
