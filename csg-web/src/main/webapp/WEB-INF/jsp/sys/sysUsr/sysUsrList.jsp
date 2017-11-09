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
            try{ace.settings.check('main-container' , 'fixed')}catch(e){}
        </script>

		<div class="main-content ">
			<div class="main-content-inner ">

				<div class="page-content ">
					<div class="row">
						<div class=" widget-container-col ui-sortable  ">
							<div class="widget-box widget-color-normal3" style="opacity: 1;">
								<div class="widget-header">
									<h5 class="widget-title bigger lighter">
										<i class="ace-icon fa fa-table"></i> 检索条件
									</h5>
									<span class="widget-toolbar"> <a data-action="collapse"
										href="#"> <i class="ace-icon fa fa-chevron-up"></i>
									</a>
									</span>
								</div>
								<div class="widget-body">
									<div class="widget-main no-padding ">
										<div class="widget-main ">
											<form class="form-inline form-horizontal" action="${ctx}/sys/sysUsr/list?entry=1"
												id="searchForm" method="post"> 
												<input type="hidden" name="pageNum" value="${pageInfo.getPageNum()}"/>
                                            	<input type="hidden" name="pageSize" value="${pageInfo.getPageSize()}">
												<div class="col-xs-12">
                                                    <div class="form-group form-group-sm width-300px">
                                                        <label for="form-field-6" class="col-sm-4 control-label widget-color-normal5 width-100px no-padding-left">用户名:</label>
                                                        <div class="col-sm-8  no-padding"  >
                                                            <input type="text" name="name" value="${sysUsr.name}" class="form-control input-small width-200px" data-placement="bottom" title=""  id="form-field-6">
                                                        </div>
                                                    </div>
                                                    <div class="form-group form-group-sm width-300px">
                                                        <label for="form-field-6" class="col-sm-4 control-label widget-color-normal5 width-100px no-padding-left">用户登陆名:</label>
                                                        <div class="col-sm-8  no-padding"  >
                                                            <input type="text" name="loginName" value="${sysUsr.loginName}" class="form-control input-small width-200px" data-placement="bottom" title=""  id="form-field-6">
                                                        </div>
                                                    </div>
                                                 </div>
                                                 
												<button class="btn btn-normal btn-sm" type="submit">
													<span class="ace-icon fa fa-search-plus icon-on-right bigger-110"></span>
													检索
												</button>
												<button class="btn btn-info btn-sm" type="button"
													onclick="resetMecForm()">
													<span class="ace-icon fa fa-refresh icon-on-right bigger-110"></span>
													清空检索条件
												</button>
												
												<button class="btn btn-info btn-sm" type="button"
													onclick="show('add','')">
													<span class="ace-icon fa fa-plus icon-on-right bigger-110"></span>
													添加内容
												</button>
												
											</form>
										</div>
									</div>
								</div>
							</div>


							<div class="widget-box widget-color-normal2" style="opacity: 1;">
								<div class="widget-header">
									<h5 class="widget-title bigger lighter">
										<i class="ace-icon fa fa-table"></i> 检索结果
									</h5>
								</div>

								 <div class="widget-body">
                                    <div class="widget-main no-padding table-responsive">
                                        <table class="table  table-condensed table-striped table-bordered table-hover dataTable no-footer DTTT_selectable">
                                            <thead class="thin-border-bottom">
                                            <tr>
                                            	<th width="10%">用户名</th>
                                            	<th width="10%">用户登陆名</th>
                                            	<th width="10%">用户登陆密码</th>
                                            	<th width="10%">创建时间</th>
                                            	<th width="10%">1：可用  0 ：停用</th>
                                            	<th width="10%">创建人</th>
                                            	<th width="10%">修改人</th>
                                            	<th width="10%">修改时间</th>
                                                <th >操作</th>
                                            </tr>
                                            </thead>

                                            <tbody>
                                            	<c:forEach var="sysUsr" items="${sysUsrs}">
                                            		<tr >
                                            			<td align="center">${sysUsr.name }</td>
                                            			<td align="center">${sysUsr.loginName }</td>
                                            			<td align="center">${sysUsr.loginPwd }</td>
                                            			<td align="center">${sysUsr.createtime }</td>
                                            			<td align="center">${sysUsr.state }</td>
                                            			<td align="center">${sysUsr.createUser }</td>
                                            			<td align="center">${sysUsr.updateUser }</td>
                                            			<td align="center">${sysUsr.updatetime }</td>
		                                                <td align="center">
		                                                <a href="javascript:show('detail','${sysUsr.uuid }')" >
															查看</a>
		                                             	<a href="javascript:show('edit','${sysUsr.uuid }')" >
															修改</a>
		                                                <a href="javascript:del('${sysUsr.uuid }')" >
															删除</a>
		                                                </td>
	                                                </tr>
                                            	</c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div> 
							</div>
						</div>
						<div class="row col-sm-12    ">
							<div class="col-sm-4 hidden-480 left">
								<label class="dataTables_info " id="dynamic-table_info" role="status" aria-live="polite"></label>
							</div>
							<div class="col-sm-8 right">
								<%@include file="../../layout/pagination.jsp"%>
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
        function resetMecForm(){
        	$(':input','#searchForm')  
          	 .not(':button, :submit, :reset, :hidden')  
          	 .removeAttr('checked')  
          	 .removeAttr('selected')
          	 .val(""); 
    	}
        
        
        function del(uuid){
        	var url="${ctx}/sys/sysUsr/delete?uuid="+uuid;
        	Modal.confirm({msg: '确认删除？'}).on(function(e){
        		if(e){
        			$.post(url,function(data){
                		data = eval("(" + data + ")")
                		Modal.alert({msg: data.message}).on( function(e){
                			if(data.message == "删除成功"){
                                $("#searchForm").submit();
                			}
                		});
                	});
        		}
        	});
        } 
        function show(type,uuid){
      	  $.fancybox.open({
  				href : '${ctx}/sys/sysUsr/'+type+'?uuid='+uuid,
  				type: 'iframe',
  	        padding: 5,
  	        scrolling: 'no',
  	        fitToView: true,
  	        width: 900,
  	        height: 800,
  	        autoSize: false,
  	        closeClick: false,
  				afterClose:function(){
  					location.reload();
  				}
  			});
      	  
        }
    	$('.chosen-select').chosen({});
		$(function() {
			$('#dateCreatedBegin').datepicker();
			$('#dateCreatedEnd').datepicker();
		});
		function subForm() {
			var startTime = $("#dateCreatedBegin").val();
			var endTime = $("#dateCreatedEnd").val();
			if (endTime < startTime) {
				Modal.alert({
					msg : '创建开始时间不能大于结束时间'
				});
				return false;
			}

			$('#searchForm').submit();
		}

    
</script>
</body>
</html>