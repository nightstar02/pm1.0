package com.peoplemoney.web;

import com.peoplemoney.po.Salary;
import com.peoplemoney.po.SalaryD;
import com.peoplemoney.po.Type;
import com.peoplemoney.po.User;
import com.peoplemoney.service.SalaryService;
import com.peoplemoney.service.TimeService;
import com.peoplemoney.service.TypeService;
import com.peoplemoney.service.UserService;
import com.peoplemoney.util.JsonUtil;
import com.peoplemoney.vo.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/salary")
public class SalaryServlet extends HttpServlet {
   private SalaryService salaryService=  new SalaryService();
   private TypeService typeService=new TypeService();
  private TimeService timeService= new TimeService();


    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收用户行为
        String actionName =request.getParameter("actionName");
        String empee_id = request.getParameter("EMPEE_ID");
        String month_id = request.getParameter("MONTH_ID");

        System.out.println(actionName);
        if ("list".equals(actionName)){
            salaryList(request,response,empee_id,month_id);
        }else if ("addOrUpdate".equals(actionName)){

            addOrUpdate(request,response);
        }else if ("delete".equals(actionName)){
            deleteSalary(request,response);
        }else if ("listd".equals(actionName)){
            salaryList2(request,response);
        }else if ("listdt".equals(actionName)){
            salaryList3(request,response);
        }
    }
    private void salaryList3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Integer qx = user.getQX();

        String time = request.getParameter("MONTH_ID");
        System.out.println(time+"时间");

        if (!time.equals(null)){

            if (qx==0){
                List<SalaryD> salaryDList= salaryService.findAllSalaryDt(time);
                request.setAttribute("salaryDList",salaryDList);
                List<Type> typeList =typeService.findTypeList();
                System.out.println(salaryDList);
                request.setAttribute("typeList",typeList);

                request.setAttribute("changePage","user/list.jsp");
            }




            else if (qx==1){
                String mkt_area_id = user.getMKT_AREA_ID();

                List<SalaryD> salaryDList= salaryService.findAllSalaryDByMktt(mkt_area_id,time);
                request.setAttribute("salaryDList",salaryDList);
                List<Type> typeList =typeService.findTypeList();

                System.out.println(salaryDList);
                request.setAttribute("typeList",typeList);

                request.setAttribute("changePage","user/list.jsp");


            }else if (qx==2){
                String empee_id = user.getEMPEE_ID();
                System.out.println("asjdisalkjd"+empee_id);
                List<SalaryD> salaryDList= salaryService.findAllSalaryDByIDt(empee_id,time);
                request.setAttribute("salaryDList",salaryDList);
                List<Type> typeList =typeService.findTypeList();

                System.out.println(salaryDList);
                request.setAttribute("typeList",typeList);

                request.setAttribute("changePage","user/list.jsp");
            }
        }else {
            if (qx==0){
                List<SalaryD> salaryDList= salaryService.findAllSalaryD();
                request.setAttribute("salaryDList",salaryDList);
                List<Type> typeList =typeService.findTypeList();
                System.out.println("哈哈哈3");
                System.out.println(salaryDList);
                request.setAttribute("typeList",typeList);

                request.setAttribute("changePage","user/list.jsp");
            }else if (qx==1){
                String mkt_area_id = user.getMKT_AREA_ID();

                List<SalaryD> salaryDList= salaryService.findAllSalaryDByMkt(mkt_area_id);
                request.setAttribute("salaryDList",salaryDList);
                List<Type> typeList =typeService.findTypeList();

                System.out.println(salaryDList);
                request.setAttribute("typeList",typeList);

                request.setAttribute("changePage","user/list.jsp");


            }else if (qx==2){
                String empee_id = user.getEMPEE_ID();
                System.out.println("asjdisalkjd"+empee_id);
                List<SalaryD> salaryDList= salaryService.findAllSalaryDByID(empee_id);
                request.setAttribute("salaryDList",salaryDList);
                List<Type> typeList =typeService.findTypeList();

                System.out.println(salaryDList);
                request.setAttribute("typeList",typeList);

                request.setAttribute("changePage","user/list.jsp");
            }
        }

        request.getRequestDispatcher("index.jsp").forward(request,response);

    }

    private void salaryList2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Integer qx = user.getQX();
        System.out.println("哈哈哈2");
        if (qx==0){
           List<SalaryD> salaryDList= salaryService.findAllSalaryD();
            request.setAttribute("salaryDList",salaryDList);
            List<Type> typeList =typeService.findTypeList();
            System.out.println("哈哈哈3");
            System.out.println(salaryDList);
            request.setAttribute("typeList",typeList);

            request.setAttribute("changePage","user/list.jsp");
        }else if (qx==1){
            String mkt_area_id = user.getMKT_AREA_ID();

            List<SalaryD> salaryDList= salaryService.findAllSalaryDByMkt(mkt_area_id);
            request.setAttribute("salaryDList",salaryDList);
            List<Type> typeList =typeService.findTypeList();

            System.out.println(salaryDList);
            request.setAttribute("typeList",typeList);

            request.setAttribute("changePage","user/list.jsp");


        }else if (qx==2){
            String empee_id = user.getEMPEE_ID();
            System.out.println("asjdisalkjd"+empee_id);
            List<SalaryD> salaryDList= salaryService.findAllSalaryDByID(empee_id);
            request.setAttribute("salaryDList",salaryDList);
            List<Type> typeList =typeService.findTypeList();

            System.out.println(salaryDList);
            request.setAttribute("typeList",typeList);

            request.setAttribute("changePage","user/list.jsp");
        }
        request.getRequestDispatcher("index.jsp").forward(request,response);

    }





    private void deleteSalary(HttpServletRequest request, HttpServletResponse response) {
        String sid = request.getParameter("SID");
        System.out.println(sid);
        ResultInfo<Salary> resultInfo =  salaryService.deleteSalary(sid);
        System.out.println(resultInfo);
        JsonUtil.toJson(response,resultInfo);
        //将resultInfo 转换未json，ajax相应
    }

    private void addOrUpdate(HttpServletRequest request, HttpServletResponse response) {
        String sid = request.getParameter("SID");
        String month_id = request.getParameter("MONTH_ID");
        Integer cSelect = Integer.valueOf(request.getParameter("cSelect"));//CID
        String empee_acct = request.getParameter("EMPEE_ACCT");
        String salary = request.getParameter("SALARY");
        String mkt_area_id = request.getParameter("MKT_AREA_ID");
        String cname = request.getParameter("cname");
        System.out.println(cname);
        System.out.println(sid);
        System.out.println(month_id);
        System.out.println(cSelect);
        System.out.println(empee_acct);
        System.out.println(salary);
        System.out.println(mkt_area_id);


        System.out.println("到了addOrUpdate");
        ResultInfo<Integer> resultInfo=salaryService.addOrUpdate(sid,month_id,cSelect,empee_acct,salary,mkt_area_id);
        JsonUtil.toJson(response,resultInfo);

    }










    private void salaryList(HttpServletRequest request, HttpServletResponse response,String empee_id,String month_id) throws ServletException, IOException {

       /* List<Salary> salaryList = salaryService.findAllSalaryByEmpeeId(empee_id);*/
        System.out.println("到查empee——id"+empee_id);
       /* System.out.println(salaryList);
        request.setAttribute("salaryList",salaryList);*/



      /*  User user = (User) request.getSession().getAttribute("user");
        Integer qx = user.getQX();
        String mkt_area_id = user.getMKT_AREA_ID();*/
 /*       if (qx==0){
            List<Salary> salaryList= salaryService.findAllSalary();
            request.setAttribute("salaryList",salaryList);
        }else if (qx==1){
            System.out.println("qx1");
            List<Salary> salaryList= salaryService.findAllSalaryByMktId(mkt_area_id);
            request.setAttribute("salaryList",salaryList);
        }else if (qx==2){
            System.out.println(empee_id);
            List<Salary> salaryList= salaryService.findAllSalaryByEmpeeId(empee_id);
            request.setAttribute("salaryList",salaryList);
        }*/
        List<Type> typeList =typeService.findTypeList();


        List<Salary> salaryList= salaryService.findAllSalaryByEmpeeIdm(empee_id,month_id);
        List<Salary> salaryListx= salaryService.findAllSalaryByEmpeeIdm(empee_id,month_id);
        request.setAttribute("salaryList",salaryList);
        request.setAttribute("salaryListx",salaryListx);
        System.out.println(salaryList+"hsaidh");
        System.out.println(typeList);
        request.setAttribute("typeList",typeList);

        request.setAttribute("changePage","salary/list.jsp");

        request.getRequestDispatcher("index.jsp").forward(request,response);
    }


}
