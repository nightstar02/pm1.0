package com.peoplemoney.service;

import cn.hutool.core.util.StrUtil;
import com.peoplemoney.dao.SalaryDao;
import com.peoplemoney.po.Salary;
import com.peoplemoney.po.SalaryD;
import com.peoplemoney.po.Type;
import com.peoplemoney.vo.ResultInfo;

import java.util.List;

public class SalaryService {

    private SalaryDao salaryDao=new SalaryDao();
    public List<Salary> findAllSalary() {
        List<Salary>  salaryList=salaryDao.querySalaryAll();
        return salaryList;
    }

    public ResultInfo<Integer> addOrUpdate(String sid, String month_id, Integer cSelect, String empee_acct, String salary,String mkt_area_id) {


        ResultInfo<Integer> resultInfo = new ResultInfo<>();
        //判断参数是否为空
        if (cSelect == null){
            System.out.println(cSelect);
            resultInfo.setCode(0);
            resultInfo.setMsg("类型不能为空！");
            return resultInfo;
        }
        System.out.println("到了");
        if (StrUtil.isBlank(month_id)){
            resultInfo.setCode(0);
            resultInfo.setMsg("类型不能为空！");
            return resultInfo;
        }
        if (StrUtil.isBlank(empee_acct)){
            resultInfo.setCode(0);
            resultInfo.setMsg("类型不能为空！");
            return resultInfo;
        }
        if (salary ==null){
            resultInfo.setCode(0);
            resultInfo.setMsg("类型不能为空！");
            return resultInfo;
        }




        Integer key=null;
        if (StrUtil.isBlank(sid)){
            //添加
            System.out.println("daol??");
            key=salaryDao.addSalary(month_id,cSelect,empee_acct,salary);

        }else {
            //修改


            System.out.println("三生三世");

            key=salaryDao.updateSalary(sid,cSelect,salary);


        }
        //判断更新结果
        if (key>0){
            resultInfo.setCode(1);
            resultInfo.setResult(key);
        }else {
            resultInfo.setCode(0);

            resultInfo.setMsg("更新失败");
        }

        return resultInfo;

    }


    public ResultInfo<Salary> deleteSalary(String sid) {

        ResultInfo<Salary> resultInfo=new ResultInfo<>();

        //判断参数是否为空
        if (StrUtil.isBlank(sid)){
            resultInfo.setCode(0);
            resultInfo.setMsg("系统异常，请重试");
            return resultInfo;
        }


        int row =salaryDao.deleteSalaryById(sid);




        if (row>0){
            resultInfo.setCode(1);
        }else {
            resultInfo.setCode(0);
            resultInfo.setMsg("删除失败！");
        }

        return resultInfo;





    }



    public List<Salary> findAllSalaryByMktId(String mkt_area_id) {
        System.out.println(mkt_area_id);
     List<Salary> list= salaryDao.querySalaryByMktId(mkt_area_id);
        return list;
    }

    public List<Salary> findAllSalaryByEmpeeId(String empee_id) {
        System.out.println(empee_id);
        List<Salary> list=salaryDao.findAllSalaryByEmpeeId(empee_id);
        return list;

    }

    public List<SalaryD> findAllSalaryD() {

        List<SalaryD> salaryDList = salaryDao.queryAll();
        return salaryDList;
    }
    public List<SalaryD> findAllSalaryDt(String time) {
        List<SalaryD> salaryDtList = salaryDao.queryAllt(time);
        return salaryDtList;
    }

    public List<SalaryD> findAllSalaryDByMkt(String mkt_area_id) {
        List<SalaryD> salaryDList = salaryDao.queryAllByMkt(mkt_area_id);
        return salaryDList;

    }

    public List<SalaryD> findAllSalaryDByID(String empee_id) {

        List<SalaryD> salaryDList = salaryDao.queryAllByid(empee_id);
        return salaryDList;
    }

    public List<Salary> findAllSalaryByEmpeeIdm(String empee_id, String month_id) {

        List<Salary> list=salaryDao.findAllSalaryByEmpeeIdm(empee_id,month_id);
        return list;

    }


    public List<SalaryD> findAllSalaryDByMktt(String mkt_area_id, String time) {
        List<SalaryD> salaryDList = salaryDao.queryAllByMktt(mkt_area_id,time);
        return salaryDList;
    }

    public List<SalaryD> findAllSalaryDByIDt(String empee_id, String time) {
        List<SalaryD> salaryDList = salaryDao.queryAllByidt(empee_id,time);
        return salaryDList;
    }
}




