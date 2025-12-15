package jp.co.mayekawa.dto.response;

import java.math.BigDecimal;

import jp.co.mayekawa.entity.MonthlyForecastCost;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

/**
 * 月次前予測原価ファンクションのレスポンスオブジェクト。
 */
@Data
@NoArgsConstructor
public class MonthlyForecastCostResponse {

    /** 製番 */
    private String sibn;

    /** 会計年月 */
    private String kkiYymm;

    /** 予実区分 */
    private String yjtTyp;

    /** 予実区分名 */
    private String yjtTypNm;

    /** 発生区分 */
    private String hisTyp;

    /** 発生区分名 */
    private String hisTypnm;

    /** 商品コード */
    private String itemCd;

    /** 商品名 */
    private String itemNm;

    /** HSコード */
    private String hsCd;

    /** HS名 */
    private String hsNm;

    /** 製造PL集計項目コード */
    private String sizPlSyuKumCd;

    /** 製造PL集計項目名 */
    private String sizPlSyuKumNm;

    /** 通貨コード */
    private String tukaCd;

    /** レート */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal rate;

    /** 数量 */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal sry;

    /** 単位コード */
    private String unitCd;

    /** 単位名 */
    private String unitNm;

    /** 単価 */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal tank;

    /** 単価（邦貨） */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal tankHouk;

    /** 金額 */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long gak;

    /** 伝票番号 */
    private String dnpyNo;

    /** 明細摘要 */
    private String msiNote;

    /**
     * レスポンスオブジェクト作成処理。
     * 
     * @param monthlyForecastCost 月次前予測原価ファンクションのエンティティクラス
     * @return 月次前予測原価ファンクションのレスポンスオブジェクト
     */
    public static MonthlyForecastCostResponse fromEntity(MonthlyForecastCost monthlyForecastCost) {
        MonthlyForecastCostResponse dto = new MonthlyForecastCostResponse();
        dto.sibn = monthlyForecastCost.getSibn();
        dto.kkiYymm = monthlyForecastCost.getKkiYymm();
        dto.yjtTyp = monthlyForecastCost.getYjtTyp();
        dto.yjtTypNm = monthlyForecastCost.getYjtTypNm();
        dto.hisTyp = monthlyForecastCost.getHisTyp();
        dto.hisTypnm = monthlyForecastCost.getHisTypnm();
        dto.itemCd = monthlyForecastCost.getItemCd();
        dto.itemNm = monthlyForecastCost.getItemNm();
        dto.hsCd = monthlyForecastCost.getHsCd();
        dto.hsNm = monthlyForecastCost.getHsNm();
        dto.sizPlSyuKumCd = monthlyForecastCost.getSizPlSyuKumCd();
        dto.sizPlSyuKumNm = monthlyForecastCost.getSizPlSyuKumNm();
        dto.tukaCd = monthlyForecastCost.getTukaCd();
        dto.rate = monthlyForecastCost.getRate();
        dto.sry = monthlyForecastCost.getSry();
        dto.unitCd = monthlyForecastCost.getUnitCd();
        dto.unitNm = monthlyForecastCost.getUnitNm();
        dto.tank = monthlyForecastCost.getTank();
        dto.tankHouk = monthlyForecastCost.getTankHouk();
        dto.gak = monthlyForecastCost.getGak();
        dto.dnpyNo = monthlyForecastCost.getDnpyNo();
        dto.msiNote = monthlyForecastCost.getMsiNote();
        return dto;
    }
}
