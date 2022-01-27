package com;

import com.peoplemoney.dao.SalaryDao;
import com.peoplemoney.dao.TypeDao;
import com.peoplemoney.po.Salary;
import com.peoplemoney.po.SalaryD;
import com.peoplemoney.po.Type;
import org.junit.Test;

import java.util.List;

public class testSalary {
    @Test
    public void testSalaryQuery(){
        SalaryDao salaryDao = new SalaryDao();
        List<Salary> salaryList = salaryDao.querySalaryAll();
        System.out.println(salaryList);


    }



    @Test
    public void testSd(){
        SalaryDao salaryDao = new SalaryDao();
/*
        List<SalaryD> salaryDS = salaryDao.queryAllt(202101)
*/
    }




}
