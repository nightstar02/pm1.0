package com.peoplemoney.service;

import cn.hutool.core.util.StrUtil;
import com.peoplemoney.dao.TypeDao;
import com.peoplemoney.po.Type;
import com.peoplemoney.vo.ResultInfo;

import java.util.List;

public class TypeService {

    private TypeDao typeDao=new TypeDao();

    public List<Type> findTypeList (){

        List<Type> typeList = typeDao.queryTypeAll();
        return typeList;

    }

    /**
     * 删除类型
     * @param cid
     * @return
     */
    public ResultInfo<Type> deleteType(String cid) {
        ResultInfo<Type> resultInfo=new ResultInfo<>();

        //判断参数是否为空
        if (StrUtil.isBlank(cid)){
            resultInfo.setCode(0);
            resultInfo.setMsg("系统异常，请重试");
            return resultInfo;
        }

        //调用Dao层 查询工资单记录数量
        long salaryCount= typeDao.findSalaryCountByCid(cid);

        if (salaryCount>0){
            resultInfo.setCode(0);
            resultInfo.setMsg("该类型已有记录，不可删除");
            return resultInfo;
        }

        int row =typeDao.deleteTypeByid(cid);
        System.out.println(row+"查询结果"+"cid="+cid);
        if (row>0){
            resultInfo.setCode(1);
        }else {
            resultInfo.setCode(0);
            resultInfo.setMsg("删除失败！");
        }

        return resultInfo;
    }


    public ResultInfo<Integer> addOrUpdate(String cid, String cname, String message,String uptor,String myDate,String typeType) {
        ResultInfo<Integer> resultInfo = new ResultInfo<>();

        //判断参数是否为空
        if (StrUtil.isBlank(cname)){
            resultInfo.setCode(0);
            resultInfo.setMsg("类型不能为空！");
            return resultInfo;
        }

        Integer code=typeDao.checkTypeName(cid,cname);

        if (code==0){
            resultInfo.setCode(0);
            resultInfo.setMsg("类型已存在！");
            return resultInfo;
        }

        Integer key=null;

        if (StrUtil.isBlank(cid)){
            //添加
            key=typeDao.addType(cname,message,uptor,typeType);

        }else {
            //修改
            key=typeDao.updateType(cname,message,cid,uptor,myDate,typeType);

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
}
