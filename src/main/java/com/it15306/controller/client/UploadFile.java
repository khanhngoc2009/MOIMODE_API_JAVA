package com.it15306.controller.client;

import java.io.File;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/miemode_api/v1")
public class UploadFile {
	@PostMapping("/uploadFile")
	public String upload(
		@RequestParam MultipartFile uploadedFile
	) {
		File myUploadFolder = new File("C:\\Users\\huong\\eclipse-workspace\\MOIMODE\\src\\main/webapp/storages");
		// Nếu folder lưu file ko tồn tại -> tạo mới
		if (!myUploadFolder.exists()) {
			myUploadFolder.mkdirs();
		}
		// Ghi file đã upload vào thư mục lưu trữ file
		File savedFile = new File(myUploadFolder, uploadedFile.getOriginalFilename());
		try {
			uploadedFile.transferTo(savedFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "oke rồi đấy";
	}
	
}
