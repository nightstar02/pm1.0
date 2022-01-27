package com.peoplemoney.dao;

import com.peoplemoney.po.Salary;
import com.peoplemoney.po.SalaryD;
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

public class SalaryDao {
    public List<Salary> querySalaryAll() {
        //定义sql语句
        String sql="select * from pm_salary ";
        List<Object> params = new ArrayList<>();

        List list = BaseDao.queryRows(sql, params, Salary.class);
        return list;


    }

    public List<SalaryD> queryAll(){
        String sql="SELECT a.MONTH_ID,a.EMPEE_ACCT,b.EMPEE_ID,b.EMPEE_NAME,IFNULL(SUM(a.`SALARY`),0) AMONEY,IFNULL(SUM(CASE WHEN c.`typeType`=1 THEN a.`SALARY` END),0) RMONEY\n" +
                " FROM pm_salary a,pm_USER b,pm_catetory c WHERE a.`EMPEE_ACCT`=b.`EMPEE_ACCT`  AND c.cid=a.`CID`\n" +
                "\n" +
                "\t GROUP BY a.month_id,a.empee_acct,b.empee_name\n";

        ArrayList<Object> params = new ArrayList<>();
        List list = BaseDao.queryRows(sql, params, SalaryD.class);
        return list;


    }
    public List<SalaryD> queryAllt(String time) {
        String sql="SELECT a.MONTH_ID,a.EMPEE_ACCT,b.EMPEE_ID,b.EMPEE_NAME,IFNULL(SUM(a.`SALARY`),0) AMONEY,IFNULL(SUM(CASE WHEN c.`typeType`=1 THEN a.`SALARY` END),0) RMONEY\n" +
                " FROM pm_salary a,pm_USER b,pm_catetory c WHERE a.`EMPEE_ACCT`=b.`EMPEE_ACCT` AND c.cid=a.`CID` AND MONTH_ID =?\n" +
                "\n" +
                "\t GROUP BY a.month_id,a.empee_acct,b.empee_name\n";

        ArrayList<Object> params = new ArrayList<>();
        params.add(time);
        List list = BaseDao.queryRows(sql, params, SalaryD.class);
        return list;
    }


    public List<SalaryD> queryAllByMkt(String mkt_area_id) {
        String sql="SELECT a.MONTH_ID,a.EMPEE_ACCT,b.EMPEE_ID,b.EMPEE_NAME,IFNULL(SUM(a.`SALARY`),0) AMONEY,IFNULL(SUM(CASE WHEN c.`typeType`=1 THEN a.`SALARY` END),0) RMONEY\n" +
                " FROM pm_salary a,pm_USER b,pm_catetory c WHERE a.`EMPEE_ACCT`=b.`EMPEE_ACCT` AND a.`MKT_AREA_ID`=? AND c.cid=a.`CID`\n" +
                "\n" +
                "\t GROUP BY a.month_id,a.empee_acct,b.empee_name\n";

                 ArrayList<Object> params = new ArrayList<>();
                 params.add(mkt_area_id);
                 List list = BaseDao.queryRows(sql, params, SalaryD.class);
                 return list;
    }


    public List<SalaryD> queryAllByMktt(String mkt_area_id, String time) {
        String sql="SELECT a.MONTH_ID,a.EMPEE_ACCT,b.EMPEE_ID,b.EMPEE_NAME,IFNULL(SUM(a.`SALARY`),0) AMONEY,IFNULL(SUM(CASE WHEN c.`typeType`=1 THEN a.`SALARY` END),0) RMONEY\n" +
                " FROM pm_salary a,pm_USER b,pm_catetory c WHERE a.`EMPEE_ACCT`=b.`EMPEE_ACCT` AND a.`MKT_AREA_ID`=? AND c.cid=a.`CID` and a.MONTH_ID =? \n" +
                "\n" +
                "\t GROUP BY a.month_id,a.empee_acct,b.empee_name\n";

        ArrayList<Object> params = new ArrayList<>();
        params.add(mkt_area_id);
        params.add(time);
        List list = BaseDao.queryRows(sql, params, SalaryD.class);
        return list;
    }

    public List<SalaryD> queryAllByid(String empee_id) {

        String sql="SELECT a.MONTH_ID,a.EMPEE_ACCT,b.EMPEE_ID,b.EMPEE_NAME,IFNULL(SUM(a.`SALARY`),0) AMONEY,IFNULL(SUM(CASE WHEN c.`typeType`=1 THEN a.`SALARY` END),0) RMONEY\n" +
                    " FROM pm_salary a,pm_USER b,pm_catetory c WHERE a.`EMPEE_ACCT`=b.`EMPEE_ACCT` AND a.`EMPEE_ID`=? AND c.cid=a.`CID`\n" +
                "\n" +
                "\t GROUP BY a.month_id,a.empee_acct,b.empee_name\n";

                ArrayList<Object> params = new ArrayList<>();
                params.add(empee_id);
                 List list = BaseDao.queryRows(sql, params, SalaryD.class);
                 return list;
    }
    public Integer checkSalary(Integer sid) {

        //1 success 0 false
        String sql ="select *from pm_salary where sid=?";
        //设置参数
        ArrayList<Object> params = new ArrayList<>();
        params.add(sid);
        Salary salary = (Salary) BaseDao.queryRow(sql, params, Salary.class);
        if (salary==null){
            return 1;
        }else {
            if (sid.equals(salary.getSID().toString())){
                return 1;
            }
        }
        System.out.println("到了checkTypeName");
        return 0;
    }

    public Integer addSalary(String month_id, Integer cSelect, String empee_acct, String salary) {
        TypeDao typeDao = new TypeDao();
        Type type = typeDao.queryTypeByCid(cSelect);
        String cname = type.getCNAME();

        UserDao userDao = new UserDao();
        User user = userDao.queryUserByAcct(empee_acct);
        String empee_id = user.getEMPEE_ID();
        String mkt_area_id = user.getMKT_AREA_ID();
        Date date=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String dateTime = df.format(date); // Formats a Date into a date/time string.
        System.out.println(dateTime);  // 2019-07-03 10:14:14
        Integer key=null;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;


        //获取cid，empee_id,day_id
        try{
            connection= DBUtil.getConnection();
            //定义sql
            String sql="insert into  pm_salary (CID,CNAME,SALARY,EMPEE_ACCT,EMPEE_ID,MONTH_ID,DAY_ID,MKT_AREA_ID) VALUES (?,?,?,?,?,?,?,?)";
            System.out.println("这里?");
            preparedStatement=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);//返回主键
            System.out.println("还是这里");
            preparedStatement.setInt(1,cSelect);
            preparedStatement.setString(2,cname);
            preparedStatement.setString(3,salary);
            preparedStatement.setString(4,empee_acct);
            preparedStatement.setString(5,empee_id);
            preparedStatement.setString(6,month_id);
            preparedStatement.setString(7,dateTime);
            preparedStatement.setString(8,mkt_area_id);
           /* preparedStatement.setString(8,mkt_area_id);*/
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

    public Integer updateSalary(String sid,Integer cSelect, String salary) {

        Date date=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String dateTime = df.format(date); // Formats a Date into a date/time string.
        System.out.println(dateTime);  // 2019-07-03 10:14:14

        Type type = new TypeDao().queryTypeByCid(cSelect);
        String cname = type.getCNAME();
        String sql="update pm_salary set CNAME = ?,CID =? ,SALARY = ? ,DAY_ID = ? WHERE SID=?";
        List<Object> params=new ArrayList<>();
        params.add(cname);
        params.add(cSelect);
        params.add(salary);
        params.add(dateTime);
        params.add(sid);


        int row = BaseDao.executeUpdate(sql, params);
        System.out.println(params);
        System.out.println("到了updateType");
        return row;

    }

    public int deleteSalaryById(String sid) {

        //定义sql
        String sql="delete from pm_salary where SID=?";
        List<Object> params = new ArrayList<>();
        params.add(sid);
        int row = BaseDao.executeUpdate(sql, params);
        return row;
    }

    public List<SalaryD> querySalaryDAll() {

        return null;
    }

    public List<Salary> querySalaryByMktId(String mkt_area_id) {
        String sql="select * from pm_salary where MKT_AREA_ID= ? ";
        List<Object> params = new ArrayList<>();
        params.add(mkt_area_id);
        List list = BaseDao.queryRows(sql, params, Salary.class);
        System.out.println("组织架构为"+mkt_area_id+"结果集"+list);
        return list;
    }

    public List<Salary> findAllSalaryByEmpeeId(String empee_id) {
        String sql="select * from pm_salary where EMPEE_ID= ? ";
        List<Object> params = new ArrayList<>();
        params.add(empee_id);
        List list = BaseDao.queryRows(sql, params, Salary.class);
        return list;
    }


    public List<Salary> findAllSalaryByEmpeeIdm(String empee_id, String month_id) {
        String sql="select * from pm_salary where EMPEE_ID= ? and MONTH_ID = ?";
        List<Object> params = new ArrayList<>();
        params.add(empee_id);
        params.add(month_id);
        List list = BaseDao.queryRows(sql, params, Salary.class);
        return list;

    }


    public List<SalaryD> queryAllByidt(String empee_id, String time) {

        String sql="SELECT a.MONTH_ID,a.EMPEE_ACCT,b.EMPEE_ID,b.EMPEE_NAME,IFNULL(SUM(a.`SALARY`),0) AMONEY,IFNULL(SUM(CASE WHEN c.`typeType`=1 THEN a.`SALARY` END),0) RMONEY\n" +
                " FROM pm_salary a,pm_USER b,pm_catetory c WHERE a.`EMPEE_ACCT`=b.`EMPEE_ACCT` AND a.`EMPEE_ID`=? AND c.cid=a.`CID` and a.MONTH_ID = ? \n" +
                "\n" +
                "\t GROUP BY a.month_id,a.empee_acct,b.empee_name\n";

        ArrayList<Object> params = new ArrayList<>();
        params.add(empee_id);
        params.add(time);
        List list = BaseDao.queryRows(sql, params, SalaryD.class);
        return list;
    }
}






