package com.yupi.springbootinit.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.springbootinit.annotation.AuthCheck;
import com.yupi.springbootinit.common.BaseResponse;
import com.yupi.springbootinit.common.DeleteRequest;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.common.ResultUtils;
import com.yupi.springbootinit.constant.UserConstant;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.exception.ThrowUtils;
import com.yupi.springbootinit.model.dto.book.BookkeepingAddRequest;
import com.yupi.springbootinit.model.dto.book.BookkeepingQueryRequest;
import com.yupi.springbootinit.model.dto.book.BookkeepingUpdateRequest;
import com.yupi.springbootinit.model.entity.BookkeepingBook;
import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.model.vo.BookkeepingBookVO;
import com.yupi.springbootinit.service.BookkeepingService;
import com.yupi.springbootinit.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookkeepingController {
    @Resource
    private BookkeepingService service;

    @Resource
    private UserService userService;

    @PostMapping("/list/page")
    public BaseResponse<Page<BookkeepingBookVO>> listBookkeepingByPage(@RequestBody BookkeepingQueryRequest queryRequest, HttpServletRequest request) {
        long current = queryRequest.getCurrent();
        long pageSize = queryRequest.getPageSize();
        Page<BookkeepingBook> page = service.page(new Page<>(current, pageSize)
                , service.getQueryWrapper(queryRequest));
        Page<BookkeepingBookVO> voPage = new Page<>(current, pageSize, page.getTotal());
        List<BookkeepingBookVO> voList = service.getBookkeepingVO(page.getRecords());
        voPage.setRecords(voList);
        return ResultUtils.success(voPage);
    }

    @PostMapping("/add")
    public BaseResponse<BookkeepingBookVO> add(@RequestBody BookkeepingAddRequest addRequest, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        BookkeepingBook bookkeepingBook = service.insertBookkeeping(addRequest, loginUser.getId());
        BookkeepingBookVO bookkeepingVO = service.getBookkeepingVO(bookkeepingBook);
        return ResultUtils.success(bookkeepingVO);

    }


    @GetMapping("/list/page")
    public BaseResponse<Page<BookkeepingBookVO>> listBookkeepingByPage(@RequestParam Long current, @RequestParam Long pageSize, HttpServletRequest request) {

        BookkeepingQueryRequest queryRequest = new BookkeepingQueryRequest();
        User loginUser = userService.getLoginUser(request);
        queryRequest.setUserId(loginUser.getId());
        long size = pageSize;
        // 限制爬虫
        //ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<BookkeepingBook> page = service.page(new Page<>(current, pageSize)
                , service.getQueryWrapper(queryRequest));
        Page<BookkeepingBookVO> voPage = new Page<>(current, pageSize, page.getTotal());
        List<BookkeepingBookVO> voList = service.getBookkeepingVO(page.getRecords());
        voPage.setRecords(voList);
        return ResultUtils.success(voPage);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteBookkeepingInfo(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        BookkeepingBook oldInterfaceInfo = service.getById(id);
        ThrowUtils.throwIf(oldInterfaceInfo == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可删除
        if (!oldInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = service.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新（仅管理员）
     *
     * @param updateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateBookkeepingInfo(@RequestBody BookkeepingUpdateRequest updateRequest) {
        if (updateRequest == null || updateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        BookkeepingBook book = new BookkeepingBook();
        BeanUtils.copyProperties(updateRequest, book);
        // 参数校验
        long id = updateRequest.getId();
        // 判断是否存在
        BookkeepingBook old = service.getById(id);
        ThrowUtils.throwIf(old == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = service.updateById(book);
        return ResultUtils.success(result);
    }
}
