package com.peoplemoney.dao;

import com.peoplemoney.po.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
//根据名字查询对象
 /*   public USER queryUserByAcct02 (String empee_name){
        USER user=null;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;

        try {
            connection=DBUtil.getConnection();

            String sql="select * from user where empee_acct = ?";

            //预编译
             preparedStatement = connection.prepareStatement(sql);
             //设置参数
            preparedStatement.setString(1,empee_name);//为java实体属性名

            //查询
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                user=new USER();
                user.setEMPEE_ID(resultSet.getString("EMPEE_ID"));
                user.setEMPEE_ACCT(resultSet.getString("EMPEE_ACCT"));
                user.setEMPEE_NAME(resultSet.getString("EMPEE_NAME"));
                user.setDEPT_NAME(resultSet.getString("DEPT_NAME"));
                user.setDEPT_ID(resultSet.getString("DEPT_ID"));
                user.setQX(resultSet.getInt("QX"));
                user.setPASSWORD(resultSet.getString("PASSWORD"));



            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.close(resultSet,preparedStatement,connection);
        }
        return user;
    }
*/



    //优化后的根据工号查询对象
    public User queryUserByAcct (String empee_acct){


        //定义sql语句
        String sql="select * from pm_user where empee_acct= ?";
        //设置参数集合
        List<Object> params =new ArrayList<>();
        params.add(empee_acct);
        //调用baseDao的方法
       User user= (User) BaseDao.queryRow(sql, params, User.class);


        return user;
    }
//查询所有信息
   public  List<User> queryUserAll(){
        //定义sql语句
        String sql="select * from pm_user ";


        //设置参数集合
        List<Object> params =new ArrayList<>();

        //调用baseDao的方法
       List list = BaseDao.queryRows(sql, params, User.class);


       return list;

   }

//查询一个名字的所有信息
    public  List<User> queryUserAllByAcct(String EMPEE_ACCT){
        //定义sql语句
        String sql="select * from pm_user  where EMPEE_ACCT = ?";


        //设置参数集合
        //设置参数集合
        List<Object> params =new ArrayList<>();
        params.add(EMPEE_ACCT);

        //调用baseDao的方法
        List list = BaseDao.queryRows(sql, params, User.class);


        return list;

    }

    public List<User> queryUserAllByMkt(String mkt_area_id) {

        //定义sql语句
        String sql="select * from pm_user  where MKT_AREA_ID = ?";


        //设置参数集合
        //设置参数集合
        List<Object> params =new ArrayList<>();
        params.add(mkt_area_id);

        //调用baseDao的方法
        List list = BaseDao.queryRows(sql, params, User.class);


        return list;
    }

    public long findUserCount(String empee_id) {
        //定义sql语句
        String sql="select COUNT(1) from pm_user  where empee_id ";
        List<Object> params =new ArrayList<>();
        
        long count = (long) BaseDao.findSingleValue(sql,params);
        return count;

    }

    public List<User> findUserListByPage( Integer index, Integer pageSize) {
        String sql="select EMPEE_NAME,EMPEE_ACCT,MKT_AREA_NAME,DEPT_NAME from pm_user ";
        List<Object> params =new ArrayList<>();
        params.add(1);
        List<User> userList = BaseDao.queryRows(sql, params, User.class);
        return userList;


    }


    /**
     *
     */



}
