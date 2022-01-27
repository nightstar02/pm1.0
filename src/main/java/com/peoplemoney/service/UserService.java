package com.peoplemoney.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.peoplemoney.dao.UserDao;
import com.peoplemoney.po.Type;
import com.peoplemoney.po.User;
import com.peoplemoney.util.Page;
import com.peoplemoney.vo.ResultInfo;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class UserService {

    private UserDao userDao=new UserDao();

    public ResultInfo<User> userLogin(String empee_acct, String password){

        System.out.println(empee_acct+password);
        /**
         *
         */
        ResultInfo<User> resultInfo = new ResultInfo<>();
        //数据回写：党=登录实现时，返回登录信息
        User u = new User();
        u.setEMPEE_ACCT(empee_acct);
        u.setPASSWORD(password);
        //设置到resultInfo中
        resultInfo.setResult(u);


        //1.判断是否为空
        if (StrUtil.isBlank(empee_acct)||StrUtil.isBlank(password)){
            resultInfo.setCode(0);
            resultInfo.setMsg("工号和密码不能为空");
            return resultInfo;
        }
        //2.不为空
        User user = userDao.queryUserByAcct(empee_acct);

        //3.判断用户是否为空
        if (user == null ){
            //如果为空，设置状态码和提示
            resultInfo.setCode(0);
            resultInfo.setMsg("该用户不存在");
            return resultInfo;
        }

        /*4.如果不为空，比较密码*/
       // password= DigestUtil.md5Hex(password);
        System.out.println(password);


        if (!password.equals(user.getPASSWORD())){
            resultInfo.setCode(0);
            resultInfo.setMsg("用户密码不正确");

            return resultInfo;
        }

        //正确

        resultInfo.setCode(1);
        resultInfo.setResult(user);


        return resultInfo;
    }


    public List<User> findUserList() {

        List<User> userList = userDao.queryUserAll();

        return userList;

    }

    public List<User> findUserListByMkt(String mkt_area_id) {
       List<User> userList= userDao.queryUserAllByMkt(mkt_area_id);
        return userList;
    }

    public List<User> findUUserById(String empee_acct) {
        List<User> userList =  userDao.queryUserAllByAcct(empee_acct);
        return userList;
    }

/*
    public Page<User> findUUserByIdPage(String pageNumStr, String pageSizeStr, String empee_id) {
        Integer pageNum=1;
        Integer pageSize=10;

        if (!StrUtil.isBlank(pageNumStr)){
            pageNum=Integer.parseInt(pageNumStr);
        }
        if (!StrUtil.isBlank(pageSizeStr)){
            pageSize=Integer.parseInt(pageSizeStr);
        }
        long count = userDao.findUserCount(empee_id);

        if (count<1){
            return null;

        }

        Page<User> page = new Page<>(pageNum, pageSize, count);


        List<User> userList=userDao.findUserListByPage()
        return null;
    }
*/
    /**
     * 分页查询
     */

    //所有

}
