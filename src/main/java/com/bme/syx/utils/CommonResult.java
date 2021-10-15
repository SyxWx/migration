package com.bme.syx.utils;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult implements Serializable {

    public CommonResult(boolean success, String msg, Object data) {
        super();
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public static CommonResult success() {
        return CommonResult.success("访问成功", null);
    }

    public static CommonResult success(Object data) {
        return CommonResult.success("访问成功", data);
    }

    public static CommonResult success(String msg) {
        return CommonResult.success(msg, null);
    }

    public static CommonResult success(String msg, Object data) {
        CommonResult result = new CommonResult(true, msg, data);
        result.setCode(ResultCode.SUCCESS);
        return result;
    }

    public static CommonResult fail() {
        return CommonResult.fail(null, ResultCode.ERROR);
    }

    public static CommonResult fail(String msg) {
        return CommonResult.fail(msg, ResultCode.ERROR);
    }

    public static CommonResult fail(String msg, Object data) {
        CommonResult result = new CommonResult(false, msg, data);
        result.setCode(ResultCode.ERROR);
        return result;
    }

    public static CommonResult fail(String msg, Integer code) {
        CommonResult result = new CommonResult(false, msg, null);
        result.setCode(code);
        return result;
    }

    @SuppressWarnings("unchecked")
    public CommonResult data(Object... objects) {
        if (objects != null) {
            if (objects.length == 1) {
                this.data = objects[0];
            } else {
                Map<String, Object> dataMap = new HashMap<String, Object>();
                if (data instanceof Map) {
                    dataMap = (Map<String, Object>) this.data;
                }
                for (int i = 0; i < objects.length / 2; i++) {
                    int index = i * 2;
                    dataMap.put(String.valueOf(objects[index]), objects[index + 1]);
                }
                this.data = dataMap;
            }
        }
        return this;
    }

    private boolean success;
    private Integer code;
    private String msg;
    private Object data;

    public static CommonResult result(boolean checkResult) {
        CommonResult result = new CommonResult();
        result.setCode(ResultCode.SUCCESS);
        result.setSuccess(checkResult);
        return result;
    }

}