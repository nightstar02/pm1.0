
function CxDgByTime(){
    var month1 =$("#months").find("option:selected").val();
    var year =$("#year").find("option:selected").val();





    if (isEmpty(year)){
        $("#msg").html("年分不能为空!");
        return;
    }

    if (isEmpty(month1)){
        $("#msg").html("月分不能为空!");
        return;
    }


    if (month1.length<2){
        var month2="0"+month1;

        var time=year+month2;

        window.location.href="salary?actionName=listdt&MONTH_ID="+time;
    }
    else if (month1.length>=2)
    {
        var time2=year+month1;

        window.location.href="salary?actionName=listdt&MONTH_ID="+time2;
    }



    /*3.不为空提交表单*/

  /*  $("#cx").submit();*/
}




