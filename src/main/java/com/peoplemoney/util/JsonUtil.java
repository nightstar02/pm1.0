package com.peoplemoney.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 将对象转换未json的字符床，响应给ajax
 */
public class JsonUtil {
    public static void toJson(HttpServletResponse response,Object result){
        try {
            response.setContentType("application/json;charset=UTF-8");
            //得到字符输出溜
            PrintWriter out = response.getWriter();

            String json = JSON.toJSONString(result);

            out.write(json);

            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
