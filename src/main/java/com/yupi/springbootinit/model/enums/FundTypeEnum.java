package com.yupi.springbootinit.model.enums;

public enum FundTypeEnum {
    flexible("灵活取用"),
    investment("投资"),
    lend("借出"),
    other("其他");
    private String des;

    FundTypeEnum(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }
}
