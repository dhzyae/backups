package com.mouse.common.config.exception;

import com.mouse.common.result.ResultCodeEnum;
import lombok.Data;
import lombok.ToString;

@Data
public class GuiguException extends RuntimeException {

    private Integer code;
    private String msg;

    public GuiguException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public GuiguException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "GuiguException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
