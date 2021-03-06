package com.peoplemoney.dao;

import com.peoplemoney.util.DBUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {




    /**
     * 更新操作
     *  添加、修改、删除
     *
     *  1。得到数据库链接
     *  2.定义sql
     *  3.预编译
     *  4，有参数就设置
     *  5.执行更新，返回收影响的行数
     *  6.关闭资源
     *
     *
     *  注：需要两个参数：sql语句和所需参数的集合
     */

    public static int executeUpdate(String sql,List<Object> params){
        int row=0; //受影响的行数
        Connection connection=null;
        PreparedStatement preparedStatement = null;
        try{
            //得到数据库链接
            connection   = DBUtil.getConnection();
            //预编译
            preparedStatement = connection.prepareStatement(sql);
            //如果有参数，则设置参数，下标从1开始
            if (params !=null && params.size()>0){
                //设置循环参数，设置参数类型为object
                for (int i = 0; i <params.size(); i++) {
                    preparedStatement.setObject(i+1,params.get(i));
                }
            }

            //执行更新，返回受受影响的行数
            row= preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.close(null,preparedStatement,connection);
        }

        return row;
    }

    /**
     * 查询一个字段
     * 常用场景，查询总数量
     *
     */
    public static Object findSingleValue(String sql, List<Object> params){
        Object object=null;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet =null;

        try{
            //得到数据库链接
             connection = DBUtil.getConnection();
             //预编译
            preparedStatement = connection.prepareStatement(sql);
            //如果有参数，则设置参数，从下标1开始
            if (params !=null && params.size()>0){
                //设置循环参数，设置参数类型为Object
                for (int i = 0; i < params.size(); i++) {
                    preparedStatement.setObject(i+1,params.get(i));
                }
            }

            //执行查询返回结果集
            resultSet = preparedStatement.executeQuery();
            //判断并分析结果集
            if (resultSet.next()){
                object=resultSet.getObject(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
                DBUtil.close(resultSet,preparedStatement,connection);
        }
        return object;
    }

    /**
     * 查询集合
     *
     */

    public  static  List queryRows(String sql,List<Object> params,Class cls){
        List list = new ArrayList();
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet =null;

        try{
            //得到数据库链接
            connection = DBUtil.getConnection();
            //预编译
            preparedStatement = connection.prepareStatement(sql);
            //如果有参数，则设置参数，从下标1开始
            if (params !=null && params.size()>0){
                //设置循环参数，设置参数类型为Object
                for (int i = 0; i < params.size(); i++) {
                    preparedStatement.setObject(i+1,params.get(i));
                }
            }

            //执行查询返回结果集
            resultSet = preparedStatement.executeQuery();

            //分析结果集

            /**
             * 得到结果集的元数据 查询到的字段数量以及查询了那些字段
             */
            ResultSetMetaData metaData = resultSet.getMetaData();
            //得到查询字段数量
            int fieldNum = metaData.getColumnCount();

            //判断分析结果集
            while (resultSet.next()){
                //实例化
                Object object = cls.newInstance();
                //遍历查询的字段数量，得到数据库中查询的每一个列名
                for (int i = 1; i <=fieldNum ; i++) {
                    //得到查询的每一个列名
                    //getColumnLabel 获取列名或别名
                    //getColumnName 获取列名

                    String columnLabel = metaData.getColumnLabel(i);

                    //通过反射，使得列名得到对应的field对象
                    Field field = cls.getDeclaredField(columnLabel);
                    //拼接set方法，获取字符串
                    String setMethod="set"+columnLabel.substring(0,1).toUpperCase()+columnLabel.substring(1);

                    //通过反射将set方法字符串反射成方法
                    Method Method = cls.getDeclaredMethod(setMethod,field.getType());

                    //得到每个字段对应的值
                    Object value =resultSet.getObject(columnLabel);

                    Method.invoke(object,value);
                }
                    //将javabean设置到集合中

                    list.add(object);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.close(resultSet,preparedStatement,connection);
        }
        return list;
    }

    /**
     * 查询一个对象
     */

    public  static  Object queryRow(String sql,List<Object> params,Class cls){
        Object object=null;
        List list = queryRows(sql, params, cls);
        if (list !=null&&list.size()>0){
                object=list.get(0);
        }
        return object;
    }


}
