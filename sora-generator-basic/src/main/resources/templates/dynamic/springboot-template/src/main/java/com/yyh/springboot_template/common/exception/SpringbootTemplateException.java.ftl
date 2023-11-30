package ${groupId}.${projectNameUnderLine}.common.exception;


import ${groupId}.${projectNameUnderLine}.common.result.ResultCodeEnum;
import lombok.Data;
import lombok.ToString;

/**
 * 自定义异常
 *
 * @author ${author}
 */
@Data
@ToString
public class ${projectNameBigCamel}Exception extends RuntimeException {
    private String code;

    public ${projectNameBigCamel}Exception(String code, String message) {
        super(message);
        this.code = code;
    }

    public ${projectNameBigCamel}Exception(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    public ${projectNameBigCamel}Exception(ResultCodeEnum resultCodeEnum, String message) {
        super(message);
        this.code = resultCodeEnum.getCode();
    }
}
