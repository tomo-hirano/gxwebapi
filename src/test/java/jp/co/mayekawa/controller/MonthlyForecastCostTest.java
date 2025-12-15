package jp.co.mayekawa.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Sql(scripts = { "/data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class MonthlyForecastCostTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void selectMonthlyForecastCost_ReturnsCorrectData() throws Exception {

        String requestJson = "{\"sibn\":\"123\"}";

        mockMvc.perform(
                post("/monthly/search")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(requestJson)
            )
            .andDo(print())
            .andExpect(status().isOk())
            // ResponseHelperでラップされるなら $.data、ラップなしなら $ に直す
            .andExpect(jsonPath("$.data", Matchers.hasSize(1)))
            .andExpect(jsonPath("$.data[0].itemCd").value("0000000001000000000200000"));
    }
}
