package com.lilikai.app.core;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.lilikai.app.utils.AppUtils.render;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result genSuccessResult() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static Result genSuccessResult(Object data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result genFailResult(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }


    /**
     * ajax返回json数据
     *
     * @param response
     * @param data     要返回的数据
     */
    public static String ajaxReturn(HttpServletResponse response, Object data) {
        render(response, JSON.toJSONString(data), "json");
        return null;
    }

    /**
     * ajax返回json数据，参数重载
     *
     * @param response
     * @param data     要返回的数据
     * @param info     返回的信息
     * @param status   返回的状态
     * @return
     */
    public static String ajaxReturn(HttpServletResponse response, Object data, String info, int status) {

        Map<String, Object> jsonData = new HashMap<String, Object>();
        jsonData.put("data", data);
        jsonData.put("info", info);
        jsonData.put("status", status);

        render(response, JSON.toJSONString(jsonData), "json");
        return null;
    }
}
