package com.yupi.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.yupi.springbootinit.model.enums.CardType;
import lombok.Data;

/**
 * 
 * @TableName card_info
 */
@Data
public class CardInfo implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 卡名称
     */
    private String cardName;

    /**
     * 卡类型
     */
    private CardType cardType;
    /**
     * 卡号
     */
    private String cardNumber;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}