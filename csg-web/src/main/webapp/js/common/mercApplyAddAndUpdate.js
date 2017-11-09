jQuery(function($) {
	if($("#fromQueryPage").val()==undefined){
		fillProvSelect('regProv', '', false);
		fillCitySelect('regProv', 'regCity', '', false);
		fillAreaSelect('regCity', 'regTown', '', false);
		// 为注册地址省绑定onchange事件
		$("#regProv").change(function() {
			fillCitySelect('regProv', 'regCity', '', false, true);
			var cityValue = $("[name='regCity']").val();
			fillAreaSelect('regCity', 'regTown', cityValue, false);
		});
		// 为注册地址市绑定onchange事件
		$("#regCity").change(function() {
			fillAreaSelect('regCity', 'regTown', '', false);
		});

		$('.chosen-select').chosen({});
		$('#mercInfoForm').formValidation({
			excluded: [':disabled'],
			message : 'This value is not valid',
			container : 'popover',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				"tObmUsrInfoApply.regMail" : {
					validators : {
						notEmpty : {
							message : "注册邮箱不能为空"
						},
						emailAddress : {
							message : "注册邮箱格式错误"
						},
						stringLength : {
							max : 64
						},

					}
				},
				"tObmUsrInfoApply.signName" : {
					validators : {
						notEmpty : {
							message : "签约名称不能为空"
						},
						stringLength : {
							max : 128
						},

					}
				},
				"tObmUsrInfoApply.regAddr" : {
					validators : {
						notEmpty : {
							message : "详细地址不能为空"
						},
						stringLength : {
							max : 60
						}
					}
				},
				"tObmUsrInfoApply.legalName" : {
					validators : {
						notEmpty : {
							message : "法人名称不能为空"
						},
						stringLength : {
							max : 60
						}
					}
				},
				"tObmUsrInfoApply.idNo" : {
					validators : {
						notEmpty : {
							message : "证件号码不能为空"
						},
						regexp : {
							regexp : /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/g,
							message : '请输入15或18位身份证号'
						}
					}
				},
				"tObmUsrInfoApply.telNo" : {
					validators : {
						notEmpty : {
							message : "电话不能为空"
						},
						stringLength : {
							max : 16
						}
					}
				},
				"tObmUsrInfoApply.cropWebsite" : {
					validators : {
						stringLength : {
							max : 90
						}
					}
				},
				"tObmUsrInfoApply.referenceNo" : {
					validators : {
						stringLength : {
							max : 40
						}
					}
				},
				"tObmUsrInfoApply.ipAddr" : {
					validators : {
						notEmpty : {
							message : "IP地址不能为空"
						},
						stringLength : {
							max : 90
						},
	                    regexp: {
	                        regexp: /^((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?),?)*$/,
	                        message:'请输入正确格式的IP地址(多个IP地址用逗号隔开)'
	                    }
					}
				},
				"tObmUsrBnkCardApply.accountName" : {
					validators : {
						notEmpty : {
							message : "开户名称不能为空"
						},
						stringLength : {
							max : 60
						}
					}
				},
				"tObmUsrBnkCardApply.accountNo" : {
					validators : {
						notEmpty : {
							message : "银行帐号不能为空"
						},
	                    stringLength: {
	                    	min : 5,
	                    	max : 30
	                    },
	                    regexp: {
	                        regexp: /^[\d|\s]*$/,
	                        message:'请输入正确格式的银行帐号'
	                    }
					}
				},
				"tobmSesInfoApply.sesMinAmt" : {
					validators : {
						notEmpty : {
							message : "起结金额不能为空"
						},
						stringLength : {
							max : 10
						},
	                    regexp: {
	                        regexp: /^([1-9][0-9]{0,}(\.\d{1,2})?)$/,
	                        message:'起结金额只能大于10且保留两位小数'
	                    }
					}
				},
				"tobmSesInfoApply.riskPeroid" : {
					validators : {
						notEmpty : {
							message : "风险预存期不能为空"
						},
						stringLength : {
							max : 10
						},
	                    regexp: {
	                        regexp: /^([12][0-9]|30|[1-9])$/,
	                        message:'风险预存期只能是1-30的整数'
	                    }
					}
				},
				"tObmBusiSwitchApplyPo.refundExpiry" : {
					validators : {
						notEmpty : {
							message : "退款有效期不能为空"
						},
						digits: {
	                    	message:'退款有效期必须为正整数'
						},
						between : {
							min : 1,
							max : 999
						}
					}
				}

			},
			"tObmUsrInfoApply.exMno" : {
				validators : {
					notEmpty : {
						message : "商户编号不能为空"
					}

				}
			},
			"tObmUsrInfoApply.userCode" : {
				validators : {
					notEmpty : {
						message : "用户号不能为空"
					}
				}
			}
		}).on('err.field.fv', function(e, data) {

            var $tabPane = data.element.parents('.tab-pane'),
                tabId    = $tabPane.attr('id');

            $('a[href="#' + tabId + '"][data-toggle="tab"]')
                .parent()
                .find('i')
                .removeClass('fa-check');
        }).
		on('success.form.fv', function(e) {
			$("#btnSubmit").attr("disabled", "disabled");
			e.preventDefault();
			var $form = $(e.target);
			var url = '/obm/onlineusrInfoApply/submitNext';
			$.post(url, $form.serialize(), function(data) {
				data = eval("(" + data + ")")
				Modal.alert({
					msg : data.message
				}).on(function(e) {
					window.parent.$.fancybox.close();
					$("#searchForm",window.parent.document).submit();
				});
			});
		});
	}

});

function openNewWindow(type) {
	var href = '';
	var width = 800;
	var height = 620;
	if (type != null && type != '') {
		if (type == 'openFirstSettleBankSearchPage')
			href = '/obm/onlineBusinessQuery/settleBankPage.do?bankIndex=1&flg=1';
		else if (type == 'openSecondSettleBankSearchPage')
			href = '/obm/onlineBusinessQuery/settleBankPage.do?bankIndex=2&flg=1';
		else if (type == 'openAffiliatedMerchantSearchPage') {
			var procDefKey = $('#procDefKey').val();
			if (procDefKey == 'mercInfoUpdate'
					&& ($('#mercId').val() == '' || $('#mercId').val().strLen() != 15)) {
				alert('请确认修改商户商编是否填写正确！');
				return;
			}
			if (procDefKey == 'mercIncome') {
				href = '/mercInfoUpdate/openAffiliatedMerchantSearchPage.do?mercType='
						+ $('#mercTyp').val();
			} else {
				href = '/mercInfoUpdate/openAffiliatedMerchantSearchPage.do?mercType='
						+ $('#mercTyp').val()
						+ '&updateMercSn='
						+ $('#mercId').val();
			}

		} else if (type == 'openUploadImagePage') {
			var applyId = $("#applyId").val();
			var type = $("#type").val();
			href = '/obm/imageUpload/gotoUploadPage?applyId=' + applyId + '&type=' + type;
			width = $(window).width() * 0.9;
			height = $(window).height() * 0.9;
		} else if (type == 'openPointChoicePage') {
			var ctxStatic = $('#ctxStatic').val();
			href = ctxStatic + '/baiduMap/baiduMap.jsp';
			width = $(window).width() * 0.9;
			height = $(window).height() * 0.9;
		} else if (type == 'openSelectAgentPage') {
			href = '/obm/onlineBusinessQuery/openSelectAgentPage';
			width = $(window).width() * 0.9;
			height = $(window).height() * 0.9;
		}

	}
	if (href != '') {
		$.fancybox.open({
			href : href,
			type : 'iframe',
			padding : 5,
			scrolling : 'no',
			fitToView : true,
			width : width,
			height : height,
			autoSize : false,
			closeClick : false
		});
	}
}

function changeBackMoney(v) {
	if (v == '0') {
		$("#backMoney").show();
		$("#inRefundFlg").val('0');
		$("#refundExpiry").removeAttr("disabled")
	} else {
		$("#inRefundFlg").val('1');
		$("#refundExpiry").val('0');
		$("#refundExpiry").attr("disabled","disabled");
		$("#backMoney").hide();
	}
}
function changeSesDiv(v) {
	if (v == '0') {
		$("#sesDiv").show();
		$("#sesMinAmt").removeAttr("disabled")
		$("#riskPeroid").removeAttr("disabled")
	} else {
		$("#sesDiv").hide();
		$("#sesMinAmt").attr("disabled","disabled");
		$("#riskPeroid").attr("disabled","disabled");
	}
}
function addRow() {
	var count = parseInt($("#countRole").val());
	var numold = (count + 1);
	var rolerowOneFlag = parseInt($("#rolerowOneFlag").val());

	$("#countRole").val(numold);

	var countnew = parseInt($("#onlyForAdd").val());// 修改增加几条删除几条之后
													// 级联混乱的问题，将tr的ID和num隔离开

	var num = (countnew + 1);
	$("#onlyForAdd").val(num);

	$(".info:last").parent().append($(".info:first")[0].outerHTML);
	$(".info:last .chosen-container").remove();
	$(".info:last .chosen-select").show().chosen("destroy").chosen({});
	$(".info:last .formText").removeAttr("disabled");
	$(".info:last .formSelect").removeAttr("disabled").chosen("destroy")
			.chosen({});
	$(".info:last .clean").show();
	$(".info:last .hh").show();
	$(".info:last .cancelButton").show();
}

function removeRow(node) {
	var count = parseInt($("#countRole").val());
	var numold = (count - 1);
	$("#countRole").val(numold);
	if ($(".info").size() > 1) {
		$(node).parents("div.info").remove();
	}
}

function clearForPersonal() {
	var inputId = [ 'accountName', 'accountNo', 'bnkOrgCd', 'bnkFullName',
			'accountProvCd', 'accountProvName', 'accountCityCd',
			'accountCityName', 'bnkCd', 'bnkNm' ];
	for (var i = 0; i < inputId.length; i++) {
		$('#' + inputId[i]).val('');
	}
	$("#mercInfoForm").data('formValidation').validate();
}

function closeApply() {
	Modal.confirm({
		msg : "确认关闭页面?"
	}).on(function(e) {
		if (e) {
			window.parent.$.fancybox.close();
			$("#searchForm",window.parent.document).submit();
		}
	});
}

function saveData() {
	$("#isSaved").val("1");
	$('#mercInfoForm').formValidation('destroy');
	$("#mercInfoForm").submit();
	$("#saveButton").attr("disabled","disabled");
}

function submitApply() {
	var nameCount = 0;
	var nameOverLength = false; 
	$("input[name='names']").each(function(i){
		if($(this).val()==''){
			nameCount++;
		}else{
			if(getStrLength($(this).val())>20){
				nameOverLength = true;
			}
		}
	});
	var positionCount = 0;
	var positionOverLength = false; 
	$("input[name='positions']").each(function(i){
		if($(this).val()==''){
			positionCount++;
		}else{
			if(getStrLength($(this).val())>16){
				positionOverLength = true;
			}
		}
	});
	var contactCount = 0;
	var contactOverLength = false; 
	$("input[name='contacts']").each(function(i){
		if($(this).val()==''){
			contactCount++;
		}else{
			if(getStrLength($(this).val())>20){
				contactOverLength = true;
			}
		}
	});
	
	if(!(nameCount==$("input[name='names']").length && positionCount==$("input[name='positions']").length && contactCount==$("input[name='contacts']").length)){
		if(nameCount>0 || contactCount>0 || positionCount){
			Modal.alert({
				msg : '联系人姓名、联系方式和职务不能为空'
			}).on(function(e) {
				enableApplyButton();
			});
			return false;
		}else{
			if(nameOverLength){
				Modal.alert({
					msg : '联系人姓名长度不能超过20'
				}).on(function(e) {
					enableApplyButton();
				});
				return false;
			}
			if(positionOverLength){
				Modal.alert({
					msg : '职务长度不能超过16'
				}).on(function(e) {
					enableApplyButton();
				});
				return false;
			}
			if(contactOverLength){
				Modal.alert({
					msg : '联系方式长度不能超过20'
				}).on(function(e) {
					enableApplyButton();
				});
				return false;
			}
		}
	}
	var contarctsArr = $("input[name='contacts']");
	var arr = [];
	contarctsArr.each(function(j){
		arr[j]=$(this).val();
	});
	
	if(isRepeat(arr)){
		Modal.alert({
			msg : '联系方式不能重复'
		}).on(function(e) {
			enableApplyButton();
		});
		return false;
	}
	if ($("#agentName").val() == '') {
		Modal.alert({
			msg : '所属代理商不能为空'
		}).on(function(e) {
			enableApplyButton();
		});
		return false;
	}
	if($("#sesFlg").val() == '0'){
		if ($("#sesMinAmt").val() < 10 || $("#sesMinAmt").val() > 999999999) {
			Modal.alert({
				msg : '起结金额只能大于10小于999999999'
			}).on(function(e) {
				enableApplyButton();
			});
			return false;
		}
	}
	if ($("#bnkOrgCd").val() == '') {
		Modal.alert({
			msg : '开户行代码不能为空'
		}).on(function(e) {
			enableApplyButton();
		});
		return false;
	}
	if ($("#bnkFullName").val() == '') {
		Modal.alert({
			msg : '开户行信息不能为空'
		}).on(function(e) {
			enableApplyButton();
		});
		return false;
	}
	if($("#olePayFlg").val()=='0'){
		if (!atleastChoiceCashVersion()) {
			Modal.alert({
				msg : '至少选择一个收银台版本'
			}).on(function(e) {
				enableApplyButton();
			});
			return false;
		}

		if (!atleastChoiceEBankType()) {
			Modal.alert({
				msg : '至少选择一种方式'
			}).on(function(e) {
				enableApplyButton();
			});
			return false;
		}
		if (!atleastChoiceConType()) {
			Modal.alert({
				msg : '至少选择一种连接方式'
			}).on(function(e) {
				enableApplyButton();
			});
			return false;
		}
		
		if (!atleastChoicePayType()) {
			Modal.alert({
				msg : '至少选择一个支付方式'
			}).on(function(e) {
				enableApplyButton();
			});
			return false;
		}
		if ($("#singleLimit").val() == '' && !$("#noSingleLimit").is(":checked")) {
			Modal.alert({
				msg : '单笔最高限额必填'
			}).on(function(e) {
				enableApplyButton();
			});
			return false;
		}
		if ($("#singleLimit").val() != '' && !$("#noSingleLimit").is(":checked")) {
			var reg = /^([1-9][\d]{0,9}|0)(\.[\d]{1,2})?$/;
			if(!reg.test($("#singleLimit").val())){
				Modal.alert({
					msg : '单笔最高限额必须是不小于1的数字，小数点后只能保留两位有效数字'
				}).on(function(e) {
					enableApplyButton();
				});
				return false;
			}
			if(parseFloat($("#singleLimit").val())<1||parseFloat($("#singleLimit").val())>9999999999){
				Modal.alert({
					msg : '单笔最高限额必须是1-9999999999的数字'
				}).on(function(e) {
					enableApplyButton();
				});
				return false;
			}
		}

		if ($("#dayLimit").val() == '' && !$("#noDayLimit").is(":checked")) {
			Modal.alert({
				msg : '每日最高限额必填'
			}).on(function(e) {
				enableApplyButton();
			});
			return false;
		}
		if ($("#dayLimit").val() != '' && !$("#noDayLimit").is(":checked")) {
			var reg = /^([1-9][\d]{0,9}|0)(\.[\d]{1,2})?$/;
			if(!reg.test($("#dayLimit").val())){
				Modal.alert({
					msg : '每日最高限额必须是不小于1的数字，小数点后只能保留两位有效数字'
				}).on(function(e) {
					enableApplyButton();
				});
				return false;
			}
			if(parseFloat($("#dayLimit").val())<1||parseFloat($("#dayLimit").val())>9999999999){
				Modal.alert({
					msg : '每日最高限额必须是大于1小于9999999999的数字'
				}).on(function(e) {
					enableApplyButton();
				});
				return false;
			}
		}


		if ($("#singleLimit").val() != '' && $("#dayLimit").val() != '') {
			if (parseInt($("#singleLimit").val()) > parseInt($("#dayLimit").val())) {
				Modal.alert({
					msg : '每日最高限额必须大于等于单笔最高限额'
				}).on(function(e) {
					enableApplyButton();
				});
				return false;
			}
		}
		
		if ($("#monLimit").val() == '' && !$("#noMonLimit").is(":checked")) {
			Modal.alert({
				msg : '每月最高限额必填'
			}).on(function(e) {
				enableApplyButton();
			});
			return false;
		}
		if ($("#monLimit").val() != '' && !$("#noMonLimit").is(":checked")) {
			var reg = /^([1-9][\d]{0,9}|0)(\.[\d]{1,2})?$/;
			if(!reg.test($("#monLimit").val())){
				Modal.alert({
					msg : '每月最高限额必须是不小于1的数字，小数点后只能保留两位有效数字'
				}).on(function(e) {
					enableApplyButton();
				});
				return false;
			}
			if(parseFloat($("#monLimit").val())<1||parseFloat($("#monLimit").val())>9999999999){
				Modal.alert({
					msg : '每月最高限额必须是大于1小于9999999999的数字'
				}).on(function(e) {
					enableApplyButton();
				});
				return false;
			}
		}
		if ($("#monLimit").val() != '' && $("#dayLimit").val() != '') {
			if (parseInt($("#dayLimit").val()) > parseInt($("#monLimit").val())) {
				Modal.alert({
					msg : '每月最高限额必须大于等于每日最高限额'
				}).on(function(e) {
					enableApplyButton();
				});
				return false;
			}
		}
		if($("#netPay").is(":checked")){
			if($("#netPayRate").val()==''){
				Modal.alert({
					msg : '网关支付方式费率不能为空'
				}).on(function(e) {
					enableApplyButton();
				});
				return false;
			}else{
				var reg = /^(?!(\.0{1,2})?$)(\d(\.\d{1,2})?|10|10.00|10.0)$/;
				if(!reg.test($("#netPayRate").val())){
					Modal.alert({
						msg : '网关支付方式费率为0到10的数字且小数点后最多保留两位有效数字'
					}).on(function(e) {
						enableApplyButton();
					});
					return false;
				}
			}
		}
		if($("#linePay").is(":checked")){
			if($("#linePayRate").val()==''){
				Modal.alert({
					msg : '直连支付方式费率不能为空'
				}).on(function(e) {
					enableApplyButton();
				});
				return false;
			}else{
				var reg = /^(?!(\.0{1,2})?$)(\d(\.\d{1,2})?|10|10.00|10.0)$/;
				if(!reg.test($("#linePayRate").val())){
					Modal.alert({
						msg : '直连支付方式费率为0到10的数字且小数点后最多保留两位有效数字'
					}).on(function(e) {
						enableApplyButton();
					});
					return false;
				}
			}
		}
	}else{
		$('#mercInfoForm').formValidation('enableFieldValidators', 'tObmBusiSwitchApplyPo.refundExpiry',false);
	}
	
	
	var exMno = $("#exMno").val();
	if(exMno=='' || exMno==null){
		var regMail = $("#regMail").val();
		var signName = $("#signName").val();
		var isUploadAllImages = 'false';
		var isEmailRepeat = "false";
		var isSignNameRepeat = "false";
		$.ajax({
	        url: '/obm/onlineBusinessQuery/judgeEmailAndSignNameRepeat',
	        data : {
	        	regMail : regMail,
	        	signName : signName
	        },
	        async:false,
	        cache: true,
	        success : function(data) {
	        	isEmailRepeat = data.isEmailRepeat;
	        	isSignNameRepeat = data.isSignNameRepeat;
	        }
		});
		if(isEmailRepeat=='true'){
    		Modal.alert({
				msg : '注册邮箱已存在'
			}).on(function(e) {
				enableApplyButton();
			});
			return false;
    	}
		
		if(isSignNameRepeat=='true'){
    		Modal.alert({
				msg : '签约名称已存在'
			}).on(function(e) {
				enableApplyButton();
			});
			return false;
    	}
		
		var applyId = $("#applyId").val();
		$.ajax({
	        url: '/obm/onlineBusinessQuery/judgeUploadAllImages',
	        data : {
	        	applyId : applyId
	        },
	        async:false,
	        cache: true,
	        success : function(data) {
	        	isUploadAllImages = data;
	        }
		});
		if(isUploadAllImages=='false'){
    		Modal.alert({
				msg : '所有图片必须上传'
			}).on(function(e) {
				enableApplyButton();
			});
			return false;
    	}
	}
	
	$("#mercInfoForm").submit();

}

function clearLimit(quotaId,ckId) {
	if ($("#"+ckId).is(":checked")) {
		$("#" + quotaId).attr("readonly", "readonly");
	} else {
		$("#" + quotaId).removeAttr("readonly");
	}
	$("#" + quotaId).val('');
}

function clickPayType(typ) {
	if ($("#" + typ).is(":checked")) {
		$("#" + typ + "Rate").removeAttr("readonly");
	} else {
		$("#" + typ + "Rate").attr("readonly", "readonly");
		$("#" + typ + "Rate").val('');
	}
}

function enableApplyButton() {
	$("#btnSubmit").removeAttr("disabled");
	$("#btnSubmit").attr("class", "btn btn-danger btn-sm");
}

function enableQueryButton() {
	$("#queryButton").removeAttr("disabled");
}
function disableQueryButton() {
	$("#queryButton").attr("disabled", "disabled");
}

function atleastChoiceCashVersion() {
	var ob = $("input[name='tObmBusiSwitchApplyPo.cashVersion']");
	var l = ob.length;
	var i = 0;
	ob.each(function() {
		if (!$(this).is(":checked")) {
			i++;
		}
	});
	return l == i ? false : true;
}

function atleastChoiceEBankType() {
	var ob = $("input[name='tObmBusiSwitchApplyPo.eBankType']");
	var l = ob.length;
	var i = 0;
	ob.each(function() {
		if (!$(this).is(":checked")) {
			i++;
		}
	});
	return l == i ? false : true;
}

function atleastChoiceConType() {
	var ob1 = $("input[name='netPay']");
	var ob2 = $("input[name='linePay']");
	var ob3 = $("input[name='movePay']");
	if (!ob1.is(":checked") && !ob2.is(":checked") && !ob3.is(":checked")) {
		return false;
	} else {
		return true;
	}
}

function atleastChoicePayType() {
	var ob1 = $("input[id='eBankType']");
	var ob2 = $("input[id='accountPayment']");
	if (!ob1.is(":checked") && !ob2.is(":checked")) {
		return false;
	} else {
		return true;
	}
}
function isRepeat(arr){

	var hash = {};

	for(var i in arr) {

	if(hash[arr[i]])

	return true;

	hash[arr[i]] = true;

	}

	return false;
}

function changeOlePay(val){
	if(val=='0'){
		$("#olePayDiv").show();
	}else{
		$("#noSingleLimit").attr("checked",true);
		$("#noDayLimit").attr("checked",true);
		$("#netPay").attr("checked",false);
		$("#netPayRate").attr("disabled", "disabled");
		$("#linePay").attr("checked",false);
		$("#linePayRate").attr("disabled", "disabled");
		$("#olePayDiv").hide();
	}
}
function getStrLength(str) {
    var realLength = 0, len = str.length, charCode = -1;
    for (var i = 0; i < len; i++) {
        charCode = str.charCodeAt(i);
        if (charCode >= 0 && charCode <= 128) realLength += 1;
        else realLength += 2;
    }
    return realLength;
}