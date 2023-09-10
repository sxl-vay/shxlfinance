package com.yupi.springbootinit.model.entity;

import com.yupi.springbootinit.annotation.TotalAdd;
import com.yupi.springbootinit.annotation.TotalSubtract;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* 
* @TableName bookkeeping_book
*/
@Data
public class BookkeepingBook implements Serializable {

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
    * 是否删除
    */
    private Integer deleteType;
    /**
    * 
    */
    @TotalAdd
    private BigDecimal zfbYue;
    /**
    * 
    */
    @TotalAdd
    private BigDecimal zfbFund;
    /**
    * 
    */
    @TotalAdd
    private BigDecimal fund;
    /**
    * 
    */
    @TotalAdd
    private BigDecimal shares;
    /**
    * 
    */
    @TotalAdd
    private BigDecimal bond;
    /**
    * 
    */
    @TotalAdd
    private BigDecimal constructionBank;
    /**
    * 
    */
    @TotalAdd
    private BigDecimal agriculturalBank;
    /**
    * 
    */
    @TotalAdd
    private BigDecimal debt;
    /**
    * 
    */
    @TotalAdd
    private BigDecimal wechatYue;
    /**
    * 
    */
    @TotalAdd
    private BigDecimal wechatFund;
    /**
    * 
    */
    @TotalAdd
    private BigDecimal merchantsBank;

    /**
     * 转移支付
     */
    //@TotalAdd
    private BigDecimal transferPayment;

    /**
     * 信用卡未还
     */
    @TotalSubtract
    private BigDecimal creditCardArrears;


}
