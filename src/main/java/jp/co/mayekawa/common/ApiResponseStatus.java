package jp.co.mayekawa.common;

/**
 * APIレスポンスステータス。
 */
public enum ApiResponseStatus {

    SUCCESS("success"),
    ERROR("error"),
    VALIDATION_ERROR("validation_error"),
    NOT_FOUND("not_found"),
    UNAUTHORIZED("unauthorized");

    private final String value;

    ApiResponseStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
