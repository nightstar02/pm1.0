Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
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

function deleteType(CID){
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
            url: "type",
            data: {
                actionName: "delete",
                CID: CID
            },
            success:function (result) {
                if (result.code == 1) {
                    swal("", "删除成功", "success");
                    deleteDom(CID);
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

function deleteDom(CID){
    //
    var myTable = $("#myTable");
    var trLength =$("#myTable tr").length;

    if (trLength ==2){
        $("#myTable").remove()
        $("#myDiv").html("<h2>暂未查询到数据</h2>")


    }else {
        $("#tr_"+CID).remove();
    }
}

/**
 * 绑定添加事件
 */

/*$("#addBtn").click(function (){
    //修改标题
    $("#myModalLabel").html("新增类型")


    $("#myModal").modal("show");

})*/
function openAdd (){
    //因为新增和添加是共用的，所有要清空
    $("#myModalLabel").html("新增类型")
    $("#CID").val("");
    $("#CNAME").val("");
    $("#MESSAGE").val("");
    $("#typeType").val("");
    $("#msg").html("");
    $("#myModal").modal("show");
}

/**
 * 绑定添加事件
 */
function openUpdateDialog(CID){

    //修改标题
    $("#myModalLabel").html("修改类型")

    //得到当期修改按钮对应的记录
    //通过id选择器获取当前的对象
    var tr=$("#tr_"+CID);
        //获取这个tr下的第n个td里的内容

    //No.2
    var CNAME = tr.children().eq(1).text();

    $("#CNAME").val(CNAME);
    //No.4
    var MESSAGE = tr.children().eq(3).text();

    $("#MESSAGE").val(MESSAGE);


    var typeType = tr.children().eq(5).text();
    $("#typeType").val(typeType);

    var CID =tr.children().eq(0).text();
    $("#CID").val(CID);
    $("#msg").html("");
    $("#myModal").modal("show");
}

/**
 * 添加类型
 */

function UpdateOrAdd(){
    {

        //获取参数 名称，备注，id
        var CID =$("#CID").val();
        var CNAME=$("#CNAME").val();
        var myDate = new Date().Format("yyyy-MM-dd");
        var empee_acct =$("#user").val();
        var typeType =$("#typeType").val();
        $("#msg2").html(CNAME);
        var MESSAGE=$("#MESSAGE").val();
        if (isEmpty(CNAME)){
            $("#msg").html("类型名称不能为空！");
            return;
        }

        //ajax
        $.ajax({
            type:"post",
            url: "type",
            data: {
                actionName: "addOrUpdate",
                CID:CID,
                CNAME:CNAME,
                MESSAGE:MESSAGE,
                myDate:myDate,
                empee_acct:empee_acct,
                typeType:typeType
            },
            success:function (result){
                //判断是否成功
                if (result.code==1){
                    $("#myModal").modal("hide");

                    if (isEmpty(CID)){//没有说明是添加

                        addDom(result.result,CNAME,MESSAGE,myDate,empee_acct,typeType);
                    }else {
                        //说明是修改

                        updateDOM(CID,CNAME,MESSAGE,myDate,empee_acct,typeType);
                    }

                }else {
                    $("#msg").html(result.msg);

                }
            }
        })

    }
}


function  updateDOM(CID,CNAME,MESSAGE,myDate,empee_acct,typeType){
    var tr=$("#tr_"+CID);
    tr.children().eq(1).text(CNAME);
    tr.children().eq(2).text(myDate);
    tr.children().eq(3).text(MESSAGE);
    tr.children().eq(4).text(empee_acct);

    if (typeType=="0"){
        var type="应发"
    } else {
        var type="实发"
    }
    tr.children().eq(5).text(type);
}



/**
 *
 * @param CID
 * @param CNAME
 * @param MESSAGE
 * @param myDate
 * @param empee_acct
 */

function  addDom(CID,CNAME,MESSAGE,myDate,empee_acct,typeType){
    if (typeType=="0"){
        var type="应发"
    } else {
        var type="实发"
    }
    //拼接tr标签
    var tr ='<tr id="tr_'+CID+'"><td>'+CID+'</td><td>'+CNAME+'</td><td>'+myDate+'</td><td>'+MESSAGE+'</td><td>'+empee_acct+'</td><td>'+type+'</td>';
    tr+='<td><button className="btn btn-primary btn2" type="button"  onClick="openUpdateDialog('+CID+')">修改</button>';
    tr+='<button className="btn btn-primary btn2" type="button" onClick="deleteType('+CID+')" >删除</button></td>';

    var myTable = $("#myTable");


    if (myTable.length >0){//表格存在
        myTable.append(tr);
    }else {
        //凭借table标签和tr标签
        myTable=' <table id="myTable" className="result-tab" style="overflow:visible;text-align: center;min-width: 500px;width: 100%">';
        myTable+='<tr>\n' +
            '            <th style="text-align: center">编号</th>\n' +
            '            <th style="text-align: center">类型</th>\n' +
            '            <th style="text-align: center">更新时间</th>\n' +
            '            <th style="text-align: center">备注</th>\n' +
            '            <th style="text-align: center">更新人</th>\n' +
            '            <th style="text-align: center">操作</th>\n' +
            '        </tr>'
        myTable+=tr +' </table>'
        $("#myDiv").html(myTable);
    }





}

