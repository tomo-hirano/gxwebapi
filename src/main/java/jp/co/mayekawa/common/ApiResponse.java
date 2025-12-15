package jp.co.mayekawa.common;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * レスポンスクラス。
 * 
 * @param <T> レスポンスデータのクラス
 */
@Data
public class ApiResponse<T> {

    /** APIレスポンスステータス */
    private String status;
    /** レスポンスのメッセージ */
    private String message;
    /** レスポンスデータ */
    private T data;
    /** エラー時のメッセージリスト */
    private List<String> errors;
    /** 実行日時 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    /**
     * コンストラクタ。
     */
    private ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    /**
     * 成功時のファクトリメソッド。
     * 
     * @param <T>  レスポンスデータのクラス
     * @param data レスポンスデータ
     * @return レスポンスオブジェクト
     */
    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.status = ApiResponseStatus.SUCCESS.getValue();
        response.message = "正常に処理されました";
        response.data = data;
        return response;
    }

    /**
     * 成功時のファクトリメソッド。
     * 
     * @param <T>     レスポンスデータのクラス
     * @param message レスポンス時のメッセージ
     * @param data    レスポンスデータ
     * @return レスポンスオブジェクト
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.status = ApiResponseStatus.SUCCESS.getValue();
        response.message = message;
        response.data = data;
        return response;
    }

    /**
     * エラー時のファクトリメソッド。
     * 
     * @param <T>     レスポンスデータのクラス
     * @param message レスポンス時のメッセージ
     * @return レスポンスオブジェクト
     */
    public static <T> ApiResponse<T> error(String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.status = ApiResponseStatus.ERROR.getValue();
        response.message = message;
        return response;
    }

    /**
     * エラー時のファクトリメソッド。
     * 
     * @param <T>    レスポンスデータのクラス
     * @param errors エラーメッセージ
     * @return レスポンスオブジェクト
     */
    public static <T> ApiResponse<T> validationError(List<String> errors) {
        ApiResponse<T> response = new ApiResponse<>();
        response.status = ApiResponseStatus.VALIDATION_ERROR.getValue();
        response.message = "入力値に不正があります";
        response.errors = errors;
        return response;
    }
}
