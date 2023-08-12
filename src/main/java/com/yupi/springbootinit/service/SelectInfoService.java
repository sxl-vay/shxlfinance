package com.yupi.springbootinit.service;

import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.model.enums.SelectTypeEnmu;
import com.yupi.springbootinit.model.vo.SelectInfoVO;

import java.util.List;

public interface SelectInfoService {
    List<SelectInfoVO> getInfo(SelectTypeEnmu type, User user);
}
