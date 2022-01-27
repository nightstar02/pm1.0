package com;

import com.peoplemoney.dao.TypeDao;
import com.peoplemoney.po.Type;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestType {


    @Test
    public void testTypeQuery(){

        TypeDao typeDao = new TypeDao();
        List<Type> typeType = typeDao.queryTypeAll();
        System.out.println(typeType);
    }

    @Test
    public void testTypeByName(){
        TypeDao typeDao = new TypeDao();
        Type 岗位工资 = typeDao.queryTypeByName("岗位工资");

        System.out.println(岗位工资);
    }

    @Test
    public void testUpdate(){
        TypeDao typeDao = new TypeDao();
        typeDao.addType("ddd","ddd","ddd","ddd");
       /* typeDao.updateType("asd修asdsa改后","lllas","1005","WOZI J","as","2");*/
    }

}
