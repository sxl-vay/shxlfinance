package com.yupi.springbootinit.model.vo.echart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineChartVO {
    List<String> times ;
    List<BigDecimal> totals;
}
