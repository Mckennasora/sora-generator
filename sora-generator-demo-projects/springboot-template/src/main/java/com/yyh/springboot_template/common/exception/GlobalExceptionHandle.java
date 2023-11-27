package com.yyh.springboot_template.common.exception;


import com.yyh.springboot_template.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author sora
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandle {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        log.error(e.getMessage());
        return Result.error("系统异常，意料之外的错误");
    }

    @ExceptionHandler({SpringbootTemplateException.class})
    @ResponseBody
    public Result fail(SpringbootTemplateException e) {
        log.error(e.getMessage());
        return Result.fail(e.getMessage());
    }

//    @ExceptionHandler({NotLoginException.class})
//    @ResponseBody
//    public Result notLogin(NotLoginException e) {
//        return Result.fail("未登录");
//    }
//
//    @ExceptionHandler({NotRoleException.class})
//    @ResponseBody
//    public Result notLogin(NotRoleException e) {
//        return Result.fail("无权限");
//    }
}


