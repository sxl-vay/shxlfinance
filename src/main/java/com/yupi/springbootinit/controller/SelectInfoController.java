package com.yupi.springbootinit.controller;


import com.yupi.springbootinit.common.BaseResponse;
import com.yupi.springbootinit.common.ResultUtils;
import com.yupi.springbootinit.model.enums.CardType;
import com.yupi.springbootinit.model.enums.SelectTypeEnmu;
import com.yupi.springbootinit.model.vo.SelectInfoVO;
import com.yupi.springbootinit.service.SelectInfoService;
import com.yupi.springbootinit.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/selectinfo")
public class SelectInfoController {
    @Resource
    private SelectInfoService service;
    @Resource
    private UserService userService;
    @GetMapping("/getinfo")
    public BaseResponse<List<SelectInfoVO>> getSelectInfo(@RequestParam String typeNumber, HttpServletRequest request) {
        return ResultUtils.success(service.getInfo(SelectTypeEnmu.valueOf(typeNumber),userService.getLoginUser(request)));
    }


}
