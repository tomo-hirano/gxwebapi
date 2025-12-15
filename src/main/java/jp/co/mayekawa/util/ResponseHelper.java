package jp.co.mayekawa.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.mayekawa.common.ApiResponse;

/**
 * レスポンスヘッダーヘルパー。
 */
@Component
public class ResponseHelper {

	/** ObjectMapper */
	private final ObjectMapper customMapper;

	/**
	 * コンストラクタ。
	 * 
	 * @param customMapper ObjectMapper
	 */
	public ResponseHelper(@Qualifier("nullToEmptyMapper") ObjectMapper customMapper) {
		this.customMapper = customMapper;
	}

	/**
	 * 成功時の出力処理。
	 * 
	 * @param <T>     出力クラス
	 * @param message メッセージ内容
	 * @param data    出力オブジェクト
	 * @return レスポンスエンティティ
	 * @throws JsonProcessingException JSON出力時
	 */
	public <T> ResponseEntity<String> successWithNullToEmpty(String message, T data) throws JsonProcessingException {
		ApiResponse<T> response = ApiResponse.success(message, data);
		String json = customMapper.writeValueAsString(response);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
	}

	/**
	 * バリデーションエラー時の出力処理。
	 * 
	 * @param errors エラー内容
	 * @return レスポンスエンティティ
	 * @throws JsonProcessingException JSON出力時
	 */
	public ResponseEntity<String> validationErrorWithNullToEmpty(List<String> errors) throws JsonProcessingException {
		ApiResponse<?> response = ApiResponse.validationError(errors);
		String json = customMapper.writeValueAsString(response);
		return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(json);

	}

}
