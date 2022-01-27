
/**
 * 用户登录校验
 * 1.获取用户名称与密码
 * 2.判断用户名和密码是否为空
 *          如果为空，提示
 * 3.如果不为孔，提交表单
 */
function checkLogin(){
    var userName = $("#userName").val();//用户名
    var passWord = $("#passWord").val();//密码


    //
        if (isEmpty(userName)){
            $("#msg").html("用户名称不能为空!");
            return;
        }

    if (isEmpty(passWord)){
        $("#msg").html("用户密码不能为空!");
        return;
    }

    /*3.不为空提交表单*/

        $("#loginForm").submit();
}




function checkUpdate(){
    var pwd = $("#pwd").val();//用户名
    var newPwd = $("#newPwd").val();//密码


    //
    if (isEmpty(pwd)){
        $("#msg1").html("原密码不能为空!");
        return;
    }

    if (isEmpty(newPwd)){
        $("#msg2").html("新密码不能为空!");
        return;
    }

    if(pwd!=newPwd){
        $("#msg3").html("新密码不能为空!");
        return;
    }

}



