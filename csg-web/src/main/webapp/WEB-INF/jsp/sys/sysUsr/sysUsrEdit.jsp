<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../layout/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>在线业务管理平台</title>

</head>
<body class="no-skin ">
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="main-content ">
			<div class="main-content-inner ">
				<div class="page-content ">
					<div class="row">
						<div class=" widget-container-col ui-sortable  ">
							<div class="widget-box widget-color-normal3" style="opacity: 1;">

								<div class="widget-header">
									<h5 class="widget-title bigger lighter">
										<i class="ace-icon fa fa-table"></i> 编辑
									</h5>
								</div>

								<div class="widget-body">
									<div class="widget-main no-padding ">
										<div class="widget-main ">
											<form class="form-inline form-horizontal" >
											<input type="hidden" name="uuid" value="${sysUsr.uuid}"/>
												<div class="col-xs-12">
													<div class="tabbable">
														<ul id="myTab4" class="nav nav-tabs padding-12 tab-color-blue background-blue">
															<li class="active"><a href="#home4" data-toggle="tab" aria-expanded="true">编辑内容</a></li>
														</ul>
															<div class="tab-content ">
															<div class="tab-pane active no-padding" id="home4">
																<div class="col-sm-12">
                                                                   <div class="form-group form-group-sm width-300px">
                                                                        <label class="col-sm-4 control-label widget-color-normal5 width-100px no-padding-left">主键:</label>
                                                                        <div class="col-sm-8  no-padding">
                                                                        <input class="form-control input-small width-200px view-control" name="uuid"
                                                                         value="${sysUsr.uuid}" data-placement="bottom" data-trigger="hover" data-rel="popover"></input>
																		</div>
                                                                    </div>
                                                                   <div class="form-group form-group-sm width-300px">
                                                                        <label class="col-sm-4 control-label widget-color-normal5 width-100px no-padding-left">用户名:</label>
                                                                        <div class="col-sm-8  no-padding">
                                                                        <input class="form-control input-small width-200px view-control" name="name"
                                                                         value="${sysUsr.name}" data-placement="bottom" data-trigger="hover" data-rel="popover"></input>
																		</div>
                                                                    </div>
                                                                   <div class="form-group form-group-sm width-300px">
                                                                        <label class="col-sm-4 control-label widget-color-normal5 width-100px no-padding-left">用户登陆名:</label>
                                                                        <div class="col-sm-8  no-padding">
                                                                        <input class="form-control input-small width-200px view-control" name="loginName"
                                                                         value="${sysUsr.loginName}" data-placement="bottom" data-trigger="hover" data-rel="popover"></input>
																		</div>
                                                                    </div>
                                                                   <div class="form-group form-group-sm width-300px">
                                                                        <label class="col-sm-4 control-label widget-color-normal5 width-100px no-padding-left">用户登陆密码:</label>
                                                                        <div class="col-sm-8  no-padding">
                                                                        <input class="form-control input-small width-200px view-control" name="loginPwd"
                                                                         value="${sysUsr.loginPwd}" data-placement="bottom" data-trigger="hover" data-rel="popover"></input>
																		</div>
                                                                    </div>
                                                                   <div class="form-group form-group-sm width-300px">
                                                                        <label class="col-sm-4 control-label widget-color-normal5 width-100px no-padding-left">创建时间:</label>
                                                                        <div class="col-sm-8  no-padding">
                                                                        <input class="form-control input-small width-200px view-control" name="createtime"
                                                                         value="${sysUsr.createtime}" data-placement="bottom" data-trigger="hover" data-rel="popover"></input>
																		</div>
                                                                    </div>
                                                                   <div class="form-group form-group-sm width-300px">
                                                                        <label class="col-sm-4 control-label widget-color-normal5 width-100px no-padding-left">1：可用  0 ：停用:</label>
                                                                        <div class="col-sm-8  no-padding">
                                                                        <input class="form-control input-small width-200px view-control" name="state"
                                                                         value="${sysUsr.state}" data-placement="bottom" data-trigger="hover" data-rel="popover"></input>
																		</div>
                                                                    </div>
                                                                   <div class="form-group form-group-sm width-300px">
                                                                        <label class="col-sm-4 control-label widget-color-normal5 width-100px no-padding-left">创建人:</label>
                                                                        <div class="col-sm-8  no-padding">
                                                                        <input class="form-control input-small width-200px view-control" name="createUser"
                                                                         value="${sysUsr.createUser}" data-placement="bottom" data-trigger="hover" data-rel="popover"></input>
																		</div>
                                                                    </div>
                                                                   <div class="form-group form-group-sm width-300px">
                                                                        <label class="col-sm-4 control-label widget-color-normal5 width-100px no-padding-left">修改人:</label>
                                                                        <div class="col-sm-8  no-padding">
                                                                        <input class="form-control input-small width-200px view-control" name="updateUser"
                                                                         value="${sysUsr.updateUser}" data-placement="bottom" data-trigger="hover" data-rel="popover"></input>
																		</div>
                                                                    </div>
                                                                   <div class="form-group form-group-sm width-300px">
                                                                        <label class="col-sm-4 control-label widget-color-normal5 width-100px no-padding-left">修改时间:</label>
                                                                        <div class="col-sm-8  no-padding">
                                                                        <input class="form-control input-small width-200px view-control" name="updatetime"
                                                                         value="${sysUsr.updatetime}" data-placement="bottom" data-trigger="hover" data-rel="popover"></input>
																		</div>
                                                                    </div>
															</div>
														</div>
													</div>
												</div>

												<div style="text-align: center;">
												 	<button class="btn btn-info btn-sm" type="submit">
                                                        <span class="ace-icon fa fa-close icon-on-right bigger-110"></span>
                                                        	修改
                                                    </button>
												 	<button id="close" class="btn btn-info btn-sm" type="button" onclick="javascript:parent.$.fancybox.close();">
                                                        <span class="ace-icon fa fa-close icon-on-right bigger-110"></span>
                                                        	关闭
                                                    </button>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.main-content -->
	</div>
	<!-- /.main-container -->
<%@include file="../../layout/common.js.jsp"%>
<script type="text/javascript">
	$('.chosen-select').chosen({});
	$('form').formValidation({
		message : 'This value is not valid',
		container : 'popover',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		moduleCode : {
		
			uuid:{
                validators : {
                    notEmpty : {
                        message : "主键不能为空"
                    },
                }
            },
			name:{
                validators : {
                    notEmpty : {
                        message : "用户名不能为空"
                    },
                }
            },
			loginName:{
                validators : {
                    notEmpty : {
                        message : "用户登陆名不能为空"
                    },
                }
            },
			loginPwd:{
                validators : {
                    notEmpty : {
                        message : "用户登陆密码不能为空"
                    },
                }
            },
			createtime:{
                validators : {
                    notEmpty : {
                        message : "创建时间不能为空"
                    },
                }
            },
			state:{
                validators : {
                    notEmpty : {
                        message : "1：可用  0 ：停用不能为空"
                    },
                }
            },
			createUser:{
                validators : {
                    notEmpty : {
                        message : "创建人不能为空"
                    },
                }
            },
			updateUser:{
                validators : {
                    notEmpty : {
                        message : "修改人不能为空"
                    },
                }
            },
			updatetime:{
                validators : {
                    notEmpty : {
                        message : "修改时间不能为空"
                    },
                }
            },
		}
	}).on('success.form.fv', function(e) {
        e.preventDefault();
        var $form = $(e.target);
        var url='${ctx}/sys/sysUsr/update';
        $.post(url,$form.serialize(),function(data){
        	data = eval("(" + data + ")")
    		Modal.alert({msg: data.message}).on( function(e){
    			if('1'== data.result){
					$("#close").click();
				}
    		});
        });
    });
</script>
</body>
</html>