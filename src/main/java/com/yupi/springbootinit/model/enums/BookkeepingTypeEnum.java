package com.yupi.springbootinit.model.enums;

/**
 * 定义主要资金渠道
 */
public enum BookkeepingTypeEnum {
    zfbYue("支付宝余额",FundTypeEnum.flexible),
    zfbFund("余额宝",FundTypeEnum.flexible),
    fund("基金",FundTypeEnum.investment),
    shares("股票",FundTypeEnum.investment),
    bond("债券",FundTypeEnum.investment),
    constructionBank("建行",FundTypeEnum.flexible),
    agriculturalBank("农行",FundTypeEnum.flexible),
    debt("外借债务",FundTypeEnum.lend),
    wechatYue("微信余额",FundTypeEnum.flexible),
    wechatFund("微信基金",FundTypeEnum.flexible),
    merchantsBank("招商银行",FundTypeEnum.flexible);
    
    private String des;

    private FundTypeEnum typeEnum;

    BookkeepingTypeEnum(String des, FundTypeEnum typeEnum) {
        this.des = des;
        this.typeEnum = typeEnum;
    }

    public String getDes() {
        return des;
    }

    public FundTypeEnum getTypeEnum() {
        return typeEnum;
    }
}
