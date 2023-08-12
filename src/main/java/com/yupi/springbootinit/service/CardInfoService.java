package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.springbootinit.model.dto.card.CardInfoAddOrUpdateRequest;
import com.yupi.springbootinit.model.entity.CardInfo;

/**
* @author 16938
* @description 针对表【card_info】的数据库操作Service
* @createDate 2023-08-07 21:36:56
*/

public interface CardInfoService extends IService<CardInfo> {

    CardInfo getCardInfoByRequest(CardInfoAddOrUpdateRequest request);

    boolean insert(CardInfoAddOrUpdateRequest cardInfoAddOrUpdateRequest);

}
