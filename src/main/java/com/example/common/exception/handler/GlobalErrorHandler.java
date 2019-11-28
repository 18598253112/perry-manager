package com.example.common.exception.handler;

import com.example.common.entity.Result;
import com.example.common.exception.CommonException;
import com.example.common.exception.EntityExistException;
import com.example.common.utils.ResultUtils;
import com.example.common.utils.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

import static org.springframework.http.HttpStatus.*;

/**
 * @author wangqin
 * @version 1.0
 * @date 2019/11/27 16:58
 */
@Slf4j
@RestControllerAdvice
public class GlobalErrorHandler {

    private final static String DEFAULT_ERROR_VIEW = "error";//错误信息页

    /**
     * 处理自定义异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(value = CommonException.class)
    public Result handleException(CommonException e, HttpServletRequest request){
        //打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return ResultUtils.error(e.getCode(), e.getMsg());
    }

    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public Result handleException(Throwable e){
        //打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return ResultUtils.error(Integer.toString(BAD_REQUEST.value()) + "" ,e.getMessage());
    }

    /**
     * 处理 EntityNotFound
     * @param e
     * @return
     */
    @ExceptionHandler(value = EntityNotFoundException.class)
    public Result entityNotFoundException(EntityNotFoundException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return ResultUtils.error(Integer.toString(NOT_FOUND.value()) + "" ,e.getMessage());
    }

    /**
     * 处理 接口无权访问异常AccessDeniedException
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public Result handleAccessDeniedException(AccessDeniedException e){
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return ResultUtils.error(Integer.toString(FORBIDDEN.value()) + "" ,e.getMessage());
    }

    /**
     * 处理 EntityExist
     * @param e
     * @return
     */
    @ExceptionHandler(value = EntityExistException.class)
    public Result entityExistException(EntityExistException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return ResultUtils.error(Integer.toString(BAD_REQUEST.value()) + "" ,e.getMessage());
    }


    /**
     * 处理所有接口数据验证异常
     * @param e
     * @returns
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        String[] str = e.getBindingResult().getAllErrors().get(0).getCodes()[1].split("\\.");
        StringBuffer msg = new StringBuffer(str[1]+":");
        msg.append(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return ResultUtils.error(Integer.toString(BAD_REQUEST.value()) + "" ,e.getMessage());
    }




}
