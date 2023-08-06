package com.yupi.springbootinit.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.springbootinit.model.dto.deposit.DepositInfoAddRequest;
import com.yupi.springbootinit.model.dto.deposit.DepositInfoQueryRequest;
import com.yupi.springbootinit.model.entity.DepositInfo;

import com.yupi.springbootinit.model.vo.DepositInfoVO;
import com.yupi.springbootinit.model.vo.echart.PieChartItemVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 16938
 * @description 针对表【deposit_info】的数据库操作Service
 * @createDate 2023-08-05 15:16:02
 */
public interface DepositInfoService extends IService<DepositInfo> {
    DepositInfo insertDepositInfo(DepositInfoAddRequest depositInfoAddRequest, Long userid);

    List<DepositInfoVO> selectByQueryRequest(DepositInfoQueryRequest DepositInfoQueryRequest);

    QueryWrapper<DepositInfo> getQueryWrapper(DepositInfoQueryRequest DepositInfoQueryRequest);

    List<DepositInfoVO> getDepositInfoVO(List<DepositInfo> DepositInfos);
    DepositInfoVO getDepositInfoVO(DepositInfo DepositInfos);

    /**
     *
     * @param userId
     * @return
     */
    List<PieChartItemVO> getLastDetails(Long userId);

    List<PieChartItemVO> getLastTypeDetails(Long userId);

}
