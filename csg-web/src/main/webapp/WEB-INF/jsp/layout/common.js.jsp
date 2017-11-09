<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>



<!-- basic scripts -->

<!--[if !IE]> -->
<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/js/formValidation.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.js"></script>
<!-- <![endif]-->

<!--[if IE]>
<script src="<%=request.getContextPath() %>/js/jquery.js"></script>
<![endif]-->

<!--[if !IE]> -->
<script type="text/javascript">
  <%-- window.jQuery || document.write("<script src='<%=request.getContextPath() %>/js/jquery.js'>"+"<"+"/script>"); --%>
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
window.jQuery || document.write("<script src='<%=request.getContextPath() %>/js/jquery1x.min.js'>"+"<"+"/script>");
</script>
<![endif]-->


<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
<script src="<%=request.getContextPath() %>/js/excanvas.min.js"></script>
<![endif]-->
<%--<script src="<%=request.getContextPath() %>/js/jquery.ui.custom.js"></script>--%>

<%-- <script src="<%=request.getContextPath() %>/js/jquery.touch.js"></script> --%>
<script src="<%=request.getContextPath() %>/js/bootstrap-datepicker.min.js"></script>
<%--<script src="<%=request.getContextPath() %>/js/bootstrap-timepicker.min.js"></script>--%>
<script src="<%=request.getContextPath() %>/js/moment.min.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery.easychart.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery.sparkline.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery.flot.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery.flot.pie.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery.flot.resize.js"></script>
<%--<script src="<%=request.getContextPath() %>/js/daterangepicker.min.js"></script>--%>
<%--<script src="<%=request.getContextPath() %>/js/bootstrap-datetimepicker.min.js"></script>--%>
<%--<script src="<%=request.getContextPath() %>/js/bootstrap-colorpicker.min.js"></script>--%>

<%-- <script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrapValidator.js"> </script> --%>

<!-- ace scripts -->
<script src="<%=request.getContextPath() %>/js/main.element.js"></script>
<script src="<%=request.getContextPath() %>/js/main.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/zh_CN.js"> </script>
<script src="<%=request.getContextPath() %>/js/chosen.jquery.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/js/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/fancy/jquery.fancybox.pack.js"></script>
<%-- <script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script> --%>
<%-- <script type="text/javascript" src="<%=request.getContextPath() %>/js/formValidation.js"></script> --%>
<%@include file="modal.jsp"%>
