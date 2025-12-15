package jp.co.mayekawa.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import jp.co.mayekawa.common.ApiResponse;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

/**
 * レスポンスヘッダーヘルパー。
 */
@Component
public class ResponseHelper {

    /** ObjectMapper */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 成功時の出力処理。
     * 
     * @param <T>     出力クラス
     * @param message メッセージ内容
     * @param data    出力オブジェクト
     * @return レスポンスエンティティ
     * @throws JacksonException JSON出力時
     */
    public <T> ResponseEntity<String> successWithNullToEmpty(String message, T data) throws JacksonException {
        ApiResponse<T> response = ApiResponse.success(message, data);

        JsonNode tree = objectMapper.valueToTree(response);
        JsonNode normalized = NullToEmptyJson.replaceNullWithEmptyString(tree);

        String json = objectMapper.writeValueAsString(normalized);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
    }

    /**
     * バリデーションエラー時の出力処理。
     * 
     * @param errors エラー内容
     * @return レスポンスエンティティ
     * @throws JacksonException JSON出力時
     */
    public ResponseEntity<String> validationErrorWithNullToEmpty(List<String> errors) throws JacksonException {
        ApiResponse<?> response = ApiResponse.validationError(errors);

        JsonNode tree = objectMapper.valueToTree(response);
        JsonNode normalized = NullToEmptyJson.replaceNullWithEmptyString(tree);

        String json = objectMapper.writeValueAsString(normalized);
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(json);
    }
}
