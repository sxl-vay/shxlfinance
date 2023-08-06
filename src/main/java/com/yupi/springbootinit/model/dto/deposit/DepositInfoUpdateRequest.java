package com.yupi.springbootinit.model.dto.deposit;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class DepositInfoUpdateRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     *
     */
    private Long userId;

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
    private Integer cardType;

    /**
     *
     */
    private Integer remindType;

    private static final long serialVersionUID = 1L;
}
