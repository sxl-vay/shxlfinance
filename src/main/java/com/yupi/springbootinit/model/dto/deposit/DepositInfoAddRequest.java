package com.yupi.springbootinit.model.dto.deposit;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class DepositInfoAddRequest implements Serializable {
    /**
     * 存款时间
     */
    private String startTime;

    /**
     * 到期时间
     */
    private String endTime;

    /**
     *
     */
    private String tips;

    /**
     *
     */
    private BigDecimal amount;


    /**
     *
     */
    private Long cardType;

    /**
     *
     */
    private Integer remindType;

}
