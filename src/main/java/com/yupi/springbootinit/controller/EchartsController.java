package com.yupi.springbootinit.controller;

import com.yupi.springbootinit.common.BaseResponse;
import com.yupi.springbootinit.common.ResultUtils;
import com.yupi.springbootinit.model.dto.book.BookkeepingQueryRequest;
import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.model.vo.BookkeepingBookVO;
import com.yupi.springbootinit.model.vo.echart.LineChartVO;
import com.yupi.springbootinit.model.vo.echart.PieChartItemVO;
import com.yupi.springbootinit.service.BookkeepingService;
import com.yupi.springbootinit.service.UserService;
import com.yupi.springbootinit.utils.TimeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @Resource
    private BookkeepingService bookkeepingService;
    @Resource
    private UserService userService;
    @GetMapping("/lineChart")
    public BaseResponse<LineChartVO> getLineChart(HttpServletRequest request) {
        //获取折线图信息，这里需要优化，将逻辑写入到service层
        User loginUser = userService.getLoginUser(request);
        BookkeepingQueryRequest queryRequest = new BookkeepingQueryRequest();
        LocalDate today = LocalDate.now();
        LocalDate previousYear = today.minus(3, ChronoUnit.MONTHS);
        Date date = Date.from(previousYear.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat sft = new SimpleDateFormat("YYYY-MM");
        String startTime = sft.format(date);
        queryRequest.setStartTime(startTime);
        queryRequest.setUserId(loginUser.getId());
        List<BookkeepingBookVO> bookkeepingBookVOS = bookkeepingService.selectByQueryRequest(queryRequest);
        List<String> times = new ArrayList<>();
        List<BigDecimal> totals = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = TimeUtils.getSimpleDateFormat();
        bookkeepingBookVOS.stream().forEach(s->{
            Date createTime = s.getCreateTime();
            String time = simpleDateFormat.format(createTime);
            times.add(time);
            BigDecimal total = bookkeepingService.getTotal(s);
            totals.add(total);
        });
        LineChartVO lineChartVO = new LineChartVO(times,totals);
        return ResultUtils.success(lineChartVO);
    }

    @GetMapping("/pieChart")
    public BaseResponse<List<PieChartItemVO>> getPieChart(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        return ResultUtils.success(bookkeepingService.getLastDetails(loginUser.getId()));
    }

    @GetMapping("/pieTypeChart")
    public BaseResponse<List<PieChartItemVO>> getPieTypeChart(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        return ResultUtils.success(bookkeepingService.getLastTypeDetails(loginUser.getId()));
    }


}

