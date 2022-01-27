package com.peoplemoney.web;

import com.peoplemoney.po.User;
import com.peoplemoney.service.UserService;
import com.peoplemoney.util.Page;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      /*  userList(request,response);*/


        request.setAttribute("changePage","user/list.jsp");
     /*User user = (User) request.getSession().getAttribute("user");*/
        request.getRequestDispatcher("index.jsp").forward(request,response);

    }

/*
    private void userList(HttpServletRequest request, HttpServletResponse response) {
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        User user = (User) request.getSession().getAttribute("user");
        //获取用户的权限
        Integer qx = user.getQX();
        if (qx==0){
            //管理员账号
        }else if (qx==1){
            //部门经理账号
        }else if(qx==2){
            //个人
          Page<User> page = new UserService().findUUserByIdPage(pageNum,pageSize,user.getEMPEE_ID());
        }


*/
/*
        request.setAttribute("page",page);
*//*


    }
*/
}

