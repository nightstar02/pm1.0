package com.peoplemoney.web;

import com.peoplemoney.po.Salary;
import com.peoplemoney.po.SalaryD;
import com.peoplemoney.po.Type;
import com.peoplemoney.service.SalaryService;
import com.peoplemoney.service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/salaryD")
public class SalaryDServlet extends HttpServlet {
    private SalaryService salaryService=  new SalaryService();
    private TypeService typeService=new TypeService();
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收用户行为
        String actionName =request.getParameter("actionName");
        if ("list".equals(actionName)){
            salaryDList(request,response);
        }
    }

    private void salaryDList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empee_id = request.getParameter("EMPEE_ID");
        System.out.println(empee_id);
        List<Salary> list = salaryService.findAllSalaryByEmpeeId(empee_id);

        request.setAttribute("listD",list);
        request.setAttribute("changePage","demo/demo.jsp");

        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
}
