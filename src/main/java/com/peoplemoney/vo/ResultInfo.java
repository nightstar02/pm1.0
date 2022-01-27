package com.peoplemoney.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 封装返回的对象
 * 状态码
 *      成功=1 失败=0
 *      提示信息
 *      返回对象（字符串、javabean、集合、map）
 */
@Getter
@Setter
public class ResultInfo<T> {

    private Integer code;//状态码 成功1 失败0
    private String msg;//返回信息
    private T result ;//返回对象

    @Override
    public String toString() {
        return "ResultInfo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
