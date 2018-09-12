/**
 * 格式化
 */

/**
 * 转换科学计数法为普通表示
 * @param {Object} num
 * @return {TypeName} 
 */
function convertNUM(num){
	var  tempValueStr =  new  String(num);  
     if  ((tempValueStr.indexOf('E') != -1) || (tempValueStr.indexOf('e') != -1)){  
         var  regExp =  new  RegExp( '^((\\d+.?\\d+)[Ee]{1}(\\d+))$', 'ig' );  
         var  result = regExp.exec(tempValueStr); 
         var  resultValue = "";  
         var  power =  "" ;  
         if  (result !=  null ){  
            resultValue = result[2];  
            power = result[3];  
            //result = regExp.exec(tempValueStr);  
        }  
         if  (resultValue !=  "" ) {  
             if  (power !=  "" ) {  
                 var  powVer = Math.pow(10, power);  
                resultValue = resultValue * powVer;  
            }  
        }
         return resultValue;
     }
     return num;
}
 /**
  * 限制数字精度
  * @param {Object} num
  * @return {TypeName} 
  */
 function decimalPrecision(num,precision){
	if(precision==null){
		precision=2;
	}
	num=num.toString();
	index=num.indexOf(".");
	diff=num.length-1-index;
	if(index==0||(!isNotEmpty(num))){
		num="0"+num
	}
	if(precision>0){
		if(index == -1){
			num+=".";
			while(precision-- > 0){
				num+='0';
			}
		}else if(diff>precision){
			num=num.substring(0,index+1+precision);
		}else if(diff<=precision){
			while(diff++ !=precision)
				num+="0";
		}
	}else{
		if(index == -1)index = num.length;
		if(precision>index)precision=index;
		num=num.substring(0,index+precision);
		if(num==null||num==""){
			num=0;
		}else{
			while(precision++ < 0){
				num+='0';
			}
		}
	}
	return num;
}
/**
 * 金额格式化 
 */
function amountFormat(value){
	if(value){
		return decimalPrecision(convertNUM(value),2);
	}else{
		return "";
	}
}

