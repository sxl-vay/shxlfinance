package com.yupi.springbootinit.model.vo.echart;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TotalCountLineVO {
    private String time;
    private BigDecimal total;
    private BigDecimal pureTotal;

}
