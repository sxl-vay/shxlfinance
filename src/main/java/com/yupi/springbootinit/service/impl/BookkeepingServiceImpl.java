package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.annotation.TotalAdd;
import com.yupi.springbootinit.annotation.TotalSubtract;
import com.yupi.springbootinit.constant.CommonConstant;
import com.yupi.springbootinit.mapper.BookkeepingBookMapper;
import com.yupi.springbootinit.model.dto.book.BookkeepingAddRequest;
import com.yupi.springbootinit.model.dto.book.BookkeepingQueryRequest;
import com.yupi.springbootinit.model.entity.BookkeepingBook;
import com.yupi.springbootinit.model.enums.BookkeepingTypeEnum;
import com.yupi.springbootinit.model.enums.FundTypeEnum;
import com.yupi.springbootinit.model.vo.BookkeepingBookVO;
import com.yupi.springbootinit.model.vo.echart.PieChartItemVO;
import com.yupi.springbootinit.service.BookkeepingService;
import com.yupi.springbootinit.utils.SqlUtils;
import com.yupi.springbootinit.utils.TimeUtils;
import com.yupi.springbootinit.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookkeepingServiceImpl extends ServiceImpl<BookkeepingBookMapper, BookkeepingBook> implements BookkeepingService {
    @Resource
    private BookkeepingBookMapper bookMapper;

    @Override
    public BookkeepingBook insertBookkeeping(BookkeepingAddRequest bookAdd, Long userId) {
        if (bookAdd == null) {
            return null;
        }
        BookkeepingBook bookkeepingBook = new BookkeepingBook();
        BeanUtils.copyProperties(bookAdd, bookkeepingBook);
        bookkeepingBook.setUserId(userId);
        bookkeepingBook.setDeleteType(0);
        String createTime = bookAdd.getCreateTime();
        if (createTime == null) {
            bookkeepingBook.setCreateTime(new Date());
        } else {
            SimpleDateFormat simpleDateFormat = TimeUtils.getSimpleDateFormat();
            try {
                Date parse = simpleDateFormat.parse(createTime);
                bookkeepingBook.setCreateTime(parse);
            } catch (ParseException e) {
                bookkeepingBook.setCreateTime(new Date());
                log.error("时间格式化异常");
            }
        }
        int insert = bookMapper.insert(bookkeepingBook);
        if (insert > 0) {
            return bookkeepingBook;
        }
        return null;
    }

    @Override
    public List<BookkeepingBookVO> selectByQueryRequest(BookkeepingQueryRequest bookkeepingQueryRequest) {
        QueryWrapper<BookkeepingBook> queryWrapper = getQueryWrapper(bookkeepingQueryRequest);
        List<BookkeepingBook> bookkeepingBooks = bookMapper.selectList(queryWrapper);
        return getBookkeepingVO(bookkeepingBooks);
    }

    @Override
    public QueryWrapper<BookkeepingBook> getQueryWrapper(BookkeepingQueryRequest request) {
        QueryWrapper<BookkeepingBook> wrapper = new QueryWrapper<>();
        Long id = request.getId();
        Long userId = request.getUserId();
        String startTime = request.getStartTime();
        String endTime = request.getEndTime();
        //设置查询条件
        wrapper.ge(startTime != null, "createTime", startTime);
        wrapper.le(endTime != null, "createTime", endTime);
        wrapper.eq(id != null, "id", id);
        wrapper.eq(userId != null, "userId", userId);
        //设置排序字段
        String sortField = request.getSortField();
        String sortOrder = request.getSortOrder();
        wrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return wrapper;
    }

    @Override
    public List<BookkeepingBookVO> getBookkeepingVO(List<BookkeepingBook> bookkeepingBooks) {
        if (CollectionUtils.isEmpty(bookkeepingBooks)) {
            return new ArrayList<>();
        }
        return bookkeepingBooks.stream().map(this::getBookkeepingVO).collect(Collectors.toList());
    }

    @Override
    public BookkeepingBookVO getBookkeepingVO(BookkeepingBook bookkeepingBook) {
        if (bookkeepingBook == null) {
            return null;
        }
        BookkeepingBookVO bookkeepingBookVO = new BookkeepingBookVO();
        BeanUtils.copyProperties(bookkeepingBook, bookkeepingBookVO);
        BigDecimal total = getTotal(bookkeepingBook);
        bookkeepingBookVO.setTotal(total);
        return bookkeepingBookVO;
    }

    /**
     * 根据数据库模型对象 bookkeepingBook 通过反射的方式获取所有的分类金额字段进行统计
     *
     * @param bookkeepingBook
     * @return
     */
    public BigDecimal getTotal(BookkeepingBook bookkeepingBook) {
        return getTotalByObj(bookkeepingBook);
    }

    public BigDecimal getTotal(BookkeepingBookVO bookkeepingBook) {
        return getTotalByObj(bookkeepingBook);
    }

    /**
     * 根据BookkeepingBook类中的属性，通过注解和反射获取传入的模型（bookModal）中数据的总和<br>
     * 需要注意的事：传入的模型 bookModal 和 BookkeepingBook模型 需要统计的向的属性名必须完全相同，并且getter方法相同
     * @param bookModal
     * @return
     */
    public BigDecimal getTotalByObj(Object bookModal) {
        Class<BookkeepingBook> bookClass = BookkeepingBook.class;
        Field[] fields = bookClass.getDeclaredFields();
        BigDecimal total = new BigDecimal(0);
        for (Field field : fields) {
            TotalAdd totalAdd = field.getAnnotation(TotalAdd.class);
            TotalSubtract totalSubtract = field.getAnnotation(TotalSubtract.class);
            try {
                if (totalAdd != null) {
                    Method method = bookModal.getClass().getMethod("get" + Util.captureName(field.getName()));
                    BigDecimal number = (BigDecimal) method.invoke(bookModal);
                    total = total.add(number);
                }
                if (totalSubtract != null) {
                    Method method = bookModal.getClass().getMethod("get" + Util.captureName(field.getName()));
                    BigDecimal number = (BigDecimal) method.invoke(bookModal);
                    if (number == null) continue;
                    total = total.subtract(number);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
        return total;
    }

    /**
     * 获取最后一次记录的信息信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<PieChartItemVO> getLastDetails(Long userId) {
        BookkeepingQueryRequest queryRequest = new BookkeepingQueryRequest();
        queryRequest.setUserId(userId);
        queryRequest.setSortField("createTime");
        queryRequest.setSortOrder(CommonConstant.SORT_ORDER_DESC);
        List<BookkeepingBookVO> bookkeepingBookVOS = selectByQueryRequest(queryRequest);
        List<PieChartItemVO> itemVOS = new ArrayList<>();
        if (bookkeepingBookVOS.size() > 0) {
            BookkeepingBookVO bookkeepingBookVO = bookkeepingBookVOS.get(0);
            BigDecimal fund = bookkeepingBookVO.getFund();
            Class bookClass = bookkeepingBookVO.getClass();
            Method[] methods = bookClass.getMethods();
            for (BookkeepingTypeEnum typeEnum : BookkeepingTypeEnum.values()) {
                try {
                    Method method = bookClass.getMethod("get" + Util.captureName(typeEnum.name()));
                    BigDecimal invoke = (BigDecimal) method.invoke(bookkeepingBookVO);
                    itemVOS.add(new PieChartItemVO(invoke, typeEnum.getDes()));
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return itemVOS;
    }

    @Override
    public List<PieChartItemVO> getLastTypeDetails(Long userId) {
        List<PieChartItemVO> itemVOS = new ArrayList<>();
        BookkeepingQueryRequest queryRequest = new BookkeepingQueryRequest();
        queryRequest.setUserId(userId);
        queryRequest.setSortField("createTime");
        queryRequest.setSortOrder(CommonConstant.SORT_ORDER_DESC);
        List<BookkeepingBookVO> bookkeepingBookVOS = selectByQueryRequest(queryRequest);
        Map<FundTypeEnum, BigDecimal> fundTypeMap = new HashMap<>();
        if (bookkeepingBookVOS.size() > 0) {
            BookkeepingBookVO bookkeepingBookVO = bookkeepingBookVOS.get(0);
            Class bookClass = bookkeepingBookVO.getClass();
            for (BookkeepingTypeEnum typeEnum : BookkeepingTypeEnum.values()) {
                try {
                    Method method = bookClass.getMethod("get" + Util.captureName(typeEnum.name()));
                    BigDecimal number = (BigDecimal) method.invoke(bookkeepingBookVO);
                    FundTypeEnum fundTypeEnum = typeEnum.getTypeEnum();
                    if (fundTypeMap.get(fundTypeEnum) == null) {
                        fundTypeMap.put(fundTypeEnum, number);
                    } else {
                        BigDecimal data = fundTypeMap.get(fundTypeEnum);
                        fundTypeMap.put(fundTypeEnum, number.add(data));
                    }
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            for (Map.Entry<FundTypeEnum, BigDecimal> entry : fundTypeMap.entrySet()) {
                FundTypeEnum key = entry.getKey();
                itemVOS.add(new PieChartItemVO(entry.getValue(), key.getDes()));
            }
        }
        return itemVOS;
    }
}
