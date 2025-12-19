package jp.co.mayekawa.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jp.co.mayekawa.entity.MonthlyForecastCost;
import jp.co.mayekawa.mapper.MonthlyForecastCostMapper;

/**
 * 月次前予測原価ファンクションを実行するためのサービス。
 */
@Service
public class MonthlyForecastCostService {

    /** Logger */
    private static final Logger log = LoggerFactory.getLogger(MonthlyForecastCostService.class);

    /** 月次前予測原価ファンクションMapper */
    private final MonthlyForecastCostMapper monthlyForecastCostMapper;

    /**
     * コンストラクタ。
     * 
     * @param monthlyForecastCostMapper 月次前予測原価ファンクションMapper
     */
    public MonthlyForecastCostService(MonthlyForecastCostMapper monthlyForecastCostMapper) {
        this.monthlyForecastCostMapper = monthlyForecastCostMapper;
    }

    /**
     * 月次前予測原価ファンクション検索処理。
     * 
     * @param sibn 製番（製品識別番号）
     * @return 検索結果リスト。該当データなしの場合は空リストを返す
     */
    public List<MonthlyForecastCost> selectMonthlyForecastCost(String sibn) {
        if (log.isDebugEnabled()) {
            log.debug("月次予測原価検索開始 製番={}", sibn);
        }

        List<MonthlyForecastCost> result = monthlyForecastCostMapper.selectMonthlyForecastCost(sibn);

        if (log.isDebugEnabled()) {
            log.debug("月次予測原価検索完了 製番={} 件数={}", sibn, result.size());
        }
        return result;
    }
}
