package jp.co.mayekawa.entity;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 月次前予測原価ファンクションのエンティティクラス。
 */
@Data
public class MonthlyForecastCost {

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
    private BigDecimal rate;

    /** 数量 */
    private BigDecimal sry;

    /** 単位コード */
    private String unitCd;

    /** 単位名 */
    private String unitNm;

    /** 単価 */
    private BigDecimal tank;

    /** 単価（邦貨） */
    private BigDecimal tankHouk;

    /** 金額 */
    private Long gak;

    /** 伝票番号 */
    private String dnpyNo;

    /** 明細摘要 */
    private String msiNote;
}
