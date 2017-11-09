<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div id="pagination" class="dataTables_paginate paging_simple_numbers clearfix pull-right"  >

</div>

<script>
    var str = "";


    var total           = ${pageInfo.getTotal()};
    var pages           = ${pageInfo.getPages()};
    if(total > 0 && pages <= 0){
        pages = parseInt(Math.ceil(total/${pageInfo.getPageSize()}));
    }
//    def action                        = (attrs.action ? attrs.action : (params.action ? params.action : "list"))

    var max                             = ${pageInfo.getPageSize()};
    var offset                  = ${pageInfo.getPageNum() -1}*max;
    var maxsteps                = 5;

    if (!offset) offset = 0;// "${offset}"              ?${offset}: 0;
    if (!max)   max             = 100;//"${max}"                ?${offset}: 10

    var linkParams = [];
//    if (attrs.params) linkParams.putAll(attrs.params)
    linkParams.offset = offset - max;
    linkParams.max = max;
//    if (params.sort)  linkParams.sort         = params.sort
//    if (params.order) linkParams.order        = params.order

//    def linkTagAttrs = [action:action]
//    if (attrs.controller)             linkTagAttrs.controller = attrs.controller
//    if (attrs.id != null)             linkTagAttrs.id = attrs.id
//    if (attrs.fragment != null)       linkTagAttrs.fragment = attrs.fragment
//    linkTagAttrs.params = linkParams

    // determine paging variables
    var steps           = maxsteps > 0;
    var currentstep     = (offset / max) + 1;
    var firststep       = 1;
    var laststep        = pages;

    // display previous link when not on firststep
    var disabledPrev = (currentstep > firststep) ? "" : "disabled";
    //          linkTagAttrs.class = 'prevLink'
    //          linkParams.offset = offset - max
   
    str += '<ul  class="pagination no-padding" >';
    str += "<li class='prev ${disabledPrev}'><a href='#' onclick='page(1)'> 首页</a>";
//    str += link(linkTagAttrs.clone()) {
//        (attrs.prev ?: messageSource.getMessage('paginate.prev', null, messageSource.getMessage('default.paginate.prev', null, 'Previous', locale), locale))
//    }
    str += "</li>";

    // display steps when steps are enabled and laststep is not firststep
    if (steps && laststep > firststep) {
//        linkTagAttrs.class = 'step'

        // determine begin and endstep paging variables
        var beginstep   = currentstep - Math.round(maxsteps / 2) + (maxsteps % 2)
        var endstep             = currentstep + Math.round(maxsteps / 2) - 1
        if (beginstep < firststep) {
            beginstep = firststep
            endstep = maxsteps
        }
        if (endstep > laststep) {
            beginstep = laststep - maxsteps + 1
            if (beginstep < firststep) {
                beginstep = firststep
            }
            endstep = laststep
        }

        // display firststep link when beginstep is not firststep
  /*      if (beginstep > firststep) {
            linkParams.offset = 0;
            str += "<li> <a href='#'  onclick='page("+firststep+ ") ;return false ;'>";
            str +=  firststep;
            str += "</a></li>";
            str += '<li class="disabled"><a href="#">…</a></li>';
        }
*/
        // display paginate steps
        for (var x = beginstep; x <= endstep ;x++){
            if (currentstep == x) {
                str+= "<li class='active'><a href='#'  onclick='page("+x+ ") ;return false ;'>"+x+"</a></li>"
            }
            else {
//                linkParams.offset = (i - 1) * max
                str+= "<li> <a href='#'  onclick='page("+x+ ") ;return false ;'>";
                str+=x;
                str+="</a> </li>";
            }
        }

        // display laststep link when endstep is not laststep
        if (endstep < laststep) {
            linkParams.offset = (laststep -1) * max
            str+= '<li class="disabled"><a href="#">…</a></li>';
            str+= "<li> <a href='#' onclick='page("+laststep+ ") ;return false ;'>";
            str+= laststep;
            str+= "</a></li>";
        }
    }

    // display next link when not on laststep
    var disabledNext = (currentstep < laststep) ? "" : "disabled"
//    linkParams.offset = (currentstep)*max
    str+= "<li class='next ${disabledNext}'>";
//    writer << link(linkTagAttrs.clone()) {
//        (attrs.next ? attrs.next : messageSource.getMessage('paginate.next', null, messageSource.getMessage('default.paginate.next', null, 'Next', locale), locale))
//    }
    str+= "<a href='#' onclick='page("+laststep+ ") ;return false ;'>尾页</a>";
    str+= "</li>";
  /* 
   str+='<li><div class="col-sm-2">';
   str+='<select id="_pSize"  class=" form-control " style="width:50px ;height:31px" onchange="onChange4PageSize(this)">';
   str+='                                                             <option  >20</option>';
   str+='                                                           <option  >50</option>';
   str+='                                                           <option  >100</option>';
   str+='                                                          </select>';
   str+='</div></li>'
 
   str+='<li> <input type="number" class=" p " style="width:40px;height:31px"/></li>';
    str+='<li><a href="#"  onclick="page($(\'.p\').val())"> GO</a></li>';
*/ 
  
  
   str+='<div class="input-group pull-right col-sm-2">';
   str+='<input type="number" min="1" class="form-control p" style="height:30px;width:50px">'
   str+='<a href="$" class="input-group-addon"  onclick="page($(\'.p\').val());return false;">跳转</a>'
   str+='</div>'
	   str+= "</ul>";

    document.getElementById("pagination").innerHTML=str;


    function page(currentPage){
    	if(currentPage == undefined || currentPage == null || currentPage == ''){
    		alert('请输入跳转页码！');
    		$('.p').focus();
    		return;
    	}
        $("#searchForm input[name='pageNum']").val(currentPage);
        document.getElementById("searchForm").submit();
        return true ;
    }
    
    document.getElementById("dynamic-table_info").parentNode.parentNode.className="row no-margin";    
    document.getElementById("dynamic-table_info").parentNode.className="pull-left no-padding";
    document.getElementById("pagination").parentNode.className="col-sm-6 pull-right no-padding";
    
   str='<div class="inline">';
   str+='<select id="_pSize"  class=" form-control " style="padding:0 5px;width:50px ;height:28px" onchange="onChange4PageSize(this)">';
   str+='                                                             <option  >10</option>';
   str+='                                                             <option  >20</option>';
   str+='                                                           <option  >50</option>';
   str+='                                                           <option  >100</option>';
   str+='                                                          </select>';
   str+='</div>';
    if(pages > 0)
         document.getElementById("dynamic-table_info").parentNode.innerHTML = ("<div class='inline'>记录数："+ (offset+1) +"-"+(offset+max) +"，共"+total +"条，共"+pages +"页，每页数量：</div>" + str);
    else
        document.getElementById("dynamic-table_info").innerHTML = ("记录数：0");

   document.getElementById("_pSize").value=max;
   function onChange4PageSize(e){
        $("#searchForm input[name='pageSize']").val(e.value);
        document.getElementById("searchForm").submit();
        return true ;

  }
</script>