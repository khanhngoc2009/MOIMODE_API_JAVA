package com.it15306.controller.client;

import java.io.File;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.it15306.config.DataResponse;
import com.it15306.dto.FileImageDto;


@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200","http://35.198.241.56" })
@RestController
@RequestMapping("/miemode_api/v1")
public class UploadFile {
	@PostMapping("/upload-file")
	public ResponseEntity<?> upload(
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
	
}
