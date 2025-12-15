package jp.co.mayekawa.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import jp.co.mayekawa.entity.MonthlyForecastCost;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Sql(scripts = { "/data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class MonthlyForecastCostMapperTest {

    @Autowired
    private MonthlyForecastCostMapper monthlyForecastCostMapper;

    @Test
    public void selectMonthlyForecastCostTest() {
        List<MonthlyForecastCost> ret1 = monthlyForecastCostMapper
                .selectMonthlyForecastCost("0000000001000000000200001");
        assertThat(ret1).hasSize(3);

        List<MonthlyForecastCost> ret2 = monthlyForecastCostMapper.selectMonthlyForecastCost("123");
        assertThat(ret2).hasSize(1);

        List<MonthlyForecastCost> ret3 = monthlyForecastCostMapper.selectMonthlyForecastCost("99999");
        assertThat(ret3).isEmpty();
    }
}
