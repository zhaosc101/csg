<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- 调用方法：
// 四个选项都是可选参数
Modal.alert(
  {
    msg: '内容',
    title: '标题',
    btnok: '确定',
    btncl:'取消'
  });

// 如需增加回调函数，后面直接加 .on( function(e){} );
// 点击“确定” e: true
// 点击“取消” e: false
Modal.confirm(
  {
    msg: "是否删除角色？"
  })
  .on( function (e) {
    alert("返回结果：" + e);
  }); -->
<!-- system modal start -->
    <div id="ycf-alert" class="modal">
      <div class="modal-dialog modal-sm">
        <div class="modal-content">
          <div class="modal-header">
            <!-- <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button> -->
            <h5 class="modal-title"><i class="fa fa-exclamation-circle"></i> [Title]</h5>
          </div>
          <div class="modal-body small center" >
            <p>[Message]</p>
          </div>
          <div class="modal-footer center" >
            <button type="button" class="btn btn-primary ok" data-dismiss="modal">[BtnOk]</button>
            <button type="button" class="btn btn-default cancel" data-dismiss="modal">[BtnCancel]</button>
          </div>
        </div>
      </div>
    </div>
<!-- system modal end -->
<script type="text/javascript">
;$(function(){window.Modal=function(){var d=new RegExp("\\[([^\\[\\]]*?)\\]","igm");var f=$("#ycf-alert");var b=f.html();var c=function(g){f.html(b);f.find(".ok").removeClass("btn-success").addClass("btn-primary");f.find(".cancel").hide();e(g);return{on:function(h){if(h&&h instanceof Function){f.find(".ok").click(function(){h(true)})}}}};var a=function(g){f.html(b);f.find(".ok").removeClass("btn-primary").addClass("btn-success");f.find(".cancel").show();e(g);return{on:function(h){if(h&&h instanceof Function){f.find(".ok").click(function(){h(true)});f.find(".cancel").click(function(){h(false)})}}}};var e=function(g){var i={msg:"提示内容",title:"操作提示",btnok:"确定",btncl:"取消"};$.extend(i,g);var h=f.html().replace(d,function(k,j){return{Title:i.title,Message:i.msg,BtnOk:i.btnok,BtnCancel:i.btncl}[j]});f.html(h);f.modal({width:500,backdrop:"static"})};return{alert:c,confirm:a}}()});
</script>