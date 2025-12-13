package jp.co.mayekawa.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 月次前予測原価ファンクションのリクエストパラメータ。
 */
@Data
public class MonthlyForecastCostRequest {

    /** 製番 */
    @NotNull(message = "製番は必須です")
    private String sibn;
}
