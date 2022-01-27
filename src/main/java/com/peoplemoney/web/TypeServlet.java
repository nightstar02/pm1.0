package com.peoplemoney.web;

import com.alibaba.fastjson.JSON;
import com.peoplemoney.po.Type;
import com.peoplemoney.po.User;
import com.peoplemoney.service.TypeService;
import com.peoplemoney.util.JsonUtil;
import com.peoplemoney.vo.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/type")
public class TypeServlet extends HttpServlet {

    private TypeService typeService=new TypeService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //等到用户行为
        String actionName = request.getParameter("actionName");


        //半段行为
        if ("list".equals(actionName)){
            typeList(request,response);
        }else if ("delete".equals(actionName)){
            deleteType(request,response);
        }else if ("addOrUpdate".equals(actionName)){

            addOrUpdate(request,response);
        }

    }

    private void addOrUpdate(HttpServletRequest request, HttpServletResponse response) {
            //获取参数
        String came = request.getParameter("CNAME");
        String cid = request.getParameter("CID");
        String message = request.getParameter("MESSAGE");
        String myDate = request.getParameter("myDate");

        String uptor = request.getParameter("empee_acct");
        String typeType = request.getParameter("typeType");
        /*获取更新操作人*/
        System.out.println(typeType);
        System.out.println("到了addOrUpdate");
        ResultInfo<Integer> resultInfo=typeService.addOrUpdate(cid,came,message,uptor,myDate,typeType);

        JsonUtil.toJson(response,resultInfo);


    }



    /**
     * 删除类型
     * @param request
     * @param response
     */
    private void deleteType(HttpServletRequest request, HttpServletResponse response) {
        //接受参数
        String cid = request.getParameter("CID");
      ResultInfo<Type> resultInfo =  typeService.deleteType(cid);

        JsonUtil.toJson(response,resultInfo);
      //将resultInfo 转换未json，ajax相应
    /*    response.setContentType("application/json;charset=UTF-8");
        //得到字符输出溜
        PrintWriter out = response.getWriter();

        String json = JSON.toJSONString(resultInfo);

        out.write(json);

        out.close();*/


    }

    /**
     * 查询所有的类型
     * @param request
     * @param response
     */
    private void typeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Type> typeList = typeService.findTypeList();
        request.setAttribute("typeList",typeList);

        request.setAttribute("changePage","type/list.jsp");

        System.out.println(typeList);
        request.getRequestDispatcher("index.jsp").forward(request,response);

    }
}
