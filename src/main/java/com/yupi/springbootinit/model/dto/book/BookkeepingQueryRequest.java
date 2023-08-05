package com.yupi.springbootinit.model.dto.book;

import com.yupi.springbootinit.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookkeepingQueryRequest extends PageRequest implements Serializable {
    private String startTime;
    private String endTime;

    private Long UserId;

    private Long id;

}
