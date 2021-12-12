package com.it15306.controller.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.config.DataResponse;
import com.it15306.config.DataResponseList;
import com.it15306.dto.PageDto;
import com.it15306.dto.favorite.FavoriteDto;
import com.it15306.dto.favorite.PayloadFavorite;
import com.it15306.dto.favorite.PayloadFollowDto;
import com.it15306.dto.favorite.PayloadList;
import com.it15306.dto.voucher.ResponBodyVoucher;
import com.it15306.dto.voucher.Voucherdto;
import com.it15306.entities.User;
import com.it15306.jwt.JwtTokenProvider;
import com.it15306.services.FavoriteService;
import com.it15306.services.UserService;
import com.it15306.services.VoucherService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200","http://35.198.241.56" })
@RestController
@RequestMapping("/miemode_api/v1")
public class CustomerFavorite {
	
	@Autowired
	FavoriteService favoriteService;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private UserService userservice;
	
	@PostMapping("/favorite/list")
	@ResponseBody
	public ResponseEntity<DataResponseList<FavoriteDto>> getFavoriteList(@RequestBody PayloadList payload,HttpServletRequest httpServletRequest) {
		try {
			String token = httpServletRequest.getHeader("Authorization").substring(7);
			String username = tokenProvider.getUserNameFromJWT(token);
			User user = userservice.getByUsername(username);
//			User user = new User();
//			user.setId(32);
			return favoriteService.listFavoriteByProduct(payload,user);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
			
	}
	@PostMapping("/favorite/unfollow")
	@ResponseBody
	public ResponseEntity<DataResponse<String>> unFollow(@RequestBody PayloadFollowDto payload,HttpServletRequest httpServletRequest) {
		try {
			String token = httpServletRequest.getHeader("Authorization").substring(7);
			String username = tokenProvider.getUserNameFromJWT(token);
			User user = userservice.getByUsername(username);
			PayloadFavorite pl= new PayloadFavorite();
			pl.setUser(user);
			pl.setId_product(payload.getProduct_id());
			return favoriteService.disableFavorite(pl);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	@PostMapping("/favorite/follow")
	@ResponseBody
	public ResponseEntity<DataResponse<FavoriteDto>> follow(@RequestBody PayloadFollowDto payload,HttpServletRequest httpServletRequest) {
		try {
			String token = httpServletRequest.getHeader("Authorization").substring(7);
			String username = tokenProvider.getUserNameFromJWT(token);
			User user = userservice.getByUsername(username);
			PayloadFavorite pl= new PayloadFavorite();
			pl.setUser(user);
			pl.setId_product(payload.getProduct_id());
			return favoriteService.enableFavorite(pl);
		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	

}
