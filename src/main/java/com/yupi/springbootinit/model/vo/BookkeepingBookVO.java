package com.yupi.springbootinit.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 记账本视图（脱敏）
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
public class BookkeepingBookVO implements Serializable {


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

    /**
     * 总和
     */
    private BigDecimal total;

    /**
     * 扣除转移支付合集
     */
    private BigDecimal pureTotal;

    /**
     * 转移支付
     */
    private BigDecimal transferPayment;
    /**
     * 信用卡未还
     */
    private BigDecimal creditCardArrears;

}