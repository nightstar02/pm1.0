package com.peoplemoney.dao;

import com.peoplemoney.po.months;
import com.peoplemoney.po.years;

import java.util.ArrayList;
import java.util.List;

public class TimeDao {
    public List<years> finaAllYears() {


        //定义sql语句
        String sql="select * from pm_years";
        List<Object> params = new ArrayList<>();
        System.out.println("Year");
        List list = BaseDao.queryRows(sql, params, years.class);
        return list;


    }

    public List<months> findAllmonths() {
        //定义sql语句
        String sql="select month from pm_months";
        List<Object> params = new ArrayList<>();

        List list = BaseDao.queryRows(sql, params, months.class);
        return list;

    }
}
