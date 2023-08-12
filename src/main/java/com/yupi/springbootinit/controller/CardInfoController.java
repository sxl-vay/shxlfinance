package com.yupi.springbootinit.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.springbootinit.annotation.AuthCheck;
import com.yupi.springbootinit.common.*;
import com.yupi.springbootinit.constant.UserConstant;
import com.yupi.springbootinit.model.dto.card.CardInfoAddOrUpdateRequest;
import com.yupi.springbootinit.model.entity.CardInfo;
import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.service.CardInfoService;
import com.yupi.springbootinit.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/card")
public class CardInfoController {
    @Resource
    private CardInfoService service;
    @Resource
    private UserService userService;
    @PostMapping("/list/page")
    public BaseResponse<Page<CardInfo>> listCardInfoByPage(@RequestBody PageRequest queryRequest, HttpServletRequest request) {
        QueryWrapper<CardInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId",userService.getLoginUser(request).getId());
        Page<CardInfo> page = service.page(new Page<>(queryRequest.getCurrent(),queryRequest.getPageSize()), queryWrapper);
        return ResultUtils.success(page);
    }

    @PostMapping("/add")
    public BaseResponse<CardInfo> add(@RequestBody CardInfoAddOrUpdateRequest cardInfoRequest, HttpServletRequest request) {
        cardInfoRequest.setUserId(userService.getLoginUser(request).getId());
        boolean save = service.insert(cardInfoRequest);
        if (save) {
            return ResultUtils.success(service.getCardInfoByRequest(cardInfoRequest));
        }
        return ResultUtils.error(ErrorCode.PARAMS_ERROR);
    }


    @GetMapping("/list/page")
    public BaseResponse<Page<CardInfo>> listCardInfoByPage(@RequestParam Long current, @RequestParam Long pageSize, HttpServletRequest request) {
        QueryWrapper<CardInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId",userService.getLoginUser(request).getId());
        Page<CardInfo> page = service.page(new Page<>(current,pageSize), queryWrapper);
        return ResultUtils.success(page);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteCardInfoInfo(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        return ResultUtils.success(service.removeById(deleteRequest.getId()));
    }

    /**
     * 更新（仅管理员）
     *
     * @param updateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateCardInfoInfo(@RequestBody CardInfoAddOrUpdateRequest updateRequest) {
        CardInfo cardInfo = new CardInfo();
        cardInfo.setId(updateRequest.getId());
        cardInfo.setCardName(updateRequest.getCardName());
        cardInfo.setCardNumber(updateRequest.getCardNumber());;
        cardInfo.setCardType(updateRequest.getCardTypeSelect());
        return ResultUtils.success(service.updateById(cardInfo));
    }

}
