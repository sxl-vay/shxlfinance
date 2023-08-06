package com.yupi.springbootinit.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class DepositInfoVO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     *
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 存款时间
     */
    private Date startTime;

    /**
     * 到期时间
     */
    private Date endTime;

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



}
