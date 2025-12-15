package jp.co.mayekawa.common;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * レスポンスクラス。
 * 
 * @param <T> レスポンスデータのクラス
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now().withNano(0);

    /**
     * コンストラクタ。
     * 
     * @param status APIレスポンスステータス
     * @param message レスポンスのメッセージ
     * @param data レスポンスデータ
     * @param errors エラー時のメッセージリスト
     */
    public ApiResponse(@NonNull String status, @NonNull String message, T data, List<String> errors) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }

    /**
     * 成功時のファクトリメソッド。
     * 
     * @param <T>  レスポンスデータのクラス
     * @param data レスポンスデータ
     * @return レスポンスオブジェクト
     */
	public static <T> ApiResponse<T> success(T data) {
		return ApiResponse.<T>builder().status(ApiResponseStatus.SUCCESS.getValue()).message("正常に処理されました").data(data)
				.build();
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
		return ApiResponse.<T>builder().status(ApiResponseStatus.SUCCESS.getValue()).message(message).data(data)
				.build();
	}

    /**
     * エラー時のファクトリメソッド。
     * 
     * @param <T>     レスポンスデータのクラス
     * @param message レスポンス時のメッセージ
     * @return レスポンスオブジェクト
     */
	public static <T> ApiResponse<T> error(String message) {
		return ApiResponse.<T>builder().status(ApiResponseStatus.ERROR.getValue()).message(message).build();
	}

    /**
     * エラー時のファクトリメソッド。
     * 
     * @param <T>    レスポンスデータのクラス
     * @param errors エラーメッセージ
     * @return レスポンスオブジェクト
     */
	public static <T> ApiResponse<T> validationError(List<String> errors) {
		return ApiResponse.<T>builder().status(ApiResponseStatus.VALIDATION_ERROR.getValue()).message("入力値に不正があります")
				.errors(errors).build();
	}
}
