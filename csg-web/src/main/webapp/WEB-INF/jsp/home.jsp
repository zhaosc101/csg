<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="layout/common.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="hidden" value="${tokenId }" id="tokenId">
<tr>
	<td><button onclick="showUsr()">用户列表</button></td>
	<td><button onclick="logout()">登出</button></td>
	<td><button onclick="usrPage()">用户页面</button></td>
	
</tr>
<hr>
<div id="info" style="color:green;"></div>
</body>
<%@include file="layout/common.js.jsp"%>
<script type="text/javascript">
/* function showUsr() {
	var tokenId=$("#tokenId").val()
	 $.ajax({
	    type: "POST",
	    url: "/usr/list",
	    data: "",
	    contentType: "application/json",
	    dataType: "json",
	    beforeSend: function(request) {                
            request.setRequestHeader("Csg-Token", tokenId);
 		},
	    success:function(data){

	    	$.each(data.usrList, function(i, item) {
	            $("#info").append(
	                    "<div>" + item.usrId + "</div>" + 
	                    "<div>" + item.uuid + "</div>" + 
	                    "<div>" + item.usrNm    + "</div>" +
	                    "<div>" + item.creNo + "</div><hr/>");
	        });
	    },error:function(data){
	                    
	    }
	});
} */
function showUsr(){
	window.location.href="${ctx}/sys/sysUsr/list";
}
function logout(){
	var tokenId=$("#tokenId").val()
	$.ajax({
	    type: "POST",
	    url: "/logout",
	    data: "",
	    contentType: "application/json",
	    dataType: "json",
	    beforeSend: function(request) {                
            request.setRequestHeader("Csg-Token", tokenId);
 		},
	    success:function(data){
	    	console.log(data);
	    	if(data.code=="0000") {
	          alert("成功");
	    	}
	    	window.location.href="${ctx}/index";
	    },error:function(data){
	                    
	    }
	});
}
function usrPage(){
	window.location.href="${ctx}/sys/sysUsr/list";
}
</script>
</html>