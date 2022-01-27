/**
 * 判断字符串是否为空
 *
 */
function isEmpty(str) {
    if (str == null || str.trim() ==""){
        return true;
    }
    return false;
}


function isNumber(val){

    var regPos = /^\d+(\.\d+)?$/; //非负浮点数
    var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
    if(regPos.test(val) && regNeg.test(val)){
        return true;
    }else{
        return false;
    }

}