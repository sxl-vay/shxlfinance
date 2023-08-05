package com.yupi.springbootinit.model.vo.echart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PieChartItemVO {
    private BigDecimal value;
    private String name;
}
