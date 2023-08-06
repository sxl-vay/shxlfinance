package com.yupi.springbootinit.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.springbootinit.annotation.AuthCheck;
import com.yupi.springbootinit.common.BaseResponse;
import com.yupi.springbootinit.common.DeleteRequest;
import com.yupi.springbootinit.common.ResultUtils;
import com.yupi.springbootinit.constant.UserConstant;
import com.yupi.springbootinit.model.dto.deposit.DepositInfoAddRequest;
import com.yupi.springbootinit.model.dto.deposit.DepositInfoQueryRequest;
import com.yupi.springbootinit.model.dto.deposit.DepositInfoUpdateRequest;
import com.yupi.springbootinit.model.entity.BookkeepingBook;
import com.yupi.springbootinit.model.entity.DepositInfo;
import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.model.vo.BookkeepingBookVO;
import com.yupi.springbootinit.model.vo.DepositInfoVO;
import com.yupi.springbootinit.service.DepositInfoService;
import com.yupi.springbootinit.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/deposit")
public class DepositInfoController {
    
    @Resource
    private DepositInfoService service;

    @Resource
    private UserService userService;
    
    @PostMapping("/list/page")
    public BaseResponse<Page<DepositInfoVO>> listDepositInfoByPage(@RequestBody DepositInfoQueryRequest queryRequest, HttpServletRequest request) {
        long current = queryRequest.getCurrent();
        long pageSize = queryRequest.getPageSize();
        Page<DepositInfo> page = service.page(new Page<>(current, pageSize)
                , service.getQueryWrapper(queryRequest));
        Page<DepositInfoVO> voPage = new Page<>(current, pageSize, page.getTotal());
        List<DepositInfoVO> depositInfoVO = service.getDepositInfoVO(page.getRecords());
        voPage.setRecords(depositInfoVO);
        return ResultUtils.success(voPage);
    }

    @PostMapping("/add")
    public BaseResponse<DepositInfoVO> add(@RequestBody DepositInfoAddRequest addRequest, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        DepositInfoVO depositInfoVO = new DepositInfoVO();
        DepositInfo depositInfo = service.insertDepositInfo(addRequest,loginUser.getId());
        BeanUtils.copyProperties(depositInfo,depositInfoVO);
        return ResultUtils.success(depositInfoVO);

    }


    @GetMapping("/list/page")
    public BaseResponse<Page<DepositInfoVO>> listDepositInfoByPage(@RequestParam Long current, @RequestParam Long pageSize, HttpServletRequest request) {

        return ResultUtils.success(new Page<>());
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteDepositInfoInfo(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {

        return ResultUtils.success(false);
    }

    /**
     * 更新（仅管理员）
     *
     * @param updateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateDepositInfoInfo(@RequestBody DepositInfoUpdateRequest updateRequest) {
        return ResultUtils.success(false);
    }

}
