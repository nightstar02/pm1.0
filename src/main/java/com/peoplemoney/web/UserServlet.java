package com.peoplemoney.web;


import com.peoplemoney.po.User;
import com.peoplemoney.po.months;
import com.peoplemoney.po.years;
import com.peoplemoney.service.TimeService;
import com.peoplemoney.service.UserService;
import com.peoplemoney.vo.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private UserService userService=new UserService();
    private TimeService timeService= new TimeService();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //接收用户行为
        String actionName =req.getParameter("actionName");
        if ("login".equals(actionName)){

            //用户登录
                userLogin(req,resp);
        }else if("logout".equals(actionName)){
                userLogOut(req,resp);
        }else if ("userUpdatePassword".equals(actionName)){
            System.out.println("参观参观参观参观");
            userUpdatePassword(req,resp);
        }/*else if ("list".equals(actionName)){

            userList(req,resp);

            System.out.println("查询用户");
        }*/
    }



    /**
     * 修改密码
     */

        private  void userUpdatePassword (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            request.setAttribute("changePage","user/updatePassword.jsp");

            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    /**
     * 用户推出
     *
     */
    private void userLogOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().invalidate();

        Cookie cookie = new Cookie("user", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        response.sendRedirect("login.jsp");
    }


    /**
     * 用户登录
     * @param request
     * @param response
     */
    private void userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");

        //调用service
        ResultInfo<User> resultInfo = userService.userLogin(userName, passWord);
       List<years> years= timeService.findAllYears();
        System.out.println(years);
       List<months> months= timeService.findAllmonths();
        System.out.println(months);

       request.getSession().setAttribute("years",years);
       request.getSession().setAttribute("months",months);
        //
        if (resultInfo.getCode()== 1) //成功
        {


            System.out.println("成功");
            //用户信息存入session
            request.getSession().setAttribute("user",resultInfo.getResult());


            //判断是否记住密码
            String rem = request.getParameter("rem");
            if ("1".equals(rem)){
                //得到cookie
                Cookie cookie = new Cookie("user", userName + "-" + passWord);
                //设置时间
                cookie.setMaxAge(3*24*60*60);

                //响应客户端
                response.addCookie(cookie);

            }
            else {
                Cookie cookie = new Cookie("user", null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);

            }



           /*if (resultInfo.getResult().getQX().equals(0)){
               response.sendRedirect("index.jsp");
           }else {
               response.sendRedirect("indexP.jsp");
           }*/
            response.sendRedirect("index.jsp");

        }else {
            //失败

            request.setAttribute("resultInfo",resultInfo);

            request.getRequestDispatcher("login.jsp").forward(request,response);
        }

    }
}
