/**
 * 填充省下拉列表
 * @param provName
 * @param provValue
 * @param fiveProTwo 是否去除五省两市
 */
function fillProvSelect(provName,provValue,isContainsEmptyOption,fiveProTwo){
	$.ajax({
        url: '/commonUtils/getProv.do',
        async:false,
        cache: true,
        success : function(provList) {
       	 	if(provList != null && provList !=undefined){
       	 		var options = '';
       	 		if(isContainsEmptyOption || isContainsEmptyOption == undefined || isContainsEmptyOption == null)
       	 			options = "<option value=''>请选择</option>";
	            for(var i=0;i<provList.length;i++){
	            	if(fiveProTwo != null && fiveProTwo !=undefined && fiveProTwo == true){	
	            		if(provList[i].areaId != '730' && provList[i].areaId != '647' && provList[i].areaId != '512' && provList[i].areaId != '1050' ){
							if(provValue != ''){
					    		if(provList[i].areaId == provValue){
					    			 options+="<option selected='selected' value=\""+provList[i].areaId+"\">"+provList[i].areaNm+"</option>";
					    		 }else{
					    			 options+="<option value=\""+provList[i].areaId+"\">"+provList[i].areaNm+"</option>";
					    		 }
					    	 }else{
					    		 options+="<option value=\""+provList[i].areaId+"\">"+provList[i].areaNm+"</option>";
					    	 }
	           			 }
	           		 }else{
	           			if(provValue != ''){
		            		if(provList[i].areaId == provValue){
		            			 options+="<option selected='selected' value=\""+provList[i].areaId+"\">"+provList[i].areaNm+"</option>";
		            		 }else{
		            			 options+="<option value=\""+provList[i].areaId+"\">"+provList[i].areaNm+"</option>";
		            		 }
		            	 }else{
		            		 options+="<option value=\""+provList[i].areaId+"\">"+provList[i].areaNm+"</option>";
		            	 } 
	           		 }
	             }
	             $("#"+provName).html(options);
	             $("#"+provName).trigger("chosen:updated");
       	 	}
        }
	});
}
/**
 * 填充市下拉列表
 * @param provName
 * @param provValue
 * @param fiveProTwo 是否去除五省两市
 */
function fillCitySelect(provName,cityName,cityValue,isContainsEmptyOption,fiveProTwo){
	var provCd = $("#"+provName).val();
	if(provCd!=''){
		$.ajax({
	        url: '/commonUtils/getCityByProvCd.do',
	        data : {
				provCd : provCd
	        },
	        async:false,
	        cache: true,
	        success : function(cityList) {
	       	 	if(cityList != null && cityList !=undefined){
		       	 	var options = '';
		       	 if(isContainsEmptyOption || isContainsEmptyOption == undefined || isContainsEmptyOption == null)
	       	 			options = "<option value=''>请选择</option>";
		             for(var i=0;i<cityList.length;i++){
		            	 if(fiveProTwo != null && fiveProTwo !=undefined && fiveProTwo == true){
		            		 if(provCd =='1305'){//福建省
		            			 if(cityList[i].areaId =='1321' ){//厦门市
									if(cityValue != ''){
						       		 	if(cityList[i].areaId == cityValue){
						       		 		options+="<option selected='selected' value=\""+cityList[i].areaId+"\">"+cityList[i].areaNm+"</option>";
						       		 	}else{
						       		 		options+="<option value=\""+cityList[i].areaId+"\">"+cityList[i].areaNm+"</option>";
						       		 	}
						       	 	}else{
						       	 		options+="<option value=\""+cityList[i].areaId+"\">"+cityList[i].areaNm+"</option>";
						       	 	}
						   		 }
		            		 }else{
			            		 if(cityList[i].areaId != '730' && cityList[i].areaId != '647' && cityList[i].areaId != '512' && cityList[i].areaId != '1050'){
									if(cityValue != ''){
						       		 	if(cityList[i].areaId == cityValue){
						       		 		options+="<option selected='selected' value=\""+cityList[i].areaId+"\">"+cityList[i].areaNm+"</option>";
						       		 	}else{
						       		 		options+="<option value=\""+cityList[i].areaId+"\">"+cityList[i].areaNm+"</option>";
						       		 	}
						       	 	}else{
						       	 		options+="<option value=\""+cityList[i].areaId+"\">"+cityList[i].areaNm+"</option>";
						       	 	}
								 }
			           		 }
		            	 }else{
		            		 if(cityValue != ''){
			            		 if(cityList[i].areaId == cityValue){
			            			 options+="<option selected='selected' value=\""+cityList[i].areaId+"\">"+cityList[i].areaNm+"</option>";
			            		 }else{
			            			 options+="<option value=\""+cityList[i].areaId+"\">"+cityList[i].areaNm+"</option>";
			            		 }
			            	 }else{
			            		 options+="<option value=\""+cityList[i].areaId+"\">"+cityList[i].areaNm+"</option>";
			            	 } 
			           	 }
		             }
		             $("#"+cityName).html(options);
		             $("#"+cityName).trigger("chosen:updated");
	       	 	}
	        }
		});
	}else{
		$("#"+cityName).html('');
		$("#"+cityName).trigger("chosen:updated");
	}
}
/**
 * 填充区域下拉列表
 * @param provName
 * @param provValue
 */
function fillAreaSelect(cityName,areaName,areaValue,isContainsEmptyOption){
	var cityCd = $("#"+cityName).val();
	if(cityCd!=''){
		$.ajax({
	        url: '/commonUtils/getAreaByCityCd.do',
	        data : {
	        	cityCd : cityCd
	        },
	        async:false,
	        cache: true,
	        success : function(areaList) {
	       	 	if(areaList != null && areaList !=undefined){
		       	 	var options = '';
		       	 if(isContainsEmptyOption || isContainsEmptyOption == undefined || isContainsEmptyOption == null)
	       	 			options = "<option value=''>请选择</option>";
		             for(var i=0;i<areaList.length;i++){
		            	 if(areaValue != ''){
		            		 if(areaList[i].areaId == areaValue){
		            			 options+="<option selected='selected' value=\""+areaList[i].areaId+"\">"+areaList[i].areaNm+"</option>";
		            		 }else{
		            			 options+="<option value=\""+areaList[i].areaId+"\">"+areaList[i].areaNm+"</option>";
		            		 }
		            	 }else{
		            		 options+="<option value=\""+areaList[i].areaId+"\">"+areaList[i].areaNm+"</option>";
		            	 }
		             }
		             $("#"+areaName).html(options);
		             $("#"+areaName).trigger("chosen:updated");
	       	 	}
	        }
		});
	}else{
		$("#"+areaName).html('');
		$("#"+areaName).trigger("chosen:updated");
	}
}
/**
 * 省市区域级联效果
 * @param isGetProv 是否需要获取省信息
 * @param provName 省Name 指定需要填充的省
 * @param cityName 市Name 指定需要填充的市
 * @param areaName 区Name 指定需要填充的区
 * @param provValue 省Value 指定需要选中的省
 * @param cityValue 市Value 指定需要选中的市
 * @param areaValue 区域/县Value 指定需要选中的区
 * @param fiveProTwo 是否去除五省两市
 */
function areaCascade(isGetProv,provName,cityName,areaName,provValue,cityValue,areaValue,isContainsEmptyOption,fiveProTwo){
	if(isGetProv){
		fillProvSelect(provName,provValue,isContainsEmptyOption,fiveProTwo);
	}
	if(provName != '' && cityName!=''){
		fillCitySelect(provName,cityName,cityValue,isContainsEmptyOption,fiveProTwo);
	}
	if(provName != '' && cityName!='' && areaName != ''){
		fillAreaSelect(cityName,areaName,areaValue,isContainsEmptyOption);
	}
}

/**
 * 根据省编号获取省名称
 * @param provCd
 * @returns {String}
 */
function getProvNameByProvCd(provCd){
	var provName = '';
	$.ajax({
        url: '/commonUtils/getProvNameByProvCd.do',
        data:{
        	provCd:provCd
        },
        async:false,
        cache: true,
        success : function(area) {
       	 	if(area != null && area !=undefined){
       	 		provName = area.areaNm;
       	 	}
        }
	});
	return provName;
}
/**
 * 根据市编号获取市名称
 * @param cityCd
 * @returns {String}
 */
function getCityNameByCityCd(cityCd){
	var cityName = '';
	$.ajax({
        url: '/commonUtils/getCityNameByCityCd.do',
        data:{
        	cityCd:cityCd
        },
        async:false,
        cache: true,
        success : function(area) {
       	 	if(area != null && area !=undefined){
       	 		cityName = area.areaNm;
       	 	}
        }
	});
	return cityName;
}
/**
 * 根据区编号获取市名称
 * @param townCd
 * @returns {String}
 */
function getTownNameByTownCd(townCd){
	var townName = '';
	$.ajax({
        url: '/commonUtils/getTownNameByTownCd.do',
        data:{
        	townCd:townCd
        },
        async:false,
        cache: true,
        success : function(area) {
       	 	if(area != null && area !=undefined){
       	 		townName = area.areaNm;
       	 	}
        }
	});
	return townName;
}
//更改formvalidate的样式
function changeFormValidateStyle(formId,field, status, validatorName){
	$("#"+formId).data('formValidation').updateStatus(field, status, validatorName);
}
//指定form进行验证
function validateFormByFormId(formId){
	$("#"+formId).data('formValidation').validate();
}
//指定fieldName进行重新验证
function revalidateFieldByFieldName(formId,fieldName){
	$("#"+formId).data('formValidation').revalidateField(fieldName);
}
//为指定form的控件添加非空验证
function setNotEmptyValidate(formId,notEmptyInputName,notEmptyMessage){
	$('#'+formId).formValidation('addField', notEmptyInputName, {
		validators: {
            notEmpty: {
            	message:notEmptyMessage
            }
        }
	});
}
//删除formValidation验证
function removeValidate(formId,notEmptyInputName){
	$('#'+formId).formValidation('destroy');
}
//设置text为参数的option被选中
function setOptionSelectedByText(selectId,text){
	$('#'+selectId+' option').each(function(){
	    if( $(this).text() == text){
	         $(this).attr('selected','selected');
	     }
	});
}
