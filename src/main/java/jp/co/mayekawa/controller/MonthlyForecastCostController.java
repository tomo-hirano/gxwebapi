package jp.co.mayekawa.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jp.co.mayekawa.dto.request.MonthlyForecastCostRequest;
import jp.co.mayekawa.dto.response.MonthlyForecastCostResponse;
import jp.co.mayekawa.service.MonthlyForecastCostService;
import jp.co.mayekawa.util.ResponseHelper;

/**
 * 月次前予測原価ファンクションを実行するためのコントローラ。
 */
@RestController
@RequestMapping("/monthly")
public class MonthlyForecastCostController {

	/** 月次前予測原価ファンクションサービス */
	private final MonthlyForecastCostService monthlyForecastCostService;
	/** レスポンスヘルパー */
	private final ResponseHelper responseHelper;

	/**
	 * コンストラクタ。
	 * 
	 * @param monthlyForecastCostService 月次前予測原価ファンクションサービス
	 * @param responseHelper             レスポンスヘルパー
	 */
	public MonthlyForecastCostController(MonthlyForecastCostService monthlyForecastCostService,
			ResponseHelper responseHelper) {
		this.monthlyForecastCostService = monthlyForecastCostService;
		this.responseHelper = responseHelper;
	}

	/**
	 * 月次前予測原価ファンクション検索処理。
	 * 
	 * @param sibn 製番
	 * @return 検索結果
	 * @throws JsonProcessingException JSON出力時
	 */
	@PostMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> selectMonthlyForecastCost(@Valid @RequestBody MonthlyForecastCostRequest request,
			BindingResult bindingResult) throws JsonProcessingException {

		if (bindingResult.hasErrors()) {
			List<String> errorMessages = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage)
					.collect(Collectors.toList());

			// バリデーションエラーもNULL→空文字変換
			return responseHelper.validationErrorWithNullToEmpty(errorMessages);
		}

		// 正常処理 - EntityからDTOに変換してNULL→空文字変換
		List<MonthlyForecastCostResponse> responseData = monthlyForecastCostService
				.selectMonthlyForecastCost(request.getSibn()).stream().map(MonthlyForecastCostResponse::fromEntity)
				.collect(Collectors.toList());

		return responseHelper.successWithNullToEmpty("検索が完了しました", responseData);
	}
}
