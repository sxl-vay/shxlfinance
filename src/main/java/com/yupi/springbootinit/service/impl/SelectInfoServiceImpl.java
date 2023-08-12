package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yupi.springbootinit.model.entity.CardInfo;
import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.model.enums.CardType;
import com.yupi.springbootinit.model.enums.SelectTypeEnmu;
import com.yupi.springbootinit.model.vo.SelectInfoVO;
import com.yupi.springbootinit.service.CardInfoService;
import com.yupi.springbootinit.service.SelectInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SelectInfoServiceImpl implements SelectInfoService {

    @Resource
    private CardInfoService cardInfoService;

    @Override
    public List<SelectInfoVO> getInfo(SelectTypeEnmu type, User user) {
        if (type == SelectTypeEnmu.cardType) {
            return getCardType();
        }
        if (type == SelectTypeEnmu.cardInfo) {
            return getCardInfo(user);
        }
        return null;
    }

    public List<SelectInfoVO> getCardType() {
        List<SelectInfoVO> selectInfoVOS = new ArrayList<>();

        for (CardType value : CardType.values()) {
            SelectInfoVO selectInfoVO = new SelectInfoVO(value.getName(), value.name());
            selectInfoVOS.add(selectInfoVO);
        }
        return selectInfoVOS;
    }

    public List<SelectInfoVO> getCardInfo(User user) {
        List<SelectInfoVO> selectInfoVOS = new ArrayList<>();
        QueryWrapper<CardInfo> cardInfoQueryWrapper = new QueryWrapper<>();
        cardInfoQueryWrapper.eq("userId",user.getId());
        List<CardInfo> list = cardInfoService.list(cardInfoQueryWrapper);
        for (CardInfo cardInfo : list) {
            selectInfoVOS.add(new SelectInfoVO(cardInfo.getCardName(),cardInfo.getId()+""));
        }
        return selectInfoVOS;
    }
}
