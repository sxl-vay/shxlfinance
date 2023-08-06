package com.yupi.springbootinit.model.dto.deposit;

import com.yupi.springbootinit.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@EqualsAndHashCode(callSuper = true)
@Data
public class DepositInfoQueryRequest extends PageRequest implements Serializable {

    private String startTime;
    private String endTime;

    private Long UserId;

    private Long id;
}
