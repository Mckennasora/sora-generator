package com.yyh.springboot_template.common.exception;


import com.yyh.springboot_template.common.result.ResultCodeEnum;
import lombok.Data;
import lombok.ToString;

/**
 * 自定义异常
 *
 * @author sora
 */
@Data
@ToString
public class SpringbootTemplateException extends RuntimeException {
    private String code;

    public SpringbootTemplateException(String code, String message) {
        super(message);
        this.code = code;
    }

    public SpringbootTemplateException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    public SpringbootTemplateException(ResultCodeEnum resultCodeEnum, String message) {
        super(message);
        this.code = resultCodeEnum.getCode();
    }
}
