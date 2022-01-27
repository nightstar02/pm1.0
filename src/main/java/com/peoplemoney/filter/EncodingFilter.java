package com.peoplemoney.filter;

import cn.hutool.core.util.StrUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 请求乱码原因
 *
 * 服务器默认的解决编码为ISO-8859-1,不支持中午
 *
 *
 */
@WebFilter("/*")//过滤所有资源
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //基于http
       HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response =(HttpServletResponse) servletResponse;

        //处理post请求
        request.setCharacterEncoding("UTF-8");

        //处理get|post请求


        String method = request.getMethod();

        if ("GET".equalsIgnoreCase(method)){

            String serverInfo = request.getServletContext().getServerInfo();//获取服务器版本

            //通过截取字符串得到具体的版本号
            String version = serverInfo.substring(serverInfo.lastIndexOf("/") + 1, serverInfo.indexOf("."));
            if (version != null && Integer.parseInt(version)<8){
                MyWapper myRequest=new MyWapper(request);
                //放行
                filterChain.doFilter(myRequest,response);
                return;
            }

        }

        filterChain.doFilter(request,response);

    }
    /**
     * 定义内部类
     */
    class MyWapper extends HttpServletRequestWrapper{
        //定义成员变量
        private  HttpServletRequest request;
        public  MyWapper(HttpServletRequest request){
            super(request);
            this.request=request;
        }

        @Override
        public String getParameter(String name) {
            String value = request.getParameter(name);
            if (StrUtil.isBlank(value)){
                return value;
            }
            try{
                value =new String(value.getBytes("ISO-8859-1"),"UTF-8");
            }catch (Exception e){
                e.printStackTrace();
            }
            return value;

        }
    }

}
