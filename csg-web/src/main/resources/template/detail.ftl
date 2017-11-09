<#import "function.ftl" as func>
<#assign class=model.variables.class>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
										<i class="ace-icon fa fa-table"></i>
									</h5>
								</div>

								<div class="widget-body">
									<div class="widget-main no-padding ">
										<div class="widget-main ">
											<form class="form-inline form-horizontal">
												<div class="col-xs-12">
													<div class="tabbable">
														<ul id="myTab4"
															class="nav nav-tabs padding-12 tab-color-blue background-blue">
															<li class="active"><a href="#home4"
																data-toggle="tab" aria-expanded="true">消息详情</a></li>
														</ul>
														<div class="tab-content ">
															<div class="tab-pane active no-padding" id="home4">
																<div class="col-sm-12">
																	<#list model.columnList as columnModel>
																	<div class="form-group form-group-sm width-300px">
																		<label class="col-sm-4 control-label widget-color-normal5 width-100px no-padding-left">${columnModel.comment}:</label>
																		<div class="col-sm-8  no-padding">
																			<label class="form-control label-small width-200px view-control"
																				data-placement="bottom" data-trigger="hover"
																				data-rel="popover">${r'${'}${class?uncap_first}.${columnModel.humpColumnName?uncap_first}}</label>
																		</div>
																	</div>
																	</#list>
																</div>
															</div>
														</div>
													</div>
													<div style="text-align: center;">
														<button class="btn btn-info btn-sm" type="button"
															onclick="javascript:parent.$.fancybox.close();">
															<span
																class="ace-icon fa fa-close icon-on-right bigger-110"></span>
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
</body>
</html>