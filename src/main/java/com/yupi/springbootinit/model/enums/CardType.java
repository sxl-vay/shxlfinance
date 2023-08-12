package com.yupi.springbootinit.model.enums;

import lombok.Data;

/**
 * 银行卡类型枚举类
 */
public enum CardType {
    //农业银行
    ABC(7,"农业银行"),
    //建设银行
    CCB(6,"建设银行"),
    //江苏银行
    BOJ(5,"江苏银行"),
    //邮政储蓄
    PSBOC(4,"邮政储蓄"),
    //招商银行
    CMB(3,"招商银行"),
    //交通银行
    BOCOM(2,"交通银行"),
    //工商银行
    ICBC(1,"工商银行");

    private Integer id;

    private String  name;

    CardType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
