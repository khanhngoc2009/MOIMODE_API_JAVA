package com.it15306.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	public DataResponse<String> createOption(@RequestBody CreateOptionDto body) {
		DataResponse<String> dataRes= new DataResponse<String>();
		try {
			Options option = new Options();
			option.setDescription(body.getDescription());
			option.setName(body.getName());
			option.setStatus(1);
			option.setCreate_date(new Date());
			optionProductServiceImpl.saveOptionProduct(option);
			dataRes.setCode(200);
			dataRes.setMessage("Thêm thành công ");
			dataRes.setData("");
		} catch (Exception e) {
			// TODO: handle exception
			dataRes.setCode(201);
			dataRes.setMessage("Thất bại ");
		}
		return dataRes;
	}
	@RequestMapping(value = "/admin/deleteOption", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DataResponse<String> deleteOption(@RequestBody DeleteOptionDto dto) {
		DataResponse<String> dataRes= new DataResponse<String>();
		try {
			Options option = new Options();
			option.setId(dto.getOption_id());
			Option_Product op_pr = optionsProductsServiceImpl.getOption(option);
			if(op_pr!=null) {
				dataRes.setCode(201);
				dataRes.setMessage("Bạn không thể xoá option này ");
			}else {
				optionProductServiceImpl.delete(option);
				dataRes.setCode(200);
				dataRes.setMessage("Xoá thành công");
			}
			dataRes.setData("");
		} catch (Exception e) {
			// TODO: handle exception
			dataRes.setCode(201);
			dataRes.setMessage("Thất bại");
		}
		return dataRes;
	}
	@RequestMapping(value = "/admin/updateOption", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DataResponse<Options> updateOption(@RequestBody UpdateOptionDto dto) {
		DataResponse<Options> dataRes= new DataResponse<Options>();
		try {
			Options option = optionProductServiceImpl.getById(dto.getId());
			if(option!=null) {
				option.setDescription(dto.getDescription());
				option.setStatus(dto.getStatus());
				option.setName(dto.getName());
				dataRes.setCode(200);
				dataRes.setMessage("Update thành công");
				dataRes.setData(option);
			}else {
				dataRes.setMessage("Không tồn tại");
				dataRes.setCode(400);
			}
		} catch (Exception e) {
			// TODO: handle exception
			dataRes.setCode(201);
			dataRes.setMessage("Thất bại");
		}
		return dataRes;
	}
	@RequestMapping(value = "/admin/listOption", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DataResponseList<OptionDTO> listOption() {
		DataResponseList<OptionDTO> dataRes= new DataResponseList<OptionDTO>();
		ModelMapper modelMapper = new ModelMapper();
		try {
			List<OptionDTO> optionDTOs = new ArrayList<OptionDTO>();
			List<Options> listOption = optionProductServiceImpl.getOptions(0, 10);
			int size = listOption.size();
			if (size > 0) {
				for (int i = 0; i < size; i++) {
					OptionDTO prDto = (modelMapper.map(listOption.get(i), OptionDTO.class));
					optionDTOs.add(prDto);
				}
			}
			dataRes.setCount(size);
			dataRes.setCode(200);
			dataRes.setMessage("Update thành công");
			dataRes.setListData(optionDTOs);
		} catch (Exception e) {
			// TODO: handle exception
			dataRes.setCode(201);
			dataRes.setMessage("Thất bại");
		}
		return dataRes;
	}
	
	
	
	
	
	
	
}
