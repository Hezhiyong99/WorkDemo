package com.bdqn.common.exception;

import com.bdqn.common.constants.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "全局异常")
public class KetaiException extends RuntimeException {
    @ApiModelProperty(value = "状态码")
    private Integer code;

    /**
     * 接受状态码和消息
     *
     * @param code
     * @param message
     */
    public KetaiException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 接收枚举类型
     *
     * @param resultCodeEnum
     */
    public KetaiException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "KetaiException{" +
                "message=" + getMessage() +
                ", code=" + code +
                '}';
    }

}
