package com.yupi.springbootinit.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @TableName deposit_info
 */
@Data
public class DepositInfo implements Serializable {
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
     * 是否删除
     */
    private Integer deleteType;

    /**
     * 
     */
    private Long cardType;

    /**
     * 
     */
    private Integer remindType;

    private static final long serialVersionUID = 1L;
}