package com.yupi.springbootinit.model.enums;

public enum SelectTypeEnmu {
    cardInfo(1),
    cardType(2),
    remindType(3);

    private Integer type;

    SelectTypeEnmu(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

}
