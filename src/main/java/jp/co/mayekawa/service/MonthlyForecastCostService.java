package jp.co.mayekawa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.mayekawa.entity.MonthlyForecastCost;
import jp.co.mayekawa.mapper.MonthlyForecastCostMapper;

/**
 * 月次前予測原価ファンクションを実行するためのサービス。
 */
@Service
public class MonthlyForecastCostService {

    @Autowired
    private MonthlyForecastCostMapper monthlyForecastCostMapper;

    /**
     * 月次前予測原価ファンクション検索処理。
     * 
     * @param sibn 製番
     * @return 検索結果
     */
    public List<MonthlyForecastCost> selectMonthlyForecastCost(String sibn) {
        return monthlyForecastCostMapper.selectMonthlyForecastCost(sibn);
    }
}
