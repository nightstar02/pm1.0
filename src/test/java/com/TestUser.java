package com;

import com.peoplemoney.dao.BaseDao;
import com.peoplemoney.dao.UserDao;
import com.peoplemoney.po.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestUser {
@Test
    public void testUserQuery(){
        UserDao userDao = new UserDao();
    //USER user = userDao.queryUserByName("71114615");
    User users = userDao.queryUserByAcct("71114615");
    System.out.println(users);
}

@Test
    public void testAdd(){
    String sql="insert into user (EMPEE_ID,EMPEE_ACCT,EMPEE_NAME,MKT_AREA_NAME,DEPT_NAME,DEPT_ID,QX,PASSWORD) values (?,?,?,?,?,?,?,?)" ;
    List<Object> params =new ArrayList<>();
    params.add("00003");
    params.add("71114111");
    params.add("张三");
    params.add("人力");
    params.add("财务");
    params.add("0003");
    params.add(2);
    params.add("aaa42296669b958c3cee6c0475c8093e");
    int i = BaseDao.executeUpdate(sql, params);
    System.out.println(i);
}
/*查询一个人的工资详情*/
    @Test
    public void  testsalary(){


    }
}
