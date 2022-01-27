Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth(), //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

var time1 = new Date().Format("yyyy-MM-dd");
var time2 = new Date().Format("yyyy-MM-dd HH:mm:ss");

/**
 * 绑定添加事件
 */
function openUpdatesDialog(SID){

    //修改标题
    $("#myModalLabel").html("修改")

    //得到当期修改按钮对应的记录
    //通过id选择器获取当前的对象
    var tr=$("#tr_"+SID);
    //获取这个tr下的第n个td里的内容

    //No.2
    var MONTH_ID= tr.children().eq(2).text();
    $("#MONTH_ID").val(MONTH_ID);
    document.getElementById("MONTH_ID").readOnly=true;
    var EMPEE_ACCT= tr.children().eq(3).text();
    $("#EMPEE_ACCT").val(EMPEE_ACCT);

    document.getElementById("EMPEE_ACCT").readOnly=true;

    var cSelect = tr.children().eq(3).text();
    $("#cSelect").val(cSelect);
    //No.4
    var SALARY = tr.children().eq(5).text();

    $("#SALARY").val(SALARY);


    var SID =tr.children().eq(0).text();
    $("#SID").val(SID);
    $("#msg").html("");
    $("#myModal").modal("show");
}
function openAddS (EMPEE_ACCT){

    //因为新增和添加是共用的，所有要清空
    $("#myModalLabel").html("新增类型")
    $("#SID").val("");
    $("#MONTH_ID").val("");
    document.getElementById("MONTH_ID").readOnly=false;
    $("#EMPEE_ACCT").val(EMPEE_ACCT);

  /*  document.getElementById("EMPEE_ACCT").readOnly=f;*/

    $("#SALARY").val("");
    $("#msg").html("");
    $("#myModal").modal("show");
}



/**
 * 添加类型
 */

function UpdateOrAddS(){
    {
        var regPos = / ^\d+$/; // 非负整数
        var regNeg = /^\-[1-9][0-9]*$/; // 负整数
        //获取参数 名称，备注，id

        var SID =$("#SID").val();
        var MONTH_ID=$("#MONTH_ID").val();

        var EMPEE_ACCT =$("#EMPEE_ACCT").val();
        var SALARY =$("#SALARY").val();
        var cname =$("#cSelect option:selected").text();

        var cSelect=$("#cSelect option:selected").val();


        var MKT_AREA_ID =$("#MKT_AREA_ID").val();
        if (isEmpty(cSelect)){
            $("#msg").html("类型名称不能为空！");
            return;
        }

        if (isEmpty(MONTH_ID)){
            $("#msg").html("时间不能为空！");
            return;
        }
        if (isEmpty(EMPEE_ACCT)){
            $("#msg").html("工号不能为空！");
            return;
        }
        if (isEmpty(SALARY)){
            $("#msg").html("金额不能为空！");
            return;
        }if (isNumber(SALARY)){
        $("#msg").html("金额不能为非数字！");
        return;
    }
            //ajax
        alert("确认工号是否存在");
        $.ajax({
            type:"post",
            url: "salary",
            data: {
                actionName: "addOrUpdate",
                SID:SID,
                MONTH_ID:MONTH_ID,
                cSelect:cSelect,
                EMPEE_ACCT:EMPEE_ACCT,
                SALARY:SALARY,
                cname:cname



            },
            success:function (result){
                //判断是否成功
                if (result.code==1){
                    $("#myModal").modal("hide");

                    if (isEmpty(SID)){//没有说明是添加

                        addDomS(MONTH_ID,EMPEE_ACCT,cname,SALARY);
                    }else {
                        //说明是修改
                        updateDOMS(SID,MONTH_ID,EMPEE_ACCT,cname,SALARY);
                    }

                }else {
                    $("#msg").html(result.msg);

                }
            }
        })

    }
}
/**
 *
 * @param CID
 * @param CNAME
 * @param MESSAGE
 * @param myDate
 * @param empee_acct
 */

function  addDomS(MONTH_ID,EMPEE_ACCT,cname,SALARY){
    //拼接tr标签
    var tr ='<tr id="tr_'+SID+'"><td>'+MONTH_ID+'</td><td>'+EMPEE_ACCT+'</td><td>'+cname+'</td><td>'+SALARY+'</td>';
   /* tr+='<td>   <button type="button" className="btn btn-default" data-dismiss="modal">取消</button>';
    tr+=' <button type="button" id="UpdateBtn_submit" onClick="UpdateOrAddS()" className="btn btn-primary">保存</button></td>';
*/

    var myTable = $("#myTableS");


    if (myTable.length >0){//表格存在
        myTable.append(tr);
    }else {
        //凭借table标签和tr标签
        myTable=' <table id="myTable" className="result-tab" style="overflow:visible;text-align: center;min-width: 500px;width: 100%">';
        myTable+='<tr>\n' +
            '            <th style="text-align: center">时间</th>\n' +
            '            <th style="text-align: center">工号</th>\n' +
            '            <th style="text-align: center">类型</th>\n' +
            '            <th style="text-align: center">金额</th>\n' +
            '            <th style="text-align: center">操作</th>\n' +
            '        </tr>'
        myTable+=tr +' </table>'
        $("#myDivS").html(myTable);
    }





}

function  updateDOMS(SID,MONTH_ID,EMPEE_ACCT,cname,SALARY){
    var tr=$("#tr_"+SID);
  tr.children().eq(1).text(MONTH_ID);
    tr.children().eq(2).text(EMPEE_ACCT);
    tr.children().eq(3).text(cname);
    tr.children().eq(4).text(SALARY);

}



function deleteTypeS(SID){
    swal(
        {
            title: "", //标题
            text: "是否要删除此记录",
            type: "warning",
            dangerMode: false,
            showCancelButton: true,



        }
    ).then(function (result) {
        if(result.value) {
            //确认用户删除，发送ajax
            $.ajax({
                type: "post",
                url: "salary",
                data: {
                    actionName: "delete",
                    SID: SID
                },
                success:function (result) {
                    if (result.code == 1) {
                        swal("", "删除成功", "success");
                        deleteDom(SID);
                    } else {
                        swal("", result.msg, "error");
                    }
                }

            });}else if (result.value === "") {
            swal({
                type: 'warning',
                html: 'cannot proceed without input'
            });}
    });
}

function deleteDom(SID){
    //
    var myTable = $("#myTableS");
    var trLength =$("#myTable tr").length;

    if (trLength ==2){
        $("#myTable").remove()
        $("#myDivS").html("<h2>暂未查询到数据</h2>")


    }else {
        $("#tr_"+SID).remove();
    }
}


/*
function 1(){
    var month1 =$("#months").find("option:selected").text()
    alert(month1);
    console.log(months);
    console.log(year);
    //
    if (isEmpty(year)){
        $("#msg").html("年分不能为空!");
        return;
    }

    if (isEmpty(months)){
        $("#msg").html("月分不能为空!");
        return;
    }

    /!*3.不为空提交表单*!/

    $("#cx").submit();
}

*/
