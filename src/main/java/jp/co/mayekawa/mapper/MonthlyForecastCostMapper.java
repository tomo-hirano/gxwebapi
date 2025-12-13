package jp.co.mayekawa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.mayekawa.entity.MonthlyForecastCost;

/**
 * 月次前予測原価ファンクションを実行するためのMapperインターフェイス。
 */
@Mapper
public interface MonthlyForecastCostMapper {

    /**
     * 月次前予測原価ファンクション検索。
     * 
     * @param sibn 製番
     * @return 検索結果
     */
    List<MonthlyForecastCost> selectMonthlyForecastCost(@Param("sibn") String sibn);
}
