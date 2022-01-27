package com.peoplemoney.dao;

import cn.hutool.extra.ssh.JschSessionPool;
import com.peoplemoney.po.Type;
import com.peoplemoney.po.User;
import com.peoplemoney.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TypeDao {
    public Type queryTypeByName (String CNAME){


        //定义sql语句
        String sql="select * from pm_catetory where CNAME= ?";
        //设置参数集合
        List<Object> params =new ArrayList<>();
        params.add(CNAME);
        //调用baseDao的方法
       Type type = (Type) BaseDao.queryRow(sql, params, Type.class);


        return type;
    }
    public Type queryTypeByCid (Integer CID){


        //定义sql语句
        String sql="select * from pm_catetory where CID= ?";
        //设置参数集合
        List<Object> params =new ArrayList<>();
        params.add(CID);
        //调用baseDao的方法
        Type type = (Type) BaseDao.queryRow(sql, params, Type.class);


        return type;
    }




    public  List<Type> queryTypeAll(){
        //定义sql语句
        String sql="select * from pm_catetory ";


        //设置参数集合
        List<Object> params =new ArrayList<>();

        //调用baseDao的方法
        List list = BaseDao.queryRows(sql, params, Type.class);


        return list;

    }

    /**
     * 通过类型id查询有多少该类型的工资条有多少
     * @param cid
     * @return
     */
    public long findSalaryCountByCid(String cid) {
        //定义sql
        String sql="select count(1) from pm_salary where CID=?";
        List<Object> params = new ArrayList<>();
        params.add(cid);
        //调用BaseDao
       long count = (long) BaseDao.findSingleValue(sql, params);
       return count;

    }

    public int deleteTypeByid(String cid) {
        //定义sql
        String sql="delete from pm_catetory where CID=?";
        List<Object> params = new ArrayList<>();
        params.add(cid);
        int row = BaseDao.executeUpdate(sql, params);
        return row;

    }

    public Integer checkTypeName(String cid, String cnme) {
        //1 success 0 false
        String sql ="select *from pm_catetory where cname=?";
        //设置参数
        ArrayList<Object> params = new ArrayList<>();
        params.add(cnme);
        Type type = (Type) BaseDao.queryRow(sql, params, Type.class);
        if (type==null){
            return 1;
        }else {
            if (cid.equals(type.getCID().toString())){
                return 1;
            }
        }
        System.out.println("到了checkTypeName");
        return 0;
    }

    public Integer addType(String cnme, String message,String uptor,String typeType) {
        Date date=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String dateTime = df.format(date); // Formats a Date into a date/time string.
        System.out.println(dateTime);  // 2019-07-03 10:14:14
        Integer key=null;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;


        try{
            connection=DBUtil.getConnection();
            //定义sql
            String sql="insert into  pm_catetory (CNAME,MESSAGE,UPTIME,UPTOR,typeType) VALUES (?,?,?,?,?)";
            preparedStatement=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//返回主键

            preparedStatement.setString(1,cnme);
            preparedStatement.setString(2,message);
            preparedStatement.setString(3,dateTime);
            preparedStatement.setString(4,uptor);
            preparedStatement.setString(5,typeType);
            int row =preparedStatement.executeUpdate();

            if (row>0){
                //获取主键，返回主键的结果集
                resultSet=preparedStatement.getGeneratedKeys();

                if (resultSet.next()){
                    key=resultSet.getInt(1);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.close(resultSet,preparedStatement,connection);
        }
        System.out.println("到了addType");
        return key;

    }

    public Integer updateType(String cnme, String message,String cid ,String uptor,String myDate,String typeType) {


        String sql="update pm_catetory set CNAME = ? ,UPTIME = ?,MESSAGE = ? ,UPTOR =? , typeType =? WHERE CID=?";
        List<Object> params=new ArrayList<>();
        params.add(cnme);
        params.add(myDate);
        params.add(message);
        params.add(uptor);
        params.add(typeType);
        params.add(cid);


        int row = BaseDao.executeUpdate(sql, params);
        System.out.println(params);
        System.out.println("到了updateType");
        return row;
    }
}
