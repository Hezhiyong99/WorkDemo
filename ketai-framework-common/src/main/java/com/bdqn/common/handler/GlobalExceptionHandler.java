package com.bdqn.common.handler;

import com.bdqn.common.constants.ResultCodeEnum;
import com.bdqn.common.exception.KetaiException;
import com.bdqn.common.util.ExceptionUtil;
import com.bdqn.common.vo.R;
import com.fasterxml.jackson.core.JsonParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        //log.error(e.getMessage());
        log.error(ExceptionUtil.getMessage(e));
        return R.error();
    }

    /*sql语法错误*/
    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public R error(BadSqlGrammarException e) {
        //log.error(e.getMessage());
        log.error(ExceptionUtil.getMessage(e));
        return R.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }

    /*json解析异常*/
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public R error(JsonParseException e) {
        //log.error(e.getMessage());
        log.error(ExceptionUtil.getMessage(e));
        return R.setResult(ResultCodeEnum.JSON_PARSE_ERROR);
    }

    @ExceptionHandler(KetaiException.class)
    @ResponseBody
    public R error(KetaiException e) {
        //log.error(e.getMessage());
        log.error(ExceptionUtil.getMessage(e));
        return R.error().message(e.getMessage()).code(e.getCode());
    }

}
