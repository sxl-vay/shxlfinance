package com.yupi.springbootinit.model.dto.book;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class BookkeepingUpdateRequest implements Serializable {
    /**
     *
     */
    private BigDecimal zfbYue;
    /**
     *
     */
    private BigDecimal zfbFund;
    /**
     *
     */
    private BigDecimal fund;
    /**
     *
     */
    private BigDecimal shares;
    /**
     *
     */
    private BigDecimal bond;
    /**
     *
     */
    private BigDecimal constructionBank;
    /**
     *
     */
    private BigDecimal agriculturalBank;
    /**
     *
     */
    private BigDecimal debt;
    /**
     *
     */
    private BigDecimal wechatYue;
    /**
     *
     */
    private BigDecimal wechatFund;
    /**
     *
     */
    private BigDecimal merchantsBank;

    private BigDecimal transferPayment;

    private BigDecimal creditCardArrears;

    private String createTime;

    private Long id;


}
