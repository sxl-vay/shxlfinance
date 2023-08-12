package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.mapper.CardInfoMapper;
import com.yupi.springbootinit.mapper.DepositInfoMapper;
import com.yupi.springbootinit.model.dto.deposit.DepositInfoAddRequest;
import com.yupi.springbootinit.model.dto.deposit.DepositInfoQueryRequest;
import com.yupi.springbootinit.model.entity.DepositInfo;
import com.yupi.springbootinit.model.vo.DepositInfoVO;
import com.yupi.springbootinit.model.vo.echart.PieChartItemVO;
import com.yupi.springbootinit.service.DepositInfoService;
import com.yupi.springbootinit.utils.TimeUtils;
import com.yupi.springbootinit.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 16938
 * @description 针对表【deposit_info】的数据库操作Service实现
 * @createDate 2023-08-05 15:16:02
 */
@Slf4j
@Service
public class DepositInfoServiceImpl extends ServiceImpl<DepositInfoMapper, DepositInfo>
        implements DepositInfoService {

    @Resource
    private DepositInfoMapper mapper;

    @Resource
    private CardInfoMapper cardInfoMapper;
    @Override
    public DepositInfo insertDepositInfo(DepositInfoAddRequest depositInfoAddRequest, Long userid) {
        DepositInfo depositInfo = new DepositInfo();
        BeanUtils.copyProperties(depositInfoAddRequest,depositInfo);

        depositInfo.setUserId(userid);
        depositInfo.setCreateTime(new Date());
        SimpleDateFormat sdf = TimeUtils.getSimpleDateFormat();
        try {
            depositInfo.setStartTime(sdf.parse(depositInfoAddRequest.getStartTime()));
            depositInfo.setEndTime(sdf.parse(depositInfoAddRequest.getEndTime()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        int insert = mapper.insert(depositInfo);

        return depositInfo;
    }

    @Override
    public List<DepositInfoVO> selectByQueryRequest(DepositInfoQueryRequest DepositInfoQueryRequest) {
        return null;
    }

    @Override
    public QueryWrapper<DepositInfo> getQueryWrapper(DepositInfoQueryRequest DepositInfoQueryRequest) {
        return null;
    }

    @Override
    public List<DepositInfoVO> getDepositInfoVO(List<DepositInfo> depositInfos) {
        if (Util.isEmpty(depositInfos)) {
            return new ArrayList<>();
        }
        return depositInfos.stream().map(this::getDepositInfoVO).collect(Collectors.toList());
    }

    @Override
    public DepositInfoVO getDepositInfoVO(DepositInfo depositInfo) {
        if (depositInfo == null) {
            return null;
        }
        DepositInfoVO depositInfoVO = new DepositInfoVO();
        BeanUtils.copyProperties(depositInfo, depositInfoVO);
        depositInfoVO.setCardType(cardInfoMapper.selectById(depositInfo.getCardType()).getCardName());
        return depositInfoVO;
    }

    @Override
    public List<PieChartItemVO> getLastDetails(Long userId) {
        return null;
    }

    @Override
    public List<PieChartItemVO> getLastTypeDetails(Long userId) {
        return null;
    }
}




