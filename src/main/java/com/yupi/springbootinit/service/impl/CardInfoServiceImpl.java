package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.mapper.CardInfoMapper;
import com.yupi.springbootinit.mapper.DepositInfoMapper;
import com.yupi.springbootinit.model.dto.card.CardInfoAddOrUpdateRequest;
import com.yupi.springbootinit.model.entity.CardInfo;
import com.yupi.springbootinit.service.CardInfoService;
import com.yupi.springbootinit.utils.Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 16938
 * @description 针对表【card_info】的数据库操作Service实现
 * @createDate 2023-08-07 21:36:56
 */
@Service
public class CardInfoServiceImpl extends ServiceImpl<CardInfoMapper, CardInfo>
        implements CardInfoService {
    @Resource
    private CardInfoMapper mapper;
    @Override
    public CardInfo getCardInfoByRequest(CardInfoAddOrUpdateRequest request) {
        CardInfo cardInfo = new CardInfo();
        if (Util.isEmpty(request)) {
            return cardInfo;
        }
        cardInfo.setId(request.getId());
        cardInfo.setCardType(request.getCardTypeSelect());
        cardInfo.setCardNumber(request.getCardNumber());
        cardInfo.setCardName(request.getCardName());
        cardInfo.setUserId(request.getUserId());
        return cardInfo;
    }

    @Override
    public boolean insert(CardInfoAddOrUpdateRequest cardInfoAddOrUpdateRequest) {
        CardInfo cardInfo = getCardInfoByRequest(cardInfoAddOrUpdateRequest);
        int insert = mapper.insert(cardInfo);
        if (insert >= 0) {
            return true;
        }
        return false;
    }

}




