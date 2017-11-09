<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String base = request.getContextPath()+"/";
%>
<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/font.css" />
<link href="<%=request.getContextPath() %>/css/datepicker.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/bootstrap-timepicker.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/daterangepicker.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrapValidator.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/main.css" class="ace-main-stylesheet" id="main-ace-style" />
<script src="<%=request.getContextPath() %>/js/main.extra.js"></script>
<script src="<%=request.getContextPath() %>/js/fileUpload.js"></script>
<script src="<%=request.getContextPath() %>/js/common/wTooltip.js"></script>
<%-- <script src="<%=request.getContextPath() %>/js/jquery.ajaxfileupload.js"></script> --%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/docsupport/prism.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/chosen.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/wTooltip.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/fancy/jquery.fancybox.css">
<style type="text/css">
    th{
    text-align: center;
    }
    ol li { margin:8px}
    
#con { font-size:14px; width:1150px; margin:0 auto;}
#tags { height:23px; width:400px; margin:0; padding:0; margin-left:10px}
#tags li { float:left; margin-right:1px; background:url(<%=request.getContextPath() %>/img/tagleft.gif) no-repeat left bottom; height:23px; list-style-type:none}
#tags li a { text-decoration:none; float:left; background:url(<%=request.getContextPath() %>/img/tagright.gif) no-repeat right bottom; height:23px; padding:0px 10px; line-height:23px; color:#999}
#tags li.emptyTag { width:4px; background:none}
#tags li.selectTag { background-position: left top; position:relative; height:25px; margin-bottom:-2px}
#tags li.selectTag a { background-position: right top; color:#000; height:25px; line-height:25px;}
#tagContent { padding:1px; background-color:#fff; border:1px solid #aecbd4;}
.tagContent { background:url(<%=request.getContextPath() %>/img/bg.gif) repeat-x;  padding:10px; color:#474747; width:1145px; display:none}
#tagContent div.selectTag{ display:block}

	.transparent>.widget-body .widget-main .table-bordered>tbody>tr:last-child>td,
	.widget-main.no-padding .table-bordered>tbody>tr:last-child>td {
		border-bottom-width: 1 !important;
	}
	
	.chosen-container-single .chosen-single {
		height:29px;
		line-height:28px;
		border-radius: 0px;
	}
</style>