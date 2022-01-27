package com.peoplemoney.filter;

import cn.hutool.crypto.digest.DigestUtil;
import com.peoplemoney.po.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 非法拦截的
 *
 *
 */
@WebFilter("/*")
public class LoginAccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //基于http
        //基于http
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response =(HttpServletResponse) servletResponse;

        //1.得到访问路径
        String path = request.getRequestURI();//项目路径/资源路径

        //指定放心页面
        if (path.contains("/login.jsp")){
            filterChain.doFilter(request,response);
            return;
        }


        if (path.contains("/static")){
            filterChain.doFilter(request,response);
            return;
        }

        if (path.contains("/user")){
            String actionName = request.getParameter("actionName");
            if ("login".equals(actionName)){
                filterChain.doFilter(request,response);
                return;
            }
        }



        //判断登录状态
        //获取session中的user对象

        User user = (User) request.getSession().getAttribute("user");

        if (user !=null){
            filterChain.doFilter(request,response);
            return;
        }


        //用户免登录

        Cookie[] cookies = request.getCookies();

        if (cookies !=null && cookies.length>0) {

            for (Cookie cookie : cookies) {

                if ("user".equals(cookie.getName())) {

                    String value = cookie.getValue();
                    System.out.println(value);
                    String[] val = value.split("-");
                    String userName = val[0];
                    String passWord = val[1];
                    String s = DigestUtil.md5Hex(passWord);
                    System.out.println(s+"加密后的密码");

                    String url = "user?actionName=login&userName="+userName+"&passWord="+passWord;
                    System.out.println("重定向地址"+url);
                    request.getRequestDispatcher(url).forward(request, response);

                    //request.getRequestDispatcher(url).forward(request, response);
                    return;
                }


            }
        }

        //拦截请求
        response.sendRedirect("login.jsp");



    }
}
