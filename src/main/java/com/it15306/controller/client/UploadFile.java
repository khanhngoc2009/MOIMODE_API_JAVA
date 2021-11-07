package com.it15306.controller.client;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.it15306.config.DataResponse;
import com.it15306.config.DataResponseList;
import com.it15306.dto.FileImageDto;
import com.it15306.dto.MultiFileDto;
import com.it15306.entities.ImageProduct;
import com.it15306.servicesImpl.ProductServiceImpl;


@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200","http://35.198.241.56" })
@RestController
@RequestMapping("/miemode_api/v1")
public class UploadFile {
	@Autowired 
	private ProductServiceImpl productServiceImpl;
	
	@PostMapping("/upload-single-file")
	public ResponseEntity<?> upload_single(
		@RequestParam MultipartFile uploadedFile
	) {
		DataResponse<FileImageDto> data = new DataResponse<FileImageDto>(); 
		try {
			System.out.print(uploadedFile.getResource());
			File myUploadFolder = new File("/var/www/MOIMODE_API_JAVA/src/main/webapp/storages");
			// Nếu folder lưu file ko tồn tại -> tạo mới
			if (!myUploadFolder.exists()) {
				myUploadFolder.mkdirs();
			}
			// Ghi file đã upload vào thư mục lưu trữ file
			File savedFile = new File(myUploadFolder, uploadedFile.getOriginalFilename());
			uploadedFile.transferTo(savedFile);
			data.setCode(200);
			FileImageDto res = new FileImageDto();
			res.setId(null);
			res.setUrl(uploadedFile.getOriginalFilename());
			data.setData(res);
			data.setMessage("Thanh cong");
			return new ResponseEntity<>(data,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(data,HttpStatus.FAILED_DEPENDENCY);
		}
		
	}
	@PostMapping("/upload-multifile-product")
	public ResponseEntity<?> upload_multi(
		@RequestParam MultiFileDto uploadedFiles
	) {
		DataResponseList<FileImageDto> data = new DataResponseList<FileImageDto>();
		List<FileImageDto> dta = new ArrayList<FileImageDto>();
		try {
			int size = uploadedFiles.getUploadedFile().size();
			for(int i=0;i<size;i++) {
				MultipartFile uploadedFile = uploadedFiles.getUploadedFile().get(i);
				System.out.print(uploadedFile.getResource());
				File myUploadFolder = new File("/var/www/MOIMODE_API_JAVA/src/main/webapp/storages");
				// Nếu folder lưu file ko tồn tại -> tạo mới
				if (!myUploadFolder.exists()) {
					myUploadFolder.mkdirs();
				}
				// Ghi file đã upload vào thư mục lưu trữ file
				File savedFile = new File(myUploadFolder, uploadedFile.getOriginalFilename());
				uploadedFile.transferTo(savedFile);
				FileImageDto res = new FileImageDto();
				ImageProduct s = new ImageProduct();
				s.setUrl(uploadedFile.getOriginalFilename());
				s.setType(2);
				s.setProduct(productServiceImpl.getById(uploadedFiles.getProduct_id()));
				ImageProduct i_p = productServiceImpl.saveImageProduct(s);
				res.setId(i_p.getId());
				res.setUrl(uploadedFile.getOriginalFilename());
				dta.add(res);
			}
			data.setCode(200);
			data.setListData(dta);
			data.setMessage("Thanh cong");
			return new ResponseEntity<>(data,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(data,HttpStatus.FAILED_DEPENDENCY);
		}
		
	}
	
}
