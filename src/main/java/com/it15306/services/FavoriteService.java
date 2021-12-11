package com.it15306.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.it15306.config.DataResponse;
import com.it15306.config.DataResponseList;
import com.it15306.dto.favorite.FavoriteDto;
import com.it15306.dto.favorite.PayloadFavorite;
import com.it15306.dto.favorite.PayloadList;
import com.it15306.dto.payment.PaymentDTO;
import com.it15306.dto.payment.RequestPaymentPage;
import com.it15306.entities.Favorite;
import com.it15306.entities.Payment;
@Service
public interface FavoriteService {
	ResponseEntity<DataResponseList<FavoriteDto>> listFavoriteByProduct(PayloadList payload);
	ResponseEntity<DataResponse<String>> disableFavorite(PayloadFavorite payload);
	ResponseEntity<DataResponse<FavoriteDto>> enableFavorite(PayloadFavorite payload);
	FavoriteDto checkFavorite(PayloadFavorite payload);
}
