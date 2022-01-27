package com.peoplemoney.service;

import com.peoplemoney.dao.TimeDao;
import com.peoplemoney.po.months;
import com.peoplemoney.po.years;

import java.util.List;

public class TimeService {
    private TimeDao timeDao=new TimeDao();

    public List<years> findAllYears() {

       List<years> yearList=timeDao.finaAllYears();
       return yearList;
    }

    public List<months> findAllmonths() {

      List<months> monthsList= timeDao.findAllmonths();
      return monthsList;
    }
}
