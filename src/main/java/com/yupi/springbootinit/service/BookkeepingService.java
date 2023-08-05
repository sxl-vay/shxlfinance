package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.springbootinit.model.dto.book.BookkeepingAddRequest;
import com.yupi.springbootinit.model.dto.book.BookkeepingQueryRequest;
import com.yupi.springbootinit.model.dto.book.BookkeepingUpdateRequest;
import com.yupi.springbootinit.model.dto.user.UserQueryRequest;
import com.yupi.springbootinit.model.entity.BookkeepingBook;
import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.model.vo.BookkeepingBookVO;
import com.yupi.springbootinit.model.vo.echart.PieChartItemVO;

import java.math.BigDecimal;
import java.util.List;

public interface BookkeepingService extends IService<BookkeepingBook>{
    BookkeepingBook insertBookkeeping(BookkeepingAddRequest bookkeepingBook,Long userid);

    List<BookkeepingBookVO> selectByQueryRequest(BookkeepingQueryRequest bookkeepingQueryRequest);

    QueryWrapper<BookkeepingBook> getQueryWrapper(BookkeepingQueryRequest bookkeepingQueryRequest);

    List<BookkeepingBookVO> getBookkeepingVO(List<BookkeepingBook> bookkeepingBooks);
    BookkeepingBookVO getBookkeepingVO(BookkeepingBook bookkeepingBooks);

    BigDecimal getTotal(BookkeepingBook bookkeepingBook);
    BigDecimal getTotal(BookkeepingBookVO bookkeepingBook);

    /**
     *
     * @param userId
     * @return
     */
    List<PieChartItemVO> getLastDetails(Long userId);

    List<PieChartItemVO> getLastTypeDetails(Long userId);
}
